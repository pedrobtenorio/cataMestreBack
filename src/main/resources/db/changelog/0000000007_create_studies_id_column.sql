--liquibase formatted sql

--changeset pedro_tenorio:7


ALTER TABLE studies_occurrence
    RENAME COLUMN studie_id TO studies_id;


ALTER TABLE studies_occurrence
    DROP CONSTRAINT studies_occurrence_studie_id_fkey,
    ADD CONSTRAINT fk_studies_occurrence_studies_id
        FOREIGN KEY (studies_id) REFERENCES studies(id);
