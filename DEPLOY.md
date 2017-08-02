# BBuddy 安装手册
#### [TOC]

## 元信息
### 任何地方出错都要终止执行
    set -ex -u -o pipefail

### 项目的变量
    DATABASE_USER=nerd
    DATABASE_PASS=dbs3cr3t
    DATABASE_NAME=bbuddydev

### 项目依赖的软件版本
    JDK_VERSION=8u131
    GRADLE_VERSION=2.13
    TOMCAT_VERSION=8.5.14

### 全局变量
    INSTALL_DIR="${INSTALL_DIR:-/opt}"
    MIRROR_SITE="${MIRROR_SITE:-https://d.chaifeng.com/mirror}"
    SOFTWARE_CACHE_DIR="${SOFTWARE_CACHE_DIR:-/vagrant}"

    typeset -A JDK_VERSION_MAP
    JDK_VERSION_MAP[8u131]='jdk1.8.0_131'

### 系统的全局函数定义
    function apt_install() {
        [[ ! -e /var/cache/apt/force_update ]] \
            || [[ "$(( $(date +%s) - $(date -r /var/cache/apt/pkgcache.bin +%s) ))" -gt $(( 3600 * 24 * 3 )) ]] \
            && apt-get update \
            && touch /var/cache/apt/force_update

        while [[ -n "${1:-}" ]]; do
            PACKAGE="$1"
            if apt-cache policy "$PACKAGE" | grep -F 'Installed: (none)'; then
                DEBIAN_FRONTEND=noninteractive apt-get install -y "$PACKAGE"
            fi
            shift
        done
    }

    function get_url() {
        URL="$1"
        ARCHIVE_FILE="${SOFTWARE_CACHE_DIR}/$(basename "$URL")"

        test -n "$URL"

        [[ -e "${ARCHIVE_FILE}" ]] \
            || curl --silent -o "${ARCHIVE_FILE}" "${URL}"
    }

    function unarchive() {
        FILE="$1"
        CREATES="$2"

        [[ -n "$CREATES" ]] && [[ -e "$CREATES" ]] && return 0

        if [[ "$FILE" == *.tar.gz ]] || [[ "$FILE" == *.tgz ]]; then
            tar -zxf "$FILE"
        elif [[ "$FILE" == *.zip ]]; then
            type unzip || apt-get install -y unzip
            unzip "$FILE"
        else
            echo "Unknow file format, $FILE"
            exit 2
        fi
    }


## 安装项目的依赖应用
### 安装 mysql
    apt_install mysql-client mysql-server

### 给应用创建默认的用户名 nerd，以及默认的密码
    mysql -uroot mysql <<< "select * from user where user='${DATABASE_USER}';" | grep -F mysql_native_password \
        || echo "CREATE USER '${DATABASE_USER}'@'%' IDENTIFIED BY '${DATABASE_PASS}';" | mysql -uroot

    echo "GRANT ALL PRIVILEGES ON * . * TO '${DATABASE_USER}'@'%';" | mysql -uroot
    echo 'CREATE DATABASE /*!32312 IF NOT EXISTS*/ '"${DATABASE_NAME}"' /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;' | mysql -uroot

## 安装构建项目的依赖工具
### 在 $INSTALL_DIR 下安装依赖的应用
    mkdir -p "$INSTALL_DIR"

### 安装 git, curl, unzip 等工具
    apt_install git curl unzip

### 下载 JDK8
    get_url "${MIRROR_SITE}/jdk-${JDK_VERSION}-linux-x64.tar.gz"

### 设置 Java Home
    export JAVA_HOME="$INSTALL_DIR/${JDK_VERSION_MAP[$JDK_VERSION]}"

### 安装 JDK8
    cd "$(dirname "$JAVA_HOME")"
    unarchive "${SOFTWARE_CACHE_DIR}/jdk-${JDK_VERSION}-linux-x64.tar.gz" "$JAVA_HOME/bin/java"

### 设置 Java Profile
    cat > /etc/profile.d/java.sh <<-EOF
    export JAVA_HOME="$JAVA_HOME"
    export PATH="\$JAVA_HOME/bin:\$PATH"
		EOF
    source /etc/profile.d/java.sh

### 获取 Gradle
    get_url "${MIRROR_SITE}/gradle-$GRADLE_VERSION-bin.zip"

### 设置 Gradle Home
    export GRADLE_HOME="$INSTALL_DIR/gradle-$GRADLE_VERSION"

### 安装 Gradle
    cd "$(dirname "$GRADLE_HOME")"
    unarchive "${SOFTWARE_CACHE_DIR}/gradle-$GRADLE_VERSION-bin.zip" "$GRADLE_HOME/bin/gradle"

### 设置 Gradle Profile
    cat > /etc/profile.d/gradle.sh <<-EOF
    export GRADLE_HOME="$GRADLE_HOME"
    export PATH="\$GRADLE_HOME/bin:\$PATH"
		EOF
    source /etc/profile.d/gradle.sh

### 获取 Tomcat
    get_url "${MIRROR_SITE}/apache-tomcat-$TOMCAT_VERSION.tar.gz"

### 设置 Catalina Home
    export CATALINA_HOME="${INSTALL_DIR}/apache-tomcat-${TOMCAT_VERSION}"

### 安装 Tomcat
    cd "$(dirname "$CATALINA_HOME")"
    unarchive "${SOFTWARE_CACHE_DIR}/apache-tomcat-$TOMCAT_VERSION.tar.gz" "$CATALINA_HOME/bin/startup.sh"

## 构建项目
### 为项目创建一个临时目录
    TMP="$(mktemp -d)"

### 从仓库中获取项目代码
    git clone https://github.com/nerds-odd-e/bbuddy.git "$TMP"

### 用 gradle 编译项目
    cd "$TMP"
    gradle war

## 部署项目
### 停止 Tomcat，如果已经运行了的话
    if pgrep -lf "/bin/java .+${CATALINA_HOME}"; then
        "$CATALINA_HOME/bin/shutdown.sh"
    fi

    SHUTDOWN_TIMEOUT=30
    while pgrep -lf "/bin/java .+${CATALINA_HOME}" && [[ "$SHUTDOWN_TIMEOUT" -gt 0 ]]; do
        sleep 1
        : $(( SHUTDOWN_TIMEOUT-- ))
    done
    if [[ "$SHUTDOWN_TIMEOUT" -le 0 ]]; then
        pgrep -f "/bin/java .+${CATALINA_HOME}" | xargs kill -9
    fi

### 删除 Tomcat 原先的根应用
    rm -r "$CATALINA_HOME"/webapps/ROOT* || true

### 部署 BBuddy 为 Tomcat 的根应用
    mv build/libs/bbuddy-HEAD.war "$CATALINA_HOME"/webapps/ROOT.war

### 启动 Tomcat
    "$CATALINA_HOME/bin/startup.sh"
    sleep 25

### 验证应用已经成功的部署
    curl --silent -L http://127.0.0.1:8080/ | grep -F '<title>BBuddy</title>'
