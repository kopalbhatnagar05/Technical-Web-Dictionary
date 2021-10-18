package com.techdict.app.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techdict.app.dao.WordRepository;
import com.techdict.app.helper.FileDelete;
import com.techdict.app.helper.FileUpload;
import com.techdict.app.model.FormModel;
import com.techdict.app.model.Word;

//import com.techdict.app.repo.WordRepository;
@ComponentScan("com.techdict.app.dao")
@Service
public class WordServiceImpl implements WordService {
	private static final Log logger = LogFactory.getLog(WordService.class);
	@Autowired
	WordRepository ws;
	FileDelete fd = null;
	FileUpload fu = null;
	Word w = null;

	public WordServiceImpl() {
		// TODO Auto-generated constructor stub

	}

	//deleting word and then the images in the folder
	@Override
	public void deleteWord(int cid) {
		// TODO Auto-generated method stub
		Word word = ws.getById(cid);
		String imgpath = word.getImageUrl();
		fd = new FileDelete();
		try {
			fd.deleteFolder(imgpath, word.getTitle());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
		}
		ws.deleteById(cid);

	}

	@Override
	public List<Word> viewAll() {
		// TODO Auto-generated method stub
		return ws.findAll();
	}

	@Override
	//getting one word and its detail
	public FormModel getOne(String title) {
		// TODO Auto-generated method stub
		// if(uniqueWord(title)) {
		w = new Word();
		fu = new FileUpload();
		w = ws.findByTitle(title).orElse(null);
		String[] imglist = fu.imageList(w.getImageUrl(), title);
		FormModel fm = new FormModel(title, w.getDescription(), imglist);
		return fm;

		// }
		// else {
		// return null;
		// }

	}
	//editing word
	@Override
	public Word editWord(MultipartFile[] files, String title, String description, int imgcount) {
		// TODO Auto-generated method stub
		w = new Word();
		w = ws.findByTitle(title).orElse(null);
		fu = new FileUpload();
		String[] fileName = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileName[i] = files[i].getOriginalFilename();
			//getting all files and their names
		}
		int alreadyimg = fu.noOfImages(title);
		if (!uniqueWord(title)) {
			//if total imgs after adding are <6 then add normally
			if (imgcount <= 4 && alreadyimg == 2) { //
				w = new Word();
				ws.delete(ws.findByTitle(title).orElse(null));
				w = ws.save(new Word(title, description, fu.storeImage(files, fileName, title)));
				
			} else if (imgcount < 6 && alreadyimg > 2) {
				if (imgcount + alreadyimg <= 6) {
					//if total imgs after adding are <6 then add normally
					w = new Word();
					ws.delete(ws.findByTitle(title).orElse(null));
					w = ws.save(new Word(title, description, fu.storeImage(files, fileName, title)));
				} else {
					//delete the required amount of images to make room for the uploaded images
					int f = alreadyimg - imgcount;
					fd = new FileDelete();
					try {
						fd.deleteFile(w.getImageUrl(), f, title);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					w = new Word();
					ws.delete(ws.findByTitle(title).orElse(null));
					w = ws.save(new Word(title, description, fu.storeImage(files, fileName, title)));

				}

			}
			// else if ()
			return w;
		} else { //if the word doesnt exist return null
			return null;
		}
	}

	@Override
	public Word saveWord(String title, String description, MultipartFile[] files, int imgcount) {
		if (uniqueWord(title)) {
			String[] fileName = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				fileName[i] = files[i].getOriginalFilename();
			}
			FileUpload fu = new FileUpload();
			//store images on disk and get the relative filepath to store
			String imagepath = fu.storeImage(files, fileName, title);
			w = new Word(title.toUpperCase(), description, imagepath);
			return ws.save(w);
		} else {
			return null;
		}
	}
	//check if word is unique
	public boolean uniqueWord(String title) {
		// TODO Auto-generated constructor stub
		w = new Word();
		w = ws.findByTitle(title.toUpperCase()).orElse(null);

		if (w == null) {
			return true;
		} else {
			return false;
		}
	}
}
