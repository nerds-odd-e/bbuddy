# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.define "vagrant"

  config.vm.box = "ubuntu/trusty32"

  config.vm.hostname = "bbuddy.local"

  config.vm.network "forwarded_port", guest: 8080, host: 9000
  config.vm.network "forwarded_port", guest: 8090, host: 8090

  config.vm.provider "virtualbox" do |vb, override|
    synced_opts = {}
    if ENV["ENABLE_NFS"] == "true"
      override.vm.network "private_network", type: "dhcp"
      synced_opts = {nfs: true}
    end

    {
      "." => "/home/vagrant/src"
    }.map { |local_folder, vagrant_folder|
      override.vm.synced_folder *([local_folder, vagrant_folder] << synced_opts)
    }

    vb.customize ["modifyvm", :id, "--memory", "2048"]

    override.vm.provision "shell", inline: <<-SHELL
      set -e
      if [[ '#{ENV["http_proxy"]}' == 'http://127.0.0.1:3128' ]]; then
        echo "export http_proxy='http://10.0.2.2:3128'"     > /etc/profile.d/proxy.sh
        echo "export https_proxy='http://10.0.2.2:3128'"   >> /etc/profile.d/proxy.sh
        echo "export all_proxy='socks5h://10.0.2.2:1080'"  >> /etc/profile.d/proxy.sh
        echo "export no_proxy='127.0.0.1,localhost,10.0.2.2,10.0.2.15,ruby.taobao.org'" >> /etc/profile.d/proxy.sh
        echo "export JAVA_OPTS='-Dhttp.proxyHost=10.0.2.2 -Dhttp.proxyPort=3128 -Dhttps.proxyHost=10.0.2.2 -Dhttps.proxyPort=3128 -DsocksProxyHost= -DsocksProxyPort='" >> /etc/profile.d/proxy.sh
        echo 'export GRADLE_OPTS="\$JAVA_OPTS"' >> /etc/profile.d/proxy.sh
        echo 'export MAVEN_OPTS="\$JAVA_OPTS"' >> /etc/profile.d/proxy.sh
      fi
      [[ -f /etc/profile.d/proxy.sh ]] && source /etc/profile.d/proxy.sh
      apt-get update -qq && apt-get install -y python
    SHELL

  end

  config.vm.provider :aws do |aws, override|
    override.vm.box = "dummy"

    aws.endpoint = ENV['AWS_ENDPOINT'] if ENV['AWS_ENDPOINT']
    aws.instance_type = ENV['AWS_INSTANCE_TYPE'] || 't2.micro'
    aws.access_key_id = ENV['AWS_ACCESS_ID']
    aws.secret_access_key = ENV['AWS_SECRET_KEY']
    aws.keypair_name = ENV['AWS_KEYPAIR']
    aws.region= ENV['AWS_REGION']

    override.ssh.username = "ubuntu"
    override.ssh.private_key_path = "~/.ssh/id_rsa"

    aws.ami = ENV['AWS_DEFAULT_AMI']
  end

  config.vm.provision "shell", inline: <<-SHELL
      apt-get update -qq && apt-get install -y python
  SHELL

  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "bbuddy.yml"
    ansible.raw_arguments  = [
      "-e", "gradle_project_path=/home/vagrant/bbuddy-dev",
      "-e", "gradle_project_task='cucumber'"
    ]
  end
end
