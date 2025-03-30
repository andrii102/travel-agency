CREATE TABLE "user"
(
    id             UUID    NOT NULL,
    username       VARCHAR(255),
    password       VARCHAR(255),
    role           VARCHAR(255),
    phone_number   VARCHAR(255),
    balance        DOUBLE PRECISION,
    account_status BOOLEAN NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE voucher
(
    voucher_id    VARCHAR(36)      NOT NULL,
    title         VARCHAR(255),
    description   VARCHAR(255),
    price         DOUBLE PRECISION NOT NULL,
    tour_type     VARCHAR(255),
    transfer_type VARCHAR(255),
    hotel_type    VARCHAR(255),
    status        VARCHAR(255),
    arrival_date  date,
    eviction_date date,
    id            UUID,
    is_hot        BOOLEAN,
    CONSTRAINT pk_voucher PRIMARY KEY (voucher_id)
);

ALTER TABLE voucher
    ADD CONSTRAINT FK_VOUCHER_ON_ID FOREIGN KEY (id) REFERENCES "user" (id);