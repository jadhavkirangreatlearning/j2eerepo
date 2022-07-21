<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignIn</title>
</head>
<body style="background-color: tomato">
<form action="EmployeeController" method="get">

<input type="hidden" name="action" value="signin">

<label>Employee Email</label>
<input type="text" name="empemailid"><br>


<label>Employee Password</label>
<input type="password" name="emppassword"><br>

<input type="submit" value="SignIn">
</form>
</body>
</html>