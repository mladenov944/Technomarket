import com.technomarket.products.ITproducts;
import com.technomarket.products.SearchBar;
import java.util.HashSet;
import java.util.Set;
import com.technomarket.products.Product;


public class Demo {

	public static void main(String[] args) {

		// Product.showAllProducts();
		ITproducts.generateITproducts();
		ITproducts.Laptop.showLaptops();
		ITproducts.MobilePhone.showMobilePhones();
		SearchBar.searchByKeyword();
		
		
		Set<Product> products = new HashSet<>();
		Product.generateProducts();
		Product.showAllProducts();
		
	}
}
