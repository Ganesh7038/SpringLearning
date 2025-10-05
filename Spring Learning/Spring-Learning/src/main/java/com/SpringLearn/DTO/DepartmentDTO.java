package com.SpringLearn.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentDTO {
		
		
		private int id;
		
		@NotBlank(message = "Department name is Required")
		@Size (min = 3, message = "Department name must have at least 3 characters")
		private String name;
		
		@Pattern(regexp = "^[A-Z]{2,5}$" , message = "Code must be 2-5 uppercase letters")
		private String code;
		
		@Size(max = 200,  message = "Description cannot exceed 200 characters")
		private String description;
		

}
