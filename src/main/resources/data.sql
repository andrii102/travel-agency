INSERT INTO "user" (id, username, password, role, phone_number, balance, account_status)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'admin', '$2a$12$PhDBZa8JUe4XPVI5BeKMvOg2YMzOwZPR4etWmq5j763UzK7n20X8a', 'ADMIN', '1234567890', 100.0, TRUE), --admin123
    ('550e8400-e29b-41d4-a716-446655440001', 'Andrii', '$2a$12$fIBTpVBEPMKYkVcHixbx8uCf98Favuzvr/HBhIefIYZMHUMRT1RTK', 'USER', '0987654321', 50.0, TRUE); --andrii123