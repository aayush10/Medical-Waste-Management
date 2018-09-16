<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
#cen{
	margin-top : 100px;
}
.bg{
	background-image : url("hospital.jpg");
	background-repeat :no-repeat;
	background-position: center;
	background-size: cover;
}
input[type = text]{
	width:100px;
	padding: 8px 20px;
	border : 1px solid #ccc;
	border-radius: 4px;
}
input[type = password]{
	width: 100px;
	padding: 8px 20px;
	border : 1px solid #ccc;
	border-radius: 4px;
	margin-left : 10px;
}
input[type = submit]{
	width: 100px;
	background-color: #4CAF50;
	color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel = "stylesheet" type = "text/css" href = "login.css">

<script>
 function validation(){	
	 var username = document.loginForm.Uname.value;
	 var password = document.loginForm.pass.value;
	 if(username=="" || password == ""){
		 error = "Please enter all the fields";
		 document.getElementById("error").innerHTML = error;
		 return false;
	 }
 }
</script>
<script>
	function checkPass(){
		var err = ${err};
		error = "Invalid Credentials";
		document.getElementById("wrongPass").innerHTML = error;
		return false;
	}
</script>
</head>
<body class = "bg" id = "cen">
	<div id = "cen" align = "center">
	<form name = "loginForm" method = "get" onsubmit = "return validation()" action = "authenticate">
		<h3>UserName:</h3> <input type = "text" name = "Uname"><br>
		<h3>Password:</h3> <input type = "password" name = "pass"> <br></br>
		<p id = "error" style= "color:red;"></p>
		<p id = "wrongPass" style = "color: red;"></p>
		<input type = "submit" value = "login">
	</form>
	<script type="text/javascript">
		checkPass();
		return false;
	</script>
	<a href = "register.html">New User? Register</a>
	</div>
</body>
</html>