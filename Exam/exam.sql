-- CREATE DATABASE university_exam;

-- USE university_exam;

-- CREATE TABLE clients (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     corporation_name VARCHAR(100),
--     corporation_type VARCHAR(100),
--     address VARCHAR(100),
--     phone VARCHAR(100),
--     manager VARCHAR(100)
-- );

-- CREATE TABLE loans (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     apr FLOAT,    
--     start_date DATETIME,
--     duration INT,
--     sum FLOAT,
--     paid BOOLEAN,
--     type_ref INT NOT NULL,
--     client_ref INT NOT NULL
-- );

-- CREATE TABLE loan_types (
--     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     type VARCHAR(100)
-- );

-- ALTER TABLE loans ADD CONSTRAINT constr_loan_type FOREIGN KEY (type_ref) REFERENCES loan_types (id);
-- ALTER TABLE loans ADD CONSTRAINT constr_clients FOREIGN KEY (client_ref) REFERENCES clients (id);

-- INSERT INTO
--     clients
-- VALUES
--     (
--         2,
--         "Google",
--         "IT",
--         "USA",
--         "+1231237777",
--         "John Smith"
--     );

-- INSERT INTO loan_types VALUES ( 2, "Small_loan");

-- INSERT INTO
--     loans
-- VALUES
--     (
--         5,
--         3.5,
--         "1999.11.11",
--         1000,
--         1000.00,
--         false,
--         1,
--         1
--     );

SELECT 
    clients.corporation_name AS "client name", 
    clients.phone AS "phone number", 
    clients.manager AS "manager"
FROM loans
INNER JOIN clients ON loans.client_ref = clients.id 
WHERE loans.duration >= 365;

SELECT 
    loan_types.type as "Loan type",
    sum(loans.sum) as "Annual loan"
FROM loans
INNER JOIN loan_types ON loans.type_ref = loan_types.id
WHERE DATE_ADD(loans.start_date, INTERVAL 365 DAY) > NOW()
GROUP BY type_ref;