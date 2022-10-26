package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	public Employee addEmpployee(Employee employee);

	public Optional<Employee> getEmpployee(Long id);

	public Iterable<Employee> getAllEmpployees();

	public void deleteEmpployee(Long id);

	public Iterable<Employee> searcEmpployee(String searchString);

}
