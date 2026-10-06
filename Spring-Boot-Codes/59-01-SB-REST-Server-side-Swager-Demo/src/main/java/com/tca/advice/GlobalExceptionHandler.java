package com.tca.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tca.exception.EmployeeAlreadyExistException;
import com.tca.exception.EmployeeNotFoundException;

/*
 * This is global Exception Handler.
 * This exception handler is going to handle exceptions from "ANY" rest controller.
 */

@RestControllerAdvice
public class GlobalExceptionHandler //[53:00]
{
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
	
	// Generic Exception handler[54:00]
	// When This handler is going to come in picture when Restcontroller will throws other than above 2 Exceptions
	
	@ExceptionHandler(Exception.class) //[55:00]
	public ResponseEntity<String> handleException(Exception ex)
	{
		return new ResponseEntity<String>("Error:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
