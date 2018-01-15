package com.revature.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.bean.Deck;
import com.revature.model.bean.User;

@Repository
public interface DeckDao extends JpaRepository<Deck, Integer> {
	
	Deck findByIdAndOwner(Integer id, User owner);

}
