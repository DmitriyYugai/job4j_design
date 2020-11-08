CREATE TABLE products (
	id serial primary key,
	name VARCHAR(20),
	price INTEGER,
	date DATE
);

INSERT INTO products(name, price, date) VALUES('Bread', 50, '08.11.2020');

SELECT * FROM products;

UPDATE products SET price=100;

DELETE FROM products;