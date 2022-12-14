DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
username VARCHAR(60) NOT NULL,
password VARCHAR(200) NOT NULL,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS inventorys;

CREATE TABLE IF NOT EXISTS inventorys(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
products_id INT NOT NULL,
quantity FLOAT NOT NULL,
report_date DATE NOT NULL,
updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(100) NOT NULL
);