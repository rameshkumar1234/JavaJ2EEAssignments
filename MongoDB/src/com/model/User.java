package com.model;

import org.bson.Document;

public class User {
	
		public String name;
		public String email;
		public String password;
		
		public User() {
			
		}
		
		public User(String name, String email, String password) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
		}
		
		public Document toDocument() {
			// Document is a HashMap and is available from library bson
			Document document = new Document();

			document.append("name", name);
			document.append("email", email);
			document.append("password", Util.encryptStirng(password));

			return document;
		}
		
		@Override
		public String toString() {
			return "User [name=" + name + ", email=" + email + ", password=" + password + "]";
		}
		
}
