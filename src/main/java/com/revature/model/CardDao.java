package com.revature.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.bean.Card;

@Repository
public interface CardDao extends JpaRepository<Card, Integer> {
	
	Card findByName(String name);

}
