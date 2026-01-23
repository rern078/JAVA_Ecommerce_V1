INSERT INTO products (id, product_name, product_description, unit_price, units_in_stock, created_at) VALUES
  (1, 'Wireless Mouse', 'Ergonomic wireless mouse', 19.99, 120, TIMESTAMP '2026-01-22 09:00:00'),
  (2, 'Mechanical Keyboard', 'RGB mechanical keyboard', 79.50, 60, TIMESTAMP '2026-01-22 09:05:00'),
  (3, 'USB-C Hub', '6-in-1 USB-C hub', 34.99, 85, TIMESTAMP '2026-01-22 09:10:00'),
  (4, 'Noise Cancelling Headphones', 'Over-ear ANC headphones', 129.00, 40, TIMESTAMP '2026-01-22 09:15:00'),
  (5, 'Webcam 1080p', 'Full HD webcam', 49.00, 70, TIMESTAMP '2026-01-22 09:20:00');

INSERT INTO customers (id, first_name, email, address, created_at) VALUES
  (1, 'Lina Park', 'lina.park@example.com', '12 Lotus St, Bangkok', TIMESTAMP '2026-01-22 09:30:00'),
  (2, 'Arun Nair', 'arun.nair@example.com', '8 Orchid Rd, Chiang Mai', TIMESTAMP '2026-01-22 09:31:00'),
  (3, 'Maya Chen', 'maya.chen@example.com', '55 River Ave, Hanoi', TIMESTAMP '2026-01-22 09:32:00'),
  (4, 'Somchai K.', 'somchai.k@example.com', '21 Bamboo Ln, Phuket', TIMESTAMP '2026-01-22 09:33:00'),
  (5, 'Nadia Ali', 'nadia.ali@example.com', '99 Palm Dr, Jakarta', TIMESTAMP '2026-01-22 09:34:00');

INSERT INTO orders (id, customer_id, status, total, created_at) VALUES
  (1, 1, 'NEW', 19.99, TIMESTAMP '2026-01-22 10:00:00'),
  (2, 2, 'PAID', 79.50, TIMESTAMP '2026-01-22 10:05:00'),
  (3, 3, 'SHIPPED', 34.99, TIMESTAMP '2026-01-22 10:10:00'),
  (4, 4, 'NEW', 129.00, TIMESTAMP '2026-01-22 10:15:00'),
  (5, 5, 'CANCELLED', 49.00, TIMESTAMP '2026-01-22 10:20:00');

INSERT INTO order_items (id, order_id, product_id, quantity, price, total) VALUES
  (1, 1, 1, 1, 19.99, 19.99),
  (2, 2, 2, 1, 79.50, 79.50),
  (3, 3, 3, 1, 34.99, 34.99),
  (4, 4, 4, 1, 129.00, 129.00),
  (5, 5, 5, 1, 49.00, 49.00);
