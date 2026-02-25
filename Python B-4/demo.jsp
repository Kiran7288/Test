<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <script>
 
 	function display(name)
 	{
 		alert("Hello, " + name);
 	}
 	
 	function modify()
 	{
 		//alert("I am Modiying your <H1> tag");
 		
 		var h1 = document.getElementById("101");
 		h1.innerText="Welcome to PUNE";
 		
 	}
 	
 	function update()
 	{
 		var tfsname = document.getElementById("sname");
 		var data = tfsname.value;
 		
 		 tfsname.value = data.toUpperCase();
 	}
 	
 </script>
 
  
</head>
<body>

<input type="button" value="Click Here" onclick= "display('sachin')" /> <br>
---------------------------------------------------------------------------------<br>

<h1 id="101" > Welcome to TCA </h1> <br>
<input type="button" value="Modify me" onclick= "modify()" /> <br>

---------------------------------------------------------------------------------<br>

<input id="sname" type="text" name="sname" onkeydown="update()"  placeholder="type here"/> <br>



</body>
</html>


