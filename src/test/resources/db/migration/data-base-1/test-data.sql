CREATE TABLE users
(
    user_id    INTEGER PRIMARY KEY,
    login      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);

INSERT INTO users (user_id, login, first_name, last_name)
VALUES (2000, 'bob@domain.com', 'Bob', 'Bobin'),
       (2001, 'bohman@domain.com', 'Bohman', 'Bohmanin');
