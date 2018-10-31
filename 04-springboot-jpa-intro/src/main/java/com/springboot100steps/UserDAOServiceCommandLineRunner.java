package com.springboot100steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot100steps.entity.User;
import com.springboot100steps.service.UserDAOService;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{

	@Autowired
	UserDAOService userDAOService;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("bibhu", "admin");
		long id = userDAOService.insert(user);
		System.out.println(id + " inserted.");
	}

}
