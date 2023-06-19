CREATE TABLE IF NOT EXISTS `spring`.`topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cluster` VARCHAR(45) NOT NULL,
  `worker` VARCHAR(45) NOT NULL,
  `updated` DATE NOT NULL,
  PRIMARY KEY (`id`));
