CREATE TABLE itprofessional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    specialty VARCHAR(255),
    country_of_residence VARCHAR(255),
    amount_of_current_year_bonus DECIMAL(15, 2),
    currency VARCHAR(3)
);