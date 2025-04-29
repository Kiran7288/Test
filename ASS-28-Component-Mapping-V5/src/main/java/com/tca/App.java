package com.tca;


import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.ContactNumber;
import com.tca.entities.Student;

public class App 
{
	public static void main(String[] args) 
	{
		Configuration configuration = null;
		SessionFactory sf = null;
		Session session = null;
		Transaction txn = null;

		try 
		{
			configuration = new Configuration(); // conf--> data
			configuration.configure("hibernate.cfg.xml"); // load & parse XML files s -> user:root dname:hfb02 pwd=root

			configuration.addAnnotatedClass(Student.class);

			sf = configuration.buildSessionFactory();
			session = sf.openSession();
			txn = session.beginTransaction();

			// ------------------------Component Mapping ----------------
/*
 			// Adding a Record
 			 
			ContactNumber contact = new ContactNumber();
			contact.setCountryCode(91);
			contact.setContact("1122334455");
			
			Student S = new Student();
			S.setRno(101);
			S.setName("AAA");
			S.setPer(70.0);
			S.setCity("PUNE");
			S.setContact(contact);
			
			session.save(S);
*/			
			Student student = session.get(Student.class, 101);
			
			System.out.println("Roll Number : " + student.getRno());
			System.out.println("Name        : " + student.getName());
			System.out.println("Percentage  : " + student.getPer());
			System.out.println("City        : " + student.getCity());
			System.out.println("Contact     : +" + student.getContact().getCountryCode() + " - " + student.getContact().getContact()); 
			
					
			//------------------------------------------
			
			
			txn.commit();

			System.out.println("Done !!!");
		} 
		catch (Exception e) 
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
