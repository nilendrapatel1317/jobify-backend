package com.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.models.Employee;
import com.spring.response.ResponseStructure;
import com.spring.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeContoller {

    @Autowired
    private EmployeeService employeeService;

    // ============================= EMPLOYEE CRUD Operations =============================

    // View all employees
    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Employee>>> viewAll() {
		List<Employee> Employees = employeeService.getAllEmployees();
		System.out.println(Employees);

		ResponseStructure<List<Employee>> response = new ResponseStructure<>();

		if (Employees != null && !Employees.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Employees fetched successfully");
			response.setData(Employees);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Employees found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

    // View employee by ID
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<Employee>> viewCurrent(@PathVariable String id) {
		Employee employees = employeeService.getEmployeeById(id);

		ResponseStructure<Employee> response = new ResponseStructure<>();

		if (employees != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Employee Fetched Successfully");
			response.setData(employees);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Employee found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Update Employee details
	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Employee>> updateById(@PathVariable String id,
			@Valid @RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateById(id, employee);

		ResponseStructure<Employee> response = new ResponseStructure<>();

		if (updateEmployee != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Employee Updated successfully");
			response.setData(updateEmployee);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Employee found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Delete Employee account (dummy mapping here; use DELETE in real app)
	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteByID(@PathVariable String id) {
		boolean deleteStatus = employeeService.deleteById(id);

		ResponseStructure<Employee> response = new ResponseStructure<>();

		if (deleteStatus) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Employee Delete successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Employee found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

    // ============================= LOGIN / REGISTER Section =============================

    // Login employee
    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<Employee>> login(@RequestBody Employee employee) {
		Employee logedInEmployee = employeeService.login(employee);

		ResponseStructure<Employee> response = new ResponseStructure<>();

		if (logedInEmployee != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Login successfully");
			response.setData(logedInEmployee);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Failed to login");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

    // Register new employee
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<Employee>> register(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = employeeService.register(employee);

		ResponseStructure<Employee> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMsg("Employee Registered Successfully");
		response.setData(savedEmployee);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

    // Logout employee
    @GetMapping("/logout/{id}")
    public ResponseEntity<ResponseStructure<Employee>> logout(@PathVariable String id) {
		boolean logoutStatus = employeeService.logout(id);

		ResponseStructure<Employee> response = new ResponseStructure<>();

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

    // Update password after forgetting
    @PostMapping("/forgetpassword/{id}")
	public ResponseEntity<ResponseStructure<Employee>> updatePassword(@PathVariable String id,
			@RequestParam String currentPassword, @RequestBody Employee employee) {

		int status = employeeService.forgetPassword(id, currentPassword, employee);

		ResponseStructure<Employee> response = new ResponseStructure<>();

		if (status == 1) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Password updated successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else if (status == -1) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMsg("Current password is incorrect");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("Employee not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

    // ============================= INTERNSHIP Section =============================

    // View all internships (admin or general)
    @GetMapping("/current/internships/all")
    public String viewAllInternships() {
        return "Fetching all available internships.";
    }

    // View internships created by current employee
    @GetMapping("/my/internships")
    public String allInternships() {
        return "Fetching internships created by the current employee.";
    }

    // View internship by ID
    @GetMapping("/internship/{id}")
    public String viewInternshipById(@PathVariable int id) {
        return "Fetching details for internship ID: " + id;
    }

    // Add new internship
    @PostMapping("/internship/create")
    public String addInternship() {
        return "New internship created successfully.";
    }

    // ============================= JOB Section =============================

    // View all jobs
    @GetMapping("/jobs/all")
    public String viewAllJobs() {
        return "Fetching all job listings.";
    }

    // View jobs posted by current employee
    @GetMapping("/my/jobs")
    public String allJobs() {
        return "Fetching job listings posted by the current employee.";
    }

    // View job by ID
    @GetMapping("/job/{id}")
    public String viewJobById(@PathVariable int id) {
        return "Fetching details for job ID: " + id;
    }

    // Add new job
    @PostMapping("/job/create")
    public String addJob() {
        return "New job created successfully.";
    }

}
