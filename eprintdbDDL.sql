
-- -----------------------------------------------------
--  Data Definition Language (DDL) for the creation
-- of the eprintdatabase database schema under MYSQL
-- -----------------------------------------------------

-- ----------------------------------------------------
-- Erase database if already exist
-- -----------------------------------------------------

DROP DATABASE IF EXISTS `eprintdatabase`;  

-- -----------------------------------------------------
-- Database inition
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `eprintdatabase`;

USE `eprintdatabase`;

-- -----------------------------------------------------
-- Table addresses creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(30) NULL DEFAULT NULL,
  `num` INT(11) NULL DEFAULT 0,
  `postal` VARCHAR(5) NULL DEFAULT NULL,
  `street` VARCHAR(40) NULL DEFAULT NULL,
  `town` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
-- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;
 

-- -----------------------------------------------------
-- Table custom_users creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `custom_users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` VARCHAR(1) NOT NULL,
  `account_non_locked` VARCHAR(1) NOT NULL,
  `credentials_non_expired` VARCHAR(1) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `enabled` VARCHAR(1) NOT NULL,
  `firstname` VARCHAR(40) NOT NULL,
  `lastname` VARCHAR(40) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(40) NOT NULL,
  `address_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_username` (`username`),
  UNIQUE INDEX `UK_email` (`email`),
  INDEX `FK_custom_users_addresses_id` (`address_id`),
  CONSTRAINT `FK_custom_users_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `addresses` (`id`))
ENGINE = InnoDB
-- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table bills creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bills` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `status` ENUM('I', 'C', 'V', 'A', 'O') NOT NULL,
  `total_item` INT(11) NOT NULL,
  `total_priceht` DECIMAL(10,2) UNSIGNED NOT NULL,
  `total_pricettc` DECIMAL(10,2) UNSIGNED NOT NULL,
  `total_weight` DECIMAL(10,2) UNSIGNED NOT NULL,
  `custom_user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `bills_custom_user_id_IDX` (`custom_user_id`),
  CONSTRAINT `FK_Bills_custom_user_id`
    FOREIGN KEY (`custom_user_id`)
    REFERENCES `custom_users` (`id`))
ENGINE = InnoDB
-- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table images creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `images` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `date` DATE NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  `owner_name` VARCHAR(40) NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `status` ENUM('I', 'C', 'V', 'A', 'O') NOT NULL,
  `type` VARCHAR(40) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `custom_user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_image_custom_user_id` (`custom_user_id`),
  CONSTRAINT `FK_image_custom_user_id`
    FOREIGN KEY (`custom_user_id`)
    REFERENCES `custom_users` (`id`))
ENGINE = InnoDB
-- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table orders creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(7,2) UNSIGNED NULL DEFAULT NULL,
  `quantity` INT(11) NOT NULL,
  `status` ENUM('I', 'C', 'V', 'A', 'O') NOT NULL,
  `time_to_print` VARCHAR(40) NOT NULL,
  `total_price` DECIMAL(7,2) UNSIGNED NULL DEFAULT NULL,
  `total_weight` DECIMAL(7,2) UNSIGNED NULL DEFAULT NULL,
  `weight` DECIMAL(7,3) UNSIGNED NULL DEFAULT NULL,
  `custom_user_id` BIGINT(20) NOT NULL,
  `image_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_order_customuser_id` (`custom_user_id`),
  INDEX `FK_image_id_order_id` (`image_id`),
  CONSTRAINT `FK_order_custom_user_id`
    FOREIGN KEY (`custom_user_id`)
    REFERENCES `custom_users` (`id`),
  CONSTRAINT `FK_image_id_order_id`
    FOREIGN KEY (`image_id`)
    REFERENCES `images` (`id`))
	ENGINE = InnoDB
-- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table roles creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NULL DEFAULT NULL,
  `default_role` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
	ENGINE = InnoDB
-- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Join table user_role creation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FK_role_user` (`role_id`),
  CONSTRAINT `FK_role_user`
    FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`id`),
  CONSTRAINT `FK_user_role`
    FOREIGN KEY (`user_id`)
    REFERENCES `custom_users` (`id`))
	ENGINE = InnoDB
-- -- Update in 2019-10-29
	DEFAULT CHARACTER SET = utf8mb4
--	COLLATE = utf8mb4_0900_ai_ci;
-- Answer before 2019-10-29
-- DEFAULT CHARACTER SET = utf8mb4 
 COLLATE utf8mb4_unicode_ci;


-- Validation of the inserted datas */
COMMIT;

-- end of the script setted back autocommit to true */
SET autocommit=1; 
