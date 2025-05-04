package com.spring.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Job {

    @Id
    private String id;

    @ManyToOne
    private Employee employee;

    @ManyToMany(mappedBy = "jobs")
    private List<Student> students;

    private String title;
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private JobType jobType;
    // ===== Enum inside the class =====
    public enum JobType {
    	IN_OFFICE, REMOTE
    }

    private int openings;
    private String description;
    private String preferences;
    private Double salary;
    private List<String> perks;
    private String assesments;

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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public int getOpenings() {
		return openings;
	}
	public void setOpenings(int openings) {
		this.openings = openings;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreferences() {
		return preferences;
	}
	public void setPreferences(String preferences) {
		this.preferences = preferences;
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
	public String getAssesments() {
		return assesments;
	}
	public void setAssesments(String assesments) {
		this.assesments = assesments;
	}




}
