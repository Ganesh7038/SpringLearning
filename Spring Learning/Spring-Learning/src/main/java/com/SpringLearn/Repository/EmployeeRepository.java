package com.SpringLearn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringLearn.Entities.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	List<Employee> findByGenderOrderBySalaryDesc(String gender);
}
