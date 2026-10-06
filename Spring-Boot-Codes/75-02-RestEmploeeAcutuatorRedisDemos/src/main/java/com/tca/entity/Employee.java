package com.tca.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EMP")
@Data
public class Employee implements Serializable {
	
	@Id
	private Integer empno;
	
	private String ename;
	
	private Double sal;
	
	private Integer deptno;

}
