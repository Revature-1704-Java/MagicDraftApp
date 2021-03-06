package com.revature.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.bean.User;
import com.revature.service.DBService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin
@RestController
public class UserValidation {
	
	@Autowired
	DBService dbService;

	@Value("${key}")
	private String key;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<User> postLogin(@Valid User user, BindingResult bindingResult) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if(bindingResult.hasErrors()) {
			return null;
		}
		
		User loggedInUser = dbService.userFindByEmailAndPass(user.getEmail(), user.getPassword());
		if(loggedInUser == null) {
			return null;
		}else {
			//Generate token
			String token = Jwts.builder().setSubject(loggedInUser.getEmail()).signWith(SignatureAlgorithm.HS512, key).compact();
			responseHeaders.add("Set-Cookie", "mtg-access-token=" + token);
			loggedInUser.setPassword("");
			return new ResponseEntity<User>(loggedInUser, responseHeaders, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<String> postLogout(){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Set-Cookie", "mtg-access-token=; Max-Age=0");
		return new ResponseEntity<String>(responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ResponseEntity<User> postSignup(@Valid User user, BindingResult bindingResult) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if(bindingResult.hasErrors()) {
			
			return null;
		}
		//Create the new user
		if(dbService.userFindByEmail(user.getEmail()) != null) {
			return null;
		}else {
			User newUser = dbService.createUser(user);
			String token = Jwts.builder().setSubject(newUser.getEmail()).signWith(SignatureAlgorithm.HS512, key).compact();
			responseHeaders.add("Set-Cookie", "mtg-access-token=" + token);
			newUser.setPassword("");
			return new ResponseEntity<User>(newUser, responseHeaders, HttpStatus.OK);
		}
		
		
	}

}
