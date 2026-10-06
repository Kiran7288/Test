package com.tca.service;

import java.util.List;

import com.tca.entity.Employee;

public interface EmployeeService 
{
	Employee fetchEmployeeById(Integer id);
	
	List<Employee> fetchEmployees();
	
	Employee  saveEmployee(Employee emp);
	
	Employee updateEmployee(Employee emp);
	
	boolean removeEmployee(Integer id);
}
