package com.tca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.JoinColumn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Department;
import com.tca.entities.Employee;




public class App 
{
    public static void main( String[] args )
    {
       Configuration configuration = new Configuration();
       configuration.configure();  // load & parse cfg.xml file
      
             
       SessionFactory sessionFactory = configuration.buildSessionFactory();
       
       Session session = sessionFactory.openSession();
      
       Transaction txn = session.beginTransaction();
/*
       // Insert department with 2 Employees
       // Also We can insert only Department
       
       Department d = new Department();
       d.setDno(111);
       d.setName("CS");
       
       Employee e1 = new Employee();
       e1.setEno(101);
       e1.setName("AAA");
       e1.setSalary(1000.0);
      // e1.setDept(d);
       
       Employee e2 = new Employee();
       e2.setEno(103);
       e2.setName("CCC");
       e2.setSalary(3000.0);
       //e2.setDept(d);
       
       List<Employee> emps = new ArrayList<>();
       emps.add(e1);
       emps.add(e2);
       d.setEmps(emps);
       
      // d.setEmps(Arrays.asList(e1, e2));
       
       session.save(d);
*/

/******************* Dont Forget to remove 'create' property from .cfg file ***********/       

/*       
       
       //--------------------Save single employee, department 222 will also get Saved Automatically-----------------------------------					
      
        // In Employee class property 'dept' must have @ManyToOne(cascade = CascadeType.ALL) - remove this cascade property you will get constrainet violation excetion
        // MySQLDialect class changed to 8 from 5 - org.hibernate.dialect.MySQL8Dialect
        // Engine MyISAM ➔ No Foreign Key Support ,
       	//	Engine InnoDB ➔ Foreign Key Support 
        // MySQL8Dialect class - Hibernate will default to InnoDB with newer MySQL versions if you use the correct dialect.
         			
         			
         		Department d = new Department();
        
       			d.setDid(222);
       			d.setDname("Arts");
       		
       			Employee e = new Employee();
       			e.setEid(102);
       			e.setEname("BBB");
       			e.setEsalary(8000.0);
       			e.setDept(d);
       			session.save(e);
     
*/       
/*       
       //-----------Trying to save only Employee---------------
       // Here I'm saving employee object but its dept is null
       // Here I'm checking wheather dept_id(Foreign Key) can be inserted 'NULL' in table
       // It will insert NULL in dept_id column, if we dont want that then use property in nullable=false in @JoinColumn(name="dept_id", nullable=false)
       Employee e = new Employee();
       e.setEid(104);
       e.setEname("DDD");
       e.setEsalary(9000.0);
       session.save(e);
 */
 
/*      
       // Suppeose emp-104 has dept_id NULL. now you want to allocate some department to this employee 
       
       Department d=  session.get(Department.class, 222);
       Employee e = session.get(Employee.class, 104);
       e.setDept(d);
       session.update(e);
*/
       
/*       
       //---------- Inserting only Department----------------
     
       Department d2 = new Department();
       d2.setDid(333);
       d2.setDname("ARTS");
       session.save(d2);
*/ 

/*
         
 
//-------------- Try to Assign Employee to an Existing Department -------

       
      // Here Dept-222 does not have any Employee
      // So trying to Initialize Employee-102 to that department
       
       Department d = session.get(Department.class, 222); // 222 ARTS emps
       
       Employee e = new Employee();
       e.setEid(105);
       e.setEname("EEE");
       e.setEsalary(2000.0);
       e.setDept(d);
      
       List<Employee> emps = d.getEmps();
       emps.add(e);
       session.save(d);
*/
 
       
/*  
       // This is another way to assign Employees to Existing Department
       // Here I'm Assigning Dept-id 222 to Employee 102
        	
       Department d = session.get(Department.class, 222); 
       
       Employee e = new Employee();
       e.setEid(106);
       e.setEname("FFF");
       e.setEsalary(2000.0);
       e.setDept(d);
       session.save(e);
*/
 
//---------------------Fetching Logics-------------------------------       
       
/*    
  	   // Fetch Department Data with its Emlpoyees's list
  	    	
       Department d = session.get(Department.class, 111); // dno dname emps
       
       System.out.println("----------------------------------");
       System.out.println("------- DEPARTMENT INFORMATION-----");
       System.out.println("----------------------------------");
       
       System.out.println("DEPT NO   : " +  d.getDid());
       System.out.println("DEPT NAME : " +  d.getDname());
       
       System.out.println("----------------------------------");
       System.out.println("-------EMPLOYEE INFORMATION-------");
       System.out.println("----------------------------------");
       List<Employee> emps = d.getEmps(); // cntl+1+Enter
       for(Employee e :  emps)
       {
    	   System.out.println("EMP NO      :" +  e.getEid());
    	   System.out.println("EMP NAME    :" +  e.getEname());
    	   System.out.println("EMP SALARY  :" +  e.getEsalary());
    	   System.out.println("----------------------------------");
       }
*/     

/*
 		// Fetch Data of Employee with its Department data
 		 
       Employee e = session.get(Employee.class, 101); // eno ename esalry dept
       System.out.println("EMP NO      :" +  e.getEid());
	   System.out.println("EMP NAME    :" +  e.getEname());
	   System.out.println("EMP SALARY  :" +  e.getEsalary());
	   System.out.println("----------------------------------");
       
	   System.out.println("Department Information");
	   Department d = e.getDept();
	   System.out.println("DEPT NO   : " +  d.getDid());
       System.out.println("DEPT NAME : " +  d.getDname());
*/     

/*       
     //------------------When I delete Dept then Respective Emp should get deleted-------------------------------------------------

		Department d = session.get(Department.class, 222);
		session.delete(d);
*/   

/*
     //----------- Emp-102 want to change its dept from 222 to 111
       
       Employee e = session.get(Employee.class, 102);
       Department d = session.get(Department.class, 111);
       e.setDept(d);
       session.update(e);
*/       
       
        txn.commit();
    	session.close();	
       	sessionFactory.close();
       	
       	System.out.println("Done !!");
    }
}







