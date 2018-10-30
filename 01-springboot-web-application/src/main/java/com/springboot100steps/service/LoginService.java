package com.springboot100steps.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateCredentials(String userName, String password) {
		return userName.equals(password);
	}
}
