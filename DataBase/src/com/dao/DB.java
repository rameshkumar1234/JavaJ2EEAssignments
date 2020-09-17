package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.*;
import com.mysql.cj.xdevapi.PreparableStatement;


public class DB {
	
	Connection connection;
	Statement statement;
	PreparedStatement prepStmt;
	
	CallableStatement callStmt;
	
	
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
	
	public String addCustomer(Customer customer) {
		
		String message = "NA";
		
	/*	try {
			
			String sql = "insert into Customer values(null, '"+customer.name+"', '"+customer.phone+"', '"+customer.email+"', "+customer.temperature+", '"+customer.entryDateTime+"' ,'"+customer.exitDateTime+"')";
			System.out.println("SQL: "+sql);
			statement = connection.createStatement();
			int i = statement.executeUpdate(sql);
			
			if(i>0) {
				message = customer.name+" Added in DataBase :)";
			}else {
				message = customer.name+" NOT Added in DataBase :)";
			}
			
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		*/
		
	try {
			
			String sql = "insert into Customer values(null, ?, ?, ?,?,?,?)";
			
			prepStmt = connection.prepareStatement(sql);
			prepStmt.setString(1, customer.name);
			prepStmt.setString(2, customer.phone);
			prepStmt.setString(3, customer.email);
			prepStmt.setFloat(4, (float) customer.temperature);
			prepStmt.setString(5, customer.entryDateTime);
			prepStmt.setString(6, customer.exitDateTime);
			
			int i = prepStmt.executeUpdate();
			
			if(i>0) {
				message = customer.name+" Added in DataBase :)";
			}else {
				message = customer.name+" NOT Added in DataBase :)";
			}
			
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		
		return message;
	}
	
	public void executeProcedureInDB(Customer customer) {
		
			try {
			
			String sql = "{ Call addCustomer(?, ?, ?,?,?,?)}";
			
			callStmt = connection.prepareCall(sql);
			callStmt.setString(1, customer.name);
			callStmt.setString(2, customer.phone);
			callStmt.setString(3, customer.email);
			callStmt.setFloat(4, (float) customer.temperature);
			callStmt.setString(5, customer.entryDateTime);
			callStmt.setString(6, customer.exitDateTime);
			
			callStmt.execute();
			System.out.println("Procedure Executed :)");
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		
	}
	
	public void executeBatchTransaction() {
		try {
			
			String sql1 = "update Customer set phone = '+91 45214 25425' where id=8";
			String sql2 = "delete from customer where cid=3";
			
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			
			statement.executeBatch();
			connection.commit();
			System.out.println("Batch Executed :)");
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
			
			try {
				
				System.out.println("ERROR OCCURED AND TRANSANCTION ROLLBACK :(");
				connection.rollback();
				
			} catch (Exception e2) {
				System.out.println("Some Exception "+e2);
			}
		}
	}
	
	
	public String markExit(String exitDateTime, int customerId) {
		String message = "NA";
		try {
			String sql = "update Customer set exitDateTime = ? where id = ?";
			prepStmt = connection.prepareStatement(sql);
			prepStmt.setString(1, exitDateTime);
			prepStmt.setInt(2, customerId);
			
			int i = prepStmt.executeUpdate();
			
			if(i>0) {
				message = customerId+" Exit time Updated in DataBase :)";
			}else {
				message = customerId+" Exit time NOT Updated in DataBase :)";
			}
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		
		return message;
	}
	
	public String deleteCustomerByID(int customerId) {
		String message = "NA";
		try {
			String sql = "delete from Customer where id = ?";
			prepStmt = connection.prepareStatement(sql);
			prepStmt.setInt(1, customerId);
			
			int i = prepStmt.executeUpdate();
			
			if(i>0) {
				message = customerId+" Deleted from DataBase :)";
			}else {
				message = customerId+" NOT deleted in DataBase :)";
			}
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		
		return message;
	}
	
	public String deleteCustomerByPhone(String customerPhone) {
		String message = "NA";
		try {
			String sql = "delete from Customer where phone = ?";
			prepStmt = connection.prepareStatement(sql);
			prepStmt.setString(1, customerPhone);
			
			int i = prepStmt.executeUpdate();
			
			if(i>0) {
				message = customerPhone+" Deleted from DataBase :)";
			}else {
				message = customerPhone+" NOT deleted in DataBase :)";
			}
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		
		return message;
	}
	
	public void fetchCustomerWithPhone(String phone) {
		
		try {
			String sql = "select * from Customer where phone = ?";
			prepStmt = connection.prepareStatement(sql);
			prepStmt.setString(1, phone);
			
			ResultSet resultSet= prepStmt.executeQuery();
			
			if(resultSet.next()) {
				System.out.println("Cutomer Found");
				
				Customer customer = new Customer();
				customer.id = resultSet.getInt(1);
				customer.name = resultSet.getString(2);
				customer.phone = resultSet.getString(3);
				customer.email = resultSet.getString(4);
				customer.temperature = resultSet.getFloat(5);
				customer.entryDateTime = resultSet.getString(6);
				customer.exitDateTime = resultSet.getString(7);
				
				System.out.println(customer);
			}else {
				System.out.println("Cutomer NOT Found");
			}
			
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
	}
	
	public ArrayList<Customer> fetchAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		try {
			String sql = "select * from Customer";
			prepStmt = connection.prepareStatement(sql);
			
			ResultSet resultSet = prepStmt.executeQuery();
			
			while(resultSet.next()) {
				
				Customer customer = new Customer();
				customer.id = resultSet.getInt(1);
				customer.name = resultSet.getString(2);
				customer.phone = resultSet.getString(3);
				customer.email = resultSet.getString(4);
				customer.temperature = resultSet.getFloat(5);
				customer.entryDateTime = resultSet.getString(6);
				customer.exitDateTime = resultSet.getString(7);
				
				customers.add(customer);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
		return customers;
	}
	
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection Closed");
		} catch (Exception e) {
			System.out.println("Some Exception "+e);
		}
	}
	
}
