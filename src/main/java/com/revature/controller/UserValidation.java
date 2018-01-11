package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserValidation {
	
	private static Logger logger = LogManager.getLogger("Logger");
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(String email, String password) {
		return "Success";
	}

}
