package com.SpringLearn.Service;

import com.SpringLearn.DTO.EmployeeDTO;
import com.SpringLearn.Entities.*;
import com.SpringLearn.Exceptions.ResourceNotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<EmployeeDTO> getEmployees(String sortBy, int pageNum)
	{
		//Sort sort = Sort.by(sortBy).descending();
		
		Pageable  pageable = PageRequest.of(pageNum,2, Sort.by(sortBy).ascending());
		Page<Employee> empPage =  repo.findAll(pageable);
		return empPage.map(empl -> mp.map(empl, EmployeeDTO.class));
	}
	
	public EmployeeDTO getEmployeeById(int id)
	{
		isEmployeeExists(id);
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

	public EmployeeDTO  deleteEmployeeById(int employeeId) {
		
		isEmployeeExists(employeeId);
		Employee getEmpl = repo.findById(employeeId).orElse(null);
		repo.deleteById(employeeId);
		return  mp.map(getEmpl, EmployeeDTO.class);
		
	}

	public EmployeeDTO partialUpdateEmployeeById(int employeeId, Map<String, Object> updates) {

		isEmployeeExists(employeeId);
		Employee savedEmpl = repo.findById(employeeId).get();
		updates.forEach((key,value) -> {
			
			Field fieldToBeUpdated = ReflectionUtils.findField(Employee.class, f -> f.getName().equals(key));
			fieldToBeUpdated.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdated, savedEmpl, value);
			
		});
		return mp.map(repo.save(savedEmpl), EmployeeDTO.class);
	}
	
	public List<EmployeeDTO> findByGenderOrderBySalary(String gender)
	{
		return repo.findByGenderOrderBySalaryDesc(gender).stream().map(emp -> mp.map(emp, EmployeeDTO.class)).toList();
	}
	
	void isEmployeeExists(int employeeId)
	{
		if(repo.existsById(employeeId)== false) throw new ResourceNotFoundException("Employee not found with ID: "+ employeeId );
	}

}






















