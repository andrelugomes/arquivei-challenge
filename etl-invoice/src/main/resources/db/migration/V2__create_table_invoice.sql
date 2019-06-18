CREATE TABLE `invoice` (
	`access_key` varchar(100) NOT NULL,
	`value` DOUBLE NOT NULL,
	PRIMARY KEY (`access_key`)
)
ENGINE=InnoDB
DEFAULT CHARSET=UTF8;