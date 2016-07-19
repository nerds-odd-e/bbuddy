[![Build Status](https://travis-ci.org/nerds-odd-e/bbuddy.svg?branch=master)](https://travis-ci.org/nerds-odd-e/bbuddy)

This is the repo for the exercise project to be developed in Shanghai team's Modern Web Development course

# Installation
Please install the following tools for this project. The latest version should be fine unless specific version is listed.
>* git
>* jdk 1.8
>* gradle
>* mysql
>* intellij idea community edition
>* Firefox

# Setup Development Environment
Use git to clone this project into a folder. Then in this folder, run the command below in order.
>1. Setup Database
`mysql -u root < db_migrations/initialize_users_and_roles.sql`
>2. Run Tests
`gradle check cucumber`
>3. Start Web Application
`gradle bootRunByPort -Pport=8090`
