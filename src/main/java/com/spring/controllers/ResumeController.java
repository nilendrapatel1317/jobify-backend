package com.spring.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    // Resume View
    @GetMapping("/viewResume")
    public String viewResume() {
        return "Viewing Resume";
    }

    @GetMapping("/curr-resume")
    public String currentResume() {
        return "Current Resume";
    }

    // Education
    @PostMapping("/add-edu")
    public String addEducation() {
        return "Education added";
    }

    @PostMapping("/edit-edu/{id}")
    public String editEducation(@PathVariable int id) {
        return "Education with ID " + id + " updated";
    }

    @GetMapping("/delete-edu/{id}")
    public String deleteEducation(@PathVariable int id) {
        return "Education with ID " + id + " deleted";
    }

    // Job
    @PostMapping("/add-job")
    public String addJob() {
        return "Job added";
    }

    @PostMapping("/edit-job/{id}")
    public String editJob(@PathVariable int id) {
        return "Job with ID " + id + " updated";
    }

    @GetMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable int id) {
        return "Job with ID " + id + " deleted";
    }

    // Project
    @PostMapping("/add-project")
    public String addProject() {
        return "Project added";
    }

    @PostMapping("/edit-project/{id}")
    public String editProject(@PathVariable int id) {
        return "Project with ID " + id + " updated";
    }

    @GetMapping("/delete-project/{id}")
    public String deleteProject(@PathVariable int id) {
        return "Project with ID " + id + " deleted";
    }

    // Skill
    @PostMapping("/add-skill")
    public String addSkill() {
        return "Skill added";
    }

    @PostMapping("/edit-skill/{id}")
    public String editSkill(@PathVariable int id) {
        return "Skill with ID " + id + " updated";
    }

    @GetMapping("/delete-skill/{id}")
    public String deleteSkill(@PathVariable int id) {
        return "Skill with ID " + id + " deleted";
    }

    // Interest
    @PostMapping("/add-interest")
    public String addInterest() {
        return "Interest added";
    }

    @PostMapping("/edit-interest/{id}")
    public String editInterest(@PathVariable int id) {
        return "Interest with ID " + id + " updated";
    }

    @GetMapping("/delete-interest/{id}")
    public String deleteInterest(@PathVariable int id) {
        return "Interest with ID " + id + " deleted";
    }

    // Internship
    @PostMapping("/add-internship")
    public String addInternship() {
        return "Internship added";
    }

    @PostMapping("/edit-internship/{id}")
    public String editInternship(@PathVariable int id) {
        return "Internship with ID " + id + " updated";
    }

    @GetMapping("/delete-internship/{id}")
    public String deleteInternship(@PathVariable int id) {
        return "Internship with ID " + id + " deleted";
    }

    // Course
    @PostMapping("/add-course")
    public String addCourse() {
        return "Course added";
    }

    @PostMapping("/edit-course/{id}")
    public String editCourse(@PathVariable int id) {
        return "Course with ID " + id + " updated";
    }

    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable int id) {
        return "Course with ID " + id + " deleted";
    }

    // Award
    @PostMapping("/add-award")
    public String addAward() {
        return "Award added";
    }

    @PostMapping("/edit-award/{id}")
    public String editAward(@PathVariable int id) {
        return "Award with ID " + id + " updated";
    }

    @GetMapping("/delete-award/{id}")
    public String deleteAward(@PathVariable int id) {
        return "Award with ID " + id + " deleted";
    }

    // Role
    @PostMapping("/add-role")
    public String addRole() {
        return "Role added";
    }

    @PostMapping("/edit-role/{id}")
    public String editRole(@PathVariable int id) {
        return "Role with ID " + id + " updated";
    }

    @GetMapping("/delete-role/{id}")
    public String deleteRole(@PathVariable int id) {
        return "Role with ID " + id + " deleted";
    }
}
