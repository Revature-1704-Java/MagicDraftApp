package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.model.DeckDao;
import com.revature.model.UserDao;
import com.revature.model.bean.Deck;
import com.revature.model.bean.User;

@Component
public class DBService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeckDao deckDao;
	
	public User createUser(User newUser) {
		return userDao.save(newUser);
	}
	
	public User findByEmailAndPass(String email, String password) {
		return userDao.findByEmailAndPassword(email, password);
	}
	
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public Deck createDeck(Deck newDeck) {
		return deckDao.save(newDeck);
	}
	
}
