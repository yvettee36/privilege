create database prictice;
use prictice;

create table privilege
(
	id varchar(40) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);

create table resource
(
	id varchar(40) primary key,
	uri varchar(255) not null unique,
	description varchar(255),
	privilege_id varchar(40),
	constraint privilege_id_FK foreign key(privilege_id) references privilege(id)
);

create table role
(
	id varchar(40) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);

create table user
(
	id varchar(40) primary key,
	username varchar(40) not null unique,
	password varchar(40) not null,
	description varchar(255)
);


create table role_privilege
(
	role_id varchar(40),
	privilege_id varchar(40),
	primary key(role_id,privilege_id),
	constraint role_id_FK foreign key(role_id) references role(id),
	constraint privilege_id_FK1 foreign key(privilege_id) references privilege(id)
);



create table user_role
(
	user_id varchar(40),
	role_id varchar(40),
	primary key(user_id,role_id),
	constraint user_id_FK foreign key(user_id) references user(id),
	constraint role_id_FK1 foreign key(role_id) references role(id)
);



