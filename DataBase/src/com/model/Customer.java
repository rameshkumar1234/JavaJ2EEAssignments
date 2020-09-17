package com.model;

public class Customer{// extends Object{ Every class in Java is by default Child of a built in class i.e. Object :)
	
	// Attributes
	public int id;
	public String name;
	public String phone;
	public String email;
	public double temperature;
	
	public String entryDateTime;
	public String exitDateTime;
	
	public Customer(){
		
	}
	
	 public Customer(String name, String phone, String email, double temperature, String entryDateTime, String exitDateTime) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.temperature = temperature;
		this.entryDateTime = entryDateTime;
		this.exitDateTime = exitDateTime;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", temperature="
				+ temperature + ", entryDateTime=" + entryDateTime + ", exitDateTime=" + exitDateTime + "]";
	}
	
	// Override toString function of the Parent Class Object here
	
}