<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, com.tca.entities.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Display Student</title>
    
  </head>
<body>

<div class="container" style="margin-top: 100px;">

<h2 class="text-center text-primary mt-5 mb-3"> Student Information </h2>

<div class="d-flex justify-content-end">
	
	<form class="d-flex  mb-4" role="search" method="GET" action="./display">
		<input class="form-control me-3" type="search" name="srno" placeholder="Search Here">
		<input class="btn btn-outline-success me-3" type="submit" name="sbtn" value="Search">
		<input class="btn btn-outline-success" type="submit" name="sbtn" value="Refresh">		
	</form>
</div>

<table class="table table-hover table-bordered text-center" >
<thead >
	<tr class="table-primary">
		<th> RNO </th> <th> NAME</th> <th>PER</th>
	</tr>
</thead>

<c:if test="${empty students}">
    		<tr>
			<td class ="text-danger bg-danger-subtle" colspan="3"> No Data Found !!! </td>
		</tr>
</c:if>

<c:if test="${not empty students}">

	<c:forEach var="sob" items="${students}">
	<tr>
         <td>${sob.rno}</td>
         <td>${sob.name}</td>
        <td>${sob.per}</td>
    </tr>
 </c:forEach>
</c:if>



	</table>


</div>  <!-- end of container tag -->



</body>
</html>