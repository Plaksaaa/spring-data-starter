CREATE TABLE IF NOT EXISTS employee (
    id serial primary key ,
    firstname varchar(64) not null ,
    lastname varchar(64) not null ,
    department varchar(32) not null ,
    salary integer not null
);