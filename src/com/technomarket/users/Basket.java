package com.technomarket.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonObject;
import com.technomarket.products.Product;

public class Basket {
	private static final double DELIVERY_PRICE = 4.5;
	private ArrayList<Product> products;
	private User user;
	private double priceAllProducts;

	public Basket(User user) {
		this.user = user;
		this.products = new ArrayList<Product>();
	}

	void addProduct(Product p, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.products.add(p);
			this.priceAllProducts += p.getPrice();
		}
		System.out.println(this.toString());
	}

	public void removeProduct(Product p) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == p.getId()) {
				products.remove(i);
				break;
			}
		}
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		if (!products.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Product p : products) {
				sb.append(p.toString());
				sb.append('\n');
			}
			return "PRODUKTI" + '\n' + sb.toString() + "Obshto za plashtane: " + calculatePrice();
		} else {
			return "Koshnicata e prazna";
		}
	}

	public double calculatePrice() {
		return DELIVERY_PRICE + priceAllProducts;
	}

	public int getBasketSize() {
		return this.products.size();
	}

	public boolean containsProduct(Product p) {
		return (this.products.contains(p)) ? true : false;
	}

	public void empty() {
		this.products.clear();
		this.priceAllProducts = 0;
	}

	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}
	
}
