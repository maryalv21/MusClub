DROP SCHEMA musclub;
CREATE SCHEMA musclub;
USE musclub;

DROP SCHEMA musclub_test;
CREATE SCHEMA musclub_test;
USE musclub_test;


DROP TABLE IF EXISTS role;  
DROP TABLE IF EXISTS user;

SELECT * FROM game;
SELECT * FROM role;
SELECT * FROM user;
SELECT * FROM player;

DELETE FROM game WHERE id >2; 

UPDATE game SET user_id = 1 WHERE id =35;



 CREATE TABLE user(
	id BIGINT NOT NULL AUTO_INCREMENT,
	password VARCHAR(255),
	username VARCHAR(255),
    PRIMARY KEY(id)
 );
 
 CREATE TABLE role(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	user_id BIGINT,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES user(id)
 );

 
   CREATE TABLE game(
	id BIGINT NOT NULL AUTO_INCREMENT,
	address VARCHAR(255),
    date VARCHAR(255),
    user_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id)

 );
 
 DROP TABLE IF EXISTS game;
 
 SELECT * FROM game;
 DELETE from game where id > 43; 
 update game set user_id = 1 where id = 23;
 INSERT INTO game(date, address, user_id) VALUES
 ('15-08-2022','bar remember', 1),
 ('22-09-2022','taberna tio paco', 2);

INSERT INTO user (password, username) VALUES
('$2a$10$.w21SnYs3LwpEa6Fr5mbKu.ipm8zBqWaJbksBN69tbPLAsBcA9aau','mafalda'),
('$2a$10$od0BwTQhHPx6KU.glRGY1emwOkgUPueQprPfgkq2Ifv/doMmqyfC6','susanita');

SELECT * FROM user;

INSERT INTO role (name, user_id) VALUES 
('MEMBER',1);

SELECT * FROM role;

DELETE FROM game WHERE id =17;

select  g.id, g.address, g.date, u.username from musclub.game as g
inner join serurity_project.user as u
 where u.id in (select user_id username from serurity_project.user);


select g.id, g.address, g.date, g.user_id from musclub.game as g
            inner join serurity_project.user as u
            on g.user_id = u.id WHERE u.id = 2;
            






