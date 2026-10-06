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
	
	
	@GetMapping(value = "/employee/{id}", produces="application/xml")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id)
	{
		// call service.fetchEmployeeById(id)
		Employee emp = service.fetchEmployeeById(id);
		if ( emp != null)
			return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			return  new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/employees", produces="application/xml")
	public ResponseEntity<List<Employee>>  getEmployees() {
		
		// call service.fetchEmployees()
		List<Employee> employees = service.fetchEmployees();
		if (employees.isEmpty())
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/add", consumes="application/xml", produces="application/xml")
			   			    
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
 	-->This Application is for Testing data Transfer in form of XML
 	using properties 'produces' and 'consumes'
 	
 	--> See methods getEmployeeById(), getEmployees(), addEmployee() 
 	
 	--> Add Dependency - "jackson-dataformat-xml" from maven site
 		Remove <Version> tag
 	
 	
 	
 	Assume Emp table is created already, Database Schema is in Employee class.
 	
 	Testing Each End Point -
 	
 	1) "/employee/{id}"
 	
 		URL :	http://localhost:8081/employee/101
 		
 		Dont forget to Set Request Method : "GET"
 	 	
 	 	Output:
 	 	
 	 	<Employee>
    		<empno>101</empno>
    		<ename>AAA</ename>
    		<sal>6000.0</sal>
    		<deptno>1111</deptno>
		</Employee>

	
	2)	"/employees"
	
		URL"http://localhost:8081/employees"
		
		Dont forget to Set Request Method : "GET"
		
		Output:
			All records are Shown in  XML format
 -------------------------------------------------------------------------------------
 	2) "/add"
 	
 		URL: http://localhost:8081/add
 		
 		Dont forget to -
 		
 		a) Set Request Method : "POST"
 		
 		b) Set Headers -
 			Content-Type: application/xml
			Accept: application/xml
			
 		b)To provide data in JSON follow : Body -> raw-> XML
 		<Employee>
    		<empno>104</empno>
    		<ename>DDD</ename>
    		<sal>6000.0</sal>
    		<deptno>444</deptno>
		</Employee>
		
		
		
		Run the code. you will get 201 created + data is shown on XML 
		
		Re-run for same record. You will get "409 Conflict"
		because we can not insert same primary key record twice.
------------------------------------------------------------------------------------		

	Lec-53 : 
	
	Testing Update & Putmapping
	=============================

	End-Point : "/update"
	Method	  : PUT
	URL       : http://localhost:8081/update
	
	Headers	  : Content-Type=application/xml
	
	To provide data in JSON follow : Body -> raw-> XML
 		<Employee>
    		<empno>105</empno>
    		<ename>DDD</ename>
    		<sal>6000.0</sal>
    		<deptno>555</deptno>
		</Employee>
		
	Run the code. you will get 201 created + data is shown on XML
	
	
	Testing partialUpdate & PathMapping
	======================================
	
	
	End-Point : "/update/{id}"
	Method	  : PUT
	URL       : http://localhost:8081/update/105
	
	Headers	  : Content-Type=application/xml
	
	To provide data in JSON follow : Body -> raw-> XML
 		<Employee>
    		<empno>105</empno>
    		<ename>DDD</ename>
    		<sal>6000.0</sal>
    		<deptno>555</deptno>
		</Employee>
		
	Run the code. you will get 201 created + data is shown on XML
	
	
	Important: Replace two line from updateEmployeeId() from Service layer
	
	 double sal = Double.valueOf(fieldsMap.get("sal").toString());
	int deptno = Integer.valueOf(fieldsMap.get("deptno").toString());
	
	Reaseon is -
			*  above two lines are different in 52-02 App
			 *  because communication was happend in JSON format 
			 *  but here client and server is communicating using XML
			 *  
			 *  But in XML → everything is treated as String by default 
			 * (unlike JSON, which can preserve number types).
*/



















