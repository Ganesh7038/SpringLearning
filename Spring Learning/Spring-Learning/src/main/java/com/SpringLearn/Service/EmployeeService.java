package com.SpringLearn.Service;

import com.SpringLearn.DTO.EmployeeDTO;
import com.SpringLearn.Entities.*;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.SpringLearn.Repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	final private EmployeeRepository repo;
	final private ModelMapper mp;

	public EmployeeService(EmployeeRepository repo,ModelMapper mp) {
		super();
		this.repo = repo;
		this.mp = mp;
	}
	
	public List<EmployeeDTO> getEmployees()
	{
		List<Employee> empList =  repo.findAll();
		return empList.stream().map(empl -> mp.map(empl, EmployeeDTO.class)).collect(Collectors.toList());
	}
	
	public EmployeeDTO getEmployeeById(int id)
	{
		Employee employee = repo.findById(id).orElse(null);
		return mp.map(employee, EmployeeDTO.class);
	}

	public EmployeeDTO addEmployee(EmployeeDTO eml) {
		
		Employee emp = mp.map(eml, Employee.class);
		Employee savedEmpl = repo.save(emp);
		return mp.map(savedEmpl, EmployeeDTO.class);
	}

}
