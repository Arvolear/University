USE university_task;

DROP PROCEDURE IF EXISTS junsToProject;

CREATE PROCEDURE junsToProject(
    IN projectName VARCHAR(100)
)
BEGIN   
    DECLARE done BOOLEAN DEFAULT FALSE;    

    DECLARE projInd INT;
    DECLARE progInd INT;    

    DECLARE junCursor CURSOR FOR SELECT card_id FROM programmers WHERE skill_level = "Junior";
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    IF EXISTS (SELECT * FROM projects WHERE name = projectName) THEN
        SELECT id FROM projects WHERE name = projectName INTO projInd;

        OPEN junCursor;

        read_loop: LOOP
            FETCH junCursor INTO progInd;            

            IF done THEN
                LEAVE read_loop;
            END IF;
            
            DELETE FROM project_programmers WHERE ref_programmer = progInd;
            INSERT INTO project_programmers VALUES (progInd, projInd);
        END LOOP;

        CLOSE junCursor;
    ELSE
        SELECT 'No such project exists' AS '';
    END IF;    
END;

CALL junsToProject("Google.com");