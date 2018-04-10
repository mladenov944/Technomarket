package com.technomarket.users;

import com.technomarket.products.SearchBar;

public class Demo {

	public static void main(String[] args) {
		// User user = User.generateUser();
		// System.out.println(user.toString());
		// User u2 = null;
		// try {
		// u2 = new User(new Registration("Harry", "Potter", "harry@abv.bg", "1234567",
		// true));
		// } catch (UserException e) {
		// e.printStackTrace();
		// }
		// System.out.println(u2.toString());
		// System.out.println("test");
		// u2.login();
		// System.out.println(u2.isLoged());
		// u2.logout();
		// System.out.println(u2.isLoged());
		// for (int i = 0; i < 10; i++) {
		// User u = User.generateUser();
		// System.out.println(u.toString());
		// }
		// User u3 = new User(new Registration("dassa", "dsadsa", "sasa@dsad.sa",
		// "sasasasdasd", true));
		// Order order = new Order(u3, new Basket(u3));

		User user = User.generateUser();
		try {
			User.login();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			SearchBar.readJsonFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SearchBar.searchByPrice();
		
//		for (int i = 0; i < 10; i++) {
//		User user2 = User.generateUser();
//	}
		
		// try {
		// User.login();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
