package com.tca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tca.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> 
{

}
