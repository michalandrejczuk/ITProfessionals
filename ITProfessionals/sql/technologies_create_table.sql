CREATE TABLE technologies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    itprofessional_id INT,
    technology VARCHAR(255),
    FOREIGN KEY (itprofessional_id) REFERENCES itprofessional(id) ON DELETE CASCADE
);
