--liquibase formatted sql

--changeset mightyloot:1
CREATE TABLE IF NOT EXISTS users
(
    id                         BIGSERIAL PRIMARY KEY,
    user_icon                  VARCHAR(256)          DEFAULT 'resources/META-INF/resources/user_icon.png',
    first_name                 VARCHAR(64)  NOT NULL,
    last_name                  VARCHAR(64)  NOT NULL,
    username                   VARCHAR(64)  NOT NULL,
    email                      VARCHAR(64)  NOT NULL,
    phone_number               VARCHAR(64)  NOT NULL,
    password                   VARCHAR(512) NOT NULL,
    role                       VARCHAR(16)  NOT NULL DEFAULT 'USER',
    is_non_expired             BOOLEAN      NOT NULL DEFAULT TRUE,
    is_account_non_locked      BOOLEAN      NOT NULL DEFAULT TRUE,
    is_credentials_non_expired BOOLEAN      NOT NULL DEFAULT TRUE,
    is_enabled                 BOOLEAN      NOT NULL DEFAULT TRUE

    );