drop schema imstest;

CREATE SCHEMA IF NOT EXISTS `imstest`;

USE `imstest` ;

DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `item`;
DROP TABLE IF EXISTS `order_items`;

CREATE TABLE IF NOT EXISTS `imstest`.`customer` (
	`customer_id` INT NOT NULL AUTO_INCREMENT,
    `customer_firstname` VARCHAR (40) NOT NULL,
    `customer_surname` VARCHAR (40) NOT NULL,
    PRIMARY KEY (`customer_id`)
    );
    
CREATE TABLE IF NOT EXISTS `imstest`.`item` (
	`item_id` INT AUTO_INCREMENT,
    `item_name` VARCHAR (40) NOT NULL,
    `item_price` DOUBLE NOT NULL,
    PRIMARY KEY (`item_id`)
    );
    
CREATE TABLE IF NOT EXISTS `imstest`.`orders` (
	`order_id` INT AUTO_INCREMENT,
    `fk_customer_id` INT NOT NULL,
    FOREIGN KEY (`fk_customer_id`) REFERENCES `customer`(`customer_id`) ON DELETE CASCADE,
    PRIMARY KEY (`order_id`)
    );
    
CREATE TABLE IF NOT EXISTS `imstest`.`order_items` (
	`orderdetails_id` INT AUTO_INCREMENT,
    `fk_order_id` INT NOT NULL,
    `fk_item_id` INT NOT NULL,
    FOREIGN KEY (`fk_order_id`) REFERENCES `orders`(`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_item_id`) REFERENCES `item`(`item_id`) ON DELETE CASCADE,
    PRIMARY KEY (`orderdetails_id`)
    );