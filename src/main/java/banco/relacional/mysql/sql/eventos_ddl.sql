DROP DATABASE IF EXISTS eventspeakers;
CREATE DATABASE IF NOT EXISTS eventspeakers;
USE eventspeakers;

DROP TABLE IF EXISTS `event`;
DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS speaker;

CREATE TABLE IF NOT EXISTS `event` (
    event_id             INTEGER          AUTO_INCREMENT,
    event_name           VARCHAR(50)      NOT NULL,
    PRIMARY KEY          (event_id)
);

CREATE TABLE IF NOT EXISTS lecture (
    lecture_id          INTEGER          AUTO_INCREMENT,
    event_id            INTEGER          NOT NULL,
    lecture_title       VARCHAR(50)      NOT NULL,
    PRIMARY KEY         (lecture_id),
    FOREIGN KEY         (event_id)       REFERENCES `event` (event_id)
);

CREATE TABLE IF NOT EXISTS speaker (
    lecture_id           INTEGER,
    speaker_name         VARCHAR(255)     NOT NULL,
    speaker_city         VARCHAR(50)      NOT NULL,
    PRIMARY KEY          (lecture_id),
    FOREIGN KEY          (lecture_id)     REFERENCES lecture (lecture_id)
);

# DML
insert into `event` values 
    (default, 'SBSI'),
    (default, 'ERBD'), 
    (default, 'SBES'), 
    (default, 'ERRC');

insert into lecture values 
    (default, 1, 'IoT'),
    (default, 1, 'Machine Learning'),
    (default, 1, 'IA'),
    (default, 2, 'Cloud Computing'),
    (default, 3, 'Big Data'),
    (default, 3, 'NoSQL'),
    (default, 4, 'NewSQL');

insert into speaker values
    (1, 'Carlos', 'São Paulo'),
    (2, 'Pedro', 'Santa Maria'),
    (3, 'João', 'Rio de Janeiro'),
    (4, 'Paulo', 'Porto Alegre'),
    (5, 'Maria', 'São Leopoldo'),
    (6, 'Joana', 'Rio Grande'),
    (7, 'Carla', 'Caxias do Sul');

select * from `event` natural join lecture natural join speaker;