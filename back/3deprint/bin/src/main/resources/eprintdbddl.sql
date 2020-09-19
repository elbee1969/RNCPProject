-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eprint3d
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema eprint3d
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eprint3d` DEFAULT CHARACTER SET latin1 ;
USE `eprint3d` ;

-- -----------------------------------------------------
-- Table `eprint3d`.`album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eprint3d`.`album` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eprint3d`.`file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eprint3d`.`file` (
  `id` VARCHAR(255) NOT NULL,
  `data` LONGBLOB NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `album_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `album_id`),
  INDEX `fk_file_album1_idx` (`album_id` ASC) VISIBLE,
  CONSTRAINT `fk_file_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `eprint3d`.`album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eprint3d`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eprint3d`.`role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NULL DEFAULT NULL,
  `default_role` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eprint3d`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eprint3d`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` VARCHAR(1) NOT NULL,
  `account_non_locked` VARCHAR(1) NOT NULL,
  `credentials_non_expired` VARCHAR(1) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `enabled` VARCHAR(1) NOT NULL,
  `firstname` VARCHAR(256) NOT NULL,
  `lastname` VARCHAR(256) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `album_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `album_id`),
  UNIQUE INDEX `UKsb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE,
  UNIQUE INDEX `UKob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE,
  INDEX `fk_user_album1_idx` (`album_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `eprint3d`.`album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `eprint3d`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eprint3d`.`user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FKa68196081fvovjhkek5m97n3y` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o`
    FOREIGN KEY (`user_id`)
    REFERENCES `eprint3d`.`user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y`
    FOREIGN KEY (`role_id`)
    REFERENCES `eprint3d`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
