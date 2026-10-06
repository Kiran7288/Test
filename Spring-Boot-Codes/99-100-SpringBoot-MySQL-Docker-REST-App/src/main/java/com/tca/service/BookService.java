package com.tca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.Book;
import com.tca.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repo;
	
	public boolean addBook(Book book) {
		try {
			repo.save(book);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public Iterable<Book> fetchBooks() {
		return repo.findAll();
	}

}