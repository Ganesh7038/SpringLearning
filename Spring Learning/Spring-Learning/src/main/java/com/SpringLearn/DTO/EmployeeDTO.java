package com.SpringLearn.DTO;

import java.time.LocalDate;

import com.SpringLearn.Annotations.EmployeeRoleValidator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	int id;
	
	@NotBlank( message = "Employee Name cannot be blank.")
	@Size(min =3, max = 15)
	private String name;
	
	@NotNull
	@Min(value = 18 , message = "Employee age cannot be less than 18.")
	@Max(value = 60 , message = "Employee age cannot be more than 60.")
	private int age;
	
	@NotBlank
	private String gender;
	
	@NotNull
	@AssertTrue(message = "Employee must be active.")
	@JsonProperty("isActive")
	private boolean isActive;
	
	@Digits(integer = 5 , fraction =2)
	private double salary;
	
	@NotBlank
	@Email( message = "Email is not valid.")
	private String email;
	
	//@Pattern(regexp = "^(ADMIN|USER)$", message = "Role can either be USER or ADMIN.")
	
	@EmployeeRoleValidator
	private String role;
	
	@PastOrPresent
	private LocalDate dateOfJoining;

}
