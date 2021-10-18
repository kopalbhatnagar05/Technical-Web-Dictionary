package com.techdict.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techdict.app.model.Word;

public interface WordRepository extends JpaRepository<Word, Integer> {
	public Optional<Word> findByTitle(String title);
}
