-- Insert Categories
INSERT INTO categories (name) VALUES
                                  ('Electronics'),
                                  ('Clothing'),
                                  ('Books');

-- Insert Products
INSERT INTO products (name, price, category_id, available_quantity)
VALUES ('Smartphone', 500, 1, 100),
       ('Laptop', 1200, 1, 50),
       ('T-shirt', 20, 2, 200),
       ('Jeans', 50, 2, 150),
       ('Java Programming', 30, 3, 500),
       ('The Great Gatsby', 15, 3, 300);
/*INSERT INTO order_table (order_description, user_id)
VALUES ('Order for John', 37); -- Assuming John's ID is 1


-- Insert shopping cart items for the above order
-- You can adjust product IDs and quantities as needed
INSERT INTO shopping_cart (product_id, product_name, quantity, amount, order_id)
VALUES
    (1, 'Smartphone', 1, 500, 1),
    (2, 'Laptop', 2, 1200, 1);

-- Insert another order for Jane
-- User ID 4 corresponds to the fourth user you just inserted
INSERT INTO order_table (order_description, user_id)
VALUES
    ('Order for Jane', 38);

-- Insert shopping cart items for the above order
-- You can adjust product IDs and quantities as needed
INSERT INTO shopping_cart (product_id, product_name, quantity, amount, order_id)
VALUES
    (3, 'T-shirt', 3, 60, 3),
    (4, 'Jeans', 1, 50, 3);
*/