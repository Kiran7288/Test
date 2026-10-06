package com.tca.repository;

import org.springframework.data.repository.CrudRepository;

import com.tca.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> 
{

}
