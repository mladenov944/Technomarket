package com.technomarket.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonObject;
import com.technomarket.products.Product;

public class Order {
	private static final int DAYS_FOR_DELIVERY = 1;
	private static long id = (long) (100000000 * Math.random());

	private long orderID;
	private User user;
	private Basket basket;
	private double price;
	private String deliveryAddress;
	private String phoneNumber;
	private LocalDate confirmDate;
	private LocalDate deliveryDate;

	public Order(User user, Basket basket) throws OrderException {
		if ((user == null) || (basket == null)) {
			throw new OrderException("Nevalidna poruchka");
		}
		if (basket.getBasketSize() <= 0) {
			throw new OrderException("Koshnicata e prazna");
		}
		this.orderID = id;
		this.user = user;
		this.basket = basket;
		this.price = basket.calculatePrice();
		id++;
	}

	void confirm(String address, String phone) throws OrderException {
		if (IData.checkString(address) && IData.checkPhone(phone)) {
			this.confirmDate = LocalDate.now();
			this.deliveryDate = confirmDate.plusDays(DAYS_FOR_DELIVERY);
			System.out.println(confirmToString());
		} else {
			throw new OrderException("Nevaliden adres ili telefon. Poruchkata ne e potvurdena");
		}
	}

	public double getPrice() {
		return this.basket.calculatePrice();
	}

	private String confirmToString() {
		return "Poruchkata e potvurdena" + '\n' + "Data na potvurjdenie: " + this.confirmDate + '\n'
				+ "Data za dostavka: " + this.deliveryDate + '\n' + "Adres za dostavka: " + this.deliveryAddress + '\n'
				+ "Telefon za kontakt: " + this.phoneNumber;
	}

	@Override
	public String toString() {
		return user.toString() + basket.toString();
	}

	public long getOrderID() {
		return orderID;
	}
	
	static void addOrederToJson() throws IOException {
		File file = new File("Orders.json");
		file.createNewFile();
		JsonObject jsonObject = new JsonObject();
		FileWriter fileWriter = new FileWriter(file);
		ArrayList<Product> basket=getProduchts();
		for (Long id : users.keySet()) {
			jsonObject.addProperty("Reg_id: ", id);
			jsonObject.addProperty("Is loged: ", users.get(id).isLoged);
			fileWriter.append(jsonObject.toString());
			fileWriter.append("\r\n");
		}
		fileWriter.flush();
		fileWriter.close();
		// System.out.println("User added to file!");
	}

}
