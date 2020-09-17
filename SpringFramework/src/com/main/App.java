package com.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.beans.Connection;

public class App {

	public static void main(String[] args) {
		

		//Connection con1 = new Connection();
		
		/*con1.setUrl("jdbc:mysql://localhost/mydb");
		con1.setUser("root");
		con1.setPassword("pass1223");
		
		Connection con2 = new Connection("mongodb://cluster0.zbc.xyz.com", "atpl", "atpl123");
		
		System.out.println(con1);
		System.out.println(con2);*/
		
//		Resource resource = new ClassPathResource("beans.xml");	// Parse the XML File
//		BeanFactory factory = new XmlBeanFactory(resource);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		// ApplicationContext constructs the Objects before hand i.e. whenever ApplicationContext is conctructed


		Connection c1 = (Connection)context.getBean("con");
		//Connection c2 = context.getBean("con2", Connection.class);
		//Connection c1 = context.getBean("con", Connection.class);

		// Above Statements we will have the references to the objects configured in XML File
		System.out.println("c1 is: "+c1+" HashCode: "+c1.hashCode());
		//System.out.println("c2 is: "+c2+" HashCode: "+c2.hashCode());
		//System.out.println("c3 is: "+c3+" HashCode: "+c3.hashCode());
		
		((ClassPathXmlApplicationContext)context).close();
		
	}

}
