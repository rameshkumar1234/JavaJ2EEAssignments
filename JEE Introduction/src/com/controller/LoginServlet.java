package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DB;
import com.model.User;


@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {
	DB db;
	
	public void init(ServletConfig config) throws ServletException {
		db = new DB();
		db.createConnection();
	}

	
	public void destroy() {
		db.closeConnection();
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user = new User();
		
		user.email = request.getParameter("txtEmail");
		user.password = request.getParameter("txtPassword");
		System.out.println(user);
		
		boolean status = db.loginUser(user);
		
		String message = "";
		String url = "'Welcome'";
		
		if(status) {
			
//			url = "'Welcome?name="+user.name+"&email="+user.email+"'";
//			String linkToHome = "<br/><a href="+url+">Click to Enter Home</a><br/>";
			
			/*String form = "<form action='Welcome' method='post'>"
					+ "<input type='hidden' name='name' value='"+user.name+"'/>"
					+ "<input type='hidden' name='email' value='"+user.email+"'/>"
					+ "<input type='submit' value='Click to Enter Home'/>"
					+ "</form>";
			
			message = user.name+" Login Success <br/>"+form;*/
//			Cookie cookie1 = new Cookie("keyName", user.name);
//			Cookie cookie2 = new Cookie("keyEmail", user.email);

			//cookie1.setMaxAge(60);

//			response.addCookie(cookie1);
//			response.addCookie(cookie2);
			
			HttpSession session = request.getSession();
			session.setAttribute("keyUser", user);
			
			String linkToHome = "<br/><a href="+url+">Click to Enter Home</a><br/>";
			message = user.name+" Login Success"+linkToHome;

			
			
		}else {
			message = "Invalid Credientials :(";
		}
		
		response.setContentType("text/html");
		String htmlResponse = "<html><body><center>Response: "+message+"</center></body></html>";

		PrintWriter out = response.getWriter();
		out.print(htmlResponse); 

		
	}
}
