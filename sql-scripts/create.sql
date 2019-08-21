create database web_employee_tracker;

use web_employee_tracker;

create table employee(
id int(11) primary key auto_increment,
first_name varchar(45),
last_name varchar(45),
email varchar(45)
);


insert into employee values(1,"Rahul","Kotha","rahul.kotha@zemosolabs.com");

insert into employee values
(2,"Sreetej","Reddy","sreetej.reddy@zemosolabs.com");


