CREATE TABLE category
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NULL,
    name            VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NULL,
    title           VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    price DOUBLE NULL,
    image_url       VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_category_list
(
    product_id       BIGINT NOT NULL,
    category_list_id BIGINT NOT NULL
);

ALTER TABLE product_category_list
    ADD CONSTRAINT fk_procatlis_on_category FOREIGN KEY (category_list_id) REFERENCES category (id);

ALTER TABLE product_category_list
    ADD CONSTRAINT fk_procatlis_on_product FOREIGN KEY (product_id) REFERENCES product (id);