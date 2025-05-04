package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.models.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

	
	@Query(value = "SELECT id FROM Employee ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLastId();
	
	@Query(value = "SELECT e FROM Employee e where e.email = :email AND e.password = :password")
	List<Employee> findByEmailAndPassword(String email, String password);
  
}
