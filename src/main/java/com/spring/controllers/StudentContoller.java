package com.spring.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.models.Student;
import com.spring.services.StudentService;

import jakarta.validation.Valid;

import com.spring.response.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentContoller {

	@Autowired
	private StudentService studentService;

	// ============ STUDENT CRUD Operations =============

	// View all students
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Student>>> viewAll() {
		List<Student> students = studentService.getAllStudents();
		System.out.println(students);

		ResponseStructure<List<Student>> response = new ResponseStructure<>();

		if (students != null && !students.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Students fetched successfully");
			response.setData(students);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Students found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// View a student by ID
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<Student>> viewCurrent(@PathVariable String id) {
		Student students = studentService.getStudentById(id);

		ResponseStructure<Student> response = new ResponseStructure<>();

		if (students != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Student fetched successfully");
			response.setData(students);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Student found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Update student details
	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Student>> updateById(@PathVariable String id,
			@Valid @RequestBody Student student) {
		Student updateStudent = studentService.updateById(id, student);

		ResponseStructure<Student> response = new ResponseStructure<>();

		if (updateStudent != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Student Updated successfully");
			response.setData(updateStudent);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Student found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Delete student account (dummy mapping here; use DELETE in real app)
	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Student>> deleteByID(@PathVariable String id) {
		boolean deleteStatus = studentService.deleteById(id);

		ResponseStructure<Student> response = new ResponseStructure<>();

		if (deleteStatus) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Student Delete successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Student found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// =========== LOGIN / REGISTER Section =================

	// Student login
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<Student>> login(@RequestBody Student student) {
		Student logedInStudent = studentService.login(student);

		ResponseStructure<Student> response = new ResponseStructure<>();

		if (logedInStudent != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Login successfully");
			response.setData(logedInStudent);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Failed to login");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Student registration
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<Student>> register(@Valid @RequestBody Student student) {
		Student savedStudent = studentService.register(student);

		ResponseStructure<Student> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMsg("Student registered successfully");
		response.setData(savedStudent);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Logout
	@GetMapping("/logout/{id}")
	public ResponseEntity<ResponseStructure<Student>> logout(@PathVariable String id) {
		boolean logoutStatus = studentService.logout(id);

		ResponseStructure<Student> response = new ResponseStructure<>();

		if (logoutStatus) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Logout successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Failed to Logout ! Please try after Login ! ");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Submit new password
	@PostMapping("/changepassword/{id}")
	public ResponseEntity<ResponseStructure<Object>> updatePassword(@PathVariable String id,
			@RequestParam String currentPassword, @Valid @RequestBody Student student) {

		int status = studentService.changePassword(id, currentPassword, student);

		ResponseStructure<Object> response = new ResponseStructure<>();

		if (status == 1) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Password updated successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else if (status == -1) {
			Map<String, String> errorData = new HashMap<>();
	        errorData.put("currentPassword", "Current Password is incorrect");
	        errorData.put("newPassword", "Password must be more than 6 characters");

	        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        response.setMsg("Validation failed");
	        response.setData(errorData);

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Student not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// ============ INTERNSHIP Section =======================

	// View all internships
	@GetMapping("/internships")
	public String internships() {
		return "Fetching list of available internships.";
	}

	// Apply for an internship
	@GetMapping("/apply/internship/{id}")
	public String applyInternship(@PathVariable String id) {
		return "Applying for internship with ID: " + id;
	}

	// ============ JOB Section ================

	// View all jobs
	@GetMapping("/jobs")
	public String jobs() {
		return "Fetching list of available jobs.";
	}

	// Apply for a job
	@GetMapping("/apply/jobs/{id}")
	public String applyJobs(@PathVariable int id) {
		return "Applying for job with ID: " + id;
	}

}
