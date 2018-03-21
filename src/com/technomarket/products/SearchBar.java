package com.technomarket.products;

import java.util.ArrayList;
import java.util.Scanner;

import com.technomarket.products.ITproducts.Laptop;
import com.technomarket.products.ITproducts.MobilePhone;

public abstract class SearchBar {

	private static String keyword;

	public static void searchByKeyword() {
		System.out.print("Search: ");
		Scanner s = new Scanner(System.in);
		setKeyword(s.nextLine());
		for (Laptop l : Laptop.getLaptops()) {
			if (l.getBrand().toLowerCase().contains(keyword.toLowerCase())
					|| l.getModel().toLowerCase().contains(keyword.toLowerCase())
					|| l.getName().toLowerCase().contains(keyword.toLowerCase())
					|| keyword.toLowerCase().contains(l.getBrand().toLowerCase())
					|| keyword.toLowerCase().contains(l.getModel().toLowerCase())
					|| keyword.toLowerCase().contains(l.getName().toLowerCase())) {
				System.out.println(l.toString());
			}
		}
		for (MobilePhone m : MobilePhone.getMobilePhones()) {
			if (m.getBrand().toLowerCase().contains(keyword.toLowerCase())
					|| m.getModel().toLowerCase().contains(keyword.toLowerCase())
					|| m.getName().toLowerCase().contains(keyword.toLowerCase())
					|| keyword.toLowerCase().contains(m.getBrand().toLowerCase())
					|| keyword.toLowerCase().contains(m.getModel().toLowerCase())
					|| keyword.toLowerCase().contains(m.getName().toLowerCase())) {
				System.out.println(m.toString());
			}
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public static void setKeyword(String k) {
		if (k != null && k.trim().length() > 0) {
			keyword = k;
		} else {
			System.out.println("No such item found!");
			return;
		}
	}
}
