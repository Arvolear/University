USE university_task;

-- SELECT max(price) AS "MAX", min(price) AS "MIN", avg(price) AS "AVG"
-- FROM projects;

-- SELECT COUNT(*) AS "number", name 
-- FROM offices, programmers
-- WHERE programmers.ref_office = offices.id
-- GROUP BY (offices.name);

-- SELECT COUNT(*) AS "number", managers.full_name 
-- FROM managers, clients 
-- WHERE clients.ref_manager = managers.card_id
-- GROUP BY managers.full_name;

-- SELECT COUNT(DISTINCT occupation) AS "occupations"
-- FROM managers;

-- SELECT COUNT(*) AS "number", projects.name 
-- FROM project_programmers
-- INNER JOIN projects ON project_programmers.ref_project = projects.id
-- INNER JOIN programmers ON project_programmers.ref_programmer = programmers.card_id
-- WHERE projects.price > 10000
-- GROUP BY projects.name;

-- SELECT projects.name 
-- FROM project_designers
-- INNER JOIN projects ON project_designers.ref_project = projects.id
-- INNER JOIN designers ON project_designers.ref_designer = designers.card_id
-- GROUP BY projects.name
-- HAVING COUNT(*) > 1;

-- SELECT managers.full_name
-- FROM managers 
-- INNER JOIN programmers ON programmers.ref_manager = managers.card_id
-- GROUP BY managers.full_name
-- HAVING COUNT(*) > 1;

-- SELECT count(DISTINCT pr.full_name)
-- FROM programmers
-- INNER JOIN programmers AS pr ON programmers.ref_supervisor = pr.card_id;

-- SELECT sum(designers.salary) AS "designers salary"
-- FROM project_designers
-- INNER JOIN projects ON project_designers.ref_project = projects.id
-- INNER JOIN designers ON project_designers.ref_designer = designers.card_id
-- WHERE projects.name = 'Google.com';

SELECT floor(avg(year(current_date()) - 
    year(birthday) - 
    (DATE_FORMAT(current_date(), '%m%d') < DATE_FORMAT(birthday, '%m%d'))))
    AS "avg age"
FROM managers;