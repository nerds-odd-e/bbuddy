# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.define "vagrant"

  config.vm.box = "ubuntu/trusty32"

  config.vm.hostname = "bbuddy.local"

  synced_opts = {}
  if ENV["ENABLE_NFS"] == "true"
    config.vm.network "private_network", type: "dhcp"
    synced_opts = {nfs: true}
  end

  {
    "." => "/home/vagrant/src"
  }.map { |local_folder, vagrant_folder|
    config.vm.synced_folder *([local_folder, vagrant_folder] << synced_opts)
  }

  config.vm.network "forwarded_port", guest: 8080, host: 9000
  config.vm.network "forwarded_port", guest: 8090, host: 8090

  config.vm.provider "virtualbox" do |vb|
    vb.customize ["modifyvm", :id, "--memory", "2048"]
  end

  config.vm.provision "shell", inline: %Q[
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
  ]

  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "bbuddy.yml"
  end
end
