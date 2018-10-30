package com.springboot100steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Profile("dev")
	@Bean
	public String dummyMessageForDev() {
		return "This bean will create when active profile is dev";
	}

	@Profile("prod")
	@Bean
	public String dummyMessageForProd() {
		return "This bean will create when active profile is prod";
	}
}
