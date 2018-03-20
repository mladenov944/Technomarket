import java.util.Set;
import java.util.TreeSet;

public class MobilePhone extends Product implements Comparable<MobilePhone> {

	private static final int MAX_PRODUCTS = 50;
	private static Set<MobilePhone> mobilePhones = new TreeSet<MobilePhone>();
	
	public MobilePhone( String brand, double price, int availability) {
		super("Mobile Phone", brand, price, availability);
	}
	
	public static void generateMobilePhones() {
		String[] MobileBrands = { "Samsung", "Asus", "Lenovo", "iPhone", "Nokia", "Huawei", "Motorola", "HTC", "Sony"};

		for (int count = 0; count < MAX_PRODUCTS; count++) {

			int randomPrice = (int) (Math.random() * 1200) + 100;
			int randomQuantity = (int) (Math.random() * 20);
			int randomBrand = (int) (Math.random() *MobileBrands.length);

			MobilePhone temp = new MobilePhone(MobileBrands[randomBrand], randomPrice, randomQuantity);
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
		return this.getId()-o.getId();
	}
	
}
