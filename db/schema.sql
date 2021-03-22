CREATE TABLE post
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE candidate
(
    id   SERIAL PRIMARY KEY,
    name TEXT,
    photo_id INTEGER,
    city_id INTEGER
);

CREATE TABLE photo
(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE "user"
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);

CREATE TABLE city
(
    id SERIAL PRIMARY KEY,
    name TEXT
);