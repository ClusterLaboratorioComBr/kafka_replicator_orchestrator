CREATE TABLE IF NOT EXISTS topic (
 id serial PRIMARY KEY,
 name VARCHAR(45) NOT NULL,
 cluster VARCHAR(45) NOT NULL,
 worker VARCHAR(45) NOT NULL,
 updated timestamp NOT NULL);