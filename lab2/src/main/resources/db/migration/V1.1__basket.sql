create table if not exists basket_positions
(
    user_id    bigserial not null,
    product_id bigserial not null references products (id),
    quantity   int       not null,
    foreign key (user_id) references users (id),
    primary key (user_id, product_id)
);