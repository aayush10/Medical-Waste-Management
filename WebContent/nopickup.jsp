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
    cursor: pointer;
}
input[type = date]{
	padding: 12px 29px;
	border : 1px solid #ccc;
	border-radius: 4px;
}
input[type = number]{
	width:100px;
	margin-top: 12px;
	padding: 8px 20px;
	border : 1px solid #ccc;
	border-radius: 4px;
}
#butt{
	width: 100px;
	background-color: #4CAF50;
	color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
input[type=submit]:hover {
    background-color: #45a049;
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function checkPrice(){
		var kg = document.getElementById("weight").value;
		var money = kg*10;
		document.getElementById("pric").innerHTML = money;
		return false;
	}
</script>
</head>
<body class = "bg" align = "center" id ="cen">
	<h3> ${name }You have no picks yet. Book one!</h3>
	<form name = "bookPickup" method = "post" action = "booked"><b>
	Appointment date: <input type="date" name = "bookDay"><br>
	Time : <input style = "margin-top: 18px;" type = "radio" name = "time" value = "Morning"> Morning<br>
			<input type = "radio" name = "time" value = "Evening">Evening<br>
			
	Expected Waste(in kgs) <input type = "number" id = "weight" name = "weight">
	<p id = "pric"></p>
	<button id = "butt" onclick= "return checkPrice()"> Check Price</button>
	<input type = "submit" value = "book"> </b>
	</form>
</body>
</html>