package com.spring.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // ============================= ADMIN CRUD Operations =============================

    // View all Admin
    @GetMapping("/all")
    public String viewAll() {
        return "Fetching all admin profiles.";
    }

    // View a admin by ID
    @GetMapping("/current/{id}")
    public String viewCurrent(@PathVariable int id) {
        return "Fetching admin details for ID: " + id;
    }

    // Update admin details
    @PostMapping("/update/{id}")
    public String updateById(@PathVariable int id) {
        return "Updating admin profile with ID: " + id;
    }

    // Delete admin account (dummy mapping here; use DELETE in real app)
    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable int id) {
        return "Deleting admin account with ID: " + id;
    }

    // ============================= LOGIN / REGISTER Section =============================

    // Admin login
    @PostMapping("/login")
    public String login() {
        return "Admin login initiated.";
    }

    // Admin registration
    @PostMapping("/register")
    public String register() {
        return "Admin registration request received.";
    }

    // Logout
    @GetMapping("/logout")
    public String logout() {
        return "Admin logged out successfully.";
    }

    // Request password reset
    @GetMapping("/forgetpassword")
    public String forgetPassword() {
        return "Password reset request received.";
    }

    // Submit new password
    @PostMapping("/forgetpassword")
    public String updatePassword() {
        return "Password updated successfully.";
    }
}
