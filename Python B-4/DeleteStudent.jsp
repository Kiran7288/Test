<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, com.tca.entities.*" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Delete Student</title>
   
    
    <script type="text/javascript">
    
    		function del(srno)
    		{
    			var status = confirm("Do you want to delete Student for Roll Number : " + srno);
    			
    			if(status==true)
    			{
    				// call a DeleteServlet with rno as trno=101 with REQUEST_METHOD = POST
    				// collect status[success, failed] from servlet
    				
    				// suceess --> show delete message
    				// failed  --> failure message
    				
    				fetch("http://localhost:8080/APP-Student-Mgmt-System/delete", 
    						{
    							method :'POST',
    							body   : new URLSearchParams({'trno': srno})
    						}
    				)
    				.then(response => response.text())
    				.then(data  => 
    							{
    								if(data.trim() == "success")
    								{
    									alert("Record is Deleted for Roll Number :" + srno);
    									
    									var tr = document.getElementById(srno);
    				    					tr.remove();
    								}
    								if(data.trim()=="failed")
    								{
    									alert("Failed to Delete Record for Roll Number :" + srno);
    								}
    							}
    				)
    				.catch( error => console.error("MyError while Deleting Rollnumber =" + srno));
    					
    			}
    			else
    			{
    				alert("Delete Skipped !!");
    			}
    		}
    	
    </script>
    
    
    
    
  </head>
<body>

<div class="container" style="margin-top: 100px;">

<h2 class="text-center text-primary mt-5 mb-3"> Student Information </h2>

<div class="d-flex justify-content-end">
	
	<form class="d-flex  mb-4" role="search" method="GET" action="./delete">
		<input class="form-control me-3" type="search" name="srno" placeholder="Search Here">
		<input class="btn btn-outline-success me-3" type="submit" name="sbtn" value="Search">
		<input class="btn btn-outline-success" type="submit" name="sbtn" value="Refresh">		
	</form>
</div>

<table class="table table-hover table-bordered text-center" >
<thead >
	<tr class="table-primary">
		<th> RNO </th> <th> NAME</th> <th>PER</th> <th> ACTION</th>
	</tr>
</thead>

<%
	List<Student> L = (List<Student>) request.getAttribute("students");
	
	if(L.isEmpty())
	{
%>
		<tr>
			<td class ="text-danger bg-danger-subtle" colspan="4"> No Data Found !!! </td>
		</tr>
<% 
	}
	else
	{

		for( Student s : L)
		{
%>
			<tr id="<%= s.getRno()  %>">
			
				<td> <%= s.getRno()  %>  </td>
				<td> <%= s.getName() %> </td>
				<td> <%= s.getPer()  %> </td>
				<td>
					<button type="button" class="btn btn-danger" onclick="del(<%= s.getRno()  %>)" >Delete</button>
				</td>
			</tr>
<% 		
		}
	}//else

%>
</table>


</div>  <!-- end of container tag -->



</body>
</html>