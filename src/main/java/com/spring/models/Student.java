package com.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Student {

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

	@Size(min = 6, message = "Password must be more than 6 characters")
	private String password;

	@Embedded
	private Avatar avatar = new Avatar();

	@OneToMany
	private List<Internship> internships; 

	@OneToMany
	private List<Job> jobs;
	
	@OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
	private Resume resume;
	
	private boolean isLogedIn = false;

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

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public List<Internship> getInternships() {
		return internships;
	}

	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public boolean isLogedIn() {
		return isLogedIn;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	} 

}

@Embeddable
class Avatar {
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
