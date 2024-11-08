CREATE TABLE receipts
(
    id                 SERIAL PRIMARY KEY,
    category           VARCHAR(255),
    product_id         INT,
    id_1               INT,
    quantity           NUMERIC(10, 2),
    product_id_1       INT,
    fs_receipt_id      INT,
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    discount_id        INT
);
