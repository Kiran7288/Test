package com.tca;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;


public class App {
	  public static void main(String[] args) 
	    {
	    	 Configuration configuration = null;
	    	 SessionFactory sf =null;
	    	 Session session=null;
	    	 Transaction txn=null;
	    	 
	    	
	    	 
	    	 try
	    	 {
	    	        configuration = new Configuration(); // conf--> data
	    	        configuration.configure("hibernate.cfg.xml");           // load & parse XML files s -> user:root dname:hfb02 pwd=root
	               
	    	        configuration.addAnnotatedClass(Student.class);
	    	        
	    	        sf = configuration.buildSessionFactory();
	    	        session = sf.openSession();
	       	        txn = session.beginTransaction();
	        
	       	        //----------------------------------------
	       	        
	    	        Student ob = new Student();
	    	        ob.setRno(888);
	    	        ob.setName("AAA");
	    	        ob.setPer(60.0);
	    	        session.save(ob);
	    	       
	    	    
	    	    
	    	          
	    	        txn.commit();
	    	       
	    	        System.out.println("Data is Saved Successfully !!!");
	    	 }
	    	 catch(Exception e)
	    	 {
	    		e.printStackTrace();
	    		txn.rollback();
	    		
	    	 }
	    	 finally
	    	 {
	    		 session.close();
	    		 sf.close();
	    	}
	        
	        
	        
	    }
}
