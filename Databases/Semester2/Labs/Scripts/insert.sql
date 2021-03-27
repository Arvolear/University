USE university_hotel;

/*----------------client---------------*/

INSERT INTO Client (
    fio,
    passport)
VALUES ('Иванов', 'ХА 457863');
		   
INSERT INTO Client (
    fio,
    passport)
VALUES ('Петров', 'ЕА 563698');

INSERT INTO Client (
    fio,
    passport)
VALUES ('Сидоров', 'ВИ 454563');

/*-----------comfort------------------*/

INSERT INTO Comfort (description)
     VALUES ('эконом');

INSERT INTO Comfort (description)
     VALUES ('стандарт');

INSERT INTO Comfort (description)
     VALUES ('полу-люкс');

INSERT INTO Comfort (description)
     VALUES ('люкс');

INSERT INTO Comfort (description)
     VALUES ('президент');

/*-----------Room------------------*/

INSERT INTO Room (
    number_room,
    capacity,
    ref_comfort,
    price)
VALUES (101, 3, 2, 80);
           
INSERT INTO Room (
    number_room,
    capacity,
    ref_comfort,
    price)
VALUES (102, 2, 3, 120);

INSERT INTO Room (
    number_room,
    capacity,
    ref_comfort,
    price)
VALUES (103, 2, 3, 120);

INSERT INTO Room (
    number_room,
    capacity,
    ref_comfort,
    price)
VALUES (104, 2, 3, 200);

INSERT INTO Room(
    number_room,
    capacity,
    ref_comfort,
    price)
VALUES (105, 1, 4, 300);

/*---------------Renting-------------*/

INSERT INTO Renting (
    ref_client,
    ref_room,
    date_in,
    date_out)
VALUES (1, 102, '12.09.2018', NULL);

INSERT INTO Renting (
    ref_client,
    ref_room,
    date_in,
    date_out)
VALUES (2, 103, '05.09.2018', '08.10.2018');

INSERT INTO Renting (
    ref_client,
    ref_room,
    date_in,
    date_out)
VALUES (3, 105, '10.09.2018', NULL);
	