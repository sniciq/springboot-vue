create database springboot;
use springboot;
CREATE TABLE staff (id BIGINT NOT NULL COMMENT 'ID' AUTO_INCREMENT, name VARCHAR (100) DEFAULT NULL, age INT DEFAULT NULL, birthday DATETIME DEFAULT NULL, email VARCHAR (100) DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB CHARSET=utf8;
CREATE TABLE department (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR (100) DEFAULT NULL, phone VARCHAR (100) DEFAULT NULL, address VARCHAR (100) DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB CHARSET=utf8;
CREATE TABLE department_staffs_ref (ID BIGINT NOT NULL AUTO_INCREMENT, departmentId INT DEFAULT NULL, staffId INT DEFAULT NULL, PRIMARY KEY (ID)) ENGINE=InnoDB CHARSET=utf8;
