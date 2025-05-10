package com.spring.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Job;
import com.spring.models.Student;
import com.spring.repository.JobRepository;
import com.spring.repository.StudentRepository;
import com.spring.services.JobService;

@Service
public class JobServiceImplementation implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

	@Override
	public Job getJobById(String id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public Job updateById(String id, Job newData) {
		if (jobRepository.existsById(id)) {
			Job oldData = jobRepository.findById(id).orElse(null);
			oldData.setProfile(newData.getProfile());
			oldData.setCompanyName(newData.getCompanyName());
			oldData.setOpenings(newData.getOpenings());
			oldData.setJobType(newData.getJobType());
			oldData.setStartDate(newData.getStartDate());
			oldData.setExperience(newData.getExperience());
			oldData.setLocation(newData.getLocation());
			oldData.setSalaryStatus(newData.getSalaryStatus());
			oldData.setSalary(newData.getSalary());
			oldData.setSkills(newData.getSkills());
			oldData.setResponsibility(newData.getResponsibility());
			oldData.setPerks(newData.getPerks());
			oldData.setAssessments(newData.getAssessments());

			return jobRepository.save(oldData);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag;
		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public Job create(Job Job) {
		Job.setId(generateID());
		return jobRepository.save(Job);
	}

	private String generateID() {
		String lastIdStr = jobRepository.findLastId().orElse("JOB@100");
		int lastNum = Integer.parseInt(lastIdStr.split("@")[1]);
		String newId = "JOB@" + (lastNum + 1);
		return newId;
	}

	@Override
	public int applyJob(String studentId, String jobId) {
		Job job = jobRepository.findById(jobId).orElse(null);
		Student student = studentRepository.findById(studentId).orElse(null);

		if (job == null || student == null) {
			return -1;
		} else if (job.getStudents().contains(student) || student.getJobs().contains(job)) {
			return 0;
		} else {
			// Avoid duplicates
			if (!job.getStudents().contains(student)) {
				job.getStudents().add(student);
			}

			if (!student.getJobs().contains(job)) {
				student.getJobs().add(job);
			}

			jobRepository.save(job);
			studentRepository.save(student);

			return 1;
		}

	}

	@Override
	public int withdrawJob(String studentId, String jobId) {
		Job job = jobRepository.findById(jobId).orElse(null);
		Student student = studentRepository.findById(studentId).orElse(null);

		if (job == null || student == null) {
			return -1;
		} else if (!job.getStudents().contains(student) || !student.getJobs().contains(job)) {
			return 0;
		} else {
			// Avoid duplicates
			if (job.getStudents().contains(student)) {
				job.getStudents().remove(student);
			}

			if (student.getJobs().contains(job)) {
				student.getJobs().remove(job);
			}

			jobRepository.save(job);
			studentRepository.save(student);

			return 1;
		}

	}
	
	@Override
	public Job activeJob(String id) {
		Job job = jobRepository.findById(id).orElse(null);
		if(job != null) {
			job.setIsActive(true);
			jobRepository.save(job);
			return job;
		}
		else {
			return job;
		}
	}

	@Override
	public Job deActiveJob(String id) {
		Job job = jobRepository.findById(id).orElse(null);
		if(job != null) {
			job.setIsActive(false);
			jobRepository.save(job);
			return job;
		}
		else {
			return job;
		}
	}



}
