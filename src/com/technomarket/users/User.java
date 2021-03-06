package com.technomarket.users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.gson.JsonObject;
import com.technomarket.products.Product;

public class User {

	private static final String[] firstNames = { "Angel", "Simo", "Toni", "Pesho", "Kiro", "Spas", "Boqn", "Stoqn",
			"Sasho" };
	private static final String[] lastNames = { "Anastasov", "Ivanov", "Gospodinov", "Petkov", "Stoqnov", "Zahariev",
			"Vasilev", "Pavlov", "Radev" };
	private static final String[] domains = { "@abv.bg", "@yahoo.com", "@mail.bg", "@gmail.com", "@hotmail.com" };
	private static final int MAX_MONEY = 3000;
	private static final int MIN_MONEY = 100;

	private static HashMap<Long, User> users = addJsonToUsers();
	private Registration reg;
	private Basket basket;
	private boolean isLoged;
	private ArrayList<Order> orders;
	static Scanner sc = new Scanner(System.in);
	private double money;
	private boolean isAdmin = false;;

	public boolean isAdmin() {
		return isAdmin;
	}

	public User(Registration reg) throws UserException {
		if (userExists(reg)) {
			throw new UserException("Takuv user sushtestvuva!");
		}
		this.reg = reg;
		this.basket = new Basket(this);
		this.orders = new ArrayList<Order>();
		this.isLoged = false;
		this.setMoney((Double) Math.random() * ((MAX_MONEY - MIN_MONEY) + MIN_MONEY));
		try {
			users.put(reg.getId(), this);
			addUsersToJson();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static User generateUser() {
		boolean isMale = (Math.random() > 0.5) ? true : false;
		String firstName = firstNames[(int) (Math.random() * firstNames.length)];
		String lastName = lastNames[(int) (Math.random() * lastNames.length)];
		String mail = firstName + lastName + domains[(int) (Math.random() * domains.length)];
		String psw = firstName + domains[(int) (Math.random() * domains.length)];
		User user = null;
		try {
			Registration reg = new Registration(firstName, lastName, mail, psw, isMale);
			user = new User(reg);
			System.out.println(user.toString());
		} catch (UserException | RegistrationException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User() {
		System.out.println("First name: ");
		String firstName = sc.nextLine();
		System.out.println("Last name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter email: ");
		String mail = sc.nextLine();
		if (mail.toLowerCase().startsWith("admin")) {
			this.isAdmin = true;
		}
		System.out.println("Enter password: ");
		String psw = sc.nextLine();
		System.out.println("m/f: ");
		char male = sc.next().charAt(0);
		boolean isMale = true;
		if (male == 'f') {
			isMale = false;
		}
		Registration reg;
		try {
			reg = new Registration(firstName, lastName, mail, psw, isMale);
			if (userExists(reg)) {
				throw new UserException("Takuv user sushtestvuva!");
			}
		} catch (UserException | RegistrationException e) {
			System.out.println(e.getMessage());
			return;
		}
		this.reg = reg;
		this.basket = new Basket(this);
		this.orders = new ArrayList<Order>();
		this.isLoged = false;
		this.setMoney((Double) Math.random() * ((MAX_MONEY - MIN_MONEY) + MIN_MONEY));
		try {
			users.put(reg.getId(), this);
			addUsersToJson();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
	}

	User(Long id, boolean isLoged, boolean isAdmin) {
		this.isLoged = isLoged;
		this.isAdmin = isAdmin;
		this.basket = new Basket(this);
		this.orders = new ArrayList<Order>();
		try {
			this.reg = Registration.getRegistration(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMoney((Double) Math.random() * ((MAX_MONEY - MIN_MONEY) + MIN_MONEY));
		// try {
		users.put(reg.getId(), this);
		// addUsersToJson();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private boolean userExists(Registration reg) {
		for (Long id : users.keySet()) {
			if (reg.getId() == id) {
				return true;
			}
		}
		return false;
	}

	static List<JSONObject> getAllUsers() throws Exception {
		List<JSONObject> json = new ArrayList<JSONObject>();
		JSONObject obj;
		String line = null;
		File file = new File("Users.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			obj = (JSONObject) new JSONParser().parse(line);
			json.add(obj);
		}
		bufferedReader.close();
		return Collections.unmodifiableList(json);
	}

	static List<JSONObject> getAllOrders() throws Exception {
		List<JSONObject> json = new ArrayList<JSONObject>();
		JSONObject obj;
		String line = null;
		File file = new File("Orders.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			obj = (JSONObject) new JSONParser().parse(line);
			json.add(obj);
		}
		bufferedReader.close();
		return Collections.unmodifiableList(json);
	}

	public void addToBasket(Product p, int quantity) {
		while (!this.isLoged) {
			System.out.println("Za da dobavite produkt v kolichkata, purvo vlezte v acaunta si");
			try {
				login();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ((p != null) && (quantity > 0) && (p.getAvailability() >= quantity)
				&& (this.money >= (this.basket.calculatePrice() + (p.getPrice() * quantity)))) {
			this.basket.addProduct(p, quantity);
			// TUKA!!!!!!!!!!!!!
			p.updateQuantity(p.getId() - 1, quantity);
			// p.setAvailability(p.getAvailability() - quantity);
			if (quantity == 0) {
				Product.removeProduct(p.getId());
			} else {
				System.out.println("Produkta ne moje da bude dobaven v koshnicata");
			}

		}
	}

	public void removeFromBasket(Product p) {
		if (this.basket.containsProduct(p)) {
			basket.removeProduct(p);
		}
	}

	public static User login() throws Exception {
		System.out.println("Email: ");
		String email = sc.nextLine();
		if (Registration.registrationExists(email)) {
			Registration reg = Registration.getRegistration(email);

			User user = getUserByID(reg.getId());
			if (user.isLoged) {
				System.out.println("Veche ste vlezli v akaunta si!");
				return user;
			} else {
				System.out.println("Password: ");
				String psw = sc.nextLine();
				if (reg.getPassword().equals(psw)) {
					user.isLoged = true;
					users.remove(reg.getId());
					users.put(reg.getId(), user);
					addUsersToJson();
					System.out.println("Lognahte se uspeshno");
					return user;
				} else {
					System.out.println("Nevaliden email ili parola");
					return null;
				}
			}
		} else {
			System.out.println("Nevaliden email ili parola");
			return null;
		}
	}

	public static void logout(long id) {
		if (users.get(id).isLoged) {
			users.get(id).isLoged = false;
			users.remove(users.get(id).reg.getId());
			users.put(users.get(id).reg.getId(), users.get(id));
			try {
				addUsersToJson();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static User getUserByID(long id) {
		for (long regId : users.keySet()) {
			if (id == regId) {
				return users.get(regId);
			}
		}
		return null;
	}

	public void buy() {
		try {
			Order o = new Order(this, this.basket);
			System.out.println(o.toString() + '\n' + "Molq potvurdete poruchkata");
			confirmOrder(o);
			this.setMoney(this.money - o.getPrice());
			orders.add(o);
			// poruchkata e potvurdena i koshnicata se prazni
			basket.empty();

		} catch (OrderException e) {
			System.out.println(e.getMessage());
		}
	}

	private void confirmOrder(Order o) throws OrderException {
		System.out.println("Adres za dostatvka: ");
		String address = sc.next();
		System.out.println("Telefon za kontakt: ");
		String phone = sc.next();
		o.confirm(address, phone);
	}

	static boolean isLoged(long id) {
		for (long uId : users.keySet()) {
			if (id == uId) {
				if (users.get(uId).isLoged == true) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	// Order checked!
	static void addUsersToJson() throws IOException {
		File file = new File("Users.json");
		file.createNewFile();
		JsonObject jsonObject = new JsonObject();
		FileWriter fileWriter = new FileWriter(file);
		for (Long id : users.keySet()) {
			jsonObject.addProperty("Reg_id: ", id);
			jsonObject.addProperty("Is loged: ", users.get(id).isLoged);
			jsonObject.addProperty("Is admin: ", users.get(id).isAdmin);
			fileWriter.append(jsonObject.toString());
			fileWriter.append("\r\n");
		}
		fileWriter.flush();
		fileWriter.close();
		// System.out.println("User added to file!");
	}

	static HashMap<Long, User> addJsonToUsers() {
		users = new HashMap<Long, User>();
		try {
			ArrayList<JSONObject> usersFromJson = new ArrayList<JSONObject>(getAllUsers());
			for (JSONObject user : usersFromJson) {
				// users.put((Long) user.get("Reg_id: "),
				// new User(false, (Boolean) user.get("Is admin: ")));
				users.put((Long) user.get("Reg_id: "),
						new User((Long) user.get("Reg_id: "), false, (Boolean) user.get("Is admin: ")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public void setMoney(double money) {
		if (money >= 0)
			this.money = money;
	}

	public boolean isLoged() {
		return isLoged;
	}

	public long getId() {
		return this.reg.getId();
	}

	public String getName() {
		return this.reg.getFirstName() + " " + this.reg.getLastName();
	}

	@Override
	public String toString() {
		return ("Ime: " + this.reg.getFirstName() + " " + this.reg.getLastName() + '\n');
	}
}