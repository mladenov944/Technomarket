import com.technomarket.products.SearchBar;

public class SearchDemo {
	public static void main(String[] args) {
		//Product.generateProducts();
		//SearchBar.searchByKeyword();
		//SearchBar.searchByPrice();
		//SearchBar.printCatalog();
		/*try {
			SearchBar.createJsonFile();
		} catch (Exception e) {
			System.out.println("Error creating file!");
			e.printStackTrace();
		}*/
		try {
			SearchBar.readJsonFile();
		} catch (Exception e) {
			System.out.println("Error reading from file!");
			e.printStackTrace();
		}
	}
}
	