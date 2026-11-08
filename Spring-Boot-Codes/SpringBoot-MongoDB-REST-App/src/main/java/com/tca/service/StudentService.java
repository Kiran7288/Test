package com.tca.service;

import java.util.List;

import com.tca.entity.Student;

public interface StudentService 
{
	public Student saveStudent(Student student);
	public Student fetchStudent(Integer rno);
	public List<Student> fetchAllStudents();
	public void removeStudent(Integer rno);
}
