package com.techdict.app.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FormModel {

	private String title;
	private String description;
	private String[] images;

	public FormModel() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	/**
	 * @param title
	 * @param description
	 * @param images
	 */
	public FormModel(String title, String description, String[] images) {
		super();
		this.title = title;
		this.description = description;
		this.images = images;
	}

}