package com.SpringLearn.Advice;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data         //@Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor in one line.
@Builder      //@Builder allows you to create objects using a builder pattern, which is cleaner than using a constructor with many arguments.
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	private String message;
	private HttpStatus status;
	private List<String> error;

}
