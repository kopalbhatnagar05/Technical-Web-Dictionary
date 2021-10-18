package com.techdict.app.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techdict.app.model.FormModel;
import com.techdict.app.model.Word;
import com.techdict.app.service.WordService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8000" })
@ComponentScan("com.techdict.app.service")
@RestController
@RequestMapping("admin")
//All the functions related to the Word only accessible by admin are placed here: 
public class AdminController {
	@Autowired
	WordService ws;
	private static final Log logger = LogFactory.getLog(AdminController.class);
	
//deleting a word
	@DeleteMapping(value = "/delete/{cid}")
	@ResponseBody
	public void delete(@PathVariable("cid") int cid) {

		ws.deleteWord(cid);
	}

//editing a word, taking array of images are multipartfile data and storing it in file system using helper scripts
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, headers = "Content-Type=multipart/form-data")
	public Word update(@RequestParam MultipartFile[] files, @RequestParam String title,
			@RequestParam String description) {

		int imgcount = files.length;
		// System.out.print("Control here");
		return ws.editWord(files, title.toUpperCase().replaceAll(" ", ""), description, imgcount);
	}
//saving a word, taking array of images are multipartfile data and storing it in file system using helper scripts
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public ResponseEntity uploadFile(@RequestParam MultipartFile[] files, @RequestParam String title,
			@RequestParam String description) {
		logger.info("logging for debugging purposes..");

		logger.info(title);
		logger.info(description);
		// Word word= null;
		int imgcount = files.length;
//touppercase to remove any word ambquity and making the words case insensitive
		ws.saveWord(title.toUpperCase().replaceAll(" ", ""), description, files, imgcount);

		return ResponseEntity.ok().build();
	}
}
