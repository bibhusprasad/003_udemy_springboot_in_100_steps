package com.springboot100steps.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	@GetMapping(path="/movie/{id}")
	public String getMovieById1(@PathVariable String id) throws Exception {
		int mId = Integer.valueOf(id);
		if(0 == mId) {
			throw new NullPointerException("NPE");
		}
		if(1 == mId) {
			throw new EnhancedException("EE");
		}
		if(2 == mId) {
			throw new Exception("Id is 1");
		}
		return "Hello";
	}
}
