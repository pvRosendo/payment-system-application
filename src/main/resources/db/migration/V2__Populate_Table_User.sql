INSERT INTO public.tb_users (id, user_balance, user_name, user_document, user_email, user_password, user_type) VALUES
    ('550e8400-e29b-41d4-a716-446655440000'::uuid, 100.50, 'John Doe', '123456789', 'john@example.com', 'password123', 1),
    ('44e8400e-e29b-41d4-a716-446655441111'::uuid, 200.75, 'Jane Doe', '987654321', 'jane@example.com', 'password456', 2),
    ('33e8400e-e29b-41d4-a716-446655442222'::uuid, 300.99, 'Alice Smith', '456789123', 'alice@example.com', 'password789', 1);