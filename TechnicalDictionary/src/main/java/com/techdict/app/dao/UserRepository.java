package com.techdict.app.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techdict.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}