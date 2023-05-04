--liquibase formatted sql

--changeset pedro_tenorio:6

ALTER TABLE studies
    ADD COLUMN    modality    VARCHAR(255),
    ADD COLUMN    term    VARCHAR(255);
