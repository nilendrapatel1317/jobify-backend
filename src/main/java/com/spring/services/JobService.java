package com.spring.services;

import java.util.List;

import com.spring.models.Job;

public interface JobService {
	
	public List<Job> getAllJobs();
	
	public Job getJobById(String id);
	
	public Job create(Job job);
		
	public Job updateById(String id,Job job);

	public boolean deleteById(String id);

	public int applyJob(String studentId, String jobId);

	public int withdrawJob(String studentId, String jobId);
	
	public Job activeJob(String id);

	public Job deActiveJob(String id);
		
}
