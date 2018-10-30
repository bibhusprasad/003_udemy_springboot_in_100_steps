package com.springboot100steps.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new User("Alok", "Admin"));
		repository.save(new User("Asutosh", "User"));
		repository.save(new User("Sonali", "Admin"));
		repository.save(new User("Babul", "User"));
		
		for (User user : repository.findAll()) {
			log.info(user.toString());
		}
		
		log.info("Admin users are.....");
		log.info("____________________");
		for (User user : repository.findByRole("Admin")) {
			log.info(user.toString());
		}
	}

}