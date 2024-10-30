package com.spring.itprofessionals.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="itprofessional")
public class ITProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "specialty")
    private String specialty;

    @ElementCollection
    @CollectionTable(name = "technologies", joinColumns = @JoinColumn(name = "itprofessional_id"))
    @Column(name="technology")
    private List<String> technologies;

    @Column(name="country_of_residence")
    private String countryOfResidence;

    @Column(name="amount_of_current_year_bonus")
    private BigDecimal amountOfCurrentYearBonus;

    @Column(name="currency")
    String currency;

    public ITProfessional() {
    }

    public ITProfessional(String firstName, String lastName, String email,
                          String specialty, List<String> technologies, String countryOfResidence,
                          BigDecimal amountOfCurrentYearBonus, String currency) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialty = specialty;
        this.technologies = technologies != null ? technologies : new ArrayList<>();
        this.countryOfResidence = countryOfResidence;
        this.amountOfCurrentYearBonus = amountOfCurrentYearBonus;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public BigDecimal getAmountOfCurrentYearBonus() {
        return amountOfCurrentYearBonus;
    }

    public void setAmountOfCurrentYearBonus(BigDecimal amountOfCurrentYearBonus) {
        this.amountOfCurrentYearBonus = amountOfCurrentYearBonus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ITProfessional{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", specialty='" + specialty + '\'' +
                ", technologies=" + technologies +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", amountOfCurrentYearBonus=" + amountOfCurrentYearBonus +
                ", currency='" + currency + '\'' +
                '}';
    }
}

