insert into products (name, price)
values ('coat', '109.99'),
	   ('warm sweater', '33.99'),
	   ('light sweater', '25.99'),
	   ('skirt', '25.99'),
	   ('shorts', '23.99'),
	   ('socks', '4.99'),
	   ('high-waisted jeans', '39.99'),
	   ('scarf', '11.99'),
	   ('warm hat', '14.99');

insert into product_seasons(id, name)
values (2, 'summer'),
	   (3, 'fall'),
	   (4, 'winter'),
	   (5, 'spring');


insert into products_to_product_seasons(product_id, product_season_id)
values (1, 4),
	   (1, 3),
	   (2, 3),
	   (2, 4),
	   (3, 3),
	   (3, 5),
	   (4, 2),
	   (4, 5),
	   (5, 2),
	   (6, 2),
	   (6, 3),
	   (6, 4),
	   (6, 5),
	   (7, 3),
	   (7, 5),
	   (8, 3),
	   (8, 4),
	   (9, 4);
