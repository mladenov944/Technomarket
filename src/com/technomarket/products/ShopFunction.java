package com.technomarket.products;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;


public abstract class ShopFunction {

	private static String keyword;
	private static int minPrice;
	private static int maxPrice;

	static Scanner s = new Scanner(System.in);

	static List<Product> catalog = new ArrayList<Product>();

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

	public static void createJsonFile() throws Exception {
		JsonObject jsonObject = new JsonObject();
		FileWriter fileWriter = new FileWriter("Products.json");

			makeCatalog();
		
		for (Product p : catalog) {
			jsonObject.addProperty("ID:", p.getId());
			jsonObject.addProperty("Type:", p.getName());
			jsonObject.addProperty("Brand:", p.getBrand());
			jsonObject.addProperty("Model:", p.getModel());
			jsonObject.addProperty("Price:", p.getPrice());
			jsonObject.addProperty("Quantity:", p.getAvailability());
			fileWriter.write(jsonObject.toString());
			fileWriter.write("\r\n");
			fileWriter.flush();
		}
		fileWriter.close();
	}

	public static void readJsonFile() throws Exception {
		JSONObject obj;
		String line = null;
		FileReader fileReader = new FileReader("Products.json");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			obj = (JSONObject) new JSONParser().parse(line);
			Object id = obj.get("ID:");
			Object name = obj.get("Type:");
			Object brand = obj.get("Brand:");
			Object model = obj.get("Model:");
			Object price = obj.get("Price:");
			Object availability = obj.get("Quantity:");
			System.out.println("Product [ID: " + id + "  Type - " + name + "  Brand - " + brand + " Model: " + model
					+ "   Price=" + price + "lv   Quantity: " + availability + "]");
		}
		bufferedReader.close();
	}
	//updates Json file when there is a purchase
	public static void updateJsonFile() throws Exception {
		File jsonFile = new File("Products.json");
		jsonFile.createNewFile();
	
		createJsonFile();
	}

	public String getKeyword() {
		return keyword;
	}

	public static void makeCatalog() {
		// Put all products in the same place
		if (!catalog.isEmpty()) {
			catalog.clear();
		}
		catalog.addAll(Product.generateProducts());
	}

	public static void getRandomProduct() throws Exception {
		int index = (int) (Math.random() * catalog.size());
		System.out.println(catalog.get(index).toString());
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
			System.out.println("Invalid min price! Setting min price to 0.");
			minPrice = 0;
		}
	}

	public static int getMaxPrice() {
		return maxPrice;
	}

	public static void setMaxPrice(int m) {
		if (m > 0 && m > getMinPrice()) {
			maxPrice = m;
		} else {
			System.out.println("Invalid max price!");
			int tempIndex = 0;
			for(int index = 0;index < catalog.size();index++) {
				if(catalog.get(tempIndex).getPrice() <= catalog.get(index).getPrice()) {
					tempIndex = index;
				}
			}
			//price of most expensive product
			maxPrice = (int) catalog.get(tempIndex).getPrice();
		}
	}
}
