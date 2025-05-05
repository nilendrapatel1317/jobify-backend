package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.models.Internship;
import com.spring.models.Student;
import java.util.List;


@Repository
public interface InternshipRepository extends JpaRepository<Internship, String>{

	
	@Query(value = "SELECT id FROM Internship ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLastId();
	
}
