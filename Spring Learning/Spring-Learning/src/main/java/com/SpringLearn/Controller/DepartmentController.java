package com.SpringLearn.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringLearn.DTO.DepartmentDTO;
import com.SpringLearn.Service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService serv;

	
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> getAllDepartments()
	{
		return ResponseEntity.ok(serv.getAllDepartments());
		
	}
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> creatDepartment(@RequestBody @Valid DepartmentDTO department )
	{
		return new ResponseEntity<>(serv.creatDepartment(department),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable int id )
	{
		return ResponseEntity.ok(serv.getDepartmentById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable int id , @RequestBody @Valid DepartmentDTO department )
	{
		return ResponseEntity.ok(serv.updateDepartmentById(id,department));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DepartmentDTO> deleteDepartmentById(@PathVariable int id )
	{
		return ResponseEntity.ok(serv.deleteDepartmentById(id));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<DepartmentDTO> partialUpdateDepartmentById(@PathVariable int id, @RequestBody Map<Object,String> department )
	{
		return ResponseEntity.ok(serv.partialUpdateDepartmentById(id,department));
	}

}
