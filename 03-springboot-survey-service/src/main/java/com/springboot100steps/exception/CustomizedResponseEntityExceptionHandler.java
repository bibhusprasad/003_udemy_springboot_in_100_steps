package com.springboot100steps.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserExceptionResponse> movieNotFound(NullPointerException ex) {
		UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
		userExceptionResponse.setTimeStamp(new Date());
		userExceptionResponse.setMessage(ex.getMessage());
		userExceptionResponse.setDetails("Movie Id should not 0");
		return new ResponseEntity<UserExceptionResponse>(userExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserExceptionResponse> movieNotFoundWithOne(EnhancedException ex) {
		UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
		userExceptionResponse.setTimeStamp(new Date());
		userExceptionResponse.setMessage(ex.getMessage());
		userExceptionResponse.setDetails("Movie Id should not 1");
		return new ResponseEntity<UserExceptionResponse>(userExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserExceptionResponse> movieNotFoundWithTwo(Exception ex) {
		UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
		userExceptionResponse.setTimeStamp(new Date());
		userExceptionResponse.setMessage(ex.getMessage());
		userExceptionResponse.setDetails("Movie Id should not 2");
		return new ResponseEntity<UserExceptionResponse>(userExceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
