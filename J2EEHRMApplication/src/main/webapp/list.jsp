<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>DONE</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="http://www.fullstackjavadeveloper.in" class="navbar-brand"> User
					Employee Mgnt App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Employee</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Employees</h3>
			<hr>
			<div class="container text-left">

				<a href="signup.jsp" class="btn btn-success">Add
					New Employee Data</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Address</th>
						<th>Gender</th>
						<th>empcontactnumber</th>
						<th>empdob</th>
						<th>empemailid</th>
						<th>emppassword</th>
						
						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="employee" items="${listEmployee}">

						<tr>
							<td><c:out value="${employee.empId}" /></td>
							<td><c:out value="${employee.empName}" /></td>
							<td><c:out value="${employee.empAddress}" /></td>
							<td><c:out value="${employee.empGender}" /></td>
							<td><c:out value="${employee.empContactNumber}" /></td>
							<td><c:out value="${employee.empDOB}" /></td>
							<td><c:out value="${employee.empEmailId}" /></td>
							<td><c:out value="${employee.empPassword}" /></td>
							<td><a href="EmployeeController?action=delete&empid=<c:out value='${employee.empId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="EmployeeController?action=delete&empid=<c:out value='${employee.empId}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
			
			<a href="EmployeeController?action=sortbyname">Sort By Name</a>
		</div>
	</div>
</body>
</html>
