package com.technomarket.products;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

abstract class OtherProducts extends Product {

	public OtherProducts(String name, String brand, double price, int availability, String model) {
		super(name, brand, price, availability, model);
	}

	// Generating all Other products
		public static void generateOtherProducts() {
			Toster.generateTosteri();
			Pechka.generatePechki();
		}
		
	// Showing all Other products 
		public static void showOtherProducts() {
			Toster.showTosteri();
			Pechka.showPechki();
			
		}
		
		// Nested class for Tosteri

		protected static class Toster extends HomeCareProducts implements Comparable<Toster> {

			public Toster(String brand, double price, int availability, String model) {
				super("Toster ", brand, price, availability, model);
			}

			private static Set<Toster> tosteri = new TreeSet<Toster>();

			@Override
			public void addProduct(String brand, double price, int availability, String model) {
				tosteri.add(new Toster(brand, price, availability, model));
			}

			@Override
			public void removeProduct(int id) {
				Iterator<Toster> removeIterator = tosteri.iterator();
				while (removeIterator.hasNext()) {
					Toster currentElement = removeIterator.next();
					if (id == currentElement.getId()) {
						removeIterator.remove();
					}
				}

			}

			@Override
			public int compareTo(Toster t) {
				return (int) (this.getId() - t.getId());
			}

			public static void generateTosteri() {
				String[] TosterBrands = { "LG", "Panasonic", "Philips", "Beko" };
				String[] TosterModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };

				for (int count = 0; count < MAX_PRODUCTS; count++) {

					int randomPrice = (int) (Math.random() * 100) + 10;
					int randomQuantity = (int) (Math.random() * 20);
					int randomBrand = (int) (Math.random() * TosterBrands.length);
					int randomModel = (int) (Math.random() * TosterBrands.length);

					Toster temp = new Toster(TosterBrands[randomBrand], randomPrice, randomQuantity,
							TosterModels[randomModel]);
					tosteri.add(temp);
				}
			}

			public static Set<Toster> getTosteri() {
				return Collections.unmodifiableSet(tosteri);
			}

			public static void showTosteri() {
				for (Toster t : tosteri) {
					System.out.println(t);
				}
			}

		}
		
		// Nested class for Prahosmukachki

		protected static class Pechka extends HomeCareProducts implements Comparable<Pechka> {

			public Pechka(String brand, double price, int availability, String model) {
				super("Pechka ", brand, price, availability, model);
			}

			private static Set<Pechka> pechki = new TreeSet<Pechka>();

			@Override
			public void addProduct(String brand, double price, int availability, String model) {
				pechki.add(new Pechka(brand, price, availability, model));
			}

			@Override
			public void removeProduct(int id) {
				Iterator<Pechka> removeIterator = pechki.iterator();
				while (removeIterator.hasNext()) {
					Pechka currentElement = removeIterator.next();
					if (id == currentElement.getId()) {
						removeIterator.remove();
					}
				}

			}

			@Override
			public int compareTo(Pechka p) {
				return this.getId()-p.getId();
			}

			public static void generatePechki() {
				String[] PechkaBrands = { "LG","Philips", "Beko", "Kercher", "Bosch"};
				String[] PechkaModels = { "Pro", "Ultra", "Slim", "Fire", "Lite" };

				for (int count = 0; count < MAX_PRODUCTS; count++) {

					int randomPrice = (int) (Math.random() * 500) + 60;
					int randomQuantity = (int) (Math.random() * 20);
					int randomBrand = (int) (Math.random() * PechkaBrands.length);
					int randomModel = (int) (Math.random() * PechkaModels.length);

					Pechka temp = new Pechka(PechkaBrands[randomBrand], randomPrice, randomQuantity,
							PechkaModels[randomModel]);
					pechki.add(temp);
				}
			}

			public static Set<Pechka> getPechki() {
				return Collections.unmodifiableSet(pechki);
			}

			public static void showPechki() {
				for (Pechka p : pechki) {
					System.out.println(p);
				}
			}

		}
}
