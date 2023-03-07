DROP TABLE users;

CREATE TABLE IF NOT EXISTS users
(
    user_id              UUID                  DEFAULT uuid_generate_v4() PRIMARY KEY,
    username             VARCHAR(25)  NOT NULL UNIQUE,
    password             VARCHAR(30)  NOT NULL,
    user_bio             VARCHAR(150),
    email                VARCHAR(320) NOT NULL,
    profile_picture_id   UUID         NOT NULL,
    is_admin             BOOLEAN      NOT NULL DEFAULT (false),
    is_banned            BOOLEAN      NOT NULL DEFAULT (false),
    date_of_registration TIMESTAMPTZ           DEFAULT (current_timestamp)
);

CREATE TABLE IF NOT EXISTS profile_pictures
(
    profile_picture_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    binary_data        BYTEA NOT NULL
);

INSERT INTO users
VALUES (DEFAULT, 'asd', 'password', 'szia', 'test@test.com', uuid_generate_v4(), DEFAULT, DEFAULT, DEFAULT);

