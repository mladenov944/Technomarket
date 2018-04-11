import java.util.Scanner;

import com.technomarket.products.Product;
import com.technomarket.products.SearchBar;
import com.technomarket.users.User;
import com.technomarket.users.UserException;

public class Demo {

	public static void main(String[] args) throws UserException {
		
		Thread reklamnaNishka = new Thread(new Commercial());
		reklamnaNishka.start();
		
		menu();
		
		
		while (true) {
			
			
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine().toLowerCase();
			
			switch (choice) {
			
			case "show all":
				SearchBar.printCatalog();
				System.out.println();
				break;
			case "register":
				new User();
				break;
			case "login":
				try {
					User.login();
				} catch (Exception e) {
					System.out.println("Cannot login right now! Try again later!");
				}
				break;
			case "logout":
//				User.logout();
				break;
			case "info":
				System.out.println("Technomarket - sait prednaznachen za elektronni ustroistva");
				break;
			case "exit":
				System.out.println("Dovijdane !");
				return;
			case "menu":
				menu();
				break;
			case "stop":
				System.out.println("Spirame reklamite...");
				reklamnaNishka.interrupt();
				break;
			default: System.out.println("Greshna komanda !");
				break;
			}
			
			
			
		}
	}

	private static void menu() {
		System.out.println("----------------Technomarket----------------");
		System.out.println();
		System.out.println("\tDobre doshli v glavnoto MENU");
		System.out.println("-------------------------------");
		System.out.println("1. Za da razgledate vsichki produkti >>> show all");
		System.out.println("2. Za da napravite registraciq >>> register");
		System.out.println("3. Za da se lognete v saita >>> login");
		System.out.println("4. Za da se otpishte ot saite >>> logout");
		System.out.println("5. Za informaciq otnostno saita >>> info");
		System.out.println("6. Za da se vurnete v menuto >>> menu");
		System.out.println("7. Za da sprete reklamite >>> stop");
		System.out.println("8. Za da izlezete >>> exit");
	}
}
