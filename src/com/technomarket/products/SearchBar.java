package com.technomarket.products;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import com.technomarket.products.HomeCareProducts.Iron;
import com.technomarket.products.HomeCareProducts.Peralnq;
import com.technomarket.products.HomeCareProducts.Prahosmukachka;
import com.technomarket.products.ITproducts.Laptop;
import com.technomarket.products.ITproducts.MobilePhone;
import com.technomarket.products.ITproducts.Tablet;
import com.technomarket.products.ITproducts.Television;

public abstract class SearchBar {

	private static String keyword;
	private static int minPrice;
	private static int maxPrice;

	static Scanner s = new Scanner(System.in);

	static Set<Product> catalog = new HashSet<Product>();

	public static void searchByKeyword() {
		System.out.print("SearchWord: ");
		setKeyword(s.nextLine());

		if (catalog.isEmpty()) {
			makeCatalog();
		}
		for (Product p : catalog) {
			if (p.getBrand().toLowerCase().contains(keyword.toLowerCase())
					|| p.getModel().toLowerCase().contains(keyword.toLowerCase())
					|| p.getName().toLowerCase().contains(keyword.toLowerCase())
					|| keyword.toLowerCase().contains(p.getBrand().toLowerCase())
					|| keyword.toLowerCase().contains(p.getModel().toLowerCase())
					|| keyword.toLowerCase().contains(p.getName().toLowerCase())) {
				System.out.println(p.toString());
			}
		}
	}

	public static void searchByPrice() {
		if (catalog.isEmpty()) {
			makeCatalog();
		}
		System.out.print("Enter min price: ");
		setMinPrice(s.nextInt());
		System.out.print("Enter max price: ");
		setMaxPrice(s.nextInt());

		for (Product p : catalog) {
			if (p.getPrice() >= getMinPrice() && p.getPrice() <= getMaxPrice()) {
				System.out.println(p.toString());
			}
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public static void makeCatalog() {
		// Put all products in the same place
		catalog.addAll(Laptop.getLaptops());
		catalog.addAll(MobilePhone.getMobilePhones());
		catalog.addAll(Tablet.getTablets());
		catalog.addAll(Television.getTelevisions());
		catalog.addAll(Prahosmukachka.getPrahosmukachki());
		catalog.addAll(Iron.getIrons());
		catalog.addAll(Peralnq.getPeralni());
	}

	public static void setKeyword(String k) {
		if (k != null && k.trim().length() > 0) {
			keyword = k;
		} else {
			System.out.println("No such item found!");
			return;
		}
	}

	public static int getMinPrice() {
		return minPrice;
	}

	public static void setMinPrice(int m) {
		if (minPrice >= 0) {
			minPrice = m;
		} else {
			System.out.println("Invalid price! Setting price to 0");
			minPrice = 0;
		}
	}

	public static int getMaxPrice() {
		return maxPrice;
	}

	// max price is minPrice + 1 until i have maxPrice of all
	public static void setMaxPrice(int m) {
		if (m > 0 && m > getMinPrice()) {
			maxPrice = m;
		} else {
			System.out.println("Invalid max price!");
			maxPrice = getMinPrice() + 1;
		}
	}
}
