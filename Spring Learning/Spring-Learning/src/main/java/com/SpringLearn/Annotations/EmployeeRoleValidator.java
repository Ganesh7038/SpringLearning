package com.SpringLearn.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmployeeRoleValidate.class})

public @interface EmployeeRoleValidator {
	
	String message() default "Role can either be USER or ADMIN. Please enter correct role.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
