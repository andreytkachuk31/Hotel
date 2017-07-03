CREATE DATABASE IF NOT EXISTS hotel;

USE hotel;

CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(20) NOT NULL,
  password VARCHAR(45) NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  fail_login_attempt INT DEFAULT 0,
  role_id INT NOT NULL,
  status_id INT NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE INDEX login_UNIQUE (login ASC)
);


CREATE TABLE IF NOT EXISTS user_roles (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name ASC)
 );

CREATE TABLE IF NOT EXISTS user_status (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(10) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name ASC)
);

 CREATE TABLE IF NOT EXISTS rooms (
  id INT NOT NULL,
  number INT NOT NULL,
  place_amount INT NOT NULL,
  category_id INT NOT NULL,
  price INT NOT NULL,
  busy_status INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX number_UNIQUE (number ASC)
);


CREATE TABLE IF NOT EXISTS room_categories (
  id INT(11) NOT NULL AUTO_INCREMENT,
  room_category INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX room_category_UNIQUE (room_category ASC)
);

CREATE TABLE IF NOT EXISTS orders (
  id INT NOT NULL AUTO_INCREMENT,
  room_id INT NOT NULL,
  user_id INT NULL,
  date_check_in DATE NOT NULL,
  date_check_out DATE NOT NULL,
  bill INT NOT NULL,
  PRIMARY KEY (id),
  INDEX id_idx (user_id ASC),
  CONSTRAINT id
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE CASCADE
  	ON UPDATE CASCADE
);

CREATE TABLE booking_requests (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NULL,
  room_id INT NULL,
  rooms_amount INT NULL,
  category_id INT NULL,
  date_check_in DATE NULL,
  date_check_out DATE NULL,
  status INT NULL,
  PRIMARY KEY (id)
);











