--liquibase formatted sql

--changeset pedro_tenorio:3


ALTER TABLE studies RENAME COLUMN username TO name;
