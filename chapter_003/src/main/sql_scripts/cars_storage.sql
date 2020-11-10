-- drop table bodies, engines, transmissions, cars;


create table bodies(
    id serial primary key,
    name varchar(255)
);

create table engines(
    id serial primary key,
    name varchar(255)
);

create table transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
	body_id int references bodies(id),
	engine_id int references engines(id),
	trans_id int references transmissions(id)
);

insert into bodies(name) values('Хэтчбек');
insert into bodies(name) values('Универсал');
insert into bodies(name) values('Лифтбэк');
insert into bodies(name) values('Купе');

insert into engines(name) values('ОМ 602 Mercedes-Benz');
insert into engines(name) values('BMW M57');
insert into engines(name) values('Skoda 1.9 TDI AGR/AQM/ALH/AHF');
insert into engines(name) values('3S-FEToyota');

insert into transmissions(name) values('ACURA');
insert into transmissions(name) values('BRILLIANCE');
insert into transmissions(name) values('LANCIA');
insert into transmissions(name) values('MINI');

insert into cars(name, body_id, engine_id, trans_id) values('BMW', 1, 2, 3);
insert into cars(name, body_id, engine_id, trans_id) values('AUDI', 2, 4, 1);
insert into cars(name, body_id, engine_id, trans_id) values('TOYOTA', 2, 1, 4);


select * from bodies;
select * from engines;
select * from transmissions;
select * from cars;

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name as car, b.name as body, e.name as engine, t.name as transmission
from cars as c
inner join bodies as b on c.body_id = b.id
inner join engines as e on c.engine_id = e.id
inner join transmissions as t on c.trans_id = t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

--Неиспользуемые кузова
select b.name as not_used_bodies
from cars as c
right join bodies as b on c.body_id = b.id
where c.name is null;

--Неиспользуемые двигатели
select e.name as not_used_engines
from cars as c
right join engines as e on c.engine_id = e.id
where c.name is null;

--Неиспользуемые трансмиссии
select t.name as not_used_transmissions
from cars as c
right join transmissions as t on c.trans_id = t.id
where c.name is null;






















