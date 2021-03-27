USE university_task;

-- SELECT full_name, phone, email FROM clients;

-- CREATE VIEW query1 AS 
-- SELECT full_name, phone, email FROM clients;


-- SELECT * FROM offices WHERE workers > 2 ORDER BY address;

-- CREATE VIEW query2 AS 
-- SELECT * FROM offices WHERE workers > 2 ORDER BY address;

-- SELECT * FROM query2;


-- SELECT projects.name, projects.price, clients.full_name, clients.phone 
-- FROM projects
-- INNER JOIN clients ON projects.ref_client = clients.id 
-- WHERE projects.price > 50000;


-- SELECT managers.full_name AS "name", 
--     managers.skill_level AS "skill", 
--     mansup.full_name AS "sup name", 
--     mansup.skill_level AS "sup skill"
-- FROM managers
-- INNER JOIN managers AS mansup ON managers.ref_supervisor = mansup.card_id;


-- SELECT programmers.full_name AS "prog name", managers.full_name AS "mana name" 
-- FROM programmers 
-- INNER JOIN managers ON programmers.ref_manager = managers.card_id
-- WHERE programmers.ref_supervisor IS NULL;


-- SELECT * FROM managers WHERE (full_name LIKE "A%") AND (skill_level = 'Senior');


-- SELECT designers.full_name, designers.email, designers.salary, offices.name
-- FROM designers
-- INNER JOIN offices ON designers.ref_office = offices.id
-- WHERE YEAR(designers.hire_date) = '2019';


-- SELECT programmers.full_name AS "prog name", 
--     supprog.full_name AS "sup name", 
--     managers.full_name AS "mana name" 
-- FROM programmers 
-- INNER JOIN programmers AS supprog ON programmers.ref_supervisor = supprog.card_id
-- INNER JOIN managers ON programmers.ref_manager = managers.card_id;


-- SELECT projects.id, projects.name, managers.full_name
-- FROM project_managers
-- INNER JOIN projects ON project_managers.ref_project = projects.id
-- INNER JOIN managers ON project_managers.ref_manager = managers.card_id
-- WHERE projects.id = 3;


-- SELECT projects.id, projects.name AS "proj name", clients.full_name AS "cli name", programmers.full_name AS "prog name"
-- FROM project_programmers
-- INNER JOIN projects ON project_programmers.ref_project = projects.id
-- INNER JOIN programmers ON project_programmers.ref_programmer = programmers.card_id
-- INNER JOIN clients ON projects.ref_client = clients.id
-- WHERE projects.id = 1;