package com.spring.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.exception.GlobalExceptionHandler;
import com.spring.models.Internship;
import com.spring.models.Job;
import com.spring.models.Student;
import com.spring.repository.InternshipRepository;
import com.spring.repository.StudentRepository;
import com.spring.services.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {

    private final InternshipRepository internshipRepository;

	private final GlobalExceptionHandler globalExceptionHandler;

	@Autowired
	private StudentRepository studentRepository;

	StudentServiceImplementation(GlobalExceptionHandler globalExceptionHandler, InternshipRepository internshipRepository) {
		this.globalExceptionHandler = globalExceptionHandler;
		this.internshipRepository = internshipRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(String id) {
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public Student updateById(String id, Student newData) {
		if (studentRepository.existsById(id)) {
			Student oldData = studentRepository.findById(id).orElse(null);
			oldData.setFirstname(newData.getFirstname());
			oldData.setLastname(newData.getLastname());
			oldData.setContact(newData.getContact());
			oldData.setCity(newData.getCity());
			oldData.setGender(newData.getGender());
			oldData.setAvatar(newData.getAvatar());

			return studentRepository.save(oldData);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public Student login(Student student) {
		List<Student> students = studentRepository.findByEmailAndPassword(student.getEmail(), student.getPassword());

		Student logedInStudent = null;

		if (!students.isEmpty()) {

			// Assign list student into logedinStudent
			for (Student student2 : students) {
				logedInStudent = student2;
			}

			// Set Login status true in database
			logedInStudent.setLogedIn(true);
			studentRepository.save(logedInStudent);

			return logedInStudent;
		} else {
			return null;
		}
	}

	@Override
	public Student register(Student student) {
		student.setId(generateID());
		return studentRepository.save(student);
	}

	private String generateID() {
		String lastIdStr = studentRepository.findLastId().orElse("STD@100");
		int lastNum = Integer.parseInt(lastIdStr.split("@")[1]);
		String newId = "STD@" + (lastNum + 1);
		return newId;
	}

	@Override
	public boolean logout(String id) {
		Student student = studentRepository.findById(id).orElse(null);

		if (student != null && student.isLogedIn()) {

			// Set Login status false in database
			student.setLogedIn(false);
			studentRepository.save(student);

			return true;
		} else {
			return false;
		}
	}

	@Override
	public int changePassword(String id, String currentPassword, Student newStudent) {
		Student oldStudent = studentRepository.findById(id).orElse(null);

		if (oldStudent != null) {
			if (oldStudent.getPassword().equals(currentPassword)) {
				oldStudent.setPassword(newStudent.getPassword());
				studentRepository.save(oldStudent);
				return 1;
			} else
				return -1;
		} else
			return 0;
	}

	@Override
	public boolean applyInternship(String studentId, String internId) {
	    Internship internship = internshipRepository.findById(internId).orElse(null);
	    Student student = studentRepository.findById(studentId).orElse(null);

	    if (internship == null || student == null) {
	        return false;
	    }

	    // Avoid duplicates
	    if (!internship.getStudents().contains(student)) {
	        internship.getStudents().add(student);
	    }

	    if (!student.getInternships().contains(internship)) {
	        student.getInternships().add(internship);
	    }
	    
	    studentRepository.save(student); 
	    
	    return true;
	}




}
