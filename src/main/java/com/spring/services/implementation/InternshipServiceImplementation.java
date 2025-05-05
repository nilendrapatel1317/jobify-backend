package com.spring.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exception.GlobalExceptionHandler;
import com.spring.models.Internship;
import com.spring.repository.InternshipRepository;
import com.spring.services.InternshipService;

@Service
public class InternshipServiceImplementation implements InternshipService {

	private final GlobalExceptionHandler globalExceptionHandler;

	@Autowired
	private InternshipRepository InternshipRepository;

	InternshipServiceImplementation(GlobalExceptionHandler globalExceptionHandler) {
		this.globalExceptionHandler = globalExceptionHandler;
	}

	@Override
	public List<Internship> getAllInternships() {
		return InternshipRepository.findAll();
	}

	@Override
	public Internship getInternshipById(String id) {
		return InternshipRepository.findById(id).orElse(null);
	}

	@Override
	public Internship updateById(String id, Internship newData) {
		if (InternshipRepository.existsById(id)) {
			Internship oldData = InternshipRepository.findById(id).orElse(null);
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
			oldData.setAssesments(newData.getAssesments());
			oldData.setInternshipType(newData.getInternshipType());
			
			return InternshipRepository.save(oldData);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (InternshipRepository.existsById(id)) {
			InternshipRepository.deleteById(id);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	

	@Override
	public Internship create(Internship internship) {
		internship.setId(generateID());
		return InternshipRepository.save(internship);
	}

	private String generateID() {
		String lastIdStr = InternshipRepository.findLastId().orElse("INT@100");
		int lastNum = Integer.parseInt(lastIdStr.split("@")[1]);
		String newId = "INT@" + (lastNum + 1);
		return newId;
	}

}
