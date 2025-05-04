package com.spring.services;

import java.util.List;

import com.spring.models.Internship;
import com.spring.models.Job;
import com.spring.models.Student;

public interface StudentService {
	
	public List<Student> getAllStudents();
	
	public Student getStudentById(String id);
	
	public Student updateById(String id,Student student);

	public boolean deleteById(String id);
	
	public Student login(Student student);
	
	public Student register(Student student);
	
	public boolean logout(String id);
	
	public int forgetPassword(String id,String currentPassword,Student student);
	
	public List<Internship> getAllInternship();
	
	public Internship addInternship(Internship internship);
	
	public List<Job> getAllJob();
	
	public Job addJob(Job job);	
	
}
