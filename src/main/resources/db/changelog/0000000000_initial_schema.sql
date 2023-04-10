--liquibase formatted sql

--changeset pedro_tenorio:1
CREATE TABLE users
(
    id                BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name              VARCHAR(255),
    email             VARCHAR(255) NOT NULL UNIQUE,
    password          VARCHAR(255),
    role              VARCHAR(255),
    created_date      TIMESTAMP,
    last_updated_date TIMESTAMP
);

CREATE TABLE studies
(
    id                BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    code              VARCHAR(255),
    username          VARCHAR(255),
    classroom         VARCHAR(255),
    type              VARCHAR(255),
    time              VARCHAR(255),
    online_link       VARCHAR(255),
    online_classroom  VARCHAR(255),
    notes             VARCHAR(255),
    color             VARCHAR(255),
    active            BOOLEAN,
    professor_id      BIGINT,
    CONSTRAINT FK_professor_id FOREIGN KEY (professor_id) REFERENCES users(id)
);

CREATE TABLE user_studies
(
    user_id     BIGINT,
    studies_id  BIGINT,
    PRIMARY KEY (user_id, studies_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT FK_studies_id FOREIGN KEY (studies_id) REFERENCES studies(id)
);