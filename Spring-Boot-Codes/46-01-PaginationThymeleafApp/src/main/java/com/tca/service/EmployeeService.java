package com.tca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tca.entity.Employee;
import com.tca.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository repo;
	
	public Page<Employee> fetchEmployees(int pageNumber, int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("sal").ascending());  // 'sal' is data member name
		
		return repo.findAll(pageable);
	}
	
}
