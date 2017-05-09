-- MySQL Script generated by MySQL Workbench
-- Tue May  9 12:51:03 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema board222
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema board
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `board` ;

-- -----------------------------------------------------
-- Schema board
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `board` DEFAULT CHARACTER SET utf8 ;
USE `board` ;

-- -----------------------------------------------------
-- Table `board`.`SPRING_SESSION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`SPRING_SESSION` ;

CREATE TABLE IF NOT EXISTS `board`.`SPRING_SESSION` (
  `SESSION_ID` CHAR(36) NOT NULL,
  `CREATION_TIME` BIGINT(20) NOT NULL,
  `LAST_ACCESS_TIME` BIGINT(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` INT(11) NOT NULL,
  `PRINCIPAL_NAME` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`SESSION_ID`),
  INDEX `SPRING_SESSION_IX1` (`LAST_ACCESS_TIME` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board`.`SPRING_SESSION_ATTRIBUTES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`SPRING_SESSION_ATTRIBUTES` ;

CREATE TABLE IF NOT EXISTS `board`.`SPRING_SESSION_ATTRIBUTES` (
  `SESSION_ID` CHAR(36) NOT NULL,
  `ATTRIBUTE_NAME` VARCHAR(200) NOT NULL,
  `ATTRIBUTE_BYTES` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`SESSION_ID`, `ATTRIBUTE_NAME`),
  INDEX `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_ID` ASC),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK`
    FOREIGN KEY (`SESSION_ID`)
    REFERENCES `board`.`SPRING_SESSION` (`SESSION_ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`comments` ;

CREATE TABLE IF NOT EXISTS `board`.`comments` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `post_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_post_id_on_comments` (`post_id` ASC),
  INDEX `idx_user_id_on_comments` (`user_id` ASC),
  INDEX `idx_created_at_on_comments` (`created_at` DESC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board`.`posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`posts` ;

CREATE TABLE IF NOT EXISTS `board`.`posts` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `type` VARCHAR(5) NOT NULL,
  `content` TEXT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `board_id` BIGINT NULL,
  `view_count` BIGINT NOT NULL DEFAULT 0,
  `comment_count` BIGINT NOT NULL DEFAULT 0,
  `post_like_count` BIGINT NOT NULL DEFAULT 0,
  `blocked` TINYINT(1) NOT NULL DEFAULT 0,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_user_id_on_posts` (`user_id` ASC),
  INDEX `idx_created_at_on_posts` (`created_at` DESC),
  INDEX `idx_updated_at_on_posts` (`updated_at` DESC),
  INDEX `idx_board_id_on_posts` (`board_id` ASC),
  INDEX `idx_blocked_on_posts` (`blocked` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`users` ;

CREATE TABLE IF NOT EXISTS `board`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(12) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NULL,
  `profile_img` VARCHAR(100) NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 0,
  `auth_uuid` VARCHAR(50) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 73
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `board`.`user_roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(10) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `expired_at` DATETIME NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_user_id_on_user_roles` (`user_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board`.`boards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`boards` ;

CREATE TABLE IF NOT EXISTS `board`.`boards` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(20) NOT NULL,
  `code` VARCHAR(6) NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_created_at_on_boards` (`created_at` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board`.`post_likes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`post_likes` ;

CREATE TABLE IF NOT EXISTS `board`.`post_likes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `post_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `unliked` TINYINT(1) NOT NULL DEFAULT 0,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_post_user_on_post_likes` (`post_id` ASC, `user_id` ASC),
  INDEX `idx_unliked_on_post_likes` (`unliked` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board`.`stocks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`stocks` ;

CREATE TABLE IF NOT EXISTS `board`.`stocks` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `code` CHAR(6) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_name_on_stocks` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board`.`companies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`companies` ;

CREATE TABLE IF NOT EXISTS `board`.`companies` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(20) NOT NULL,
  `company_name` VARCHAR(20) NULL,
  `group_mail` VARCHAR(20) NOT NULL,
  `company_mail` VARCHAR(20) NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_group_mail_on_companies` (`group_mail` ASC),
  INDEX `idx_company_name_on_companies` (`company_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `board`.`post_reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `board`.`post_reports` ;

CREATE TABLE IF NOT EXISTS `board`.`post_reports` (
  `id` INT NOT NULL,
  `post_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
