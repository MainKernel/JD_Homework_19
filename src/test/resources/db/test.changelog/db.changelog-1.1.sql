--liquibase formatted sql

--changeset mightyloot:1
CREATE TABLE IF NOT EXISTS notes
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(128)  NOT NULL,
    content    VARCHAR(2048) NOT NULL,
    user_id    BIGINT REFERENCES users (id),
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
)