CREATE SCHEMA `paf` ;

CREATE TABLE `paf`.`patients` (
	`pid` INT NOT NULL AUTO_INCREMENT,
	`pcode` INT NOT NULL,
	`pname` VARCHAR(20) NOT NULL,
    `page` INT NOT NULL,
	`pgender` VARCHAR(10) NOT NULL,
	`paddress` VARCHAR(50),
	`pmobile` INT,
	PRIMARY KEY (`pid`)
);

