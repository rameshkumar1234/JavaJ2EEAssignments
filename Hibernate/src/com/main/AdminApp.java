package com.main;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.dao.DB;
import com.model.Restaurant;



public class AdminApp {

	public static void main(String[] args) {


		Restaurant restaurant1 = new Restaurant(null, "Khalsha fast food", "+91 81456 94595", "Atam park Nagar", "Veg", 4.4, "12:00 to 22:00");

		Restaurant restaurant2 = new Restaurant();
		restaurant2.setRid(null);
		restaurant2.setName("Table By Basant");
		restaurant2.setAddress("Feroze Gandhi Market");
		restaurant2.setPhone("+91 98091 12345");
		restaurant2.setType("Veg-NonVeg");
		restaurant2.setRatings(5.0);
		restaurant2.setOperatingHours("10:00 to 24:00");

		System.out.println(restaurant1);
		System.out.println(restaurant2);

		
		DB db = new DB();
		db.saveObject(restaurant1);
		db.close();

	}

}