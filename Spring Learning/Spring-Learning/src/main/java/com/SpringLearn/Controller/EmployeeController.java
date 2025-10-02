package com.SpringLearn.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringLearn.DTO.EmployeeDTO;
import com.SpringLearn.Service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	final private EmployeeService serv;
	
	public EmployeeController(EmployeeService serv) {
		super();
		this.serv = serv;
	}

	@GetMapping
	public List<EmployeeDTO> getEmployees()
	{
		return serv.getEmployees();
	}
	
	@GetMapping("{employeeId}")
	public EmployeeDTO getEmployeeById(@PathVariable (name = "employeeId") int id )
	{
		
		return serv.getEmployeeById(id);
	}
	

	@PostMapping("/object")
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO eml )
	{
		return serv.addEmployee(eml);
	}

}
