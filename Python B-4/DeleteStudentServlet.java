package com.tca.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tca.entities.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteStudentServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		/* Purpose :
		 * When First time made a request or click on 'Search/Refresh' Button show all student data 
		 */
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
		
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		
		final String DB_URL = "jdbc:postgresql://localhost/ajdb20";
		final String DB_USER= "root";
		final String DB_PWD = "root@123";
		final String DB_DRIVER="org.postgresql.Driver";
		
		String qry="";
		
		String sbtn = request.getParameter("sbtn");
		String srno = request.getParameter("srno");  // String srno="";
		
		if(sbtn==null  || srno.isEmpty() || sbtn.equals("Refresh"))
		{		
			qry = "SELECT * FROM STUDENT ORDER BY RNO";
		}
		else if(sbtn.equals("Search"))
		{
			
			qry = "SELECT * FROM STUDENT WHERE rno="+srno;
		}
		
		try
		{
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL,DB_USER, DB_PWD);
			ps =  con.prepareStatement(qry);
			rs = ps.executeQuery();
			
			
			List<Student> L = new ArrayList<>(); // L --> [101..],[102..],[103.]
			
			while(rs.next())
			{
				int rno = rs.getInt("rno");
				String name = rs.getString("name");
				double per = rs.getDouble("per");
				
				L.add( new Student(rno,name,per) );
			}
			
			
			
			/* Redirecting List of Student to view Layer */
			
			request.setAttribute("students", L );
			RequestDispatcher rd = request.getRequestDispatcher("DeleteStudent.jsp");
			rd.forward(request, response);
				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				rs.close();
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*
		 * Purpose:
		 * Delete Student based on Roll number.
		 * Rollnumber is sent from Java Script
		 */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Connection con = null;
		PreparedStatement ps= null;
		
		final String DB_URL = "jdbc:postgresql://localhost/ajdb20";
		final String DB_USER= "root";
		final String DB_PWD = "root@123";
		final String DB_DRIVER="org.postgresql.Driver";
		
		String trno = request.getParameter("trno");
		String qry  = "DELETE FROM student WHERE rno="+trno; 
				
		try
		{
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL,DB_USER, DB_PWD);
			ps =  con.prepareStatement(qry);
			
			ps.executeUpdate();
			
			out.println("success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("failed");
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				out.println("failed");
			}
		}
		
	}
	
}
