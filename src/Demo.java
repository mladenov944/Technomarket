import com.technomarket.products.ITproducts;
import com.technomarket.products.SearchBar;

public class Demo {

	public static void main(String[] args) {

		// Product.showAllProducts();
		ITproducts.generateITproducts();
		ITproducts.Laptop.showLaptops();
		ITproducts.MobilePhone.showMobilePhones();
		SearchBar.searchByKeyword();
	}
}
