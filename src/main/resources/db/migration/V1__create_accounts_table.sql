CREATE TABLE accounts (
    account_id BIGSERIAL PRIMARY KEY,
    document_number VARCHAR(50) NOT NULL
);

CREATE SEQUENCE account_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;