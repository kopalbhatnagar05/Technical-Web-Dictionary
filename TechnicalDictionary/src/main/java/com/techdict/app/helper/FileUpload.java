package com.techdict.app.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.multipart.MultipartFile;
//class to create and upload files and folders
public class FileUpload {
	public String baseurl = "C:\\Users\\ZMJKWXN\\reactlearning\\" + 
			"techdict\\public\\images\\";
	private String consturl = "\\images\\";

	private static final Log logger = LogFactory.getLog(FileUpload.class);

	public String storeImage(MultipartFile[] imageArray, String[] filename, String title) {
		Path path = null;
		// String x="\\reactlearning\\";
		// String el=Paths.get(x).toAbsolutePath().toString();
		// logger.info("pathe: "+el);
		String relativep = consturl + title;
		try {
			path = Paths.get(baseurl + title);
			//will create directory if doesnt exist
			Files.createDirectories(path);
			for (int i = 0; i < imageArray.length; i++) {
				InputStream inputStream = imageArray[i].getInputStream();
				//saving files into the folder
				Path filePath = path.resolve(filename[i]);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Failed to create directory", e);
		}
		return relativep;
	}
//returning number of images
	public int noOfImages(String title) {
		String path = Paths.get(baseurl + title).toString();

		File directoryPath = new File(path);
		// List of all files and directories
		String contents[] = directoryPath.list();
		// System.out.println("List of files and directories in the specified
		// directory:");

		return contents.length;

	}
//returning list of images in a directory
	public String[] imageList(String imgurl, String title) {

		String path = Paths.get(baseurl + title).toString();
		logger.info("path: " + path);

		File directoryPath = new File(path);
		// List of all files and directories
		String contents[] = directoryPath.list();
		// System.out.println("List of files and directories in the specified
		// directory:");

		String[] imglist = new String[contents.length];
		for (int i = 0; i < contents.length; i++) {
			imglist[i] = imgurl.concat("\\" + contents[i]);
		}

		return imglist;

	}

}
