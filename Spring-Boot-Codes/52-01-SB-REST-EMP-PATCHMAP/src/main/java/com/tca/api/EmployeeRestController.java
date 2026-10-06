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
	
	
	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id)
	{
		// call service.fetchEmployeeById(id)
		Employee emp = service.fetchEmployeeById(id);
		if ( emp != null)
			return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			return  new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/employees")
	public ResponseEntity<List<Employee>>  getEmployees() {
		
		// call service.fetchEmployees()
		List<Employee> employees = service.fetchEmployees();
		if (employees.isEmpty())
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/add" )
			   			    
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
	
	@PutMapping( value = "/update" )
			   
			  
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
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<Employee> partialUpdate(@RequestBody Map<String, Object> fieldsMap, @PathVariable Integer id) {
	
		Employee updatedEmployee = service.updateEmployeeById(id, fieldsMap);
	
		if (updatedEmployee == null)
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	
	}
}

/* 
 	** This appln is copied from last application 50-51-SB-REST-EMP-CRUD**
 	* In this app PATCH method is added and developed, see option(5)
 	*
 	Assume Emp table is created already, Database Schema is in Employee class.
 	
 	Testing Each End Point -
 	
 	1) "/employee/{id}"
 	
 		URL :	http://localhost:8081/employee/101
 	
 	 	Output:
 	 	{
    		"empno": 101,
    		"ename": "AAA",
    		"sal": 10000, 
    		"deptno": 111
		}
	#####
	
		URL"http://localhost:8081/employees"
		
		Output:
			All records are Shown in  JSON format
 -------------------------------------------------------------------------------------
 	2) "/add"
 	
 		URL: http://localhost:8081/add
 	
 		To provide data in JSON follow : Body -> raw-> JSON
 		{
    		"empno": 104,
    		"ename": "DDD",
    		"sal": 9999,
    		"deptno": 111
		}
		
		Dont forget to Set Request Method : "POST"
		
		Run the code. you will get 201 created + data is shown on JSON 
		
		Re-run for same record. You will get "409 Conflict"
		because we can not insert same primary key record twice.
------------------------------------------------------------------------------------		
		
	3) "/update
 	
 		URL: http://localhost:8081/update
 		Dont forget to Set Request Method : "PUT"
 		
 		To provide data in JSON follow : Body -> raw-> JSON
 		{
    		"empno": 104,
    		"ename": "ddd",
    		"sal": 9999,
    		"deptno": 111
		}
		
		
		
		Run the code. you will get 200 OK + data is shown on JSON 
		#########
		
		
		Give Invalid JSON where empno=109
		
		{
    		"empno": 109,
    		"ename": "ddd",
    		"sal": 9999,
    		"deptno": 111
		}
		
		Re-run for same record. You will get "404 Not Found"
		because "empno=109" Record is not Found.
		#########
		
		
		Give JSON where one of field is missing, For example you are not giving "sal"
		
		{
    		"empno": 104,
    		"ename": "ddd",
    		"deptno": 111
		}
 		
 		Run the code. you will get 200 OK. "sal" will be null
 		{
    		"empno": 104,
    		"ename": "DDD",
    		"sal": null,
    		"deptno": 111
		}
		
------------------------------------------------------------------------------------		
		
	4) "/delete/{id}"
	
	URL : http://localhost:8081/delete/104
	Dont forget to Set Method "DELETE"
	
	Run the code. you will get 200 OK and Response "Employee is deleted...."
	#########
	
	Re-Run the Code for same id
	http://localhost:8081/delete/104
	
	you will get 200 OK and Response Employee doesn't exist
	#########
	
	5) "/update/{id}"
	
	URL : http://localhost:8081/update/101
	Dont forget to Set Method "PATH"
	
	To provide data in JSON follow : Body -> raw-> JSON
 		{
    		 "sal": 6000.0,
    		"deptno": 1111
		}
	On Success you will get emp object 200 OK
	{
    	"empno": 101,
    	"ename": "AAA",
    	"sal": 6000.0,
    	"deptno": 1111
	}
	
	on Failure you will get 400 NOT FOUND
 */



















