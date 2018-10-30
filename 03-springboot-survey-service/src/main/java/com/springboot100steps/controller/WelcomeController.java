package com.springboot100steps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot100steps.configuration.BasicConfiguration;

@RestController
public class WelcomeController {

	@Autowired
	WelcomeService welcomeService;
	
	@Autowired
	BasicConfiguration basicConfiguration;
	
	@GetMapping(path="/welcome")
	public String getWelcomeMessage() {
		return welcomeService.getMessage();
	}
	
	@GetMapping(path="/dynamic-configuration")
	public BasicConfiguration dynamicConfiguration() {
		return this.basicConfiguration;
	}
}


@Service
class WelcomeService{

	@Value("${welcome.message}")
	private String message;
	
	public String getMessage() {
		return this.message;
	}
	
}