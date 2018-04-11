package com.technomarket.products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class OtherProducts extends Product {

	public OtherProducts(String name, String brand, double price, int availability, String model) {
		super(name, brand, price, availability, model);
	}

	// Generating all Other products
		public static List<OtherProducts> generateOtherProducts() {
			
			List<OtherProducts> op = new ArrayList<OtherProducts>();
			op.addAll(Toster.generateTosteri());
			op.addAll(Pechka.generatePechki());
			
			return op;
		}
		
	// Showing all Other products 
		public static void showOtherProducts() {
			Toster.showTosteri();
			Pechka.showPechki();
			
		}
		
		// Nested class for Tosteri

		public static class Toster extends OtherProducts implements Comparable<Toster> {

			public Toster(String brand, double price, int availability, String model) {
				super("Toster ", brand, price, availability, model);
			}

			private static Set<Toster> tosteri = new TreeSet<Toster>();

			@Override
			public int compareTo(Toster t) {
				return (int) (this.getId() - t.getId());
			}

			public static List<Toster> generateTosteri() {
				String[] TosterBrands = { "LG", "Panasonic", "Philips", "Beko" };
				String[] TosterModels = { "Pro", "Ultra", "Slim", "Clean", "Lite" };

				List<Toster> tosters = new ArrayList<Toster>();
				
				for (int count = 0; count < MAX_PRODUCTS; count++) {

					int randomPrice = (int) (Math.random() * 100) + 10;
					int randomQuantity = (int) (Math.random() * 20);
					int randomBrand = (int) (Math.random() * TosterBrands.length);
					int randomModel = (int) (Math.random() * TosterBrands.length);

					Toster temp = new Toster(TosterBrands[randomBrand], randomPrice, randomQuantity,
							TosterModels[randomModel]);
					tosteri.add(temp);
					tosters.add(temp);
				}
				return tosters;
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
		
		// Nested class for Pechka

		public static class Pechka extends OtherProducts implements Comparable<Pechka> {

			public Pechka(String brand, double price, int availability, String model) {
				super("Pechka ", brand, price, availability, model);
			}

			private static Set<Pechka> pechki = new TreeSet<Pechka>();

			@Override
			public int compareTo(Pechka p) {
				return this.getId()-p.getId();
			}

			public static List<Pechka> generatePechki() {
				String[] PechkaBrands = { "LG","Philips", "Beko", "Kercher", "Bosch"};
				String[] PechkaModels = { "Pro", "Ultra", "Slim", "Fire", "Lite" };

				List<Pechka> pechkis = new ArrayList<Pechka>();
				
				for (int count = 0; count < MAX_PRODUCTS; count++) {

					int randomPrice = (int) (Math.random() * 500) + 60;
					int randomQuantity = (int) (Math.random() * 20);
					int randomBrand = (int) (Math.random() * PechkaBrands.length);
					int randomModel = (int) (Math.random() * PechkaModels.length);

					Pechka temp = new Pechka(PechkaBrands[randomBrand], randomPrice, randomQuantity,
							PechkaModels[randomModel]);
					pechki.add(temp);
					pechkis.add(temp);
				}
				return pechkis;
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
