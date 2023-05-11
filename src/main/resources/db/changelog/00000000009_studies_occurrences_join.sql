--liquibase formatted sql

--changeset pedro_tenorio:9


CREATE TABLE studies_occurrences (
    id SERIAL PRIMARY KEY,
    occurrences_id INTEGER REFERENCES studies_occurrence(id),
     studies_id INTEGER REFERENCES studies(id)
);
