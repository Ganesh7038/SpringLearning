package com.SpringLearn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringLearn.Entities.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	
	
	

}
