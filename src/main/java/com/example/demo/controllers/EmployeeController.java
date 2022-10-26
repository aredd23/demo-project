package com.example.demo.controllers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kafka.KafkaSender;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private KafkaSender kafkaSender;

	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Employee response = employeeService.addEmpployee(employee);
		map.put("data", response);
		map.put("message", "Record is Saved Successfully!");

		kafkaSender.send(response);
		kafkaSender.send("Record is Saved Successfully!");
		return new ResponseEntity<>(map, HttpStatus.CREATED);

	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @RequestParam Long id) {
		employee.setId(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Employee response = employeeService.addEmpployee(employee);
		map.put("data", response);
		map.put("message", "Record is Saved Successfully!");
		return new ResponseEntity<>(map, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public Optional<Employee> getEmployee(@PathVariable Long id) {
		return employeeService.getEmpployee(id);

	}

	@GetMapping("/")
	public Iterable<Employee> getAllEmployees() {
		return employeeService.getAllEmpployees();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmpployee(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

	}

	@GetMapping("search/{searchString}")
	public Iterable<Employee> searchEmployee(@PathVariable String searchString) {
		return employeeService.searcEmpployee(searchString);

	}

}
