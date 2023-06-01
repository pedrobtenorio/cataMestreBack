--liquibase formatted sql

--changeset pedro_tenorio:12

ALTER TABLE studies DROP COLUMN online_classroom, DROP COLUMN online_link;

ALTER TABLE studies_occurrence
    ADD COLUMN     online_classroom      VARCHAR(80),
    ADD COLUMN    online_link         VARCHAR(80);
