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
import com.tca.exception.EmployeeAlreadyExistException;
import com.tca.exception.EmployeeNotFoundException;
import com.tca.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Employeee", description="Employee REST API")
@RestController
public class EmployeeRestController {
	
	@Autowired
	EmployeeService service;
	
	@Operation(summary="Get Employee by id", description="Fetches Employee by Empno")
	@ApiResponse(responseCode="200", description="Employee retrived from DB Successfully")
	@GetMapping(value = "/employee/{id}", 
				produces={"application/json"}    // observe made json
			   )
	public ResponseEntity<Employee> getEmployeeById(@Parameter(description="empno to fetch the details") @PathVariable Integer id)
	{
		// call service.fetchEmployeeById(id)
		Employee emp = service.fetchEmployeeById(id);
		if ( emp != null)
			return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else //[29:01]
		{
			throw  new EmployeeNotFoundException("Employee with id : " + id +" doesn't exist !");
		}
	}
	
	@GetMapping(value = "/employees", produces={"application/json"})
	public ResponseEntity<List<Employee>>  getEmployees() {
		
		// call service.fetchEmployees()
		List<Employee> employees = service.fetchEmployees();
		if (employees.isEmpty())
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/add", consumes={ "application/json"}, produces={"application/json"} )
			   			    
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {
		
		Employee emp = service.saveEmployee(e);
		if(emp == null) //43:33
		{
			throw new EmployeeAlreadyExistException(" Employee with id :"+e.getEmpno()+" already Exist !");
			
			//return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
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
	
	/* 
	 * Here in RestController , I have written 2 Exception handler methods.
	 * These are Local Exception Handler methods. Methods from this restcontroller can access it only.
	 * Other RestController methods can not use the exection handler methods.
	 * So, Solution is - better to write exception handler methods in Global Exception Handler.
	 * Using @RestControllerAdvice annotation -  place to write exception handlers
	 * Pls, see com.tca.advice package
	 * 
	 * Note: After Executing, I am commenting following two Exception handlers
	 * [50:18]
	 */

/*
 	// These 2 exception handlers are shifted to com.tca.advice package.
	
	@ExceptionHandler(EmployeeNotFoundException.class) //32:15
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex)
	{
		return new ResponseEntity<String>("Error:" + ex.getMessage(), HttpStatus.NOT_FOUND);  //36:06
	}
	
	
	@ExceptionHandler(EmployeeAlreadyExistException.class) //32:15
	public ResponseEntity<String> handleEmployeeAlreadyExistException(EmployeeAlreadyExistException ex)
	{
		return new ResponseEntity<String>("Error:" + ex.getMessage(), HttpStatus.BAD_REQUEST);  //42:41
	}
*/ 
}



















