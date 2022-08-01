DROP SCHEMA serurity_project;
CREATE SCHEMA serurity_project;
USE serurity_project;

DROP SCHEMA serurity_project_test;
CREATE SCHEMA serurity_project_test;
USE serurity_project_test;

CREATE TABLE user(
	id BIGINT AUTO_INCREMENT NOT NULL,
    password VARCHAR(255),
    username VARCHAR(255),
    PRIMARY KEY(id)
);

SELECT * FROM user;
SELECT * FROM member;
SELECT * FROM player;
SELECT * FROM role;
SELECT * FROM game; 
DELETE FROM user WHERE id > 2;
DELETE FROM player WHERE id > 2;

DELETE FROM role WHERE id = 6;

INSERT INTO user (password, username) VALUES 
-- 123456
('$2a$10$.w21SnYs3LwpEa6Fr5mbKu.ipm8zBqWaJbksBN69tbPLAsBcA9aau', 'mafalda'),
-- 111111
('$2a$10$od0BwTQhHPx6KU.glRGY1emwOkgUPueQprPfgkq2Ifv/doMmqyfC6', 'susanita');

DROP TABLE IF EXISTS user; 


CREATE TABLE role(
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    user_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);

SELECT * FROM role; 
DROP TABLE IF EXISTS role;
DELETE FROM ROLe where user_id > 3;
INSERT INTO role (name, user_id) VALUES 
('USER',2);

CREATE TABLE player(
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    player_name VARCHAR(255),
    PRIMARY KEY(id)
	);
    
CREATE TABLE member(
	id BIGINT AUTO_INCREMENT NOT NULL,
    level VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY(id)
	);

SELECT * FROM player;
DELETE FROM player WHERE id > 1; 
DROP TABLE IF EXISTS player;
INSERT INTO player (id, name, player_name) VALUES
(1, 'manolito', 'lolo');

SELECT * FROM member;
DELETE FROM game WHERE id > 45;
DROP TABLE IF EXISTS member;
INSERT INTO member (email, level, name, player_name, id) VALUES
('mafi@maf', 'beginner','mafalda', 'mafi', 1),
('susy@sus', 'beginner', 'susanita', 'susy', 2);

   CREATE TABLE game(
	id BIGINT NOT NULL AUTO_INCREMENT,
	address VARCHAR(255),
    date VARCHAR(255),
    user_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
 );
 
 DROP TABLE IF EXISTS game; 

INSERT INTO game(date, address, user_id) VALUES
 ( '15-08-2022','bar manolo', 1),
 ('22-09-2022','bar paco',  2);
 
SELECT * FROM game;