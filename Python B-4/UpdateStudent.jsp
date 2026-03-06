<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, com.tca.entities.*" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Student</title>
    
    
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    <script type="text/javascript">
    	
    function openUpdatePopup(trno)
    {
   		var tr = document.getElementById(trno);
		var td = tr.getElementsByTagName("td");  //td[0]:101  td[1]:aaa  td[2]:60
		
		var srno = td[0].textContent;
		var sname = td[1].textContent;
		var sper = td[2].textContent;
		
		//alert("Hi--->" + srno + "---" + sname + "-- "+sper);
			
		
		var modalRno = document.getElementById("modalRno");
		var modalName = document.getElementById("modalName");
		var modalPer = document.getElementById("modalPer");
		
		modalRno.value = srno;
		modalName.value = sname;
		modalPer.value = sper;
		
 		new bootstrap.Modal(document.getElementById("updateModal")).show();
    }
    
    function modify()
    {
    		//alert("I am ready to modify !!!");
    		
    		var modalRno = document.getElementById("modalRno");
    		var modalName = document.getElementById("modalName");
    		var modalPer = document.getElementById("modalPer");
    		
    		var updateSrno = modalRno.value;
    		var updateSname = modalName.value;
    		var updatePer = modalPer.value;
    		
    		//alert("Update Servlet -->"+ updateSrno +" *****" + updateSname +"****" + updatePer );
    		
    		fetch("http://localhost:8080/APP-Student-Mgmt-System/update", 
    				
    				{
    					method: 'POST',
    					body  : new URLSearchParams({"trno":updateSrno, "tname": updateSname, "tper": updatePer})
    				}		
    		)
    		.then(response => response.text())
    		.then(data =>
    					{
    						if(data.trim() == 'success')	
    						{
    							   	
    							document.querySelector('#updateModal  .btn-close').click();
    		
    							
    							swal("Success", "Record is Updated Succesfully !!!", "success")
    							.then( () => 
    										{
    											location.reload()
    										}
    							);
    							
    							
    						}
    						if(data.trim() == 'failed')	
    						{
    							swal("Failure", "Failed to Update Record !!!", "error");
    						}
    					}
    		
    		)
    		.catch(err => console.error("Error:", err))
    }
    		
    	
    </script>
    	
    		
    
    
    
  </head>
<body>

<div class="container" style="margin-top: 100px;">

<h2 class="text-center text-primary mt-5 mb-3"> Student Information </h2>

<div class="d-flex justify-content-end">
	
	<form class="d-flex  mb-4" role="search" method="GET" action="./update">
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
					<button type="button" class="btn btn-primary" onclick="openUpdatePopup(<%= s.getRno()  %>)" > Update</button>
				</td>
			</tr>
<% 		
		}
	}//else

%>
</table>

</div>  <!-- end of container tag -->


<!-- Modal -->

<div class="modal" tabindex="-1" id="updateModal">
  <div class="modal-dialog">
    <div class="modal-content">


    <!-- header part -->
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title ">Update Student</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      	
      </div>


      <!-- body part -->
      <div class="modal-body">
      	
      	<div>
      		<label class="form-label"> Roll Number</label>
      		<input type="text" class="form-control" id="modalRno" readonly>
      	</div>
        		
        <div>
        			<label class="form-label"> Name </label>
      			<input type="text" class="form-control" id="modalName" >
        </div>
        
        <div>
        			<label class="form-label"> Percentage </label>
      			<input type="text" class="form-control" id="modalPer" >
        </div>
        
      </div>
      
      
      <!-- footer part -->

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="modify()">Update changes</button>
      </div>
    </div>
  </div>
</div>




</body>

</html>
