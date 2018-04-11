import java.util.Scanner;

import com.technomarket.products.HomeCareProducts.Iron;
import com.technomarket.products.HomeCareProducts.Peralnq;
import com.technomarket.products.HomeCareProducts.Prahosmukachka;
import com.technomarket.products.ITproducts.Laptop;
import com.technomarket.products.ITproducts.MobilePhone;
import com.technomarket.products.ITproducts.Tablet;
import com.technomarket.products.ITproducts.Television;
import com.technomarket.products.OtherProducts.Pechka;
import com.technomarket.products.OtherProducts.Toster;
import com.technomarket.products.Product;
import com.technomarket.products.ShopFunction;
import com.technomarket.users.User;

public class Demo {

	public static void main(String[] args) throws Exception {

		User u = null;
		long id = 0;
		Product.generateProducts();
		Thread reklamnaNishka = new Thread(new Commercial());
		reklamnaNishka.start();

		menu();

		while (true) {

			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine().toLowerCase();

			switch (choice) {

			case "show all":
				try {
					ShopFunction.readJsonFile();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println();
				break;
			case "register":
				new User();
				break;
			case "login":
				try {
					u = User.login();
					id = u.getId();
				} catch (Exception e) {
				}
				break;
			case "logout":
				if ((u == null) || (!u.isLoged())) {
				System.out.println("Ne ste lognat");
				} else {
					User.logout(id);
					break;
				}
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
				
			case "buy":
				u.buy();
				ShopFunction.updateJsonFile();
				break;
			case "addproduct":
				
				System.out.println("Vuvedete ID na produkta");
				int id3 = sc.nextInt();
				System.out.println("Vuvedete kolichestvo");
				int kol = sc.nextInt();
				Product temp = Product.getProductById(id3-1);
				u.addToBasket(temp, kol);
				break;
				
			case "stop":
				System.out.println("Spirame reklamite...");
				reklamnaNishka.interrupt();
				break;

			case "admin":
				if (u == null) {
					System.out.println("Please log in...");
					break;
				}
				if (u.isAdmin()) {
					System.out.println("Admin login!");
					adminMenu();
					Scanner sc2 = new Scanner(System.in);
					String choice2 = sc2.nextLine().toLowerCase();

					boolean adminLiSi = true;
					while (adminLiSi) {

						switch (choice2) {

						case "remove":
							System.out.println("vuvedi ID:");
							int productId = sc.nextInt();
							Product.removeProduct(productId);
							ShopFunction.updateJsonFile();
							adminLiSi = false;
							menu();
							break;

						case "add":
							System.out.println("Vuvedete kategoriq - homecareproducts, itproducts, otherproducts...");
							String cat = sc.nextLine();

							if (cat.toLowerCase().trim().equals("homecareproducts")) {
								System.out.println("Izberete vid - Prahosmukachka, Utiq, Peralnq");
								String cat2 = sc.nextLine();

								if (cat2.toLowerCase().trim().equals("prahosmukachka")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Prahosmukachka p = new Prahosmukachka(marka, cena, nalichnost, model);
									Product.addProduct(p);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + p.toString());
									adminLiSi = false;
									menu();
									break;
								}
								if (cat2.toLowerCase().trim().equals("utiq")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Iron iron = new Iron(marka, cena, nalichnost, model);
									Product.addProduct(iron);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + iron.toString());
									adminLiSi = false;
									menu();
									break;
								}
								if (cat2.toLowerCase().trim().equals("peralnq")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Peralnq peralnq = new Peralnq(marka, cena, nalichnost, model);
									Product.addProduct(peralnq);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + peralnq.toString());
									adminLiSi = false;
									menu();
									break;
								}
							} else {
								System.out.println("Wrong name");
								break;
							}

							if (cat.toLowerCase().trim().equals("itproducts")) {
								System.out.println("Izberete vid - phone, tv, laptop, tablet");
								String cat2 = sc.nextLine();

								if (cat2.toLowerCase().trim().equals("phone")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									MobilePhone mp = new MobilePhone(marka, cena, nalichnost, model);
									Product.addProduct(mp);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + mp.toString());
									adminLiSi = false;
									menu();
									break;
								}
								if (cat2.toLowerCase().trim().equals("tv")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Television tv = new Television(marka, cena, nalichnost, model);
									Product.addProduct(tv);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + tv.toString());
									adminLiSi = false;
									menu();
									break;
								}
								if (cat2.toLowerCase().trim().equals("laptop")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Laptop lp = new Laptop(marka, cena, nalichnost, model);
									Product.addProduct(lp);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + lp.toString());
									adminLiSi = false;
									menu();
									break;
								}
								if (cat2.toLowerCase().trim().equals("tablet")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Tablet tab = new Tablet(marka, cena, nalichnost, model);
									Product.addProduct(tab);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + tab.toString());
									adminLiSi = false;
									menu();
									break;
								}

							} else {
								System.out.println("Wrong name");
								break;
							}

							if (cat.toLowerCase().trim().equals("otherproducts")) {
								System.out.println("Izberete vid - Pechka, Toster");
								String cat2 = sc.nextLine();

								if (cat2.toLowerCase().trim().equals("pechka")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Pechka pech = new Pechka(marka, cena, nalichnost, model);
									Product.addProduct(pech);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + pech.toString());
									adminLiSi = false;
									menu();
									break;
								}
								if (cat2.toLowerCase().trim().equals("toster")) {
									System.out.println("vuvedete marka:");
									String marka = sc.nextLine();

									System.out.println("vuvedete cena sus zapetaika :");
									double cena = sc.nextDouble();

									System.out.println("vuvedete nalichnost:");
									int nalichnost = sc.nextInt();

									System.out.println("vuvedete model:");
									String model = sc.next();

									Toster to = new Toster(marka, cena, nalichnost, model);
									Product.addProduct(to);
									ShopFunction.updateJsonFile();
									System.out.println("Nov produkt beshe dobaven kum kataloga" + to.toString());
									adminLiSi = false;
									menu();
									break;
								}
							} else {
								System.out.println("Wrong name");
								break;
							}
						default:
							System.out.println("nevalidna komanda");
							adminLiSi = false;
							menu();

						} 
					}
				} else {
					System.out.println("Nqmate prava !");
				}
				break;
			default:
				System.out.println("Greshna komanda !");
				break;
			}

		}
	}

	private static void menu() {
		System.out.println("----------------Technomarket----------------");
		System.out.println();
		System.out.println("\tDobre doshli v glavnoto MENU");
		System.out.println("-------------------------------");
		System.out.println("0. Admin menu >> admin");
		System.out.println("1. Za da razgledate vsichki produkti >>> show all");
		System.out.println("2. Za da napravite registraciq >>> register");
		System.out.println("3. Za da se lognete v saita >>> login");
		System.out.println("4. Za da se otpishte ot saite >>> logout");
		System.out.println("5. Za informaciq otnostno saita >>> info");
		System.out.println("6. Za da se vurnete v menuto >>> menu");
		System.out.println("7. Za da sprete reklamite >>> stop");
		System.out.println("8. Za da dobavite produkt kum kolichkata >>> addproduct");
		System.out.println("9. Za da zakupite vashite produkti >>> buy");
		System.out.println("10. Za da izlezete >>> exit");
	}

	private static void adminMenu() {
		System.out.println("----------------ADMIN----------------");

		System.out.println("1. Premahvane na produkt po id >>> remove");
		System.out.println("2. Dobavqne >>> add");

	}
}
