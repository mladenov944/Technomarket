package com.technomarket.users;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.technomarket.products.Product;

public class User {

	private static final String[] firstNames = { "Angel", "Simo", "Toni", "Pesho", "Kiro", "Spas", "Boqn", "Stoqn",
			"Sasho" };
	private static final String[] lastNames = { "Anastasov", "Ivanov", "Gospodinov", "Petkov", "Stoqnov", "Zahariev",
			"Vasilev", "Pavlov", "Radev" };
	private static final String[] domains = { "@abv.bg", "@yahoo.com", "@mail.bg", "@gmail.com", "@hotmail.com" };

	private Registration reg;
	private Basket basket;
	private boolean isLoged;
	private Map<Long, Order> orders;
	Scanner sc = new Scanner(System.in);

	public User(Registration reg) {
		this.reg = reg;
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
		} catch (UserException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void addToBasket(Product p, int quantity) {
		while (!this.isLoged) {
			System.out.println("Za da dobavite produkt v kolichkata, purvo vlezte v acaunta si");
			this.login();
		}
		if ((p != null) && (quantity > 0) && (p.getAvailability() >= quantity)) {
			this.basket.addProduct(p, quantity);
		} else {
			System.out.println("Produkta ne moje da bude dobaven v koshnicata");
		}
	}

	public void removeFromBasket(Product p) {
		if (this.basket.containsProduct(p)) {
			basket.removeProduct(p);
		}
	}

	public void login() {
		if (!this.isLoged) {
			System.out.println("Email: ");
			String email = sc.nextLine();
			System.out.println("Password: ");
			String psw = sc.nextLine();
			if (email.equals(this.reg.getEmail()) && psw.equals(this.reg.getPassword())) {
				this.isLoged = true;
			} else {
				System.out.println("Nevaliden email ili parola");
			}
		}
	}

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

	public boolean isLoged() {
		return isLoged;
	}

	@Override
	public String toString() {
		return ("USER: " + '\n' + "Ime: " + this.reg.getFirstName() + " " + this.reg.getLastName() + '\n' + "Email: "
				+ this.reg.getEmail() + '\n');
	}
}