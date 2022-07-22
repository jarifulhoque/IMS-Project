INSERT INTO `customer` (`customer_firstname`, `customer_surname`) VALUES ('Jarif', 'Hoque');
INSERT INTO `customer` (`customer_firstname`, `customer_surname`) VALUES ('Gary', 'Smith');

INSERT INTO item (item_name, item_price) VALUES ('Charger', 10.00);
INSERT INTO item (item_name, item_price) VALUES ('Adapter', 20.00);

INSERT INTO orders (fk_customer_id) VALUES (1);
INSERT INTO orders (fk_customer_id) VALUES (2);

INSERT INTO order_items (fk_order_id, fk_item_id) VALUES (1, 1);
INSERT INTO order_items (fk_order_id, fk_item_id) VALUES (2, 2);