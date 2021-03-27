CREATE DATABASE university_hotel;

USE university_hotel;

CREATE USER 'uni'@'localhost' IDENTIFIED BY 'uni';
GRANT ALL PRIVILEGES ON * . * TO 'uni'@'localhost';

CREATE TABLE Client (
    id_client smallint AUTO_INCREMENT NOT NULL PRIMARY KEY,
	fio nvarchar(50) NOT NULL,
	passport nvarchar(50) NOT NULL
);

CREATE TABLE Comfort (
	id_comfort tinyint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	description nvarchar(50)
); 

CREATE TABLE Room(
	number_room smallint NOT NULL PRIMARY KEY,
	capacity tinyint NOT NULL,
	ref_comfort tinyint NOT NULL,
	price float NULL
); 

ALTER TABLE Room
ADD CONSTRAINT FK_Room_Comfort 
FOREIGN KEY (ref_comfort)
REFERENCES Comfort (id_comfort);

CREATE TABLE Renting (
	ref_client smallint NOT NULL,
	ref_room smallint NOT NULL,
	date_in date NOT NULL,
	date_out date NULL,
    CONSTRAINT PK_Renting PRIMARY KEY (
	    ref_client ASC,
	    ref_room ASC,
	    date_in ASC
    )
);

ALTER TABLE Renting 
ADD CONSTRAINT FK_Renting_Client 
FOREIGN KEY (ref_client)
REFERENCES Client (id_client);

ALTER TABLE Renting
ADD CONSTRAINT FK_Renting_Room FOREIGN KEY (ref_room)
REFERENCES Room (number_room);