package com.springboot100steps.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot100steps.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login-view-page";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String showWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if(!loginService.validateCredentials(name, password)) {
			model.put("errorMessage", "Invalid Credential.");
			return "login-view-page";
		}
		model.put("name", name);
		return "welcome-view-page";
	}

}
