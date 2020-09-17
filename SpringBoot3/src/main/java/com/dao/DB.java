package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.User;

public class DB {

	Connection connection;
	PreparedStatement prepStmt;
	
	
	
	public DB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded :)");
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
	}
	public void createConnection() {
		try {
			
			String url = "jdbc:mysql://localhost/mydb";
			
			String user = "root";
			String password = "";
			
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Created :)");
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
	}
	public String registerUser(User user) {

		String message = "NA";

		try {
			String sql = "insert into dbuser values(null, ?, ?, ?)";

			prepStmt = connection.prepareStatement(sql);
			prepStmt.setString(1, user.name);
			prepStmt.setString(2, user.email);
			prepStmt.setString(3, user.password);

			int i = prepStmt.executeUpdate(); // executeUpdate -> will perform SQL insert, update and delete commands

			if(i>0) {
				message = "Success: "+user.name+" Registered  :)";
			}else {
				message = "Failure: "+user.name+" NOT Registered :(";
			}

		} catch (Exception e) {
			System.out.println("Some Exception: "+e);
		}

		return message;
	}
	
	public boolean loginUser(User user) {

		boolean loginStatus = false;

		try {
			String sql = "select * from dbuser where email = ? and password = ?";


			prepStmt = connection.prepareStatement(sql);
			prepStmt.setString(1, user.email);
			prepStmt.setString(2, user.password);

			ResultSet rs = prepStmt.executeQuery(); // executeUpdate -> will perform SQL insert, update and delete commands

			loginStatus = rs.next();

			if(loginStatus) {
				user.name = rs.getString(2);
			}

		} catch (Exception e) {
			System.out.println("Some Exception: "+e);
		}

		return loginStatus;
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection Closed");
		} catch (Exception e) {
			System.out.println("Some Exception: "+e);
		}
	}
}
