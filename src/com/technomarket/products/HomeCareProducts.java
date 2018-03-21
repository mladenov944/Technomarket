package com.technomarket.products;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class HomeCareProducts extends Product {

	public HomeCareProducts(String name, String brand, double price, int availability, String model) {
		super(name, brand, price, availability, model);
	}

	// Generating all Homecare products
	public static void generateHomeCareProducts() {
		Prahosmukachka.generatePrahosmukachki();
		Iron.generateIrons();
		Peralnq.generatePeralni();
	}

	public static void showAllHomeCareProducts() {
		Prahosmukachka.showPrahosmukachki();
		Iron.showIrons();
		Peralnq.showPeralni();
	}

	// Nested class for Prahosmukachki

	protected static class Prahosmukachka extends HomeCareProducts implements Comparable<Prahosmukachka> {

		public Prahosmukachka(String brand, double price, int availability, String model) {
			super("Prahosmukachka ", brand, price, availability, model);
		}

		private static Set<Prahosmukachka> prahosmukachki = new HashSet<Prahosmukachka>();

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			prahosmukachki.add(new Prahosmukachka(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<Prahosmukachka> removeIterator = prahosmukachki.iterator();
			while (removeIterator.hasNext()) {
				Prahosmukachka currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}

		}

		@Override
		public int compareTo(Prahosmukachka o) {
			return (int) (this.getId() - o.getId());
		}

		public static void generatePrahosmukachki() {
			String[] PrahosmukachkaBrands = { "LG", "Samsung", "Panasonic", "Philips", "Beko", "Kercher", "Sony" };
			String[] PrahosmukachkaModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 500) + 60;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * PrahosmukachkaBrands.length);
				int randomModel = (int) (Math.random() * PrahosmukachkaModels.length);

				Prahosmukachka temp = new Prahosmukachka(PrahosmukachkaBrands[randomBrand], randomPrice, randomQuantity,
						PrahosmukachkaModels[randomModel]);
				prahosmukachki.add(temp);
			}
		}

		public static Set<Prahosmukachka> getPrahosmukachki() {
			return prahosmukachki;
		}

		public static void showPrahosmukachki() {
			for (Prahosmukachka p : prahosmukachki) {
				System.out.println(p);
			}
		}

	}

	// Nested class for Irons

	protected static class Iron extends HomeCareProducts implements Comparable<Iron> {

		public Iron(String brand, double price, int availability, String model) {
			super("Utiq ", brand, price, availability, model);
		}

		private static Set<Iron> irons = new HashSet<Iron>();

		public static Set<Iron> getIrons() {
			return irons;
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			irons.add(new Iron(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<Iron> removeIterator = irons.iterator();
			while (removeIterator.hasNext()) {
				Iron currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}

		}

		@Override
		public int compareTo(Iron iron) {
			return (int) (this.getId() - iron.getId());
		}

		public static void generateIrons() {
			String[] ironBrands = { "LG", "Samsung", "Panasonic", "Philips", "Sony", "Bosch" };
			String[] ironModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 500) + 60;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * ironBrands.length);
				int randomModel = (int) (Math.random() * ironModels.length);

				Iron temp = new Iron(ironBrands[randomBrand], randomPrice, randomQuantity, ironModels[randomModel]);
				irons.add(temp);
			}
		}

		public static void showIrons() {
			for (Iron iron : irons) {
				System.out.println(iron);
			}
		}

	}

	// Nested class for Peralni

	protected static class Peralnq extends HomeCareProducts implements Comparable<Peralnq> {

		public Peralnq(String brand, double price, int availability, String model) {
			super("Peralnq ", brand, price, availability, model);
		}

		private static Set<Peralnq> peralni = new HashSet<Peralnq>();

		public static Set<Peralnq> getPeralni() {
			return peralni;
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			peralni.add(new Peralnq(brand, price, availability, model));
		}

		@Override
		public void removeProduct(int id) {
			Iterator<Peralnq> removeIterator = peralni.iterator();
			while (removeIterator.hasNext()) {
				Peralnq currentElement = removeIterator.next();
				if (id == currentElement.getId()) {
					removeIterator.remove();
				}
			}

		}

		@Override
		public int compareTo(Peralnq peralnq) {
			return (int) (this.getId() - peralnq.getId());
		}

		public static void generatePeralni() {
			String[] peralnqBrands = { "LG", "Samsung", "Beko", "Candy", "Bosch", "Litestart" };
			String[] peralnqModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 500) + 60;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * peralnqBrands.length);
				int randomModel = (int) (Math.random() * peralnqModels.length);

				Peralnq temp = new Peralnq(peralnqBrands[randomBrand], randomPrice, randomQuantity,
						peralnqModels[randomModel]);
				peralni.add(temp);
			}
		}

		public static void showPeralni() {
			for (Peralnq p : peralni) {
				System.out.println(p);
			}
		}

	}

}
