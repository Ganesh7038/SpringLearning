package com.SpringLearn.Advice;

import java.time.LocalTime;
import lombok.Data;

@Data
public class ApiResponse<T> {
	
	//@JsonFormat(pattern = "hh:mm:ss DD-MM-YYYY")
	private LocalTime locaTime;
	
	private T data;
	private ApiError errors;
	
	public ApiResponse()
	{
		this.locaTime = LocalTime.now();
	}
	
	public ApiResponse(ApiError errors)
	{
		this();
		this.errors = errors;
	}
	
	public ApiResponse ( T data)
	{
		this();
		this.data = data;
	}
	
}
