import java.util.HashSet;
import java.util.Set;

import com.technomarket.products.Product;

public class Demo {


	public static void main(String[] args) {
		
		
		Set<Product> products = new HashSet<>();
		Product.generateProducts();
		Product.showAllProducts();
		
	
	}
}
