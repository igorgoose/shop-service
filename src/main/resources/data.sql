drop table if exists users cascade;
drop table if exists products cascade;
drop table if exists order_positions;
drop table if exists order_requests;

create table users(
    id int auto_increment primary key,
    username varchar(256) not null unique,
    password varchar(256) not null,
    first_name varchar(256) not null,
    last_name varchar(256) not null,
    role varchar(32) not null
);

create table products(
  id int auto_increment primary key,
  name varchar(256) not null,
  price decimal(20, 2) not null,
  quantity int not null default 10
);

insert into products (name, price) values ('soap', '2.99');
insert into products (name, price) VALUES ('shampoo', '14.99');
insert into products (name, price) VALUES ('lotion', '14.99');
insert into products (name, price) VALUES ('shaving foam', '14.99');
insert into products (name, price) VALUES ('toothpaste', '14.99');
insert into products (name, price) VALUES ('shower gel', '14.99');
insert into products (name, price) VALUES ('liquid soap', '14.99');
insert into products (name, price) VALUES ('toothbrush', '14.99');
insert into products (name, price) VALUES ('blade', '14.99');

create table order_requests(
    id int auto_increment primary key,
    author_id int not null default 1,
    customer_name varchar(256),
    customer_address varchar(256),
    creation_date timestamp not null,
    order_status varchar(256) not null default 'Created',
    total_price decimal(20, 2) not null,
    bill blob,
    foreign key (author_id) references users(id)
);

create table order_positions(
    id int auto_increment primary key,
    product_id int not null,
    order_request_id int not null,
    quantity int not null,
    price_per_product decimal(20, 2) not null,
    foreign key (product_id) references products(id),
    foreign key (order_request_id) references order_requests(id)
);
