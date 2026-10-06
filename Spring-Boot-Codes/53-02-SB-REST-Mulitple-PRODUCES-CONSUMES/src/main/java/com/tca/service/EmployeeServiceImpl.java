package com.tca.service;

import java.util.List;
import java.util.Map;
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
	
	
	@Override
	public Employee updateEmployeeById(Integer id, Map<String,Object> fieldsMap) {
		
		if (repository.existsById(id)){
			
			/*
			 *  Below two lines are different in 52-02 App
			 *  because communication was happend in JSON format 
			 *  but here client and server is communicating using XML
			 *  
			 *  But in XML → everything is treated as String by default 
			 * (unlike JSON, which can preserve number types).
			 */
			double sal = Double.valueOf(fieldsMap.get("sal").toString());
			int deptno = Integer.valueOf(fieldsMap.get("deptno").toString());
			repository.partialUpdateEmp(sal, deptno, id);
			
			return repository.findById(id).get();
		}
		else {
			return null;
		}
	};
}
