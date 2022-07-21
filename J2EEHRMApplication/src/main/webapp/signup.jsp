<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>
</head>
<body style="background-color: gray">
<form action="EmployeeController" method="get">

<input type="hidden" name="action" value="signup">

<label>Employee Id</label>
<input type="text" name="empid"><br>

<label>Employee Name</label>
<input type="text" name="empname"><br>

<label>Employee Address</label>
<input type="text" name="empaddress"><br>

<label>Gender</label><br>
<label>Male</label>
<input type="radio" name="empgender" value="male">
<label>Female</label>
<input type="radio" name="empgender" value="female">
<br>
<label>Employee Contact Number</label>
<input type="text" name="empcontactnumber"><br>


<label>Employee DOB</label>
<input type="text" name="empdob"><br>

<label>Employee Email</label>
<input type="text" name="empemailid"><br>


<label>Employee Password</label>
<input type="password" name="emppassword"><br>

<input type="submit" value="SignUp">

</form>
</body>
</html>