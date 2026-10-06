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
  mydb.Student table must have atleat 11 records
  
create table emp(empno int primary key, ename varchar(10), sal double, deptno int);
insert into emp values(101,'AAA', 10000, 111);
insert into emp values(102,'BBB', 20000, 222);
insert into emp values(103,'CCC', 30000, 333);
insert into emp values(104,'DDD', 70000, 333);
insert into emp values(105,'EEE', 40000, 333);
insert into emp values(106,'FFF', 90000, 333);
insert into emp values(107,'GGG', 6000,  333);
insert into emp values(108,'HHH', 50000, 333);
insert into emp values(109,'III', 20000, 333);
insert into emp values(110,'JJJ', 80000, 333);
insert into emp values(111,'KKK', 30000, 333);
*/