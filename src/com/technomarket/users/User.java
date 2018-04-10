package com.technomarket.users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
<<<<<<< HEAD
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.time.LocalDate;
=======
>>>>>>> 74436c9bbec7f28a4d0a2cb03b9bbdd78d3fb522
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

	private HashMap<Long, Boolean> users = new HashMap<>();

	private Registration reg;
	private Basket basket;
	private boolean isLoged;
	private Map<Long, Order> orders;
	static Scanner sc = new Scanner(System.in);

	public User(Registration reg) throws UserException {
		if (userExists(reg)) {
			throw new UserException("Takuv user sushtestvuva!");
		}
		this.reg = reg;
		try {
			users.put(reg.getId(), false);
			addUsersToJson();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.basket = new Basket(this);
		this.orders = new HashMap<Long, Order>();
		this.isLoged = false;
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


	private boolean userExists(Registration reg) {
		for (Long id : users.keySet()) {
			if (reg.getId() == id) {
				return true;
			}
		}
		return false;
	}

	static ArrayList<JSONObject> getAllUsers() throws Exception {
		ArrayList<JSONObject> json = new ArrayList<JSONObject>();
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
		return json;
	}


	public void addToBasket(Product p, int quantity) {
		while (!this.isLoged) {
			System.out.println("Za da dobavite produkt v kolichkata, purvo vlezte v acaunta si");
			try {
				this.login();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ((p != null) && (quantity > 0) && (p.getAvailability() >= quantity)) {
			this.basket.addProduct(p, quantity);
			p.setAvailability(p.getAvailability() - quantity);
			if (quantity == 0) {
				p.removeProduct(p.getId());
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


	public static void login() throws Exception {
		// if (!this.isLoged) {
		System.out.println("Email: ");
		String email = sc.nextLine();
		if (Registration.registrationExists(email)) {
			Registration reg = Registration.getRegistration(email);
			if (isLoged(reg.getId())) {
				System.out.println("Veche ste vlezli v akaunta si!");
			} else {
				System.out.println("Password: ");
				String psw = sc.nextLine();
				if (reg.getPassword().equals(psw)) {

					System.out.println("Lognate se uspeshno");
				} else {
					System.out.println("Nevaliden email ili parola");
				}
			}
		}

	}

	// static void removeLine(Long regId) throws IOException {
	// File file = new File("Users.json");
	// File temp = new File(file.getAbsolutePath() + ".tmp");
	// PrintWriter out = new PrintWriter(new FileWriter(temp));
	// Files.lines(file.toPath()).filter(line -> !line.contains("" +
	// regId)).forEach(out::println);
	// out.flush();
	// out.close();
	// file.delete();
	// if (!temp.renameTo(file)) {
	// System.out.println("Not renamed");
	// }
	// }

	public void logout() {
		if (this.isLoged) {
			this.isLoged = false;
		}
	}

	public void buy() {
		try {
			Order o = new Order(this, this.basket);
			System.out.println(o.toString() + '\n' + "Molq potvurdete poruchkata");
			confirmOrder(o);
			orders.put(o.getOrderID(), o);
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
		ArrayList<JSONObject> users;
		try {
			users = getAllUsers();
			for (JSONObject jo : users) {
				if (id == (Long) jo.get("Reg_id: ")) {
					if ((Boolean) jo.get("Is loged: ") == true) {
						return true;
					}
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void addUsersToJson() throws IOException {
		File file = new File("Users.json");
		file.createNewFile();
		JsonObject jsonObject = new JsonObject();
		FileWriter fileWriter = new FileWriter(file, true);
		for (Long id : users.keySet()) {
			jsonObject.addProperty("Reg_id: ", id);
			jsonObject.addProperty("Is loged: ", users.get(id));
			fileWriter.append(jsonObject.toString());
			fileWriter.append("\r\n");
		}
		fileWriter.flush();
		fileWriter.close();
		System.out.println("User added to file!");
	}

	@Override
	public String toString() {
		return ("USER: " + '\n' + "Ime: " + this.reg.getFirstName() + " " + this.reg.getLastName() + '\n' + "Email: "
				+ this.reg.getEmail() + '\n');
	}
}