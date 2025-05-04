package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.models.Student;
import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

	
	@Query(value = "SELECT id FROM Student ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLastId();
	
	@Query(value = "SELECT s FROM Student s where s.email = :email AND s.password = :password")
	List<Student> findByEmailAndPassword(String email, String password);
  
}
