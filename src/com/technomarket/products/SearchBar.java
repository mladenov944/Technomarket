package com.technomarket.products;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.technomarket.products.HomeCareProducts.Iron;
import com.technomarket.products.HomeCareProducts.Peralnq;
import com.technomarket.products.HomeCareProducts.Prahosmukachka;
import com.technomarket.products.ITproducts.Laptop;
import com.technomarket.products.ITproducts.MobilePhone;
import com.technomarket.products.ITproducts.Tablet;
import com.technomarket.products.ITproducts.Television;
import com.technomarket.products.OtherProducts.Pechka;
import com.technomarket.products.OtherProducts.Toster;

public abstract class SearchBar {

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

	public static void printCatalog() {
		if (catalog.isEmpty()) {
			makeCatalog();
		}
		for (Product p : catalog) {
			System.out.println(p.toString());
		}
	}

	public static void createJsonFile() throws Exception {
		JsonObject jsonObject = new JsonObject();
		FileWriter fileWriter = new FileWriter("Products.json");
		if (catalog.isEmpty()) {
			makeCatalog();
		}
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

	public String getKeyword() {
		return keyword;
	}

	public static void makeCatalog() {
		// Put all products in the same place
		catalog.addAll(MobilePhone.getMobilePhones());
		catalog.addAll(Laptop.getLaptops());
		catalog.addAll(Television.getTelevisions());
		catalog.addAll(Tablet.getTablets());
		catalog.addAll(Prahosmukachka.getPrahosmukachki());
		catalog.addAll(Iron.getIrons());
		catalog.addAll(Peralnq.getPeralni());
		catalog.addAll(Toster.getTosteri());
		catalog.addAll(Pechka.getPechki());
	}

	public static void getRandomProduct() throws Exception {
		ArrayList<JSONObject> json = new ArrayList<JSONObject>();
		JSONObject obj;
		String line = null;
		FileReader fileReader = new FileReader("Products.json");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			obj = (JSONObject) new JSONParser().parse(line);
			json.add(obj);
		}
		bufferedReader.close();
		int index = (int) (Math.random() * json.size());
		System.out.println(json.get(index).toJSONString());
		json.remove(index);
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
