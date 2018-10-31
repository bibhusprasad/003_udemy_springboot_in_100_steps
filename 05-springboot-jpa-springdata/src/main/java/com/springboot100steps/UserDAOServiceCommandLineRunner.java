package com.springboot100steps;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot100steps.entity.User;
import com.springboot100steps.service.UserRepository;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("bibhu", "admin");
		User result = userRepository.save(user);
		
		Optional<User> userOne = userRepository.findById((long) 1);
		List<User> userList = userRepository.findAll();
		
		System.out.println(result);
		System.out.println(userOne);
		System.out.println(userList);
	}

}
