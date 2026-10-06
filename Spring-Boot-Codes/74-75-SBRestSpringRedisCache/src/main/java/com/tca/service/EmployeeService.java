package com.tca.service;

import java.util.List;
import java.util.Map;

import com.tca.entity.Employee;

public interface EmployeeService {
	
	Employee fetchEmployeeById(Integer id);
	
	List<Employee> fetchEmployees();
	
	Employee  saveEmployee(Employee emp);
	
	Employee updateEmployee(Employee emp);
	
	boolean removeEmployee(Integer id);
	
	Employee updateEmployeeById(Integer id, Map<String, Object> fieldsMap);

}
