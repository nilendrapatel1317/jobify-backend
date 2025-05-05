package com.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Employee {

	@Id
	private String id;

	@Size(min = 3, message = "First name must be more than 3 characters")
	private String firstname;

	@Size(min = 3, max = 10, message = "Last name must be between 3 and 10 characters")
	private String lastname;

	@Size(min = 10, max = 10, message = "Contact must be 10 digits")
	private String contact;

	@Size(min = 3, max = 10, message = "City must be between 3 and 10 characters")
	private String city;

	@Pattern(regexp = "Male|Female|Others", message = "Gender must be Male, Female, or Others")
	private String gender;

	@Email(message = "Please provide a valid email")
	@Column(unique = true)
	private String email;

	// @NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be more than 6 characters")
	private String password;

	@Size(min = 4, message = "Organization name must be more than 4 characters")
	private String organizationName;

	@Embedded
	private OrganizationLogo organizationLogo = new OrganizationLogo();

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Internship> internships; // Stores ObjectIds as strings

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Job> jobs;

	private boolean isLogedIn = false;

	public boolean isLogedIn() {
		return isLogedIn;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public OrganizationLogo getOrganizationLogo() {
		return organizationLogo;
	}

	public void setOrganizationLogo(OrganizationLogo organizationLogo) {
		this.organizationLogo = organizationLogo;
	}


	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}


	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

}

@Embeddable
class OrganizationLogo {

	private String fileId = "abc123";

	private String url = "https://images.unsplash.com/photo-1682685797886-79020b7462a4";

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}