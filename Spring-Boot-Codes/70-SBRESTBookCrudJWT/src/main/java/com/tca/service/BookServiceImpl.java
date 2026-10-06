package com.tca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.Book;
import com.tca.exception.BookAlreadyExistException;
import com.tca.exception.BookNotFoundException;
import com.tca.repository.BookRepository;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository repository;

	@Override
	public Book saveBook(Book newBook) {
		if (!repository.existsById(newBook.getId())) {
			return repository.save(newBook);
		}
		else
			throw new BookAlreadyExistException("A Book with this id : " + newBook.getId()+ " Already exists in Database!!");
	}

	@Override
	public Book fetchBookById(Integer id) {
		if (repository.existsById(id))
			return repository.findById(id).get();
		else
			throw new BookNotFoundException("A Book with this id : "+id + " Doesn't exist in DB!!");
	}

	@Override
	public List<Book> fetchBooks() {
		return repository.findAll();
	}

	@Override
	public void deleteBookById(Integer id) {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new BookNotFoundException("A Book with this id : "+id + " Doesn't exist in DB!!");
	}

}
