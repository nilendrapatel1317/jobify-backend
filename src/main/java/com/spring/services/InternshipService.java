package com.spring.services;

import java.util.List;

import com.spring.models.Internship;

public interface InternshipService {
	
	public List<Internship> getAllInternships();
	
	public Internship getInternshipById(String id);
	
	public Internship create(Internship internship);
		
	public Internship updateById(String id,Internship internship);

	public boolean deleteById(String id);
		
}
