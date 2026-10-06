package com.tca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="EMP")
@Data
public class Employee {
	
	@Id
	private Integer empno;
	private String ename;
	private Double sal;
	private Integer deptno;
	

}
/*
	create table emp(empno int primary key, ename varchar(10), sal double, deptno int);
	insert into emp values(101,'AAA', 10000, 111);
	insert into emp values(102,'BBB', 20000, 222);
	insert into emp values(103,'CCC', 30000, 333);
*/