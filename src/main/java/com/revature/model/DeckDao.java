package com.revature.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.bean.Deck;

@Repository
public interface DeckDao extends JpaRepository<Deck, Integer> {
	
	

}
