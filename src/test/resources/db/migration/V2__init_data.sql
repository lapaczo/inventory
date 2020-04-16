-- products
INSERT INTO product (id, name, price) VALUES (1, 'Potato', 10.11);
INSERT INTO product (id, name, price) VALUES (2, 'Tomato', 20.54);
INSERT INTO product (id, name, price) VALUES (3, 'Milk', 50.89);
INSERT INTO product (id, name, price) VALUES (4, 'Butter', 1024.6);
INSERT INTO product (id, name, price) VALUES (5, 'Oranges', 499);
INSERT INTO product (id, name, price) VALUES (6, 'Cheese', 210.1);
INSERT INTO product (id, name, price) VALUES (7, 'Ham', 70.9);
INSERT INTO product (id, name, price) VALUES (8, 'Bread', 84.8);
INSERT INTO product (id, name, price) VALUES (9, 'Toilette paper', 9999);

-- orders
INSERT INTO order_entity (id, email) VALUES (1, 'alfred.werner@roche.ch');
INSERT INTO order_entity (id, email) VALUES (2, 'paul.karrer@roche.ch');
INSERT INTO order_entity (id, email) VALUES (3, 'jaroslav.heyrovsky@roche.ch');
INSERT INTO order_entity (id, email) VALUES (4, 'kurt.wuethrich@roche.ch');
INSERT INTO order_entity (id, email) VALUES (5, 'jacques.dubochet@roche.ch');

INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (1, 1);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (1, 3);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (1, 6);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (2, 3);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (2, 4);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (2, 8);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (3, 1);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (3, 2);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (3, 7);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (4, 1);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (4, 5);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (4, 7);
INSERT INTO order_entity_products (order_entity_id, products_id) VALUES (5, 2);
