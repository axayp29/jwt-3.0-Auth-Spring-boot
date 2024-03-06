--liquibase formatted sql
--changeset pankaj mandal:0000
--comment: createTable = tableName:admin_table

create table `admin_table`(
admin_id BIGINT(6) NOT NULL AUTO_INCREMENT,
admin_name varchar(50) NULL,
PRIMARY KEY(admin_id));
insert into admin_table (admin_name) values ('Admin');