--liquibase formatted sql

--changeset pedro_tenorio:10

ALTER TABLE users
    ADD COLUMN    name    VARCHAR(255);