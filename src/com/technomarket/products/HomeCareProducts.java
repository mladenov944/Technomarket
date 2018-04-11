package com.technomarket.products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class HomeCareProducts extends Product {

	public HomeCareProducts(String name, String brand, double price, int availability, String model) {
		super(name, brand, price, availability, model);
	}

	// Generating all Homecare products
	public static List<HomeCareProducts> generateHomeCareProducts() {
		
		List<HomeCareProducts> hcp = new ArrayList<HomeCareProducts>();
		hcp.addAll(Prahosmukachka.generatePrahosmukachki());
		hcp.addAll(Iron.generateIrons());
		hcp.addAll(Peralnq.generatePeralni());
		
		return hcp;
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

		private static Set<Prahosmukachka> prahosmukachki = new TreeSet<Prahosmukachka>();

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			prahosmukachki.add(new Prahosmukachka(brand, price, availability, model));
		}

//		@Override
//		public void removeProduct(int id) {
//			Iterator<Prahosmukachka> removeIterator = prahosmukachki.iterator();
//			while (removeIterator.hasNext()) {
//				Prahosmukachka currentElement = removeIterator.next();
//				if (id == currentElement.getId()) {
//					removeIterator.remove();
//				}
//			}
//
//		}

		public static List<Prahosmukachka> generatePrahosmukachki() {
			String[] PrahosmukachkaBrands = { "LG", "Samsung", "Panasonic", "Philips", "Beko", "Kercher", "Sony" };
			String[] PrahosmukachkaModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };
			
			List<Prahosmukachka> prahos = new ArrayList<Prahosmukachka>();

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 500) + 60;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * PrahosmukachkaBrands.length);
				int randomModel = (int) (Math.random() * PrahosmukachkaModels.length);

				Prahosmukachka temp = new Prahosmukachka(PrahosmukachkaBrands[randomBrand], randomPrice, randomQuantity,
						PrahosmukachkaModels[randomModel]);
				prahosmukachki.add(temp);
			}
			
			for (Prahosmukachka p : prahosmukachki) {
				prahos.add(p);
			}
			return prahos;
		}

		public static Set<Prahosmukachka> getPrahosmukachki() {
			return Collections.unmodifiableSet(prahosmukachki);
		}

		public static void showPrahosmukachki() {
			for (Prahosmukachka p : prahosmukachki) {
				System.out.println(p);
			}
		}

		@Override
		public int compareTo(Prahosmukachka p) {
			return this.getId() - p.getId();
		}

	}

	// Nested class for Irons

	protected static class Iron extends HomeCareProducts implements Comparable<Iron> {

		public Iron(String brand, double price, int availability, String model) {
			super("Utiq ", brand, price, availability, model);
		}

		private static Set<Iron> irons = new TreeSet<Iron>();

		public static Set<Iron> getIrons() {
			return Collections.unmodifiableSet(irons);
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			irons.add(new Iron(brand, price, availability, model));
		}

//		@Override
//		public void removeProduct(int id) {
//			Iterator<Iron> removeIterator = irons.iterator();
//			while (removeIterator.hasNext()) {
//				Iron currentElement = removeIterator.next();
//				if (id == currentElement.getId()) {
//					removeIterator.remove();
//				}
//			}
//
//		}

		@Override
		public int compareTo(Iron iron) {
			return (int) (this.getId() - iron.getId());
		}

		public static List<Iron> generateIrons() {
			String[] ironBrands = { "LG", "Samsung", "Panasonic", "Philips", "Sony", "Bosch" };
			String[] ironModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };
			
			List<Iron> utii = new ArrayList<Iron>();

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 500) + 60;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * ironBrands.length);
				int randomModel = (int) (Math.random() * ironModels.length);

				Iron temp = new Iron(ironBrands[randomBrand], randomPrice, randomQuantity, ironModels[randomModel]);
				irons.add(temp);
				utii.add(temp);
			}
			
			return utii;
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

		private static Set<Peralnq> peralni = new TreeSet<Peralnq>();

		public static Set<Peralnq> getPeralni() {
			return Collections.unmodifiableSet(peralni);
		}

		@Override
		public void addProduct(String brand, double price, int availability, String model) {
			peralni.add(new Peralnq(brand, price, availability, model));
		}

//		@Override
//		public void removeProduct(int id) {
//			Iterator<Peralnq> removeIterator = peralni.iterator();
//			while (removeIterator.hasNext()) {
//				Peralnq currentElement = removeIterator.next();
//				if (id == currentElement.getId()) {
//					removeIterator.remove();
//				}
//			}
//
//		}

		@Override
		public int compareTo(Peralnq peralnq) {
			return (int) (this.getId() - peralnq.getId());
		}

		public static List<Peralnq> generatePeralni() {
			String[] peralnqBrands = { "LG", "Samsung", "Beko", "Candy", "Bosch", "Litestart" };
			String[] peralnqModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };
			
			List<Peralnq> peralnis = new ArrayList<Peralnq>();

			for (int count = 0; count < MAX_PRODUCTS; count++) {

				int randomPrice = (int) (Math.random() * 500) + 60;
				int randomQuantity = (int) (Math.random() * 20);
				int randomBrand = (int) (Math.random() * peralnqBrands.length);
				int randomModel = (int) (Math.random() * peralnqModels.length);

				Peralnq temp = new Peralnq(peralnqBrands[randomBrand], randomPrice, randomQuantity,
						peralnqModels[randomModel]);
				peralni.add(temp);
				peralnis.add(temp);
			}
			return peralnis;
		}

		public static void showPeralni() {
			for (Peralnq p : peralni) {
				System.out.println(p);
			}
		}

	}

}
