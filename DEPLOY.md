#!/bin/bash
# BBuddy Installation Guide
#### [TOC]

## Meta Data
	# -*- encode: UTF-8 -*-

### Exit if any errors occur
    set -ex -u -o pipefail

### Variables for this project
    DATABASE_USER=nerd
    DATABASE_PASS=dbs3cr3t
    DATABASE_NAME=bbuddydev

### Versions of dependent applications
    JDK_VERSION=8u131
    GRADLE_VERSION=2.13
    TOMCAT_VERSION=8.5.14

### Global Variables
    INSTALL_DIR="${INSTALL_DIR:-/opt}"
    MIRROR_SITE="${MIRROR_SITE:-https://d.chaifeng.com/mirror}"
    SOFTWARE_CACHE_DIR="${SOFTWARE_CACHE_DIR:-/vagrant}"

    typeset -A JDK_VERSION_MAP
    JDK_VERSION_MAP[8u131]='jdk1.8.0_131'

### Global functions
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
            return 2
        fi
    }


## Install dependent applications
### Install MySQL
    apt_install mysql-client mysql-server

### Create database user and grant permission
    mysql -uroot mysql <<< "select * from user where user='${DATABASE_USER}';" | grep -F mysql_native_password \
        || echo "CREATE USER '${DATABASE_USER}'@'%' IDENTIFIED BY '${DATABASE_PASS}';" | mysql -uroot

    echo "GRANT ALL PRIVILEGES ON * . * TO '${DATABASE_USER}'@'%';" | mysql -uroot
    echo 'CREATE DATABASE /*!32312 IF NOT EXISTS*/ '"${DATABASE_NAME}"' /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;' | mysql -uroot

## Install building tools
### Install tools into `$INSTALL_DIR`
    mkdir -p "$INSTALL_DIR"

### Install git, curl, unzip
    apt_install git curl unzip

### Download JDK8
    get_url "${MIRROR_SITE}/jdk-${JDK_VERSION}-linux-i586.tar.gz"

### Set `$JAVA_HOME`
    export JAVA_HOME="$INSTALL_DIR/${JDK_VERSION_MAP[$JDK_VERSION]}"

### Install JDK8

    cd "$(dirname "${JAVA_HOME}")"
    unarchive "${SOFTWARE_CACHE_DIR}/jdk-${JDK_VERSION}-linux-i586.tar.gz" "${JAVA_HOME}/bin/java"

### Set Java Profile
	cat > /etc/profile.d/java.sh <<-EOF
	export JAVA_HOME="${JAVA_HOME}"
	export PATH="\${JAVA_HOME}/bin:\$PATH"
	EOF
	source /etc/profile.d/java.sh

### Download Gradle
    get_url "${MIRROR_SITE}/gradle-$GRADLE_VERSION-bin.zip"

### Set Gradle Home
    export GRADLE_HOME="${INSTALL_DIR}/gradle-${GRADLE_VERSION}"

### Install Gradle
    cd "$(dirname "$GRADLE_HOME")"
    unarchive "${SOFTWARE_CACHE_DIR}/gradle-$GRADLE_VERSION-bin.zip" "$GRADLE_HOME/bin/gradle"

### Set Gradle Profile
    cat > /etc/profile.d/gradle.sh <<-EOF
    export GRADLE_HOME="$GRADLE_HOME"
    export PATH="\$GRADLE_HOME/bin:\$PATH"
		EOF
    source /etc/profile.d/gradle.sh

### Download Tomcat
    get_url "${MIRROR_SITE}/apache-tomcat-$TOMCAT_VERSION.tar.gz"

### Set Catalina Home
    export CATALINA_HOME="${INSTALL_DIR}/apache-tomcat-${TOMCAT_VERSION}"

### Install Tomcat
    cd "$(dirname "$CATALINA_HOME")"
    unarchive "${SOFTWARE_CACHE_DIR}/apache-tomcat-$TOMCAT_VERSION.tar.gz" "$CATALINA_HOME/bin/startup.sh"

## Build project
### Create a temporary directory for building
    TMP="$(mktemp -d)"

### Check out project code
    git clone https://github.com/nerds-odd-e/bbuddy.git "$TMP"

### Builde project using gradle
    cd "$TMP"
    gradle war

## Deploy project
### Shutdown Tomcat if it is running
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

### Remove Tomcat ROOT application
    rm -r "$CATALINA_HOME"/webapps/ROOT* || true

### Deploy BBuddy as Tomcat ROOT application
    mv build/libs/bbuddy-HEAD.war "$CATALINA_HOME"/webapps/ROOT.war

### Start up Tomcat
    "$CATALINA_HOME/bin/startup.sh"
    sleep 25

### Validate BBuddy has already started
    curl --silent -L http://127.0.0.1:8080/ | grep -F '<title>BBuddy</title>'

