CREATE DATABASE IF NOT EXISTS inventory;
USE inventory;


CREATE TABLE Product (
	id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
	name VARCHAR(100),
		price DOUBLE,
	quantity INT
);


CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);
INSERT INTO roles (role_name) VALUES
	('EMPLOYEE'),  -- 1 
	('ADMIN'),  -- 2
	('MANAGER'); -- 3



CREATE TABLE users (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(100) NOT NULL, 
    roles_id INT NOT NULL,
	FOREIGN KEY (role_id) REFERENCES roles(id)
  );