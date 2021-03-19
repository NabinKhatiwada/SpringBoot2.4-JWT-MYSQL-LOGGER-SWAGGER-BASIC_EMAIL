CREATE DATABASE pos;
use pos;

CREATE TABLE tbl_role(
	id int primary key,
	name varchar(50)
);

INSERT INTO tbl_role(id,name) values (1,'ROLE_SUPER_ADMIN');
INSERT INTO tbl_role(id,name) values (2,'ROLE_RESTAURANT_ADMIN');
INSERT INTO tbl_role(id,name) values (3,'ROLE_RESTAURANT_STAFF');
INSERT INTO tbl_role(id,name) values (4,'ROLE_USER');

CREATE TABLE tbl_super_admin(
	id varchar(50) primary key,
    active bool,
	created_datetime datetime,
	updated_datetime datetime,
	name varchar(100), 
	user_name varchar(100) unique not null, 
	password varchar(50) not null,
	first_time_login bool,
	role_id int,
    FOREIGN KEY (role_id) REFERENCES tbl_role(id)
);

