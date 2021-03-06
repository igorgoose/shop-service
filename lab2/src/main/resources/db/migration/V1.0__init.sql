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

insert into products (name, price)
values ('coat', '109.99');
insert into products (name, price)
VALUES ('warm sweater', '33.99');
insert into products (name, price)
VALUES ('light sweater', '25.99');
insert into products (name, price)
VALUES ('skirt', '25.99');
insert into products (name, price)
VALUES ('shorts', '23.99');
insert into products (name, price)
VALUES ('socks', '4.99');
insert into products (name, price)
VALUES ('high-waisted jeans', '39.99');
insert into products (name, price)
VALUES ('scarf', '11.99');
insert into products (name, price)
VALUES ('warm hat', '14.99');

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

insert into product_seasons(id, name)
values (2, 'summer');
insert into product_seasons(id, name)
values (3, 'fall');
insert into product_seasons(id, name)
values (4, 'winter');
insert into product_seasons(id, name)
values (5, 'spring');

create table if not exists products_to_product_seasons
(
    product_id        bigserial not null,
    product_season_id bigserial not null,
    primary key (product_id, product_season_id)
);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (1, 4);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (1, 3);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (2, 3);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (2, 4);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (3, 3);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (3, 5);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (4, 2);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (4, 5);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (5, 2);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (6, 2);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (6, 3);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (6, 4);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (6, 5);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (7, 3);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (7, 5);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (8, 3);
insert into products_to_product_seasons(product_id, product_season_id)
VALUES (8, 4);

insert into products_to_product_seasons(product_id, product_season_id)
VALUES (9, 4);
