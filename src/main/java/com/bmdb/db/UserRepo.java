package com.bmdb.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	// Find a User by username and password
	Optional<User> findByUsernameAndPassword(String username, String password);
	
}