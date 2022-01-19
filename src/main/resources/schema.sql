drop table if exists accident_rule;
drop table if exists accident;
drop table if exists type;
drop table if exists rule;

create table if not exists type(
    id serial primary key,
    name varchar(2000)
);

create table if not exists rule(
    id serial primary key,
    name varchar(2000)
);

create table if not exists accident (
    id serial primary key,
    name varchar(2000),
    type_id int references type(id),
    text varchar(2000),
    address varchar(2000),
    status varchar(2000) default 'Зарегистрирована'
);

create table if not exists accident_rule(
    id serial primary key,
    id_accident int references accident(id),
    id_rule int references rule(id)
);

insert into type(name) values ('Две машины');
insert into type(name) values ('Машина и человек');
insert into type(name) values ('Машина и велосипед');
insert into type(name) values ('Одна машина');

insert into rule(name) values ('Статья №1');
insert into rule(name) values ('Статья №2');
insert into rule(name) values ('Статья №3');
insert into rule(name) values ('Статья №4');

insert into accident(name, type_id, text, address) values
('Проехал на красный', 4,
 'водитель машины под номером таким то.....',
 'Красный проспект 45');
insert into accident(name, type_id, text, address) values
('Пересёк двойную сплошную', 4,
 'водитель машины под номером таким то.....',
 'Красный проспект 15');
insert into accident(name, type_id, text, address) values
('Авария двух авто', 1,
 'водитель машины под номером таким то.....',
 'Ленина 25');

insert into accident_rule(id_accident, id_rule) VALUES (1, 1);
insert into accident_rule(id_accident, id_rule) VALUES (1, 2);
insert into accident_rule(id_accident, id_rule) VALUES (2, 2);
insert into accident_rule(id_accident, id_rule) VALUES (3, 1);
insert into accident_rule(id_accident, id_rule) VALUES (3, 3);

-- select
-- accident.id,
--        accident.name,
--        accident.text,
--        accident.address,
--        accident.status,
--        a.id,
--        a.name
-- from accident
-- left join type a on accident.type_id = a.id;