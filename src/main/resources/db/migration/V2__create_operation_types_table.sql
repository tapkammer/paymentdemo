CREATE TABLE operation_types (
    operation_type_id SMALLSERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

INSERT INTO operation_types (operation_type_id, description) VALUES (1, 'COMPRA A VISTA');
INSERT INTO operation_types (operation_type_id, description) VALUES (2, 'COMPRA PARCELADA');
INSERT INTO operation_types (operation_type_id, description) VALUES (3, 'SAQUE');
INSERT INTO operation_types (operation_type_id, description) VALUES (4, 'PAGAMENTO');