USE university_task;

-- DROP TRIGGER IF EXISTS updateOfficeInsert;

-- CREATE TRIGGER updateOfficeInsert BEFORE INSERT ON managers
-- FOR EACH ROW
-- BEGIN    
--     IF EXISTS (SELECT * FROM managers WHERE office_place = NEW.office_place AND ref_office = NEW.ref_office) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate office place";
--     END IF;

--     IF EXISTS (SELECT * FROM programmers WHERE office_place = NEW.office_place AND ref_office = NEW.ref_office) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate office place";
--     END IF;

--     IF EXISTS (SELECT * FROM designers WHERE office_place = NEW.office_place AND ref_office = NEW.ref_office) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate office place";
--     END IF;

--     IF EXISTS (SELECT * FROM managers WHERE card_id = NEW.card_id) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate card id";
--     END IF;

--     IF EXISTS (SELECT * FROM programmers WHERE card_id = NEW.card_id) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate card id";
--     END IF;

--     IF EXISTS (SELECT * FROM designers WHERE card_id = NEW.card_id) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate card id";
--     END IF;
           
--     UPDATE offices SET workers = workers + 1 WHERE id = NEW.ref_office;      
-- END;

-- INSERT INTO managers VALUES (10, "ASD", "A@mail.com", "+1", "1970.01.01", 400, "1970.01.01", 10, "PM", "Senior", NULL, 2);

-- DROP TRIGGER IF EXISTS updateOfficeDelete;

-- CREATE TRIGGER updateOfficeDelete AFTER DELETE ON managers
-- FOR EACH ROW
-- BEGIN
--     UPDATE offices SET workers = workers - 1 WHERE id = OLD.ref_office;
-- END;

-- DELETE FROM managers WHERE full_name = "ASD";

-- DROP TRIGGER IF EXISTS updateOfficeUpdate;

-- CREATE TRIGGER updateOfficeUpdate BEFORE UPDATE ON managers
-- FOR EACH ROW 
-- BEGIN
--     IF EXISTS (SELECT * FROM managers WHERE office_place = NEW.office_place AND ref_office = NEW.ref_office) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate office place";
--     END IF;

--     IF EXISTS (SELECT * FROM programmers WHERE office_place = NEW.office_place AND ref_office = NEW.ref_office) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate office place";
--     END IF;

--     IF EXISTS (SELECT * FROM designers WHERE office_place = NEW.office_place AND ref_office = NEW.ref_office) THEN
--         SIGNAL sqlstate '45001' SET message_text = "Duplicate office place";
--     END IF;
-- END;