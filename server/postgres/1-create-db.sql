CREATE DATABASE bookstore;
GRANT ALL PRIVILEGES ON DATABASE bookstore TO postgres;

\c bookstore;

CREATE TABLE IF NOT EXISTS author (
    author_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS book (
    book_id SERIAL PRIMARY KEY,
    author_id INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    first_sentence VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE CASCADE
);
