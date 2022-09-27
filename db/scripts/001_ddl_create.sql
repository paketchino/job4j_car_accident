create table if not exists accident_types (
    id serial primary key,
    name varchar(2000) not null
);

create table if not exists rules (
    id serial primary key,
    name varchar(2000) not null
);

CREATE TABLE if not exists accidents (
    id serial primary key,
    name varchar(2000) not null ,
    rules_id int not null references rules(id),
    accidentType_id int not null references accident_types(id),
    address varchar(200) not null ,
    number int not null,
    desc varchar(2000) not null ,
    photo bytea,
    status BOOLEAN default false
);