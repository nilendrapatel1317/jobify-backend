package com.spring.services;

import java.util.List;

import com.spring.models.Employee;
import com.spring.models.Internship;
import com.spring.models.Job;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(String id);
	
	public Employee updateById(String id,Employee Employee);

	public boolean deleteById(String id);
	
	public Employee login(Employee Employee);
	
	public Employee register(Employee Employee);
	
	public boolean logout(String id);
	
	public int forgetPassword(String id,String currentPassword,Employee Employee);
	
	public List<Internship> getAllInternship();
	
	public Internship addInternship(Internship internship);
	
	public List<Job> getAllJob();
	
	public Job addJob(Job job);	
	
}
