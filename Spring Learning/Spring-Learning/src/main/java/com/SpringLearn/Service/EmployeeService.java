package com.SpringLearn.Service;

import com.SpringLearn.DTO.EmployeeDTO;
import com.SpringLearn.Entities.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
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
		Optional<Employee> employee = repo.findById(id);
		return employee.map(emp -> mp.map(emp, EmployeeDTO.class)).orElse(null);
	}

	public EmployeeDTO addEmployee(EmployeeDTO eml) {
		
		Employee emp = mp.map(eml, Employee.class);
		Employee savedEmpl = repo.save(emp);
		return mp.map(savedEmpl, EmployeeDTO.class);
	}

	public EmployeeDTO updateEmployeeByID(int employeeId, EmployeeDTO empl) {

		Employee empNew = mp.map(empl, Employee.class);
		Employee getEmpl = repo.findById(employeeId).orElse(null);
		if(getEmpl == null)
		{
			Employee emplSaved = repo.save(empNew);
			return mp.map(emplSaved, EmployeeDTO.class);
		}
		empNew.setId(employeeId);
		Employee emplSaved = repo.save(empNew);
		return mp.map(emplSaved, EmployeeDTO.class);
	}

	public String  deleteEmployeeById(int employeeId) {
		
		boolean exists = repo.existsById(employeeId);
		if(!exists)
		{
			return "Employee not found !";
		}
		
		repo.deleteById(employeeId);
		
		return "Employee Deleted";
		
	}

	public EmployeeDTO partialUpdateEmployeeById(int employeeId, Map<String, Object> updates) {

		boolean exists = repo.existsById(employeeId);
		if(!exists) return null;
		
		Employee savedEmpl = repo.findById(employeeId).get();
		updates.forEach((key,value) -> {
			
			Field fieldToBeUpdated = ReflectionUtils.findField(Employee.class, f -> f.getName().equals(key));
			fieldToBeUpdated.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdated, savedEmpl, value);
			
		});
		return mp.map(repo.save(savedEmpl), EmployeeDTO.class);
	}

}






















