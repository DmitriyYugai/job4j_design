CREATE DATABASE items_system;

CREATE TABLE roles (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE rules (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE roles_rules (
	role_id int references roles(id),
	rule_id int references rules(id)
);

CREATE TABLE states (
	id serial primary key,
	name varchar(255)
);

CREATE TABLE categories (
	id serial primary key,
	name varchar(255)
);


CREATE TABLE users (
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);

CREATE TABLE items (
	id serial primary key,
	name varchar(255),
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);

CREATE TABLE comments (
	id serial primary key,
	text varchar(255),
	item_id int references items(id)
);

CREATE TABLE attachs (
	id serial primary key,
	name varchar(255),
	item_id int references items(id)
);

INSERT INTO roles(name) VALUES ('role');
INSERT INTO rules(name) VALUES ('rule');
INSERT INTO roles_rules(role_id, rule_id) VALUES (1, 1);
INSERT INTO states(name) VALUES ('state');
INSERT INTO categories(name) VALUES ('category');
INSERT INTO users(name, role_id) VALUES ('user', 1);
INSERT INTO items(name, user_id, category_id, state_id) VALUES ('item', 1, 1, 1);
INSERT INTO comments(text, item_id) VALUES ('comment', 1);
INSERT INTO attachs(name, item_id) VALUES ('file_name', 1);






















