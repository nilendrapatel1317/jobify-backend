package com.spring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.models.Job;
import com.spring.models.Student;
import com.spring.response.ResponseStructure;
import com.spring.services.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

	@Autowired
	private JobService jobService;

	// ============ Job CRUD Operations =================

	// View all Jobs
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Job>>> viewAll() {
		List<Job> Jobs = jobService.getAllJobs();
		System.out.println(Jobs);

		ResponseStructure<List<Job>> response = new ResponseStructure<>();

		if (Jobs != null && !Jobs.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Jobs fetched successfully");
			response.setData(Jobs);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Jobs found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// View a Job by ID
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<Job>> viewCurrent(@PathVariable String id) {
		Job Jobs = jobService.getJobById(id);

		ResponseStructure<Job> response = new ResponseStructure<>();

		if (Jobs != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Job fetched successfully");
			response.setData(Jobs);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Job found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Job registration
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Job>> register(@Valid @RequestBody Job job) {
		Job savedJob = jobService.create(job);

		ResponseStructure<Job> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMsg("Job Created Successfully");
		response.setData(savedJob);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Update Job details
	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Job>> updateById(@PathVariable String id,
			@Valid @RequestBody Job Job) {
		Job updateJob = jobService.updateById(id, Job);

		ResponseStructure<Job> response = new ResponseStructure<>();

		if (updateJob != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Job Updated successfully");
			response.setData(updateJob);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Job found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Delete Job account (dummy mapping here; use DELETE in real app)
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Job>> deleteByID(@PathVariable String id) {
		boolean deleteStatus = jobService.deleteById(id);

		ResponseStructure<Job> response = new ResponseStructure<>();

		if (deleteStatus) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Job Delete successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Job found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Apply for an Job
	@PostMapping("/apply")
	public ResponseEntity<?> applyJob(@RequestBody Map<String, String> payload) {
		String jobId = payload.get("jobId");
		String studentId = payload.get("studentId");

		int appliedStatus = jobService.applyJob(studentId, jobId);

		ResponseStructure<Student> response = new ResponseStructure<>();

		if (appliedStatus == 1) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Successfully Applied !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else if (appliedStatus == 0) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("You Already Applied !");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else if (appliedStatus == -1) {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Job or Student Not Found !");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Failed to Apply Job !");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Apply for an Job
	@PostMapping("/withdraw")
	public ResponseEntity<?> withdrawJob(@RequestBody Map<String, String> payload) {
		String jobId = payload.get("jobId");
		String studentId = payload.get("studentId");

		int appliedStatus = jobService.withdrawJob(studentId, jobId);

		ResponseStructure<Student> response = new ResponseStructure<>();

		switch (appliedStatus) {
		case 1:
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Job withdrawn successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);

		case 0:
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMsg("You haven’t applied for this Job !");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		case -1:
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Job or student details not found !");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		default:
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMsg(
					"Something went wrong while processing your withdrawal request. Please try again later.");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/activate")
	public ResponseEntity<?> activateJob(@RequestBody Map<String, String> payload) {
		String jobId = payload.get("jobId");

		Job activateStatus = jobService.activeJob(jobId);

		ResponseStructure<Job> response = new ResponseStructure<>();

		if (activateStatus != null) {

			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Job Activated successfully !");
			response.setData(activateStatus);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {

			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMsg("Falied to activate job !");
			response.setData(activateStatus);			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/deactivate")
	public ResponseEntity<?> deActivateJob(@RequestBody Map<String, String> payload) {
		String jobId = payload.get("jobId");
		
		Job deActivateStatus = jobService.deActiveJob(jobId);
		
		ResponseStructure<Job> response = new ResponseStructure<>();
		
		if (deActivateStatus != null) {
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Job Deactivated successfully !");
			response.setData(deActivateStatus);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMsg("Falied to deactivate job !");
			response.setData(deActivateStatus);			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}

}
