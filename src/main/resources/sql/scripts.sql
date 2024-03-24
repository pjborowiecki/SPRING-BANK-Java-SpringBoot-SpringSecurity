CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `enabled` INT NOT NULL,
    PRIMARY KEY(`id`));

CREATE TABLE `authorities` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `authority` VARCHAR(45) NOT NULL,
    PRIMARY KEY(`id`));

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);

CREATE TABLE `customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(45) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    PRIMARY KEY(`id`));