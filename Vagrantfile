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

  config.vm.provider "virtualbox" do |vb|
    vb.customize ["modifyvm", :id, "--memory", "2048"]
  end

  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "bbuddy.yml"
  end
end
