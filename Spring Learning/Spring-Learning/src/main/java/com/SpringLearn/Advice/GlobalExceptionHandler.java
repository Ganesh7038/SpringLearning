package com.SpringLearn.Advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.SpringLearn.Exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> globalExceptionHandler(Exception exception)
	{
		ApiError apiError = ApiError.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(exception.getMessage())
				.build();
		
		return buidErrorResponse(apiError);
				
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception)
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
		return buidErrorResponse(apiError);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> resourceNotFoundHandeler(ResourceNotFoundException exception)
	{
		ApiError apiError = ApiError.builder()
				.status(HttpStatus.NOT_FOUND)
				.message(exception.getMessage())
				.build();
		
		return buidErrorResponse(apiError);

	}
	
	private ResponseEntity<ApiResponse<?>> buidErrorResponse(ApiError apiError) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
	}
	

}
  