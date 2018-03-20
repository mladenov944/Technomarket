package com.technomarket.products;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class ITproducts extends Product {

	protected static final int MAX_PRODUCTS = 50;
	public ITproducts(String name, String brand, double price, int availability, String model) {
		super(name, brand, price, availability,model);
	}
	
	public static void generateITproducts() {
		MobilePhone.generateMobilePhones();
		Laptop.generateLaptops();
	}
	
	// nested class for Mobile phones
	public static class MobilePhone extends ITproducts implements Comparable<MobilePhone> {
		
		public static void sort() {
			List<MobilePhone> mp = new ArrayList<MobilePhone>(mobilePhones);
			Collections.sort(mp, (Comparator<MobilePhone>) (MobilePhone a, MobilePhone b)
					-> {return (a.getBrand().compareToIgnoreCase(b.getBrand()));} );
			
			for (MobilePhone map : mp) {
				System.out.println(map);
			}
		}

		private static Set<MobilePhone> mobilePhones = new HashSet<MobilePhone>();

		public MobilePhone(String brand, double price, int availability,String model) {
			super("Mobile Phone", brand, price, availability,model);
		}

		public static void generateMobilePhones() {
			String[] mobileBrands = { "Samsung", "Asus", "Lenovo", "iPhone", "Nokia", "Huawei", "Motorola", "HTC", "Sony" };
			String[] mobileModels = {"One", "Super", "Bussiness", "Pro", "A1", "S10"};

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 1200) + 100;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * mobileBrands.length);
				int randomModel = (int) (Math.random()*mobileModels.length);

				MobilePhone temp = new MobilePhone(mobileBrands[randomBrand], randomPrice, randomQuantity, mobileModels[randomModel]);
				mobilePhones.add(temp);
			}
		}

		public static void showMobilePhones() {
			for (MobilePhone mp : mobilePhones) {
				System.out.println(mp);
			}
		}

		@Override
		public int compareTo(MobilePhone o) {
			return this.getId() - o.getId();
		}

	}
	// nested class for Laptop
	public static class Laptop extends ITproducts implements Comparable<Laptop> {

		private static Set<Laptop> laptops = new TreeSet<Laptop>();

		public Laptop(String brand, double price, int availability, String model) {
			super("Laptop", brand, price, availability,model);
		}

		public static void generateLaptops() {
			String[] laptopBrands = { "Dell", "Asus", "Lenovo", "Acer", "Toshiba", "HP", "Mac" };
			String[] laptopModels = {"One", "Pro", "Super", "Gaming", "Bussiness"};

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 2500) + 300;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * laptopBrands.length);
				int randomModel = (int) (Math.random() * laptopModels.length);

				Laptop temp = new Laptop(laptopBrands[randomBrand], randomPrice, randomQuantity, laptopModels[randomModel]);
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
}
