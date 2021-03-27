USE university_task;

-- DELIMITER $$

-- DROP PROCEDURE IF EXISTS addToProject $$

-- CREATE PROCEDURE addToProject (
--     IN position VARCHAR(30), 
--     IN fullName VARCHAR(50), 
--     IN projectName VARCHAR(50)
-- )
-- BEGIN
--     DECLARE personID INT;
--     DECLARE projectID INT;

--     IF EXISTS (SELECT * FROM projects WHERE name = projectName) THEN
--         SET projectID = (SELECT id FROM projects WHERE name = projectName);

--         IF position = 'manager' THEN                    
--             SET personID = (SELECT card_id FROM managers WHERE full_name = fullName);            

--             IF EXISTS (SELECT * FROM managers WHERE full_name = fullName) THEN                            
--                 IF NOT EXISTS (SELECT * FROM project_managers WHERE ref_project = projectID AND ref_manager = personID) THEN                                    
--                     INSERT INTO project_managers VALUES (NULL, projectID, personID);                                    
--                 ELSE
--                     SELECT 'Duplicate query' AS '';
--                 END IF;
--             ELSE             
--                 SELECT 'No such manager exists' AS '';
--             END IF;
--         ELSEIF position = 'programmer' THEN
--             SET personID = (SELECT card_id FROM programmers WHERE full_name = fullName);            

--             IF EXISTS (SELECT * FROM programmers WHERE full_name = fullName) THEN                            
--                 IF NOT EXISTS (SELECT * FROM project_programmers WHERE ref_project = projectID AND ref_programmer = personID) THEN                                    
--                     INSERT INTO project_programmers VALUES (NULL, projectID, personID);                                    
--                 ELSE
--                     SELECT 'Duplicate query' AS '';
--                 END IF;
--             ELSE             
--                 SELECT 'No such programmer exists' AS '';
--             END IF;
--         ELSEIF position = 'designer' THEN
--             SET personID = (SELECT card_id FROM designers WHERE full_name = fullName);            

--             IF EXISTS (SELECT * FROM designers WHERE full_name = fullName) THEN                            
--                 IF NOT EXISTS (SELECT * FROM project_designers WHERE ref_project = projectID AND ref_designer = personID) THEN                                    
--                     INSERT INTO project_designers VALUES (NULL, projectID, personID);                                    
--                 ELSE
--                     SELECT 'Duplicate query' AS '';
--                 END IF;
--             ELSE             
--                 SELECT 'No such designer exists' AS '';
--             END IF;    
--         ELSE                    
--             SELECT 'No such position exists' AS '';
--         END IF;
--     ELSE        
--         SELECT 'No such project exists' AS '';
--     END IF;
-- END$$

-- DELIMITER ;

-- USE university_task;

-- DELIMITER $$

-- DROP PROCEDURE IF EXISTS totalSalary $$

-- CREATE PROCEDURE totalSalary (
--     OUT sal FLOAT, 
--     OUT num INT
-- )
-- BEGIN
--     SET sal = (SELECT (SELECT sum(salary) FROM managers) +
--     (SELECT sum(salary) FROM programmers) +
--     (SELECT sum(salary) FROM designers));

--     SET num = (SELECT (SELECT count(*) FROM managers) +
--     (SELECT count(*) FROM programmers) +
--     (SELECT count(*) FROM designers));
-- END$$

-- DELIMITER ;

-- USE university_task;

-- DELIMITER $$

-- DROP PROCEDURE IF EXISTS averageSalary $$

-- CREATE PROCEDURE averageSalary (
--     OUT avSal FLOAT    
-- )
-- BEGIN
--     DECLARE sal FLOAT;
--     DECLARE num INT;

--     CALL totalSalary(sal, num);

--     SET avSal = sal / num;
-- END$$

-- DELIMITER ;