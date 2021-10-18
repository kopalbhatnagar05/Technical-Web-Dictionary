
package com.techdict.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techdict.security.model.User;
import com.techdict.security.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@ComponentScan("com.techdict.security.service")
@RestController

public class SecurityController {
	@Autowired
	UserService us;

	@GetMapping(path = "/basicauth")
	public String auth() {
		// throw new RuntimeException("Some Error has Happened! Contact Support at
		// ***-***");
		return "You are authenticated";
	}

	@GetMapping(path = "/listuser")
	public List<User> list() {


		return us.viewAll();
	}

	@PostMapping(path = "/registeruser")
	public User addUser(@RequestBody User user) {
		
		User user1 = new User(user.getUserName(), user.getPassword(), true, "ROLE_USER");
		return us.userAdd(user1);
	}

	@PostMapping(path = "/registeradmin")
	public User addAdmin(@RequestBody User user) {
		
		User user1 = new User(user.getUserName(), user.getPassword(), true, "ROLE_ADMIN");
		return us.userAdd(user1);
	}
}
