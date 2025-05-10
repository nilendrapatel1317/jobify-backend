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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Internship {

	@Id
	private String id;

	@ManyToOne
	private Employee employee;

	@ManyToMany(mappedBy = "internships")
	private List<Student> students = new ArrayList<>();

	@Size(min = 5, message = "Profile name must be more than 5 characters")
	private String profile;

	@Size(min = 1, message = "At least one skill is required")
	private List<String> skills;

	@Pattern(regexp = "Onsite|Remote|Hybrid", message = "Select any one Internship Type")
	private String internshipType;

	@Min(value = 1, message = "Openings must be at least 1")
	private int openings;

	@NotBlank(message = "From date is required")
	private String fromDate;

	@NotBlank(message = "To date is required")
	private String toDate;

	@NotBlank(message = "Duration is required")
	private String duration;

	@Size(min = 1, message = "At least one responsibility is required")
	private List<String> responsibility;

	@Pattern(regexp = "Fixed|Negotiable|Performance_Based|Unpaid", message = "Select any one Stipend Status")
	private String stipendStatus;

	@DecimalMin(value = "0.0", inclusive = true, message = "Stipend amount cannot be negative")
	private Double stipendAmount;

	@Size(min = 1, message = "At least one perk is required")
	private List<String> perks;

	@Size(min = 1, message = "At least one assessments is required")
	private List<String> assessments;

	private boolean isActive;

	private LocalDateTime postedAt;

	@PrePersist
	public void onCreate() {
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

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getInternshipType() {
		return internshipType;
	}

	public void setInternshipType(String internshipType) {
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

	public String getStipendStatus() {
		return stipendStatus;
	}

	public void setStipendStatus(String stipendStatus) {
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

	public List<String> getAssessments() {
		return assessments;
	}

	public void setAssessments(List<String> assessments) {
		this.assessments = assessments;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}
}
