package com.revature.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.bean.User;
import com.revature.service.DBService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserValidation {
	
	@Autowired
	DBService dbService;

	@Value("${key}")
	private String key;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<String> postLogin(@Valid User user, BindingResult bindingResult) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if(bindingResult.hasErrors()) {
			return null;
		}
		
		User loggedInUser = dbService.userFindByEmailAndPass(user.getEmail(), user.getPassword());
		//Generate token
		String token = Jwts.builder().setSubject(loggedInUser.getEmail()).signWith(SignatureAlgorithm.HS512, key).compact();
		responseHeaders.add("Set-Cookie", "mtg-access-token=" + token);
		
		return new ResponseEntity<String>("Success", responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<String> postLogout(){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Set-Cookie", "mtg-access-token=; Max-Age=0");
		return new ResponseEntity<String>(null, responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ResponseEntity<String> postSignup(@Valid User user, BindingResult bindingResult) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if(bindingResult.hasErrors()) {
			
			return new ResponseEntity<String>("Fail", responseHeaders, HttpStatus.NO_CONTENT);
		}
		//Create the new user
		User newUser = dbService.createUser(user);
		String token = Jwts.builder().setSubject(newUser.getEmail()).signWith(SignatureAlgorithm.HS512, key).compact();
		responseHeaders.add("Set-Cookie", "mtg-access-token=" + token);
		return new ResponseEntity<String>(null, responseHeaders, HttpStatus.OK);
		
	}

}
