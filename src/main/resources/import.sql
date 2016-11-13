
/*ACCOUNT*/
SET @account_with_orders_id = 2;
INSERT INTO account(account_id, username, password ) VALUES (@account_with_orders_id, "account_with_orders", "password1");
INSERT INTO account_roles (account_fk, roles) VALUES (@account_with_orders_id, "ROLE_ADMIN");

SET @account_without_orders_id = 3;
INSERT INTO account(account_id, username, password ) VALUES (@account_without_orders_id, "account_without_orders", "password1");
INSERT INTO account_roles (account_fk, roles) VALUES (@account_without_orders_id, "ROLE_ADMIN");

/*PRODUCT*/
SET @product_id_1 = 1;
SET @product_id_2 = 2;
SET @product_id_3 = 3;
SET @product_number_1 = 'ProductNumber1';
SET @product_number_2 = 'ProductNumber2';
SET @product_number_3 = 'ProductNumber3';
SET @product_number_4 = 'ProductNumber4';
SET @product_name_1 = 'Yogurt';
SET @product_name_2 = 'Ketchup';
SET @product_name_3 = 'Chocolate';
SET @product_name_4 = 'IceCream';

INSERT INTO product(product_id, number, name, price, discount_percentage, discount_threshold_value) VALUES (1, @product_number_1, @product_name_1, 10.0, 5, 200);
INSERT INTO product(product_id, number, name, price, discount_percentage, discount_threshold_value) VALUES (2, @product_number_2, @product_name_2, 100.0, 5, 200);
INSERT INTO product(product_id, number, name, price, discount_percentage, discount_threshold_value) VALUES (3, @product_number_3, @product_name_3, 1000.0, 5, 200);
INSERT INTO product(product_id, number, name, price, discount_percentage, discount_threshold_value) VALUES (4, @product_number_4, @product_name_4, 20.0, 2, 200);

/*ORDER #1 */
SET @order_1_id = 1;
INSERT INTO orders(order_id, account_fk) VALUES (@order_1_id, @account_with_orders_id);
INSERT INTO order_item(order_item_id, order_fk, product_number, product_name, count, total) VALUES (1, @order_1_id, @product_number_1, @product_name_1, 10, 100.0);
INSERT INTO order_item(order_item_id, order_fk, product_number, product_name, count, total) VALUES (2, @order_1_id, @product_number_2, @product_name_2, 10, 1000.0);
INSERT INTO order_item(order_item_id, order_fk, product_number, product_name, count, total) VALUES (3, @order_1_id, @product_number_3, @product_name_3, 10, 10000.0);

/*ORDER #2 */
SET @order_2_id = 2;
INSERT INTO orders(order_id, account_fk) VALUES (@order_2_id, @account_with_orders_id);
INSERT INTO order_item(order_item_id, order_fk, product_number, product_name, count, total) VALUES (4, @order_2_id, @product_number_1, @product_name_1, 1, 100.0);
INSERT INTO order_item(order_item_id, order_fk, product_number, product_name, count, total) VALUES (5, @order_2_id, @product_number_2, @product_name_2, 1, 1000.0);
INSERT INTO order_item(order_item_id, order_fk, product_number, product_name, count, total) VALUES (6, @order_2_id, @product_number_3, @product_name_3, 1, 10000.0);
