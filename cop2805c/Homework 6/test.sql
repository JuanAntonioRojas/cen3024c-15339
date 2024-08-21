-- This is the code to create the db, insert values and select all values

create database test;
use test;
create table name ( Last varchar(50), First varchar(50));
insert into `name` values('Rojas', 'Juan');
insert into `name` values('Williamson', 'Brian');
select * from name;
drop database test2;