
<%@page import="com.dao.DB"%>
<%@ page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file = "header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	
	<center>
	<%
		User user = new User();
	
		// Capture data from Client Request :)
		// request is refering to Object of HttpServletRequest from where we can extract the data
		// PS: JSP files are converted into Servlet eventually :)
		// Implicit Objects: out, request, response
		user.email = request.getParameter("txtEmail");
		user.password = request.getParameter("txtPassword");
		
		DB db = new DB();
		db.createConnection();
		boolean status = db.loginUser(user);
		
		String message = "";
		
		if(status){
			message = user.name+" Welcome to LMS";
			/*String url = "welcome.jsp?name="+user.name+"&email="+user.email;
			out.print("<a href='"+url+"'>ENTER HOME</a>");*/
			
			// session is reference variable which points to HttpSession Object
			session.setAttribute("keyUser", user);
			
		}else{
			message = "Invalid Credentials :(";
		}
		
	%>

	Response: <%= message %>
	<br/>
	<!-- <a href="welcome.jsp">ENTER HOME</a> -->
	<jsp:forward page="welcome.jsp"/>

	</center>


</body>
</html>