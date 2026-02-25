<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TCA, Pune</title>
    
</head>

<body>

<div class="container" style="width: 500px; margin-top: 100px;">


<h2 class="text-center text-primary mt-5 mb-3"> Registration Form </h2>

<form method="POST" action="./addstudent">

<div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Roll Number</label>
    <input type="text"  name="rno" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
</div>

<div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Name</label>
    <input type="text"  name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
</div>


<div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Percentage</label>
    <input type="text"  name="per" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
</div>
	
<div class="d-grid gap-2">	
	<input type="submit" class="btn btn-primary" value="Save">
</div>

</form>


<p> ${msg}  </p>

</div>


</body>
</html>