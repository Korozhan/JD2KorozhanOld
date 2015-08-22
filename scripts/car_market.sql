CREATE DATABASE IF NOT EXISTS `car_market` CHARACTER SET UTF8;

CREATE TABLE `car_market`.`users` (
  `id_user` INT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

#insert into users (login, password) values ("username", "123");

CREATE TABLE `car_market`.`roles` (
  `id_role` INT NULL AUTO_INCREMENT,
  `role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_role`),
  INDEX `role_INDEX` (`role` ASC));

CREATE TABLE `car_market`.`bill` (
  `id_bill` INT NULL AUTO_INCREMENT,
  `price` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id_bill`));

CREATE TABLE `car_market`.`car_type` (
  `id_type` INT NULL AUTO_INCREMENT,
  `type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_type`),
  INDEX `type_INDEX` (`type` ASC));

CREATE TABLE `car_market`.`details` (
  `id_details` INT NULL AUTO_INCREMENT,
  `country` VARCHAR(20) NOT NULL,
  `mark` VARCHAR(20) NOT NULL,
  `model` VARCHAR(20) NOT NULL,
  `gearbox` VARCHAR(20) NOT NULL,
  `mileage` INT NOT NULL,
  `fuel` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_details`));
  
CREATE TABLE `car_market`.`car` (
  `id_car` INT NULL AUTO_INCREMENT,
  `id_type` INT NOT NULL,
  `id_details` INT NOT NULL,
  PRIMARY KEY (`id_car`),
  CONSTRAINT `car_car_type`
    FOREIGN KEY (`id_type`)
    REFERENCES `car_market`.`car_type` (`id_type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `car_details`
    FOREIGN KEY (`id_details`)
    REFERENCES `car_market`.`details` (`id_details`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `car_market`.`lot` (
  `id_user` INT NOT NULL,
  `id_role` INT NOT NULL,
  `id_car` INT NOT NULL,
  `id_bill` INT NOT NULL,
  CONSTRAINT `lot_users`
    FOREIGN KEY (`id_user`)
    REFERENCES `car_market`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `lot_roles`
    FOREIGN KEY (`id_role`)
    REFERENCES `car_market`.`roles` (`id_role`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `lot_car`
    FOREIGN KEY (`id_car`)
    REFERENCES `car_market`.`car` (`id_car`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `lot_bill`
    FOREIGN KEY (`id_bill`)
    REFERENCES `car_market`.`bill` (`id_bill`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
