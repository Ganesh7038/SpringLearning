package com.SpringLearn.Annotations;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeRoleValidate implements ConstraintValidator <EmployeeRoleValidator,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if( value == null) return false;
		List<String> role = List.of("USER","ADMIN");
		return role.contains(value);
	}

}
