--liquibase formatted sql

--changeset pedro_tenorio:4

ALTER TABLE studies DROP COLUMN notes, DROP COLUMN active,
                    DROP COLUMN time, DROP COLUMN type;


CREATE TABLE studies_occurrence (
        id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
         weekday VARCHAR(20),
         time VARCHAR(20),
         notes TEXT,
        active BOOLEAN,
        studie_id BIGINT,
        type VARCHAR(255),
        FOREIGN KEY (studie_id) REFERENCES studies (id)
);
