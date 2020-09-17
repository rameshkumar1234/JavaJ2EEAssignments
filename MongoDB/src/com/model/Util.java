package com.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	public static final String URI = "mongodb://Ramesh:Ram12345@cluster0-shard-00-00.3ny2j.mongodb.net:27017,cluster0-shard-00-01.3ny2j.mongodb.net:27017,cluster0-shard-00-02.3ny2j.mongodb.net:27017/MyDataBase?ssl=true&replicaSet=atlas-evnmn8-shard-0&authSource=admin&retryWrites=true&w=majority";
	public static final String DB_NAME = "MyDataBase";
	public static final String COLLECTION_USERS = "users";
	public static final String COLLECTION_CUSTOMERS = "customers";
	
	//Making your strings secure
	public static String encryptStirng(String input) {
		String encryptedOutput = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(input.getBytes());

			encryptedOutput = new String(digest.digest());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptedOutput;
	}

}
