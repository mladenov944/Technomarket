
public abstract class Product {

	private final int id;
	private String name;
	private String brand;
	private int price;
	private int availability;

	public Product(int id, String name, String brand, int price, int availability) {
		this.id = ((id > 0) ? id : 0);
		this.name = (name == null) ? "bezIme" : name;
		this.brand = (brand == null) ? "bezMarka" : brand;
		this.price = (price > 0) ? price : 0;
		this.availability = (availability >= 0) ? availability : 0;
	}

}
