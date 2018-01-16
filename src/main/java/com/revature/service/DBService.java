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
	
	public User userFindByEmailAndPass(String email, String password) {
		return userDao.findByEmailAndPassword(email, password);
	}
	
	public User userFindByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public Deck createDeck(Deck newDeck) {
		return deckDao.save(newDeck);
	}
	
	public Deck deckFindByIdAndOwner(Integer id, User owner) {
		return deckDao.findByIdAndOwner(id, owner);
	}
	
	public Deck deckFindById (Integer id){
		return deckDao.findById(id);
	}
	
}
