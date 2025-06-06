INSERT INTO "user" (id, username, password, first_name, last_name, role, phone_number, balance, account_status)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'admin', '$2a$12$30KAqjJ1.FY5Qc0zbFIWse3M6aqLCQw7zIa/oI1s9kOeDqwoMcvtS','Admin',null, 'ADMIN', '+381234567890', 100.0, TRUE), --admin123
    ('550e8400-e29b-41d4-a716-446655440001', 'manager', '$2a$12$FKLrwzxPlL.yk1WnGXup3OefDYN/eUw.ajKlWJUStDOKoaCoYVvXG', 'Manager', null, 'MANAGER', '+381224567890', 0, TRUE),
    ('550e8400-e29b-41d4-a716-446655440002', 'Andrii', '$2a$12$fIBTpVBEPMKYkVcHixbx8uCf98Favuzvr/HBhIefIYZMHUMRT1RTK','Andrii', null, 'USER', '+380987654321', 50.0, TRUE); --andrii123

INSERT INTO voucher (voucher_id, title, description, price, tour_type, transfer_type, hotel_type, status, arrival_date, eviction_date, user_id, is_hot)
VALUES
    ('a1b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c5d', 'Summer Beach Escape', '5-day all-inclusive beach vacation in Bali', 1200.00, 'LEISURE', 'PRIVATE_CAR', 'FIVE_STARS', 'REGISTERED', '2025-06-15', '2025-06-20', null, TRUE),
    ('b2c3d4e5-f6a7-4b8c-9d0e-1f2a3b4c5d6e', 'Mountain Adventure', '7-day hiking tour in the Swiss Alps', 1800.50, 'ADVENTURE', 'BUS', 'FOUR_STARS', 'PAID', '2025-07-01', '2025-07-08', null, FALSE),
    ('c3d4e5f6-a7b8-4c9d-0e1f-2a3b4c5d6e7f', 'Cultural City Break', '3-day tour of Rome with guided visits', 750.00, 'CULTURAL', 'TRAIN', 'THREE_STARS', 'PAID', '2025-05-10', '2025-05-13', null, TRUE),
    ('d4e5f6a7-b8c9-4d0e-1f2a-3b4c5d6e7f8a', 'Winter Ski Package', '6-day ski vacation in Aspen with equipment', 2200.00, 'SPORTS', 'PLANE', 'FIVE_STARS', 'PAID', '2025-12-15', '2025-12-21', null, FALSE),
    ('e5f6a7b8-c9d0-4e1f-2a3b-4c5d6e7f8a9b', 'Luxury Safari Experience', '8-day African safari with premium lodges', 3500.00, 'SAFARI', 'SHIP', 'FOUR_STARS', 'PAID', '2025-08-05', '2025-08-13', null, TRUE),
    ('f6a7b8c9-d0e1-4f2a-3b4c-5d6e7f8a9a0b', 'Budget Backpacking', '10-day Southeast Asia tour for young travelers', 800.00, 'ECO', 'MINIBUS', 'ONE_STAR', 'CANCELED', '2025-09-01', '2025-09-11', null, FALSE),
    ('a7b8c9d0-e1f2-4a3b-4c5d-6e7f8a9a0b1c', 'Romantic Getaway', '4-day couples package in Paris with dinner cruise', 1500.00, 'LEISURE', 'JEEPS', 'TWO_STARS', 'REGISTERED', '2024-06-01', '2025-06-05', null, TRUE);
