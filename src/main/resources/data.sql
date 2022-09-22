--password1234
INSERT INTO USERS (username, email, password)
VALUES ('test1', 'test1@example.com', '$2a$10$bYRzhqSNj6YS79jA2BD5nOnkjKIX/12z4LdLgsAWM8NUsmpBa.evm');
INSERT INTO USERS (username, email, password)
VALUES ('test2', 'test2@example.com', '$2a$10$bYRzhqSNj6YS79jA2BD5nOnkjKIX/12z4LdLgsAWM8NUsmpBa.evm');

INSERT INTO inventorys (products_id, quantity, report_date) VALUES ('1', '3.0', '2022-07-07');
INSERT INTO inventorys (products_id, quantity, report_date) VALUES ('2', '7.0', '2022-07-07');
INSERT INTO inventorys (products_id, quantity, report_date) VALUES ('1', '0.2', '2022-07-08');
INSERT INTO inventorys (products_id, quantity, report_date) VALUES ('2', '4.5', '2022-07-08');


INSERT INTO products (name) VALUES ('test1');
INSERT INTO products (name) VALUES ('test2');