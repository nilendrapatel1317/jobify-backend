package com.spring.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeContoller {

    // ============================= EMPLOYEE CRUD Operations =============================

    // View all employees
    @GetMapping("/all")
    public String viewAll() {
        return "Fetching all employees.";
    }

    // View employee by ID
    @GetMapping("/current/{id}")
    public String viewCurrent(@PathVariable int id) {
        return "Fetching details of employee with ID: " + id;
    }

    // Update employee details by ID
    @PostMapping("/update/{id}")
    public String updateById(@PathVariable int id) {
        return "Updating employee with ID: " + id;
    }

    // Delete employee account (or simulate logout)
    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable int id) {
        return "Deleting employee with ID: " + id;
    }

    // ============================= LOGIN / REGISTER Section =============================

    // Login employee
    @PostMapping("/login")
    public String login() {
        return "Employee login request received.";
    }

    // Register new employee
    @PostMapping("/register")
    public String register() {
        return "New employee registration request received.";
    }

    // Logout employee
    @GetMapping("/logout")
    public String logout() {
        return "Employee logged out successfully.";
    }

    // Update password after forgetting
    @PostMapping("/forgetpassword")
    public String updatePassword() {
        return "Password updated successfully.";
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
