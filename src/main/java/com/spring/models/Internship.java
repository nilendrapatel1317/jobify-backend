package com.spring.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Internship {

	@Id
	private String id;

	@ManyToOne
	private Employee employee;

	@ManyToMany(mappedBy = "internships")
	private List<Student> students;

	private String profile;
	
	private List<String> skills;

	@Enumerated(EnumType.STRING)
	private InternshipType internshipType;
	
	// ===== Enums defined inside the class =====
	public enum InternshipType {
		IN_OFFICE, REMOTE
	}

	private int openings;
	private String fromDate;
	private String toDate;
	private String duration;
	private List<String> responsibility;

	@Enumerated(EnumType.STRING)
	private StipendStatus stipendStatus;

	public enum StipendStatus {
		FIXED, NEGOTIABLE, PERFORMANCE_BASED, UNPAID
	}

	private Double stipendAmount;
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public InternshipType getInternshipType() {
		return internshipType;
	}
	public void setInternshipType(InternshipType internshipType) {
		this.internshipType = internshipType;
	}
	public int getOpenings() {
		return openings;
	}
	public void setOpenings(int openings) {
		this.openings = openings;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public List<String> getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(List<String> responsibility) {
		this.responsibility = responsibility;
	}
	public StipendStatus getStipendStatus() {
		return stipendStatus;
	}
	public void setStipendStatus(StipendStatus stipendStatus) {
		this.stipendStatus = stipendStatus;
	}
	public Double getStipendAmount() {
		return stipendAmount;
	}
	public void setStipendAmount(Double stipendAmount) {
		this.stipendAmount = stipendAmount;
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
