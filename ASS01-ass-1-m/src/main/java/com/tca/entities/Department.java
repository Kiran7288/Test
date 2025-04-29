package com.tca.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEPT")
public class Department 
{
	@Id
	@Column(name="did")
	private Integer did;
	
	@Column(name="dname")
	private String dname;
	
	/*
	 * mappedBy : The entity 'Department' that uses mappedBy to point to the owning side
	 *            i.e 'dept' is name of property in Employee class
	 */
	//@OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id")
	List<Employee> emps;

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public List<Employee> getEmps() {
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}


		
}
