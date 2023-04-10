--liquibase formatted sql

--changeset pedro_tenorio:3


ALTER TABLE users RENAME COLUMN name TO username;
