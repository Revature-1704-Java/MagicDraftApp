package com.revature.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	User findById(int i);
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
}
