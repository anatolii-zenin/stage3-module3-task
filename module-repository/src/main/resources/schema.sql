CREATE SCHEMA IF NOT EXISTS test;
SET SCHEMA test;

CREATE TABLE IF NOT EXISTS author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    createDate DATE,
    lastUpdateDate DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS news (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    content VARCHAR(355),
    createDate DATE,
    lastUpdateDate DATE,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE
);