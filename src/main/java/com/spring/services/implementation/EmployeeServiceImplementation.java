package com.spring.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Employee;
import com.spring.models.Internship;
import com.spring.models.Job;
import com.spring.repository.EmployeeRepository;
import com.spring.services.EmployeeService;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(String id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee updateById(String id, Employee newData) {
		if (employeeRepository.existsById(id)) {
			Employee oldData = employeeRepository.findById(id).orElse(null);
			oldData.setFirstname(newData.getFirstname());
			oldData.setLastname(newData.getLastname());
			oldData.setContact(newData.getContact());
			oldData.setCity(newData.getCity());
			oldData.setGender(newData.getGender());
			oldData.setOrganizationName(newData.getOrganizationName());

			return employeeRepository.save(oldData);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(String id) {
		boolean flag = false;
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public Employee login(Employee Employee) {
		List<Employee> Employees = employeeRepository.findByEmailAndPassword(Employee.getEmail(), Employee.getPassword());

		Employee logedInEmployee = null;

		if (!Employees.isEmpty()) {

			// Assign list Employee into logedinEmployee
			for (Employee Employee2 : Employees) {
				logedInEmployee = Employee2;
			}

			// Set Login status true in database
			logedInEmployee.setLogedIn(true);
			employeeRepository.save(logedInEmployee);

			return logedInEmployee;
		} else {
			return null;
		}
	}

	@Override
	public Employee register(Employee Employee) {
		Employee.setId(generateID());
		return employeeRepository.save(Employee);
	}

	private String generateID() {
		String lastIdStr = employeeRepository.findLastId().orElse("EMP@100");
		int lastNum = Integer.parseInt(lastIdStr.split("@")[1]);
		String newId = "EMP@" + (lastNum + 1);
		return newId;
	}

	@Override
	public boolean logout(String id) {
		Employee Employee = employeeRepository.findById(id).orElse(null);

		if (Employee != null && Employee.isLogedIn()) {

			// Set Login status false in database
			Employee.setLogedIn(false);
			employeeRepository.save(Employee);

			return true;
		} else {
			return false;
		}
	}

	@Override
	public int forgetPassword(String id, String currentPassword, Employee newEmployee) {
		Employee oldEmployee = employeeRepository.findById(id).orElse(null);

		if (oldEmployee != null) {
			if (oldEmployee.getPassword().equals(currentPassword)) {
				oldEmployee.setPassword(newEmployee.getPassword());
				employeeRepository.save(oldEmployee);
				return 1;
			} else
				return -1;
		} else
			return 0;
	}

	@Override
	public List<Internship> getAllInternship() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Internship addInternship(Internship internship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> getAllJob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job addJob(Job job) {
		// TODO Auto-generated method stub
		return null;
	}

}
