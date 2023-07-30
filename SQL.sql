DROP DATABASE IF EXISTS  human_friends;
CREATE DATABASE IF NOT EXISTS human_friends;
USE  human_friends;

SET @my_counter = 0;

DELIMITER //
CREATE FUNCTION incrementCalc ( val INT )
RETURNS INT 
NO SQL
BEGIN 
   DECLARE i INT; 
   SET i = 1 + val;
   SET @my_counter = i;
   RETURN i; 
END; //
DELIMITER ;

CREATE TABLE animals (
id INT NOT NULL AUTO_INCREMENT,
type_name VARCHAR(45) NOT NULL UNIQUE,
PRIMARY KEY (id)
);

INSERT INTO animals (type_name)VALUES ("pets"), ("pack_animals");

CREATE TABLE pet_animals (
`id` INT NOT NULL AUTO_INCREMENT,
`id_type` INT NOT NULL,
`group_name` VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (id),
FOREIGN KEY (id_type) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pet_animals (id_type, group_name)
VALUES
  (1, "dogs"),
  (1, "cats"),
  (1, "hamsters");

CREATE TABLE pack_animals (
`id` INT NOT NULL AUTO_INCREMENT,
`id_type` INT NOT NULL,
`group_name` VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (id),
FOREIGN KEY (id_type) REFERENCES animals (id) ON DELETE CASCADE  ON UPDATE CASCADE
);

INSERT INTO pack_animals (id_type, group_name)
VALUES
  (2, "horses"),
  (2, "camels"),
  (2, "donkeys");

CREATE TABLE dogs (
  `id` int ,
  `id_group` int DEFAULT 1,
  `animal_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `command` varchar(100) DEFAULT NULL,
  FOREIGN KEY (id_group) REFERENCES pet_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE cats (
  `id` int ,
  `id_group` int DEFAULT 2,
  `animal_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `command` varchar(100) DEFAULT NULL,
  FOREIGN KEY (id_group) REFERENCES pet_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE hamsters (
  `id` int ,
  `id_group` int DEFAULT 3,
  `animal_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `command` varchar(100) DEFAULT NULL,
  FOREIGN KEY (id_group) REFERENCES pet_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE horses (
  `id` INT,
  `id_group` int DEFAULT 1,
  `animal_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `command` varchar(100) DEFAULT NULL,
  FOREIGN KEY (id_group) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels (
  `id` int,
  `id_group` int DEFAULT 2,
  `animal_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `command` varchar(100) DEFAULT NULL,
  FOREIGN KEY (id_group) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE donkeys (
  `id` int,
  `id_group` int DEFAULT 3 ,
  `animal_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `command` varchar(100) DEFAULT NULL,
  FOREIGN KEY (id_group) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER //
CREATE TRIGGER tr_before_insert_donkeys
BEFORE INSERT ON donkeys
FOR EACH ROW
BEGIN
 SET NEW.id = incrementCalc(@my_counter);
END;
//
CREATE TRIGGER tr_before_insert_horses
BEFORE INSERT ON horses
FOR EACH ROW
BEGIN
   SET NEW.id = incrementCalc(@my_counter);
END;
//
CREATE TRIGGER tr_before_insert_camels
BEFORE INSERT ON camels
FOR EACH ROW
BEGIN
  SET NEW.id = incrementCalc(@my_counter);
END;

CREATE TRIGGER tr_before_insert_hamsters
BEFORE INSERT ON hamsters
FOR EACH ROW
BEGIN
 SET NEW.id = incrementCalc(@my_counter);
END;
//
CREATE TRIGGER tr_before_insert_cats
BEFORE INSERT ON cats
FOR EACH ROW
BEGIN
   SET NEW.id = incrementCalc(@my_counter);
END;
//
CREATE TRIGGER tr_before_insert_dogs
BEFORE INSERT ON dogs
FOR EACH ROW
BEGIN
  SET NEW.id = incrementCalc(@my_counter);
END;
//
DELIMITER ;

# Заполнить низкоуровневые таблицы именами(животных), командами
# которые они выполняют и датами рождения

INSERT INTO dogs(animal_name, birthday, command)VALUES
	("Alice", "2022-05-15", "Go"),
    ("Sofia", "2021-09-13", "Stop"),
    ("Bob", "2023-02-28", "Jump"),
    ("Charlie", "2023-07-12", "Pretend");
INSERT INTO cats(animal_name, birthday, command)VALUES
	("Daisy", "2022-11-05", "To_me"),
    ("Thomas", "2023-06-23", "Walk"),
    ("Ella", "2021-12-20", "Stop"),
    ("Felix", "2023-06-10", "Walk");
INSERT INTO hamsters(animal_name, birthday, command)VALUES
	("Grace", "2022-09-03", "Jump"),
    ("Uma", "2022-10-02", "Go"),
    ("Henry", "2023-04-18", "Go"),
    ("Isabella", "2021-10-25", "Pretend");
INSERT INTO horses(animal_name, birthday, command)VALUES
	("Jack", "2023-01-07", "To_me"),
    ("Katie", "2022-08-12", "Get_up"),
    ("Victor", "2023-02-17", "Jump"),
    ("Liam", "2023-03-21", "Jump");
INSERT INTO camels(animal_name, birthday, command)VALUES 
	("Mia", "2021-06-30", "Stop"),
    ("Wendy", "2021-07-27", "Get_up"),
    ("Noah", "2023-05-09", "Walk"),
    ("Olivia", "2022-07-14", "Go");
INSERT INTO donkeys(animal_name, birthday, command)VALUES 
	("Peter", "2021-11-29", "Jump"),
    ("Xander", "2023-05-05", "Jump"),
    ("Quinn", "2023-04-03", "Pretend"),
    ("Ryan", "2022-12-08", "To_me");
    
# Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
# питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
DELETE FROM pack_animals
WHERE group_name = "camels";
SELECT * FROM  pack_animals;

CREATE VIEW no_camels AS(
SELECT * FROM horses
UNION ALL
SELECT * FROM donkeys);

SELECT * FROM no_camels;

# Создать новую таблицу “молодые животные” в которую попадут все
# животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
# до месяца подсчитать возраст животных в новой таблице
DROP TABLE all_animals;
CREATE TEMPORARY TABLE all_animals
AS
SELECT * FROM no_camels
UNION SELECT * FROM cats
UNION SELECT * FROM hamsters
UNION SELECT * FROM dogs;

SELECT *  FROM all_animals;

DROP TABLE IF EXISTS young;
CREATE TABLE young
AS
SELECT *, TIMESTAMPDIFF(MONTH, birthday, current_date()) AS age_months 
FROM all_animals
WHERE TIMESTAMPDIFF(YEAR, birthday, current_date()) BETWEEN 1 AND 3; 

SELECT * FROM young;