CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

select * from company;

select * from person;

delete from person;
delete from company;


insert into company(id, name) values(1, 'Sberbank');
insert into company(id, name) values(2, 'Azoft');
insert into company(id, name) values(5, 'Epam');

insert into person(id, name, company_id) values(1, 'Anton', 1);
insert into person(id, name, company_id) values(2, 'Artem', 1);
insert into person(id, name, company_id) values(3, 'Sveta', 2);
insert into person(id, name, company_id) values(4, 'Vlad', 2);
insert into person(id, name, company_id) values(5, 'Pavel', 2);
insert into person(id, name, company_id) values(6, 'Kostya', 3);
insert into person(id, name, company_id) values(7, 'Stas', 3);
insert into person(id, name, company_id) values(8, 'Petr', 3);
insert into person(id, name, company_id) values(9, 'Andrei', 3);

--1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
select p.name, c.name
from (
    select name
    from person
    where company_id != 5
) as p
left join company as c 
on p.company_id = c.id;

--2) Select the name of the company with the maximum number of persons + number of persons in this company

select c.name, count(p.name)
from company as c
left join person as p
on p.company_id = c.id
group by c.name
order by count(p.name) desc
limit 1;





















