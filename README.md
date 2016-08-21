[![Build Status](https://travis-ci.org/nerds-odd-e/bbuddy.svg?branch=master)](https://travis-ci.org/nerds-odd-e/bbuddy)

This is the repo for the exercise project to be developed in Shanghai team's Modern Web Development course

# Installation
Please install the following tools for this project. The latest version should be fine unless specific version is listed.
>* git
>* jdk 1.8
>* gradle
>* mysql
>* intellij idea community edition with the following plug-in installed
>>* lombok plug-in
>* Firefox

# Setup Development Environment
Use git to clone this project into a folder. Then in this folder, run the command below in order.
>1. Run Tests
`gradle clean check cucumber`
>2. Start Web Application
`gradle clean bootRunByPort -Pport=8090`

# Setup Development Environment using VM
Install the following tools first.
>* [VirtualBox](https://www.virtualbox.org/)
>* [Vagrant](https://www.vagrantup.com)
>* [Ansible](https://www.ansible.com/)

Run the below command:
`vagrant up`

# If you are using MacOS, and have [Homebrew](http://brew.sh/) installed.
Run the below commands:

    brew cask install virtualbox vagrant
    brew install ansible
    vagrant up
