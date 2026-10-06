package com.tca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tca.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> 
{

}
