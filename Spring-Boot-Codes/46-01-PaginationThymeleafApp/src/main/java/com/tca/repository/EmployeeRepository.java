package com.tca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.tca.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{

}
