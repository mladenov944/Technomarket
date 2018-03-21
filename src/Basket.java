import java.util.ArrayList;
import com.technomarket.products.Product;

public class Basket {
	private static final int DELIVERY_PRICE = 4;
	private ArrayList<Product> products;
	private User user;
	private double priceAllProducts;

	public Basket(User user) {
		this.user = user;
		this.products = new ArrayList<Product>();
	}

	void addProduct(Product p, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.products.add(p);
			this.priceAllProducts += p.getPrice();
		}
		showBasket();
	}

	public void removeProduct(Product p) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == p.getId()) {
				products.remove(i);
				break;
			}
		}
		showBasket();
	}

	private void showBasket() {
		if (!products.isEmpty()) {
			for (Product p : products) {
				System.out.println(p.toString());
			}
			System.out.println("Obshto za plashtane: " + (DELIVERY_PRICE + priceAllProducts));
		} else {
			System.out.println("Koshnicata e prazna");
		}
	}

	public boolean containsProduct(Product p) {
		return (this.products.contains(p)) ? true : false;
	}

}
