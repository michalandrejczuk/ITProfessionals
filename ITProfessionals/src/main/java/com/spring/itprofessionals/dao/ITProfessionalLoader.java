package com.spring.itprofessionals.dao;

import com.spring.itprofessionals.entity.ITProfessional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ITProfessionalLoader implements CommandLineRunner {

    EntityManager entityManager;

    @Autowired
    public ITProfessionalLoader(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void run(String... args) throws IOException {
        loadData();
    }


    public void loadData() throws IOException {


        try (InputStream inputStream = getClass().getResourceAsStream("/data/it_professionals.csv");
             Reader reader = new InputStreamReader(inputStream);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            List<ITProfessional> listITProfessionals = csvParser.getRecords().stream()
                    .skip(1)
                    .map(record -> {

                        String technologiesString = record.get(4).replace("\"", "");
                        List<String> technologies = Arrays.asList(technologiesString.split(",\\s*"));

                        String firstName = record.get(0);
                        String lastName = record.get(1);
                        String email = record.get(2);
                        String specialty = record.get(3);
                        String countryOfResidence = record.get(5);
                        BigDecimal amountOfCurrentYearBonus = BigDecimal.valueOf(Double.parseDouble(record.get(6)));
                        String currency = record.get(7);
                        return new ITProfessional(firstName, lastName, email, specialty, technologies,
                                countryOfResidence, amountOfCurrentYearBonus, currency);

                    }).toList();

            for(ITProfessional professional : listITProfessionals) {
                if(!existsByEmail(professional.getEmail())) {
                    entityManager.persist(professional);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data from CSV file: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private boolean existsByEmail(String email) {
        Query query = entityManager.createQuery("SELECT count(p) FROM ITProfessional p WHERE p.email = :email");
        query.setParameter("email", email);
        long count = (long) query.getSingleResult();
        return count > 0;
    }
}
