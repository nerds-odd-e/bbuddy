CREATE USER 'nerd'@'localhost' IDENTIFIED WITH mysql_native_password BY 'dbs3cr3t';
GRANT ALL PRIVILEGES ON *.* TO 'nerd'@'localhost';
FLUSH PRIVILEGES;
