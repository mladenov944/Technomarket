package com.technomarket.users;

public class Demo {

	public static void main(String[] args) {

		User user = User.generateUser();
		try {
			User.login();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
