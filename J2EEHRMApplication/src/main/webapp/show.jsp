<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.csi.service.EmployeeServiceImpl"%>


<%@page import="com.csi.service.EmployeeService"%>
<%@page import="com.csi.model.Employee"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Employee</title>
</head>
<body>
<%
	EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
	List<Employee> empList = employeeServiceImpl.getAllData();
	//Iterator<UserBean> itr = userList.iterator();
%>
<table border="1">
	<tr>
		<th>Id</th>
		<th><a
			href="EmployeeController?action=bynamesort">Name</a></th>
		<th>Employee Address</th>
		<th>Employee Gender</th>
		<th>Employee DOB</th>
		<th>Employee Contact Number</th>
		<th><a
			href="EmployeeController?action=byemailsort">Email</a></th>
		<th>Employee Password</th>
	</tr>
	<tr>
		<%
			/*while(itr.hasNext())
			 {
			 System.out.println(user.getId());*/
			for (Employee employee : empList) {
			
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String dob = simpleDateFormat.format(employee.getEmpDOB());
				
				
		%>
		<td><%=employee.getEmpId()%></td>
		<td><%=employee.getEmpName()%></td>
		<td><%=employee.getEmpAddress()%></td>
		<td><%=employee.getEmpGender()%></td>
		<td><%=dob%></td>
		<td><%=employee.getEmpContactNumber()%></td>
		<td><%=employee.getEmpEmailId()%></td>
		<td><%=employee.getEmpPassword()%></td>
		<td><a
			href="EmployeeController?action=editform&empid=<%=employee.getEmpId()%>">Update</a></td>
		<td><a
			href="EmployeeController?action=delete&empid=<%=employee.getEmpId()%>">Delete</a></td>

	</tr>
	<%
		}
		//}
	%>
</table>

<a href="EmployeeController?action=deletealldata">Delete All Data</a>
<p><a href="signup.jsp">Add Employee</a></p>
</body>
</html>