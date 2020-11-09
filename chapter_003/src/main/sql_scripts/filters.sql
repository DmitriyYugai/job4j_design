drop table product, type;

create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type(name) values('МОЛОКО');
insert into type(name) values('СЫР');
insert into type(name) values('НАПИТОК');

insert into product(name, type_id, expired_date, price) values('Топлёное молоко', 1, current_date + interval '1 week', 70);
insert into product(name, type_id, expired_date, price) values('Сгущеное молоко', 1, current_date + interval '6 week', 80);
insert into product(name, type_id, expired_date, price) values('Обычное молоко', 1, current_date + interval '3 week', 60);
insert into product(name, type_id, expired_date, price) values('Альметте', 2, current_date + interval '7 week', 200);
insert into product(name, type_id, expired_date, price) values('Аперифрэ', 2, current_date + interval '2 week', 100);
insert into product(name, type_id, expired_date, price) values('Моцарелла', 2, current_date + interval '10 week', 300);
insert into product(name, type_id, expired_date, price) values('Кола', 3, current_date + interval '4 week', 250);
insert into product(name, type_id, expired_date, price) values('Кола', 3, current_date + interval '10 week', 550);
insert into product(name, type_id, expired_date, price) values('Кола', 3, current_date + interval '9 week', 150);


--1. Написать запрос получение всех продуктов с типом "СЫР"
select * 
from product as p
inner join type as t
on t.id = p.type_id
where t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * 
from product
where name like '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * 
from product
where expired_date > current_date + interval '1 month';

--4. Написать запрос, который выводит самый дорогой продукт.
select name, price
from product
where price in (
	select max(price)
	from product
);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(*)
from type as t
inner join product as p
on t.id = p.type_id
group by t.name
having(t.name = 'СЫР');

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select t.name, p.name
from type as t
inner join product as p
on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select t.name, count(*)
from type as t
inner join product as p
on t.id = p.type_id
group by t.name
having (count(*) < 10);

--8. Вывести все продукты и их тип.
select *
from product as p
inner join type as t
on t.id = p.type_id

















