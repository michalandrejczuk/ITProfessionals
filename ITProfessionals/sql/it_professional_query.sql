SELECT p.id, p.first_name, p.last_name, p.email, p.specialty, 
       GROUP_CONCAT(t.technology SEPARATOR ', ') AS technologies
FROM itprofessional p
LEFT JOIN technologies t ON p.id = t.itprofessional_id
GROUP BY p.id;
