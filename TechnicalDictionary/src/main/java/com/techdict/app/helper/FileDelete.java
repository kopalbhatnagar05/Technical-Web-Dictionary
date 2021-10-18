package com.techdict.app.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;
//class to delete folders and files
public class FileDelete extends FileUpload {

	public FileDelete() {
		// TODO Auto-generated constructor stub
	}

	public void deleteFolder(String path, String title) throws IOException {
		String lpath = baseurl + title;
		System.out.println(lpath);
		FileUtils.deleteDirectory(new File(lpath));
	}

	public void deleteFile(String path, int x, String title) throws IOException {
		FileUpload fu = new FileUpload();
		// String lpath=baseurl+path;
		String[] list = fu.imageList(path, title);
		for (int i = 0; i < x; i++)
			FileUtils.deleteQuietly(new File(baseurl + list[(list.length) - i-1]));
	}
}
