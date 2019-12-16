DROP DATABASE IF EXISTS secret_santa;

CREATE DATABASE secret_santa;
USE secret_santa;

CREATE TABLE participants(
id INT auto_increment primary KEY,
user_name VARCHAR(100),
user_last_name VARCHAR(100),
is_santa_already BOOLEAN DEFAULT FALSE);

CREATE TABLE santa_connections(
id INT auto_increment primary KEY,
santa_name VARCHAR(100),
santa_last_name VARCHAR(100),
reciving_participant_name VARCHAR(100),
reciving_participant_last_name VARCHAR(100));


INSERT INTO participants (id, user_name, user_last_name) 
VALUES 	(1, 'Dwight','Schrute'), 
		(2, 'Stanley','Hudson'), 
        (3, 'Meredith','Palmer'),
        (4,'Angela','Martin'),
        (5,'Kevin','Malone'),
        (6,'Oscar','Martinez'),
        (7,'Phyllis','Vance'),
        (8,'Andy','Bernard'),
        (9,'Gabe','Lewis'),
        (10,'Toby','Flenderson'),
        (11,'Holly','Flax'),
        (12,'Ryan','Howard'),
        (13,'Kelly','Kapoor'),
        (14,'Darryl','Philbin'),
        (15,'Karen','Filippelli'),
        (16,'Jan','Levinson-Gould'),
        (17,'Erin','Hannon');
        
SELECT * FROM participants;
SELECT * FROM santa_connections;