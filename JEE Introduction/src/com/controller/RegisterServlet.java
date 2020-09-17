package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({ "/RegisterServlet", "/Register" })
public class RegisterServlet extends HttpServlet {
	
	DB db;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("RegisterServlet - init executed");
		db = new DB();
		db.createConnection();
	}

	
	public void destroy() {
		System.out.println("RegisterServlet - destroy executed");
		db.closeConnection();
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet - service executed");
		
		User user = new User();
		
		user.name = request.getParameter("txtName");
		user.email = request.getParameter("txtEmail");
		user.password = request.getParameter("txtPassword");
		System.out.println(user);
		
		String message = db.registerUser(user);
		System.out.println(message);
		
		response.setContentType("text/html");
		String htmlResponse = "<html><body><center>Response: "+message+"</center></body></html>";

		PrintWriter out = response.getWriter();
		out.print(htmlResponse); 

		
	}

}
