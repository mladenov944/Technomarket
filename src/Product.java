
public abstract class Product {

	private final int id;
	private String name;
	private String brand;
	private double price;
	private int availability;

	public Product(String name, String brand, double price, int availability) {
		int randomId = (int) (Math.random() * 100000);
		this.id = randomId;
		this.name = (name == null) ? "bezIme" : name;
		this.brand = (brand == null) ? "bezMarka" : brand;
		this.price = (price > 0) ? price : 0;
		this.availability = (availability >= 0) ? availability : 0;
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

	protected static void generateProducts() {
		Laptop.generateLaptops();
		MobilePhone.generateMobilePhones();
	}

	protected static void showAllProducts() {
		Laptop.showLaptops();
		MobilePhone.showMobilePhones();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", availability="
				+ availability + "]";
	}
}
