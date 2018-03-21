package com.technomarket.products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class ITproducts extends Product {


	protected static final int MAX_PRODUCTS = 50;


	public ITproducts(String name, String brand, double price, int availability, String model) {
		super(name, brand, price, availability, model);
	}


//	// Generating all IT products
//	public static Set<Product> generateITproducts() {
//		Set<Product> products = new HashSet<Product>();

	public static void generateITproducts() {
		MobilePhone.generateMobilePhones();
		Laptop.generateLaptops();
		Television.generateTelevisions();
		Tablet.generateTablets();
		
		
		
//		return products;
	}

	public static void showAllProducts() {
		MobilePhone.showMobilePhones();
		Television.showTelevisions();
		Laptop.showLaptops();
		Tablet.showTablets();
	}

	// nested class for Mobile phones
	public static class MobilePhone extends ITproducts implements Comparable<MobilePhone> {

		public static void sort() {
			List<MobilePhone> mp = new ArrayList<MobilePhone>(mobilePhones);
			Collections.sort(mp, (Comparator<MobilePhone>) (MobilePhone a, MobilePhone b) -> {
				return (a.getBrand().compareToIgnoreCase(b.getBrand()));
			});

			for (MobilePhone map : mp) {
				System.out.println(map);
			}
		}

		private static Set<MobilePhone> mobilePhones = new TreeSet<MobilePhone>();

		public MobilePhone(String brand, double price, int availability, String model) {
			super("Mobile Phone", brand, price, availability, model);
		}

		public static void generateMobilePhones() {
			
			
			String[] mobileBrands = { "Samsung", "Asus", "Lenovo", "iPhone", "Nokia", "Huawei", "Motorola", "HTC",
					"Sony" };
			String[] mobileModels = { "One", "Super", "Bussiness", "Pro", "A1", "S10" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 1200) + 100;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * mobileBrands.length);
				int randomModel = (int) (Math.random() * mobileModels.length);

				MobilePhone temp = new MobilePhone(mobileBrands[randomBrand], randomPrice, randomQuantity,
						mobileModels[randomModel]);
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

		public static Set<MobilePhone> getMobilePhones() {
			return mobilePhones;
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			mobilePhones.add(new MobilePhone(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<MobilePhone> removeIterator = mobilePhones.iterator();
			while (removeIterator.hasNext()) {
				MobilePhone currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}

		}

	}

	// nested class for Laptop
	public static class Laptop extends ITproducts implements Comparable<Laptop> {

		private static Set<Laptop> laptops = new TreeSet<Laptop>();

		public Laptop(String brand, double price, int availability, String model) {
			super("Laptop", brand, price, availability, model);
		}

		public static void generateLaptops() {
			String[] laptopBrands = { "Dell", "Asus", "Lenovo", "Acer", "Toshiba", "HP", "Mac" };
			String[] laptopModels = { "One", "Pro", "Super", "Gaming", "Bussiness" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 2500) + 300;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * laptopBrands.length);
				int randomModel = (int) (Math.random() * laptopModels.length);

				Laptop temp = new Laptop(laptopBrands[randomBrand], randomPrice, randomQuantity,
						laptopModels[randomModel]);
				laptops.add(temp);
			}
		}

		public static void showLaptops() {
			for (Laptop l : laptops) {
				System.out.println(l);
			}
		}

		public static Set<Laptop> getLaptops() {
			return laptops;
		}

		@Override
		public int compareTo(Laptop laptop) {
			return this.getId() - laptop.getId();
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			laptops.add(new Laptop(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<Laptop> removeIterator = laptops.iterator();
			while (removeIterator.hasNext()) {
				Laptop currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}
		}

	}

	// nested class for Laptop
	public static class Tablet extends ITproducts implements Comparable<Tablet> {

		private static Set<Tablet> tablets = new TreeSet<Tablet>();

		public Tablet(String brand, double price, int availability, String model) {
			super("Tablet", brand, price, availability, model);
		}

		public static void generateTablets() {
			String[] tabletBrands = { "Dell", "Asus", "Lenovo", "Acer", "Toshiba", "HP", "Mac" };
			String[] tabletModels = { "One", "Pro", "Super", "Gaming", "Bussiness" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 1200) + 100;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * tabletBrands.length);
				int randomModel = (int) (Math.random() * tabletModels.length);

				Tablet temp = new Tablet(tabletBrands[randomBrand], randomPrice, randomQuantity,
						tabletModels[randomModel]);
				tablets.add(temp);
			}
		}

		public static void showTablets() {
			for (Tablet t : tablets) {
				System.out.println(t);
			}
		}

		@Override
		public int compareTo(Tablet tablet) {
			return this.getId() - tablet.getId();
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			tablets.add(new Tablet(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<Tablet> removeIterator = tablets.iterator();
			while (removeIterator.hasNext()) {
				Tablet currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}
		}

	}

	// nested class for Televisions
	public static class Television extends ITproducts implements Comparable<Television> {

		private static Set<Television> televisions = new TreeSet<Television>();

		public Television(String brand, double price, int availability, String model) {
			super("Television", brand, price, availability, model);
		}

		public static void generateTelevisions() {
			String[] TvBrands = { "LG", "Samsung", "Panasonic", "Philips", "Toshiba", "JVC", "Sony" };
			String[] TvModels = { "HD", "FullHD", "UHD", "Led", "Oled" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 4000) + 300;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * TvBrands.length);
				int randomModel = (int) (Math.random() * TvModels.length);

				Television temp = new Television(TvBrands[randomBrand], randomPrice, randomQuantity,
						TvModels[randomModel]);
				televisions.add(temp);
			}
		}

		public static void showTelevisions() {
			for (Television tv : televisions) {
				System.out.println(tv);
			}
		}

		@Override
		public int compareTo(Television tv) {
			return this.getId() - tv.getId();
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			televisions.add(new Television(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<Television> removeIterator = televisions.iterator();
			while (removeIterator.hasNext()) {
				Television currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}
		}

	}
}
