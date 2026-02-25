package com.tca.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/addstudent")
public class AddStudentServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.sendRedirect("./AddStudent.jsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("./AddStudent.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String message="";
			
		
		int rno = Integer.parseInt(request.getParameter("rno"));
	    String name = request.getParameter("name");
		double per = Double.parseDouble(request.getParameter("per"));
		
		Connection con=null;
		PreparedStatement ps=null;
		
		final String DB_URL = "jdbc:postgresql://localhost/ajdb20";
		final String DB_USER= "root";
		final String DB_PWD = "root@123";
		final String DB_DRIVER="org.postgresql.Driver";
		
		try
		{
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
			
			con.setAutoCommit(false);
			ps =  con.prepareStatement("INSERT INTO student VALUES(?,?,?)");
			ps.setInt(1, rno);
			ps.setString(2, name);
			ps.setDouble(3, per);
			
			ps.executeUpdate();
			con.commit();
			
			message = "<div class='alert alert-success mt-3 text-center' role='alert'>Registration Succesful for Roll Number :" +  rno +"</div>";
			
		}
		catch(Exception e)
		{
			message = "<div class='alert alert-danger mt-3 text-center' role='alert'>Registration Failed !!! </div>";
			
			e.printStackTrace();
			
			try 
			{
				con.rollback();
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally
		{
			try 
			{
				con.close();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
		
		/* Redirecting Message to view Layer */
		request.setAttribute("msg", message);
		RequestDispatcher rd = request.getRequestDispatcher("AddStudent.jsp");
		rd.forward(request, response);
		
	}

}



