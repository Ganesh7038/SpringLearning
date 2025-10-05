package com.SpringLearn.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.lang.reflect.Field;


import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.SpringLearn.DTO.DepartmentDTO;
import com.SpringLearn.Entities.Department;
import com.SpringLearn.Exceptions.ResourceNotFoundException;
import com.SpringLearn.Repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final ModelMapper mp;
	private final DepartmentRepository repo;
	
	public DepartmentService(ModelMapper mp, DepartmentRepository repo) {
		super();
		this.mp = mp;
		this.repo = repo;
	}

	public List<DepartmentDTO> getAllDepartments() {
		
		List<Department> departments = repo.findAll();
		return departments.stream().map(dept -> mp.map(dept, DepartmentDTO.class)).collect(Collectors.toList());
	}

	public DepartmentDTO creatDepartment(DepartmentDTO department) {
	
		Department newDept = mp.map(department, Department.class);
		Department savedDept = repo.save(newDept);		
		return mp.map(savedDept, DepartmentDTO.class);
	}

	public DepartmentDTO getDepartmentById(int id) {

		isExistDepartment(id);
		Department savedDept = repo.findById(id).orElse(null);
		return mp.map(savedDept, DepartmentDTO.class);
	}
	
	public DepartmentDTO updateDepartmentById(int id, DepartmentDTO department) {
		
		isExistDepartment(id);
		Department newDept = mp.map(department,Department.class);
		newDept.setId(id);
		Department savedDept = repo.save(newDept);
		return mp.map(savedDept, DepartmentDTO.class);
	}

	public DepartmentDTO deleteDepartmentById(int id) {
		
		isExistDepartment(id);
		Department savedDept = repo.findById(id).orElse(null);
		repo.deleteById(id);
		return mp.map(savedDept, DepartmentDTO.class);
	}

	public DepartmentDTO partialUpdateDepartmentById(int id, Map<Object, String> department) {
		
		isExistDepartment(id);
		Department savedDept = repo.findById(id).orElse(null);
		
		department.forEach((key,value) -> {
			
			Field fieldToBeUpdated = ReflectionUtils.findField(Department.class, f -> f.getName().equals(key));
			fieldToBeUpdated.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdated,savedDept,value);
		});
		return mp.map(repo.save(savedDept), DepartmentDTO.class);
	}
	
	private void isExistDepartment(int id) {

		boolean isDeptExist = repo.existsById(id);
		if(isDeptExist == false) throw new ResourceNotFoundException("Department with ID " + id + " does not exists.");
		
	}

}
