INSERT INTO `car_market`.`users` (`login`, `password`) 
VALUES ('user1', 'user1password');
INSERT INTO `car_market`.`users` (`login`, `password`) 
VALUES ('user2', 'user2password');
INSERT INTO `car_market`.`users` (`login`, `password`) 
VALUES ('user3', 'user3password');

INSERT INTO `car_market`.`roles` (`role`) 
VALUES ('user');
INSERT INTO `car_market`.`roles` (`role`) 
VALUES ('admin');
INSERT INTO `car_market`.`roles` (`role`) 
VALUES ('user');

INSERT INTO `car_market`.`bill` (`price`, `date`) 
VALUES ('100', '2015-08-01');
INSERT INTO `car_market`.`bill` (`price`, `date`) 
VALUES ('200', '2015-08-02');
INSERT INTO `car_market`.`bill` (`price`, `date`) 
VALUES ('300', '2015-08-03');

INSERT INTO `car_market`.`car-type` (`type`) 
VALUES ('car');
INSERT INTO `car_market`.`car-type` (`type`) 
VALUES ('truck');
INSERT INTO `car_market`.`car-type` (`type`) 
VALUES ('bus');

INSERT INTO `car_market`.`details` (`country`, `mark`, `model`, `gearbox`, `mileage`, `fuel`) 
VALUES ('Germany', 'BMV', 'Isetta', 'auto', '2000', 'petrol');
INSERT INTO `car_market`.`details` (`country`, `mark`, `model`, `gearbox`, `mileage`, `fuel`) 
VALUES ('USA', 'Ford', 'B-max', 'auto', '3000', 'diesel');
INSERT INTO `car_market`.`details` (`country`, `mark`, `model`, `gearbox`, `mileage`, `fuel`) 
VALUES ('USA', 'Audi', 'M2', 'auto', '4000', 'diesel');

