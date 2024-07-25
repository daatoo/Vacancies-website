package com.example.demo.vacancy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private String CompanyName;
    private Double salary;
    private String requirements;
    private LocalDate postedDate;
    private LocalDate expiryDate;
    private String contactEmail;
    private String imagePath;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getJobType() {
        return jobType;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public Double getSalary() {
        return salary;
    }

    public String getRequirements() {
        return requirements;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
