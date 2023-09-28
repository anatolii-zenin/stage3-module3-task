CREATE SCHEMA IF NOT EXISTS test1;
SET SCHEMA test1;

CREATE TABLE author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    createDate DATE,
    lastUpdateDate DATE,
    PRIMARY KEY (id)
);

CREATE TABLE news (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(30),
    content VARCHAR(255),
    createDate DATE,
    lastUpdateDate DATE,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE
);