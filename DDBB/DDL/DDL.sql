DROP DATABASE IF EXISTS `bancoutn`;

CREATE SCHEMA `bancoutn` DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `bancoutn`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `bancoutn`.`province` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `bancoutn`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NULL,
  `postalCode` INT NOT NULL,
  `idProvince` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `postalCode_UNIQUE` (`postalCode` ASC) VISIBLE,
  INDEX `idProvince_idx` (`idProvince` ASC) VISIBLE,
  CONSTRAINT `idProvince`
    FOREIGN KEY (`idProvince`)
    REFERENCES `bancoutn`.`province` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

