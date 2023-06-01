--liquibase formatted sql

--changeset pedro_tenorio:5

ALTER TABLE studies DROP CONSTRAINT FK_professor_id, DROP COLUMN professor_id, DROP COLUMN classroom;

ALTER TABLE studies_occurrence
    ADD COLUMN     professor_id      BIGINT,
    ADD COLUMN    classroom         VARCHAR(255),
    ADD CONSTRAINT FK_professor_id FOREIGN KEY (professor_id) REFERENCES users(id);
