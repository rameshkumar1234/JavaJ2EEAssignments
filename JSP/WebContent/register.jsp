
<%@page import="com.dao.DB"%>
<%@ page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file = "header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	
	
	<center>
	<%
		User user = new User();
	
		// Capture data from Client Request :)
		// request is refering to Object of HttpServletRequest from where we can extract the data
		// PS: JSP files are converted into Servlet eventually :)
		// Implicit Objects: out, request, response
		user.name = request.getParameter("txtName");
		user.email = request.getParameter("txtEmail");
		user.password = request.getParameter("txtPassword");
		
		DB db = new DB();
		db.createConnection();
		String message = db.registerUser(user);
		
		
	%>

	Response: <%= message %>
	<br/>

	</center>


</body>
</html>