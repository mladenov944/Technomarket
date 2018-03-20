package com.technomarket.products;

public abstract class Product {
	
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

	// Genereting random IT products
	protected static void generateProducts() {
		ITproducts.generateITproducts();
	}
	// Showing all products
	protected static void showAllProducts() {
		ITproducts.Laptop.showLaptops();
		ITproducts.MobilePhone.showMobilePhones();
	}

	@Override
	public String toString() {
		return "Product [ID: " + id + "  Type - " + name + "  Marka - " + brand + " Model: " +model + "   Price=" + price + "lv,   Quantity: "
				+ availability + "]";
	}
}
