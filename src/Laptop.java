import java.util.Set;
import java.util.TreeSet;

public class Laptop extends Product implements Comparable<Laptop> {

	private static final int MAX_PRODUCTS = 50;
	private static Set<Laptop> laptops = new TreeSet<Laptop>();

	public Laptop(String brand, double price, int availability) {
		super("Laptop", brand, price, availability);
	}

	public static void generateLaptops() {
		String[] laptopBrands = { "Dell", "Asus", "Lenovo", "Acer", "Toshiba", "HP", "Mac" };

		for (int count = 0; count < MAX_PRODUCTS; count++) {

			int randomPrice = (int) (Math.random() * 2500) + 300;
			int randomQuantity = (int) (Math.random() * 20);
			int randomBrand = (int) (Math.random() * laptopBrands.length);

			Laptop temp = new Laptop(laptopBrands[randomBrand], randomPrice, randomQuantity);
			laptops.add(temp);
		}
	}

	public static void showLaptops() {
		for (Laptop l : laptops) {
			System.out.println(l);
		}
	}

	@Override
	public int compareTo(Laptop laptop) {
		return this.getId() - laptop.getId();
	}

}
