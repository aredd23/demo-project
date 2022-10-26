package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired private EmployeeRepository employeeRepository;

	@Override
	public Employee addEmpployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> getEmpployee(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Iterable<Employee> getAllEmpployees() {
		
		Pageable sortedByName = 
				  PageRequest.of(0, 10, Sort.by("name"));
		
		return employeeRepository.findAll(sortedByName);
	}

	@Override
	public void deleteEmpployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Iterable<Employee> searcEmpployee(String searchString) {
		return employeeRepository.findByName(searchString);
	}

}
