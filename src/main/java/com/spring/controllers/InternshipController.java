package com.spring.controllers;

import java.util.List;

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

import com.spring.models.Internship;
import com.spring.response.ResponseStructure;
import com.spring.services.InternshipService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipController {

	@Autowired
	private InternshipService internshipService;

	// ============ Internship CRUD Operations =================

	// View all Internships
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Internship>>> viewAll() {
		List<Internship> Internships = internshipService.getAllInternships();
		System.out.println(Internships);

		ResponseStructure<List<Internship>> response = new ResponseStructure<>();

		if (Internships != null && !Internships.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Internships fetched successfully");
			response.setData(Internships);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Internships found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// View a Internship by ID
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<Internship>> viewCurrent(@PathVariable String id) {
		Internship Internships = internshipService.getInternshipById(id);

		ResponseStructure<Internship> response = new ResponseStructure<>();

		if (Internships != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Internship fetched successfully");
			response.setData(Internships);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Internship found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Internship registration
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Internship>> register(@Valid @RequestBody Internship Internship) {
		Internship savedInternship = internshipService.create(Internship);

		ResponseStructure<Internship> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMsg("Internship Created Successfully");
		response.setData(savedInternship);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Update Internship details
	@PostMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Internship>> updateById(@PathVariable String id,
			@Valid @RequestBody Internship Internship) {
		Internship updateInternship = internshipService.updateById(id, Internship);

		ResponseStructure<Internship> response = new ResponseStructure<>();

		if (updateInternship != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Internship Updated successfully");
			response.setData(updateInternship);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Internship found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	// Delete Internship account (dummy mapping here; use DELETE in real app)
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Internship>> deleteByID(@PathVariable String id) {
		boolean deleteStatus = internshipService.deleteById(id);

		ResponseStructure<Internship> response = new ResponseStructure<>();

		if (deleteStatus) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMsg("Internship Delete successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMsg("No Internship found with id : " + id);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

}
