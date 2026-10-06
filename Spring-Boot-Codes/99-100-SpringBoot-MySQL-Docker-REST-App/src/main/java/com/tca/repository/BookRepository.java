package com.tca.repository;

import org.springframework.data.repository.CrudRepository;

import com.tca.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> 
{

}
