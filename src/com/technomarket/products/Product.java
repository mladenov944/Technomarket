package com.technomarket.products;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Product {

	protected static final int MAX_PRODUCTS = 10;

	private static List<Product> allProducts = new ArrayList<Product>();
	
	private final int id;
	private static int staticId = 0;
	private String name;
	private String brand;
	private double price;
	private int availability;
	private String model;

	public Product(String name, String brand, double price, int availability, String model) {
		this.id = ++staticId;
		this.name = (name == null) ? "bezIme" : name;
		this.brand = (brand == null) ? "bezMarka" : brand;
		this.price = (price > 0) ? price : 0;
		this.availability = (availability >= 0) ? availability : 0;
		this.model = (model == null) ? "BezModel" : model;
	}

	public static void addProduct(Product p) {
		allProducts.add(p);
	}
	
	public void updateQuantity(int id, int q) {
		allProducts.get(id).setAvailability(allProducts.get(id).getAvailability()-q);
	}

	public static void removeProduct(int id) {
		
		Iterator<Product> removeIterator = allProducts.iterator();
		while (removeIterator.hasNext()) {
			Product currentElement = removeIterator.next();
			if (id == currentElement.getId()) {
				removeIterator.remove();
			}
		}
	}
	
	public static Product getProductById(int id) {
		return allProducts.get(id);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public double getPrice() {
		return price;
	}

	public int getAvailability() {
		return availability;
	}

	public String getModel() {
		return model;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	// Generating random IT products
	public static List<Product> generateProducts() {
		
		File jsonFile = new File("Products.json");
		if (!jsonFile.exists()) {
			allProducts.addAll(ITproducts.generateITproducts());
			allProducts.addAll(HomeCareProducts.generateHomeCareProducts());
			allProducts.addAll(OtherProducts.generateOtherProducts());
			try {
				ShopFunction.createJsonFile();
			} catch (Exception e) {
				System.out.println("Json file could not be created!");
			}
			return allProducts;
		} else {
			try {
				ShopFunction.readJsonFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allProducts;
	}

	// Showing all products
	public static void showAllProducts() {
		File jsonFile = new File("Products.json");
		if (jsonFile.exists()) {
			try {
				ShopFunction.readJsonFile();
			} catch (Exception e) {
				System.out.println("Cannot open Json file!" + e.getMessage());
			}
		} else {
			System.out.println("Cannot find Json file!");
		}
	}

	@Override
	public String toString() {

		return "Product [ID: " + id + "  Type - " + name + "  Brand - " + brand + " Model: " + model + "   Price="
				+ price + "lv,   Quantity: " + availability + "]";

	}
}