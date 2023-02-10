CREATE TABLE transactions (
    transaction_id BIGSERIAL PRIMARY KEY,
    account_id BIGSERIAL NOT NULL,
    operation_type_id SMALLSERIAL NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    event_date TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT transaction_account_id_fk FOREIGN KEY (account_id)
        REFERENCES accounts (account_id) MATCH SIMPLE,
    CONSTRAINT transaction_operation_type_id_fk FOREIGN KEY (operation_type_id)
        REFERENCES operation_types (operation_type_id) MATCH SIMPLE
);

CREATE SEQUENCE transaction_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;