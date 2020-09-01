<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
</head>
<body>

	<%!
		Date date = new Date();
		String today = date.toString();
	%>

	<center>
		<br/>
		------------------------------------
		<h3>Welcome to LMS</h3>
		Work hard, Get Luckier
		<br/>
		<%= today %>
		<br/>
		------------------------------------
		<br/>
	</center>

</body>
</html>