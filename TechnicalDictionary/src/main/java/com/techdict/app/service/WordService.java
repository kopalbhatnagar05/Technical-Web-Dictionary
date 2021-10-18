package com.techdict.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techdict.app.model.FormModel;
import com.techdict.app.model.Word;

@Service
public interface WordService {

	public void deleteWord(int cid);

	public List<Word> viewAll();

	public FormModel getOne(String title);

	public Word saveWord(String title, String description, MultipartFile[] files, int imgcount);

	public Word editWord(MultipartFile[] files, String title, String description, int imgcount);

}
