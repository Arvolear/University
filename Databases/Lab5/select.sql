-- CREATE DATABASE lab5;

-- USE lab5;

-- CREATE TABLE Person 
-- (
--     id_person INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     Name VARCHAR(50),
--     ref_country INT NULL
-- );

-- CREATE TABLE Country
-- (
--     id_country INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     name_country VARCHAR(50)
-- );

-- ALTER TABLE Person
-- ADD CONSTRAINT constr_Person_Country
-- FOREIGN KEY (ref_country)
-- REFERENCES Country (id_country);

-- INSERT INTO Country VALUES(5, 'USA');
-- SELECT * FROM Country;

-- INSERT INTO Person VALUES(5, 'Yriy', 1);
-- SELECT * FROM Person;


-- SELECT Name, name_country FROM Person 
-- INNER JOIN Country ON ref_country = id_country;

-- SELECT Name, name_country FROM Person 
-- LEFT JOIN Country ON ref_country = id_country;

-- SELECT name_country FROM Person 
-- RIGHT JOIN Country ON ref_country = id_country
-- WHERE ref_country IS NULL;

-- SELECT Name FROM Person 
-- INNER JOIN Country ON ref_country = id_country
-- WHERE name_country = "Ukraine";


-- CREATE TABLE Firm 
-- (
--     id_firm INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     name_firm VARCHAR(50),
--     ref_country INT NULL
-- );

-- CREATE TABLE Telephone
-- (
--     ref_firm INT NULL,
--     telephone VARCHAR(50),
--     ref_person INT NULL
-- );

-- ALTER TABLE Firm
-- ADD CONSTRAINT constr_Firm_Country
-- FOREIGN KEY (ref_country)
-- REFERENCES Country (id_country);

-- ALTER TABLE Telephone
-- ADD CONSTRAINT constr_Telephone_Firm
-- FOREIGN KEY (ref_firm)
-- REFERENCES Firm (id_firm);

-- ALTER TABLE Telephone
-- ADD CONSTRAINT constr_Telephone_Person
-- FOREIGN KEY (ref_person)
-- REFERENCES Person (id_person);

-- INSERT INTO Firm VALUES(3, "Microsoft", 5);
-- SELECT * FROM Firm;

-- INSERT INTO Telephone VALUES(2, "55-55-55", 3);
-- SELECT * FROM Telephone;


-- SELECT name_firm, Name, telephone, name_country FROM Telephone
-- INNER JOIN Firm ON ref_firm = id_firm
-- INNER JOIN Person ON ref_person = id_person
-- LEFT JOIN Country ON Person.ref_country = id_country;

-- SELECT name_firm, Name FROM Telephone
-- RIGHT JOIN Firm ON ref_firm = id_firm
-- LEFT JOIN Person ON ref_person = id_person;

-- SELECT name_firm, FC.name_country AS "Firm country", Name, PC.name_country AS "Residence country" 
-- FROM Telephone
-- INNER JOIN Firm ON ref_firm = id_firm
-- INNER JOIN Country FC ON Firm.ref_country = FC.id_country
-- INNER JOIN Person ON ref_person = id_person
-- LEFT JOIN Country PC ON Person.ref_country = PC.id_country;


USE university_task;

-- SELECT projects.name AS "project name", designers.full_name AS "name"
-- FROM project_designers
-- INNER JOIN projects ON ref_project = projects.id
-- RIGHT JOIN designers ON ref_designer = designers.card_id;

-- SELECT projects.name AS "project name", count(ref_programmer) AS "programmers count"
-- FROM project_programmers
-- RIGHT JOIN projects ON ref_project = projects.id
-- GROUP BY projects.name;

-- SELECT projects.name FROM project_managers
-- RIGHT JOIN projects ON ref_project = projects.id
-- WHERE ref_project IS NULL;
