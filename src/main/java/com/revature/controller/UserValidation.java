package com.revature.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.bean.User;
import com.revature.service.DBService;

@RestController
public class UserValidation {
	
	@Autowired
	DBService dbService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User postLogin(@Valid User user, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return null;
		}
		
		return dbService.findByEmailAndPass(user.getEmail(), user.getPassword());
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ResponseEntity<String> postSignup(@Valid User user, BindingResult bindingResult) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if(bindingResult.hasErrors()) {
			
			return new ResponseEntity<String>("Fail", responseHeaders, HttpStatus.OK);
		}
		
		User newUser = dbService.createUser(user);
		
		return new ResponseEntity<String>(newUser.getId().toString(), responseHeaders, HttpStatus.OK);
		
	}

}
