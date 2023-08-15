-- Insert Categories
INSERT INTO categories (name) VALUES
                                  ('Electronics'),
                                  ('Clothing'),
                                  ('Books');

-- Insert Products
INSERT INTO products (name, price, category_id) VALUES
                                                    ('Smartphone', 500, 1),
                                                    ('Laptop', 1200, 1),
                                                    ('T-shirt', 20, 2),
                                                    ('Jeans', 50, 2),
                                                    ('Java Programming', 30, 3),
                                                    ('The Great Gatsby', 15, 3);
