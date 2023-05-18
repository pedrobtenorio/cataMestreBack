--liquibase formatted sql

--changeset pedro_tenorio:11


UPDATE users SET name = username;

