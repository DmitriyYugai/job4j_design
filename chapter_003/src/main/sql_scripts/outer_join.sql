drop table employees, departments;
--1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
	dep_id int references departments(id)
);

insert into departments(name) values('testing');
insert into departments(name) values('development');
insert into departments(name) values('management');
insert into departments(name) values('HR');
insert into departments(name) values('kitchen');

insert into employees(name, dep_id) values('Anton', 1);
insert into employees(name, dep_id) values('Dima', 2);
insert into employees(name, dep_id) values('Sveta', 2);
insert into employees(name, dep_id) values('Anna', 3);
insert into employees(name, dep_id) values('Kostya', 3);
insert into employees(name, dep_id) values('Valera', 2);
insert into employees(name, dep_id) values('Olga', 1);
insert into employees(name, dep_id) values('Rose', null);
insert into employees(name, dep_id) values('Boris', null);

-- select * from employees;
-- select * from departments;

--2. Выполнить запросы с left, rigth, full, cross соединениями
select *
from employees as e
left join departments as d
on d.id = e.dep_id;

select *
from employees as e
right join departments as d
on d.id = e.dep_id;

select *
from employees as e
full join departments as d
on d.id = e.dep_id;

select *
from employees
cross join departments

--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select e.name
from employees as e
left join departments as d
on d.id = e.dep_id
where dep_id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат. 
select e.name, d.name
from employees as e
left join departments as d
on d.id = e.dep_id;

select e.name, d.name
from departments as d
right join employees as e
on d.id = e.dep_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values('Anton', 'М');
insert into teens(name, gender) values('Kostya', 'М');
insert into teens(name, gender) values('Vlad', 'М');
insert into teens(name, gender) values('Eugenya', 'Ж');
insert into teens(name, gender) values('Sveta', 'Ж');
insert into teens(name, gender) values('Sasha', 'Ж');

select m.name as man, f.name as woman
from (
	select * 
	from teens 
	where gender = 'М'
) as m
cross join (
	select * 
	from teens 
	where gender = 'Ж' 
) as f;













