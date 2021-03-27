USE firm;

-- INSERT INTO position VALUES (NULL, "Head Manager");
-- SELECT * FROM position;

-- INSERT INTO agent VALUES(5, "Tiger Woods", 1, 4000.0, 4);
-- SELECT * FROM agent;

-- INSERT INTO client VALUES (NULL, "John Washington", "Earth, New York", "555-555", 1, 2000.0, 5);
-- SELECT * from client;

-- INSERT INTO type VALUES (NULL, "bouncy castle");
-- SELECT * FROM type;

-- INSERT INTO owner VALUES (NULL, "Brad Pitt", "Mars, North pole", "22-22");
-- SELECT * FROM owner;

-- INSERT INTO property VALUES (NULL, "Venus", NULL, NULL, 1, 2, 6);
-- SELECT * FROM property;

-- INSERT INTO agreement VALUES (NULL, 2, 1, '2020.10.30', '2020.10.30', 2000, "Something", NULL);
-- SELECT * FROM agreement;

-- ------------------------------------------------------------------------------------------------------

-- SELECT name, address, phone FROM owner ORDER BY name DESC;

-- SELECT num as "Agreement number" FROM agreement WHERE YEAR(start_date) = YEAR(CURRENT_DATE()) AND MONTH(start_date) = MONTH(CURRENT_DATE()) ORDER BY start_date DESC;

-- SELECT client.num, client.full_name AS "client name", type.name AS "type name", agent.full_name AS "agent name" FROM client 
-- INNER JOIN type ON client.ref_type = type.gkey 
-- INNER JOIN agent ON client.ref_agent = agent.num 
-- WHERE type.name = '3-storeyed house' AND client.max_pay > 2000;

-- SELECT property.address FROM property
-- INNER JOIN type ON property.ref_type = type.gkey
-- WHERE type.name = 'garage' AND property.price BETWEEN 50 AND 100;

-- SELECT DISTINCT client.full_name, client.phone FROM agreement
-- INNER JOIN client ON agreement.ref_client = client.num
-- INNER JOIN type ON client.ref_type = type.gkey
-- WHERE (type.name = '3-storeyed house' OR type.name = 'garage') AND
-- (YEAR(agreement.start_date) = YEAR(CURRENT_DATE()));

-- SELECT name, phone FROM owner WHERE address = 'Earth, New York';

-- SELECT num, address, price, price * 6 AS "Half a year price" FROM property;

-- SELECT agent.num, agent.full_name AS "agent name", position.name AS "position", agent.salary, a.full_name AS "super agent" FROM agent
-- INNER JOIN position ON agent.ref_position = position.gkey
-- INNER JOIN agent a ON agent.ref_super_agent = a.num ORDER BY agent.num 

-- SELECT * FROM agent WHERE ref_super_agent IS NULL;

-- SELECT agreement.num, agreement.start_date, property.price * 0.5 AS "agency income" FROM agreement
-- INNER JOIN property ON agreement.ref_property = property.num
-- WHERE YEAR(agreement.start_date) = YEAR(CURRENT_DATE());
