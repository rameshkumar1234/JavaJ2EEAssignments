package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.model.Restaurant;

public class DB {
	
	SessionFactory sessionFactory;
	Session session;
	Transaction transaction;
	
	public DB() {
		

			try {

				// Reading hibernate.cfg.xml file and managing the operations in the background
				StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


			} catch (Exception e) {
				System.out.println("Some Exception: "+e);
			}

			// Obtain Connection to the DataBase
			
			
			session = sessionFactory.openSession();

			// Obtain Transaction from the Session
			transaction = session.getTransaction();
			
	}
	
	public String saveObject(Object object) {

		transaction.begin();
		session.save(object);
		transaction.commit();

		System.out.println("Object Saved");
		return "Object Saved in Database :)";
	}

	public List<Restaurant> fetchAllObjects() {
		// Explore new API against createCriteria here :)
		Criteria criteria = session.createCriteria(Restaurant.class);
		//criteria.add(Restrictions.gt("ratings", 4.8));

		List<Restaurant> restaurants = criteria.list();
		return restaurants;
	}
	
	public void close() {
		session.close();
		sessionFactory.close();
		System.out.println("Session Closed :)");
	}

	
}
