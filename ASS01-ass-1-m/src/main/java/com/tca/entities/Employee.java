package com.tca.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMP")
public class Employee 
{
	@Id
	@Column(name="eid")
	private Integer eid;
	
	@Column(name="ename")
	private String ename;
	
	@Column(name="esalary")
	private Double esalary;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id", nullable=false) // Otherwise you can insert dept_id null in table 
	Department dept;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Double getEsalary() {
		return esalary;
	}

	public void setEsalary(Double esalary) {
		this.esalary = esalary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}	   

	
}
