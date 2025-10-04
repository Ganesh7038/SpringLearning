package com.SpringLearn.Exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> globalExceptionHandler(Exception exception)
	{
		ApiError apiError = ApiError.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(exception.getMessage())
				.build();
		
		return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception)
	{
		
		List<String> errors = exception
				.getBindingResult()
				.getAllErrors()
				.stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		
		ApiError apiError = ApiError.builder()
				.status(HttpStatus.BAD_REQUEST)
				.message("Input validation failed")
				.error(errors)
				.build();
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> resourceNotFoundHandeler(ResourceNotFoundException exception)
	{
		ApiError apiError = ApiError.builder()
				.status(HttpStatus.NOT_FOUND)
				.message(exception.getMessage())
				.build();
		
		return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

	}
	
	

}
