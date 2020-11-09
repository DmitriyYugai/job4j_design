create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);


--2. Заполнить таблицы данными
insert into devices(name, price) values('notebook', 70000);
insert into devices(name, price) values('mouse', 2000);
insert into devices(name, price) values('book', 5000);
insert into devices(name, price) values('pen', 60);
insert into devices(name, price) values('phone', 20000);

insert into people(name) values('Anton');
insert into people(name) values('Alex');
insert into people(name) values('Vlad');

insert into devices_people(people_id, device_id) values(1, 2);
insert into devices_people(people_id, device_id) values(1, 4);
insert into devices_people(people_id, device_id) values(2, 3);
insert into devices_people(people_id, device_id) values(2, 1);
insert into devices_people(people_id, device_id) values(2, 5);
insert into devices_people(people_id, device_id) values(3, 1);
insert into devices_people(people_id, device_id) values(3, 2);
insert into devices_people(people_id, device_id) values(3, 3);
insert into devices_people(people_id, device_id) values(3, 4);
insert into devices_people(people_id, device_id) values(3, 5);

--3. Используя агрегатные функции вывести среднюю цену устройств
select avg(price)
from devices

--4. Используя группировку вывести для каждого человеку среднюю цену его устройств
select p.name, avg(d.price)
from devices_people as dp
inner join people as p on p.id = dp.people_id
inner join devices as d on d.id = dp.device_id
group by p.name;

--5. Дополнить запрос п.4. условием, что цена устройства должны быть больше 5000
select p.name, avg(d.price) as average
from devices_people as dp
inner join people as p on p.id = dp.people_id
inner join devices as d on d.id = dp.device_id
group by p.name
having(avg(d.price) > 5000);





















