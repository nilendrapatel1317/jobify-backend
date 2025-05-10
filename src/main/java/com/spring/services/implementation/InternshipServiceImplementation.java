package com.spring.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exception.GlobalExceptionHandler;
import com.spring.models.Internship;
import com.spring.models.Student;
import com.spring.repository.InternshipRepository;
import com.spring.repository.StudentRepository;
import com.spring.services.InternshipService;

@Service
public class InternshipServiceImplementation implements InternshipService {


	@Autowired
	private InternshipRepository internshipRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Internship> getAllInternships() {
		return internshipRepository.findAll();
	}

	@Override
	public Internship getInternshipById(String id) {
		return internshipRepository.findById(id).orElse(null);
	}

	@Override
	public Internship updateById(String id, Internship newData) {
		if (internshipRepository.existsById(id)) {
			Internship oldData = internshipRepository.findById(id).orElse(null);
			oldData.setProfile(newData.getProfile());
			oldData.setOpenings(newData.getOpenings());
			oldData.setStipendAmount(newData.getStipendAmount());
			oldData.setStipendStatus(newData.getStipendStatus());
			oldData.setFromDate(newData.getFromDate());
			oldData.setToDate(newData.getToDate());
			oldData.setDuration(newData.getDuration());
			oldData.setSkills(newData.getSkills());
			oldData.setResponsibility(newData.getResponsibility());
			oldData.setPerks(newData.getPerks());
			oldData.setAssessments(newData.getAssessments());
			oldData.setInternshipType(newData.getInternshipType());

			return internshipRepository.save(oldData);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (internshipRepository.existsById(id)) {
			internshipRepository.deleteById(id);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public Internship create(Internship internship) {
		internship.setId(generateID());
		return internshipRepository.save(internship);
	}

	private String generateID() {
		String lastIdStr = internshipRepository.findLastId().orElse("INT@100");
		int lastNum = Integer.parseInt(lastIdStr.split("@")[1]);
		String newId = "INT@" + (lastNum + 1);
		return newId;
	}

	@Override
	public int applyInternship(String studentId, String internshipId) {
		Internship internship = internshipRepository.findById(internshipId).orElse(null);
		Student student = studentRepository.findById(studentId).orElse(null);

		if (internship == null || student == null) {
			return -1;
		} else if (internship.getStudents().contains(student) || student.getInternships().contains(internship)) {
			return 0;
		} else {
			// Avoid duplicates
			if (!internship.getStudents().contains(student)) {
				internship.getStudents().add(student);
			}

			if (!student.getInternships().contains(internship)) {
				student.getInternships().add(internship);
			}

			internshipRepository.save(internship);
			studentRepository.save(student);

			return 1;
		}

	}

	@Override
	public int withdrawInternship(String studentId, String internshipId) {
		Internship internship = internshipRepository.findById(internshipId).orElse(null);
		Student student = studentRepository.findById(studentId).orElse(null);

		if (internship == null || student == null) {
			return -1;
		} else if (!internship.getStudents().contains(student) || !student.getInternships().contains(internship)) {
			return 0;
		} else {
			// Avoid duplicates
			if (internship.getStudents().contains(student)) {
				internship.getStudents().remove(student);
			}

			if (student.getInternships().contains(internship)) {
				student.getInternships().remove(internship);
			}

			studentRepository.save(student);

			return 1;
		}

	}

	@Override
	public Internship activeInternship(String id) {
		Internship internship = internshipRepository.findById(id).orElse(null);
		if(internship != null) {
			internship.setIsActive(true);
			internshipRepository.save(internship);
			return internship;
		}
		else {
			return internship;
		}
	}

	@Override
	public Internship deActiveInternship(String id) {
		Internship internship = internshipRepository.findById(id).orElse(null);
		if(internship != null) {
			internship.setIsActive(false);
			internshipRepository.save(internship);
			return internship;
		}
		else {
			return internship;
		}
	}

}
