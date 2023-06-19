CREATE TABLE IF NOT EXISTS `spring`.`topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `source_cluster` VARCHAR(45) NOT NULL,
  `associated_worker` VARCHAR(45) NOT NULL,
  `updated_at` DATE NOT NULL,
  PRIMARY KEY (`id`));
