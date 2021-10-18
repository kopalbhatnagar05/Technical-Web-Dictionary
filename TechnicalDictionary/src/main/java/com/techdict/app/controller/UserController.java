package com.techdict.app.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techdict.app.model.FormModel;
import com.techdict.app.model.Word;
import com.techdict.app.service.WordService;

@CrossOrigin(origins = "http://localhost:3000")
@ComponentScan("com.techdict.app.service")
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	WordService ws;
	private static final Log logger = LogFactory.getLog(UserController.class);

	//return all word with details, since it can be accessed by both user and admin, placed here, check securityconfig.java for more
	@GetMapping(value = "/viewall")
	@ResponseBody
	
	public List<Word> view() {
		return ws.viewAll();
	}
	
	//returns a word with details, since it can be accessed by both user and admin, placed here
	@GetMapping(value = "/getdata/{title}")
	public FormModel getValue(@PathVariable("title") String title) {
		return ws.getOne(title.toUpperCase().replaceAll(" ", ""));
	}
}
