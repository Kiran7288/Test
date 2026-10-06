package com.tca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.Employee;
import com.tca.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService 
{

	@Autowired
	EmployeeRepository repository;

	@Override
	public Employee fetchEmployeeById(Integer id) {
		
		Optional<Employee> opt = repository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	@Override
	public List<Employee> fetchEmployees() {
		return repository.findAll();
	}
	
	@Override
	public Employee saveEmployee(Employee emp) {
		if (repository.existsById(emp.getEmpno()))
		{
			return null;
		}
		else {
			return repository.save(emp);
		}
		
	}
	
	@Override
	public Employee updateEmployee(Employee emp) {
		if (repository.existsById(emp.getEmpno()))
		{
			return repository.save(emp);
		}
		else {
			return null;
		}
	}
	
	
	@Override
	public boolean removeEmployee(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		else
			return false;
	}
}
