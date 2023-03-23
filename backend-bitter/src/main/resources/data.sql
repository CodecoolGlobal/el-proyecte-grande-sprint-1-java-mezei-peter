Insert Into users VALUES (uuid_generate_v4(), current_timestamp, false, false, 'admin', 'test_picture', 'test bio', 'test_email', 'test_user');
Insert Into bit VALUES (uuid_generate_v4(), 'Test Content', current_timestamp, '026fa192-4de8-4b9e-902a-f9d029961302');
Insert INTO users_bits VALUES ('026fa192-4de8-4b9e-902a-f9d029961302', 'cdab10d5-0ffe-45c2-8cae-1876ab951290');