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
