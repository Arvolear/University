USE university_task;

-- ALTER TABLE managers ADD CONSTRAINT managers_salary_con CHECK (salary >= 0);
-- ALTER TABLE programmers ADD CONSTRAINT programmers_salary_con CHECK (salary >= 0);
-- ALTER TABLE designers ADD CONSTRAINT designers_salary_con CHECK (salary >= 0);

-- ALTER TABLE projects ADD CONSTRAINT projects_price_con CHECK (price >= 0);
-- ALTER TABLE projects ADD CONSTRAINT projects_payed_con CHECK (client_payed >= 0);

-- INSERT INTO managers VALUES (6, "Random Name", "email", "number", "1970.01.01", -123, "1970.01.01", 1, "", "", NULL, 2);

-- ALTER TABLE managers ADD CONSTRAINT managers_email_con CHECK (email LIKE '%@%.%');
-- ALTER TABLE programmers ADD CONSTRAINT programmers_email_con CHECK (email LIKE '%@%.%');
-- ALTER TABLE designers ADD CONSTRAINT designers_email_con CHECK (email LIKE '%@%.%');
-- ALTER TABLE clients ADD CONSTRAINT clients_email_con CHECK (email LIKE '%@%.%');

-- INSERT INTO managers VALUES (6, "Random Name", "bad email", "number", "1970.01.01", 2000, "1970.01.01", 1, "", "", NULL, 2);

-- ALTER TABLE offices ADD CONSTRAINT offices_workers_con CHECK (workers <= max_workers);

-- UPDATE offices SET workers = 0 WHERE id = 5;

-- ALTER TABLE managers ALTER hire_date SET DEFAULT (CURRENT_DATE);
-- ALTER TABLE programmers ALTER hire_date SET DEFAULT (CURRENT_DATE);
-- ALTER TABLE designers ALTER hire_date SET DEFAULT (CURRENT_DATE);
