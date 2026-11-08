package com.tca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.Student;
import com.tca.exception.StudentAlreadyExistException;
import com.tca.exception.StudentNotFoundException;
import com.tca.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository repo;

	@Override
	public Student saveStudent(Student student) 
	{
		if(repo.existsById(student.getRno()))
		{
			throw new StudentAlreadyExistException("Student Already Exists Roll Number:" + student.getRno());
		}
		else
		{
			return repo.save(student);
		}
	}

	@Override
	public Student fetchStudent(Integer rno) 
	{
		if(repo.existsById(rno))
		{
			return repo.findById(rno).get();
		}
		else
		{
			throw new StudentNotFoundException("Student Not Found for Roll Number:" + rno);
		}
	}

	@Override
	public List<Student> fetchAllStudents() 
	{
		return repo.findAll();
	}

	@Override
	public void removeStudent(Integer rno) 
	{
		if(repo.existsById(rno))
		{
			repo.deleteById(rno);
		}
		else
		{
			throw new StudentNotFoundException("Student Not Found for Roll Number:" + rno);
		}
		
	}
		
}
