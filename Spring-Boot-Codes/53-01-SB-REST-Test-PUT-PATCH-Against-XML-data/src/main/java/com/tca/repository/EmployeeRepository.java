package com.tca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tca.entity.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
	@Query( value = "UPDATE EMP SET SAL = ?, DEPTNO = ? WHERE EMPNO = ?", nativeQuery = true)
	@Modifying
	@Transactional
	int partialUpdateEmp(double sal, int deptno, int empno);

}
