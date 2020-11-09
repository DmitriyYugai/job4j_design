create table teachers(
    id serial primary key,
    name varchar(255),
    school_number int
);

create table pupils(
    id serial primary key,
    name varchar(255),
    teacher_id int references teachers(id)
);

insert into teachers(name, school_number) values ('Ivan', 123);
insert into teachers(name, school_number) values ('Petr', 456);
insert into teachers(name, school_number) values ('Andrei', 234);

insert into pupils(name, teacher_id) values ('Petya', 3);
insert into pupils(name, teacher_id) values ('Dima', 3);
insert into pupils(name, teacher_id) values ('Vasya', 2);
insert into pupils(name, teacher_id) values ('Anya', 2);
insert into pupils(name, teacher_id) values ('Sveta', 2);

select * from teachers;
select * from pupils;

select *
from teachers as t
inner join pupils as p
on t.id = p.teacher_id;


select t.name, p.name
from teachers as t
inner join pupils as p
on t.id = p.teacher_id;


select t.name as Учитель, p.name as Ученик
from teachers as t
inner join pupils as p
on t.id = p.teacher_id;





















