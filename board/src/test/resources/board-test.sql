-- MySQL Script generated by MySQL Workbench
-- Tue Apr  4 15:39:43 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema board-test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `board-test` ;

-- -----------------------------------------------------
-- Schema board-test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `board-test` DEFAULT CHARACTER SET utf8 ;
USE `board-test` ;

-- -----------------------------------------------------
-- Table `board-test`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board-test`.`users` ;

CREATE TABLE IF NOT EXISTS `board-test`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(12) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NULL,
  `fb_id` VARCHAR(45) NULL,
  `profile_img` VARCHAR(100) NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board-test`.`posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board-test`.`posts` ;

CREATE TABLE IF NOT EXISTS `board-test`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `type` VARCHAR(5) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `view_count` INT NOT NULL DEFAULT 0,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_posts_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_posts_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `board-test`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board-test`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board-test`.`comments` ;

CREATE TABLE IF NOT EXISTS `board-test`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(1000) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_posts1_idx` (`post_id` ASC),
  INDEX `fk_comments_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comments_posts1`
    FOREIGN KEY (`post_id`)
    REFERENCES `board-test`.`posts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `board-test`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;