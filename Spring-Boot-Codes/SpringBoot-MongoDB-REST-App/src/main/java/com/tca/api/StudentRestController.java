package com.tca.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tca.entity.Student;
import com.tca.exception.StudentAlreadyExistException;
import com.tca.exception.StudentNotFoundException;
import com.tca.service.StudentService;

@RestController
public class StudentRestController 
{
	@Autowired
	private StudentService service;
	
	@GetMapping(value="/student/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id)
	{
		return new ResponseEntity<Student>(service.fetchStudent(id), HttpStatus.OK);
	}
	
	@GetMapping(value="/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getAllStudents()
	{
		return new ResponseEntity<List<Student>>(service.fetchAllStudents(), HttpStatus.OK);
	}
	
	@PostMapping(value="/create", 
				produces=MediaType.APPLICATION_JSON_VALUE, 
				consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		return new ResponseEntity<Student>(service.saveStudent(student), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Integer id)
	{
		service.removeStudent(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StudentAlreadyExistException.class)
	public ResponseEntity<String> handleStudentAlreadyExistException(StudentAlreadyExistException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
	}
}


/*
 sb01db> db.student.find();
[
  { _id: 104, name: 'BBB', per: 70, _class: 'com.tca.entity.Student' }
]
 
 */
