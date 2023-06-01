--liquibase formatted sql

--changeset pedro_tenorio:8


ALTER TABLE studies_occurrence
    RENAME COLUMN weekday to week_day