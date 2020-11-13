USE university_task;

-- SELECT full_name FROM managers
-- INNER JOIN offices ON ref_office = id
-- WHERE name = "Sweaty office";

-- SELECT full_name FROM managers
-- WHERE ref_office = 
-- (SELECT id FROM offices
-- WHERE name = "Sweaty office");

-- SELECT clients.full_name FROM clients
-- INNER JOIN managers ON ref_manager = card_id
-- WHERE managers.full_name = "Abraham Lincoln";

-- SELECT full_name FROM clients
-- WHERE ref_manager = 
-- (SELECT card_id FROM managers
-- WHERE full_name = "Abraham Lincoln");

-- SELECT projects.name FROM project_programmers
-- INNER JOIN projects ON project_programmers.ref_project = projects.id
-- GROUP BY project_programmers.ref_project
-- HAVING COUNT(*) > 1;

-- SELECT name FROM projects
-- WHERE id IN
-- (SELECT ref_project FROM project_programmers
-- GROUP BY ref_project
-- HAVING COUNT(*) > 1);

-- SELECT sup.full_name FROM managers
-- INNER JOIN managers as sup ON managers.ref_supervisor = sup.card_id
-- GROUP BY sup.full_name
-- HAVING COUNT(*) > 1;

-- SELECT full_name FROM managers
-- WHERE card_id IN
-- (SELECT ref_supervisor FROM managers
-- GROUP BY ref_supervisor
-- HAVING COUNT(*) > 1);

-- SELECT programmers.full_name FROM programmers
-- INNER JOIN managers ON programmers.ref_manager = managers.card_id
-- WHERE managers.skill_level = "Senior";

-- SELECT full_name FROM programmers
-- WHERE ref_manager IN
-- (SELECT card_id FROM managers 
-- WHERE skill_level = "Senior");

-- SELECT full_name FROM designers
-- WHERE salary > 
-- (SELECT avg(salary) FROM designers);

-- SELECT sum(
-- (SELECT sum(salary) FROM managers) +
-- (SELECT sum(salary) FROM programmers) +
-- (SELECT sum(salary) FROM designers)) AS "total salary";

-- SELECT full_name FROM managers 
-- WHERE salary > ALL 
-- (SELECT salary FROM programmers);

-- SELECT name FROM offices
-- WHERE NOT EXISTS
-- (SELECT * FROM managers WHERE ref_office = id);

SELECT sum(
-- (SELECT count(*) FROM managers WHERE skill_level = "Junior") +
-- (SELECT count(*) FROM programmers WHERE skill_level = "Junior") +
-- (SELECT count(*) FROM designers WHERE skill_level = "Junior")) AS "total juniors";