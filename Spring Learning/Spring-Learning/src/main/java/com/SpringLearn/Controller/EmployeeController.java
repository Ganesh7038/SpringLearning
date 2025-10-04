package com.SpringLearn.Controller;

import java.util.List;
import java.util.Map;

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

import com.SpringLearn.DTO.EmployeeDTO;
import com.SpringLearn.Exceptions.ResourceNotFoundException;
import com.SpringLearn.Service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	final private EmployeeService serv;
	
	public EmployeeController(EmployeeService serv) {
		super();
		this.serv = serv;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getEmployees()
	{
		return ResponseEntity.ok(serv.getEmployees());
	}
	
	@GetMapping("{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable (name = "employeeId") int id )
	{

		return ResponseEntity.ok(serv.getEmployeeById(id));
	}
	

	@PostMapping("/object")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO eml )
	{
		return new ResponseEntity<>(serv.addEmployee(eml),HttpStatus.CREATED);
	}
	
	@PutMapping("/{employeeId}")
	public EmployeeDTO updateEmployeeByID(@PathVariable int employeeId,@RequestBody EmployeeDTO empl)
	{
		return serv.updateEmployeeByID(employeeId,empl);
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable int employeeId )
	{
		return  ResponseEntity.ok(serv.deleteEmployeeById(employeeId));
	}
	
	@PatchMapping("/{employeeId}")
	public EmployeeDTO partialUpdateEmployeeById(@PathVariable int employeeId, @RequestBody Map<String,Object> updates )
	{
		return serv.partialUpdateEmployeeById(employeeId,updates);
	}

}


























