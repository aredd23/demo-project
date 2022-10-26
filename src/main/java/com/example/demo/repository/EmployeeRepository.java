package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%")
	Iterable<Employee> findByName(String searchString);

}
