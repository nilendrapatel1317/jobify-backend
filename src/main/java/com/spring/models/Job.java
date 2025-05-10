package com.spring.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Job {

    @Id
    private String id;

    @ManyToOne
    private Employee employee;

    @ManyToMany(mappedBy = "jobs")
    private List<Student> students = new ArrayList<>();

    @Size(min = 5, message = "Profile name must be more than 5 characters")
    private String profile;

    @Size(min = 5, message = "Company name must be more than 5 characters")
    private String companyName;

    @Pattern(regexp = "Full_Time|Part_Time", message = "Select any one job Type")
    private String jobType;

    @Size(min = 1, message = "At least one skill is required")
    private List<String> skills;

    @Min(value = 1, message = "Openings must be at least 1")
    private int openings;

    @NotBlank(message = "Start date is required")
    private String startDate;

    @NotBlank(message = "Experience is required")
    private String experience;

    @Size(min = 1, message = "At least one responsibility is required")
    private List<String> responsibility;

    @NotBlank(message = "Location is required")
    private String location;

    @Pattern(regexp = "Fixed|Negotiable|Company_Standard", message = "Select any one Salary Status")
    private String salaryStatus;

    @DecimalMin(value = "0.0", inclusive = true, message = "Stipend amount cannot be negative")
    private Double salary;

    @Size(min = 1, message = "At least one perk is required")
    private List<String> perks;

    @Size(min = 1, message = "At least one assessments is required")
    private List<String> assessments;

    private Boolean isActive;

    private LocalDateTime postedAt;

    @PrePersist
    protected void onCreate() {
        this.postedAt = LocalDateTime.now();
        this.isActive = true;
    }

    // ===== Getters and Setters =====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getOpenings() {
        return openings;
    }

    public void setOpenings(int openings) {
        this.openings = openings;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(List<String> responsibility) {
        this.responsibility = responsibility;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<String> getPerks() {
        return perks;
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public List<String> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<String> assessments) {
        this.assessments = assessments;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }
}
