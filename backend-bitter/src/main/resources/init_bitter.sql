CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS bit_responses;
DROP TABLE IF EXISTS bit_likes;
DROP TABLE IF EXISTS bits;
DROP TABLE IF EXISTS user_blocks;
DROP TABLE IF EXISTS user_follows;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS profile_pictures;

CREATE TABLE users
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

CREATE TABLE user_follows
(
    user_following_id UUID NOT NULL,
    user_followed_id  UUID NOT NULL
);

CREATE TABLE user_blocks
(
    user_blocking_id UUID NOT NULL,
    user_blocked_id  UUID NOT NULL
);

CREATE TABLE profile_pictures
(
    profile_picture_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    binary_data        BYTEA NOT NULL
);

CREATE TABLE bits
(
    bit_id           UUID        DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_id          UUID NOT NULL,
    date_of_creation TIMESTAMPTZ DEFAULT (current_timestamp),
    bit_content      VARCHAR(280)
);

CREATE TABLE bit_responses
(
    bit_id               UUID NOT NULL,
    bit_response_id      UUID        DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_id              UUID NOT NULL,
    date_of_creation     TIMESTAMPTZ DEFAULT (current_timestamp),
    bit_response_content VARCHAR(280)
);

CREATE TABLE bit_likes
(
    user_id UUID NOT NULL,
    bit_id  UUID NOT NULL
);

ALTER TABLE users
DROP
CONSTRAINT IF EXISTS profile_picture_to_user;
ALTER TABLE users
    ADD CONSTRAINT profile_picture_to_user FOREIGN KEY (profile_picture_id) REFERENCES profile_pictures (profile_picture_id);

ALTER TABLE user_follows
DROP
CONSTRAINT IF EXISTS user_to_following,
DROP
CONSTRAINT IF EXISTS user_to_followed;
ALTER TABLE user_follows
    ADD CONSTRAINT user_to_following FOREIGN KEY (user_following_id) REFERENCES users (user_id),
    ADD CONSTRAINT user_to_followed FOREIGN KEY (user_followed_id) REFERENCES users (user_id);

ALTER TABLE user_blocks
    DROP
        CONSTRAINT IF EXISTS user_to_blocking,
    DROP
        CONSTRAINT IF EXISTS user_to_blocked;
ALTER TABLE user_blocks
    ADD CONSTRAINT user_to_blocking FOREIGN KEY (user_blocking_id) REFERENCES users (user_id),
    ADD CONSTRAINT user_to_blocked FOREIGN KEY (user_blocked_id) REFERENCES users (user_id);

ALTER TABLE bits
    DROP
        CONSTRAINT IF EXISTS user_to_bit;
ALTER TABLE bits
    ADD CONSTRAINT user_to_bit FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE bit_responses
    DROP
        CONSTRAINT IF EXISTS bit_to_bit_response,
    DROP
        CONSTRAINT IF EXISTS user_to_bit_response;
ALTER TABLE bit_responses
    ADD CONSTRAINT bit_to_bit_response FOREIGN KEY (bit_id) REFERENCES bits (bit_id),
    ADD CONSTRAINT user_to_bit_response FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE bit_likes
    DROP
        CONSTRAINT IF EXISTS bit_to_bit_like,
    DROP
        CONSTRAINT IF EXISTS user_to_bit_like;
ALTER TABLE bit_likes
    ADD CONSTRAINT bit_to_bit_like FOREIGN KEY (bit_id) REFERENCES bits (bit_id),
    ADD CONSTRAINT user_to_bit_like FOREIGN KEY (user_id) REFERENCES users (user_id);



--INSERT INTO users
--VALUES (DEFAULT, 'asd', 'password', 'szia', 'test@test.com', uuid_generate_v4(), DEFAULT, DEFAULT, DEFAULT);

