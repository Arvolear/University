-- CREATE DATABASE university_task;

USE university_task;

-- CREATE TABLE offices (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     address VARCHAR(100),
--     name VARCHAR(100),
--     max_workers INT,
--     workers INT);

-- CREATE TABLE clients (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     full_name VARCHAR(100),    
--     phone VARCHAR(50),
--     email varchar(50),
--     ref_manager INT NULL);

-- CREATE TABLE projects (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     name VARCHAR(100),    
--     price FLOAT,
--     client_payed FLOAT,
--     start_date DATE,
--     finish_date DATE,
--     is_finished BOOLEAN,
--     ref_client INT NOT NULL);

-- CREATE TABLE managers (
--     card_id INT PRIMARY KEY NOT NULL,
--     full_name VARCHAR(100),
--     email VARCHAR(50),
--     phone VARCHAR(50),
--     birthday DATE,
--     salary FLOAT,
--     hire_date DATE,
--     office_place INT,
--     occupation VARCHAR(100),
--     skill_level VARCHAR(50),
--     ref_supervisor INT NULL,
--     ref_office INT NOT NULL);

-- CREATE TABLE programmers (
--     card_id INT PRIMARY KEY NOT NULL,
--     full_name VARCHAR(100),
--     email VARCHAR(50),
--     phone VARCHAR(50),
--     birthday DATE,
--     salary FLOAT,
--     hire_date DATE,
--     office_place INT,
--     occupation VARCHAR(100),
--     skill_level VARCHAR(50),
--     ref_supervisor INT NULL,
--     ref_manager INT NOT NULL,
--     ref_office INT NOT NULL);

-- CREATE TABLE designers (
--     card_id INT PRIMARY KEY NOT NULL,
--     full_name VARCHAR(100),
--     email VARCHAR(50),
--     phone VARCHAR(50),
--     birthday DATE,
--     salary FLOAT,
--     hire_date DATE,
--     office_place INT,
--     occupation VARCHAR(100),
--     skill_level VARCHAR(50),    
--     ref_manager INT NOT NULL,
--     ref_office INT NOT NULL);

-- CREATE TABLE project_managers (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     ref_project INT NULL,
--     ref_manager INT NULL);

-- CREATE TABLE project_programmers (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     ref_project INT NULL,
--     ref_programmer INT NULL);

-- CREATE TABLE project_designers (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     ref_project INT NULL,
--     ref_designer INT NULL);

-- ALTER TABLE clients ADD CONSTRAINT constr_clients_managers FOREIGN KEY (ref_manager) REFERENCES managers (card_id);

-- ALTER TABLE projects ADD CONSTRAINT constr_projects_clients FOREIGN KEY (ref_client) REFERENCES clients (id);

-- ALTER TABLE managers ADD CONSTRAINT constr_managers_managers FOREIGN KEY (ref_supervisor) REFERENCES managers (card_id);
-- ALTER TABLE managers ADD CONSTRAINT constr_managers_offices FOREIGN KEY (ref_office) REFERENCES offices (id);

-- ALTER TABLE programmers ADD CONSTRAINT constr_programmers_managers FOREIGN KEY (ref_manager) REFERENCES managers (card_id);
-- ALTER TABLE programmers ADD CONSTRAINT constr_programmers_programmers FOREIGN KEY (ref_supervisor) REFERENCES programmers (card_id);
-- ALTER TABLE programmers ADD CONSTRAINT constr_programmers_offices FOREIGN KEY (ref_office) REFERENCES offices (id);

-- ALTER TABLE designers ADD CONSTRAINT constr_designers_managers FOREIGN KEY (ref_manager) REFERENCES managers (card_id);
-- ALTER TABLE designers ADD CONSTRAINT constr_designers_offices FOREIGN KEY (ref_office) REFERENCES offices (id);

-- ALTER TABLE project_managers ADD CONSTRAINT constr_project_managers_projects FOREIGN KEY (ref_project) REFERENCES projects (id);
-- ALTER TABLE project_managers ADD CONSTRAINT constr_project_managers_managers FOREIGN KEY (ref_manager) REFERENCES managers (card_id);

-- ALTER TABLE project_programmers ADD CONSTRAINT constr_project_programmers_projects FOREIGN KEY (ref_project) REFERENCES projects (id);
-- ALTER TABLE project_programmers ADD CONSTRAINT constr_project_programmers_programmers FOREIGN KEY (ref_programmer) REFERENCES programmers (card_id);

-- ALTER TABLE project_designers ADD CONSTRAINT constr_project_designers_projects FOREIGN KEY (ref_project) REFERENCES projects (id);
-- ALTER TABLE project_designers ADD CONSTRAINT constr_project_designers_designers FOREIGN KEY (ref_designer) REFERENCES designers (card_id);