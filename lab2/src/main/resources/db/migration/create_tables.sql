create table if not exists users
(
    id         bigserial primary key,
    username   varchar(256) not null unique,
    password   varchar(256) not null,
    first_name varchar(256) not null,
    last_name  varchar(256) not null,
    role       varchar(32)  not null
);

create table if not exists products
(
    id       bigserial primary key,
    name     varchar(256)   not null,
    price    decimal(20, 2) not null,
    quantity int            not null default 10
);

create table if not exists order_requests
(
    id               bigserial primary key,
    author_id        int            not null default 1,
    customer_name    varchar(256),
    customer_address varchar(256),
    creation_date    timestamp      not null,
    order_status     varchar(256)   not null default 'Created',
    total_price      decimal(20, 2) not null,
    bill             bytea,
    foreign key (author_id) references users (id)
);

create table if not exists order_positions
(
    id                bigserial primary key,
    product_id        int            not null,
    order_request_id  int            not null,
    quantity          int            not null,
    price_per_product decimal(20, 2) not null,
    foreign key (product_id) references products (id),
    foreign key (order_request_id) references order_requests (id)
);

create table if not exists product_seasons
(
    id   bigserial primary key,
    name varchar(64) not null
);

create table if not exists products_to_product_seasons
(
    product_id        bigserial not null,
    product_season_id bigserial not null,
    primary key (product_id, product_season_id)
);
