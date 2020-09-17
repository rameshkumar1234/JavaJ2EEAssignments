package com.beans;

public class Connection {

	String url;
	String user;
	String password;

	public Connection() {
		System.out.println("Connection Object Constructed - Default constructor");
	}

	public Connection(String url, String user, String password) {
		System.out.println("Connection Object Constructed - Parameterized constructor");
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Connection [url=" + url + ", user=" + user + ", password=" + password + "]";
	}
	
	public void myInit() {
		System.out.println("myInit executed");
	}

	public void myDestroy() {
		System.out.println("myDestroy executed");
	}
	
}
