CREATE TABLE inventory
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    product_id        BIGINT      NOT NULL,
    quantity          INT         NOT NULL,
    reserved_quantity INT         NOT NULL DEFAULT 0,
    created_at        DATETIME(6) NOT NULL,
    updated_at        DATETIME(6) NOT NULL,

    CONSTRAINT pk_inventory PRIMARY KEY (id),
    CONSTRAINT uk_inventory_product_id UNIQUE (product_id)
);