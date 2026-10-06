package com.tca.service;

import java.util.List;

import com.tca.entity.Book;

public interface BookService {
	
	Book saveBook(Book newBook);
	
	Book fetchBookById(Integer id);
	
	List<Book> fetchBooks();
	
	void deleteBookById(Integer id);
}
