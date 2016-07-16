CREATE DATABASE  IF NOT EXISTS `bbuddy`;
USE `bbuddy`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `password` varchar(50) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

