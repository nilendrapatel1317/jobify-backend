package com.spring.models.resume.sections;

import com.spring.models.Resume;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Skill {
	@Id
	private String id;
	
	@ManyToOne
	private Resume resume;
}
