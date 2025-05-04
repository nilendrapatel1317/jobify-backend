package com.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

import com.spring.models.resume.sections.Award;
import com.spring.models.resume.sections.Course;
import com.spring.models.resume.sections.Education;
import com.spring.models.resume.sections.Experience;
import com.spring.models.resume.sections.Interest;
import com.spring.models.resume.sections.Project;
import com.spring.models.resume.sections.Skill;
import com.spring.models.resume.sections.ResumeInternship;
import com.spring.models.resume.sections.Language;

@Entity
public class Resume {

	@Id
	private String id;

	@OneToOne
	private Student student;

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Education> educations; // List of education entries

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Experience> experiences; // List of job experiences

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Project> projects; // List of projects

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Skill> skills; // List of skills

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Interest> interests; // List of interests

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<ResumeInternship> internships; // List of internships

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Course> courses; // List of courses

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Award> awards; // List of awards

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
	private List<Language> languages ; // List of languages

//	 Getter and Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public List<ResumeInternship> getInternships() {
		return internships;
	}

	public void setInternships(List<ResumeInternship> internships) {
		this.internships = internships;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
}
