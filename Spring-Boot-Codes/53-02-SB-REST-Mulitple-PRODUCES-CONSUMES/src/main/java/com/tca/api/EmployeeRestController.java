package com.tca.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.tca.entity.Employee;
import com.tca.service.EmployeeService;



@RestController
public class EmployeeRestController {
	
	@Autowired
	EmployeeService service;
	
	
	@GetMapping(value = "/employee/{id}", 
				produces={"application/xml", "application/json"}
			   )
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id)
	{
		// call service.fetchEmployeeById(id)
		Employee emp = service.fetchEmployeeById(id);
		if ( emp != null)
			return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			return  new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/employees", produces={"application/xml", "application/json"})
	public ResponseEntity<List<Employee>>  getEmployees() {
		
		// call service.fetchEmployees()
		List<Employee> employees = service.fetchEmployees();
		if (employees.isEmpty())
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/add", consumes={"application/xml", "application/json"}, produces={"application/xml", "application/json"} )
			   			    
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {
		
		Employee emp = service.saveEmployee(e);
		if(emp == null)
		{
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}
		else
		{
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping( value = "/update",consumes="application/xml", produces="application/xml")
			   
			  
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e)
	{
		Employee  updatedEmp = service.updateEmployee(e);
		if ( updatedEmp == null ) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String>  deleteEmployee(@PathVariable Integer id) {
		boolean flag = service.removeEmployee(id);
		if (flag == true)
			return new ResponseEntity<String>("Employee is deleted....", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee doesn't exist", HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping(value = "/update/{id}",consumes="application/xml", produces="application/xml")
	public ResponseEntity<Employee> partialUpdate(@RequestBody Map<String, Object> fieldsMap, @PathVariable Integer id) {
	
		Employee updatedEmployee = service.updateEmployeeById(id, fieldsMap);
	
		if (updatedEmployee == null)
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	
	}
}

/* 
 	See notes.txt
 */



















