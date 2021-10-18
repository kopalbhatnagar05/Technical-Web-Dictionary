package com.techdict.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.techdict.app.dao.UserRepository;
import com.techdict.app.model.Word;
import com.techdict.security.model.User;
@ComponentScan("com.techdict.app.dao")
@Service
public class UserService {
	@Autowired
	UserRepository ur;
	public User userAdd(User user) {
		if(uniqueUser(user.getUserName())) {
			return ur.save(user);
		}
		//}
		else {
			
		return null;
		}
	}
	public boolean uniqueUser(String userName) {
		// TODO Auto-generated constructor stub
		User u=new User();
		u=ur.findByUserName(userName).orElse(null);
		if(u==null) {
			return true;
		}
		else {
			return false;
		}
	}
	public List<User> viewAll() {
		// TODO Auto-generated method stub
		return ur.findAll();
	}

}
