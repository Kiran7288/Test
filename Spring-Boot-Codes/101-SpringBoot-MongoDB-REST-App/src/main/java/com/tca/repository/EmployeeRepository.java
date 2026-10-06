package com.tca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tca.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {}