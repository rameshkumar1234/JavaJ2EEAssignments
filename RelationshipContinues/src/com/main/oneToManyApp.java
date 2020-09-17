package com.main;

import java.util.ArrayList;
import java.util.List;

import com.dao.DB;
import com.model.FoodItem;
import com.model.Restaurant;

public class oneToManyApp {

	public static void main(String[] args) {
		

		FoodItem item1 = new FoodItem(null, "SimpleBurger", 60);
		FoodItem item2 = new FoodItem(null, "Pizza", 199);
		FoodItem item3 = new FoodItem(null, "Noodles", 150);
		FoodItem item4 = new FoodItem(null, "Cupcake", 80);
		FoodItem item5 = new FoodItem(null, "Sandwich", 100);

		List<FoodItem> items = new ArrayList<FoodItem>();
		items.add(item1);
		items.add(item2);
		//items.add(item3);
		items.add(item4);
		//items.add(item5);

		//Restaurant restaurant = new Restaurant(null, "Raj Cafeteria", "veg", 4.5, "11:00 to 24:00", items);

		DB db = new DB();
		//db.saveObject(restaurant);

		Restaurant restaurant1 = db.getRestaurant(2);
		restaurant1.showRestaurant();

		//System.out.println();

		//Restaurant restaurant2 = db.getRestaurant(2);
		//restaurant2.showRestaurant();

		//System.out.println();
		//System.out.println("RE-READ the Same RECORD");
		//Restaurant restaurant3 = db.getRestaurant(1);
		//restaurant3.showRestaurant();

		// First Level Cache in Hibernate
		// Is maintained by Session Object

		// If we re-read the same record, Hibernate will not contact the database
		// Rather it will fetch the record from the Session Object and return it back

		// Till Time below db.close() which is not closing the Session, we will have the data available if its is read once :)
		db.close();

		// Second Level Of Cache
		// Data among multiple Sessions
		//  SessionFactory: For multiple Sessions for the SessionFactory data will be maintained  by it

		// Third Level Cache
		// We must manage it eg: EHCache customizations
	}

}

// Web Admin App should also save the FoodItem corresponding to a Restaurant
// 1. Clicking on Restaurant Navigate to another page with URL Rewriting for Restaurant ID and show its all of the restaurnats
// 2. Also show a Form to add FoodItems