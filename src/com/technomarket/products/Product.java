package com.technomarket.products;

public abstract class Product {
	
	protected static final int MAX_PRODUCTS = 50;
	
	 
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

	public abstract void addProduct(String brand, double price, int availability, String model);

	public abstract void removeProduct(int id);
	// public abstract void editProduct(int id);

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
	public static void generateProducts() {
		ITproducts.generateITproducts();
		HomeCareProducts.generateHomeCareProducts();
	}

	// Showing all products
	public static void showAllProducts() {
		ITproducts.showAllProducts();
		HomeCareProducts.showAllHomeCareProducts();
	
		
	}

	@Override
	public String toString() {

		return "Product [ID: " + id + "  Type - " + name + "  Brand - " + brand + " Model: " + model + "   Price="
				+ price + "lv,   Quantity: " + availability + "]";
//		return "Product [ID: " + id + "  Type - " + name + "  Marka - " + brand + " Model: " +model + "   Price=" + price + "lv,   Quantity: "
//				+ availability + "]";

	}
}
