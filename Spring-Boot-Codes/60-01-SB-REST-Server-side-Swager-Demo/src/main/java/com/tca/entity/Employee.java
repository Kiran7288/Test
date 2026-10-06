package com.tca.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="EMP")
@Data
@Schema(description = "Emploee Model")
public class Employee {
	
	@Schema(description = "ID of the Employee")
	@Id
	private Integer empno;
	
	@Schema(description = "Name of the Employee")
	private String ename;
	
	@Schema(description = "Salary of the Employee")
	private Double sal;
	
	@Schema(description = "Department of the Employee")
	private Integer deptno;
	

}
/*
	create table emp(empno int primary key, ename varchar(10), sal double, deptno int);
	insert into emp values(101,'AAA', 10000, 111);
	insert into emp values(102,'BBB', 20000, 222);
	insert into emp values(103,'CCC', 30000, 333);
*/