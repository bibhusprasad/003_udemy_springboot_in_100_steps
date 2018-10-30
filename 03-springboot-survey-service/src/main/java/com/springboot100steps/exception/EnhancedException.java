package com.springboot100steps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnhancedException extends Exception{

	private static final long serialVersionUID = 1L;

	public EnhancedException(String message) {
		super(message);
	}
}
