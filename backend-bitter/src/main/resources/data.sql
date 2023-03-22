Insert Into users VALUES (uuid_generate_v4(), current_timestamp, false, false, 'admin', 'test_picture', 'test bio', 'test_email', 'test_user');
Insert Into bit VALUES (uuid_generate_v4(), 'Test Content', current_timestamp, 'c6654c58-b1f6-4db5-88b1-744d9290f300');
Insert INTO users_bits VALUES ('c6654c58-b1f6-4db5-88b1-744d9290f300', 'b0da8370-1125-4c7d-9412-4ba89b21d067');