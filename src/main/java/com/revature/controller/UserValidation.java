package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserDao;

@RestController
public class UserValidation {
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(String email, String password) {
		return "Success";
	}

}
