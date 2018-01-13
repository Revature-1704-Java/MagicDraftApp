package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.model.UserDao;
import com.revature.model.bean.User;

@Component
public class DBService {

	@Autowired
	private UserDao userDao;
	
	public User createUser(User newUser) {
		return userDao.save(newUser);
	}
	
	public User findByIdAndPass(String email, String password) {
		return userDao.findByEmailAndPassword(email, password);
	}
	
}
