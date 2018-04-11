import com.technomarket.products.SearchBar;

public class Commercial extends Thread {

	public Commercial() {
		setDaemon(true);
	}

	@Override
	public void run() {

		while (true) {
			if (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					return;
				}
				int random = (int) (Math.random() * 3) + 1;
				if (random == 1) {
					System.out.println();
					System.out.println("----------------------------------------------------------------");
					System.out.println("Samo ytre - vsichki ceni namaleni s 10%. Samo v Tehchnomarket !");
					System.out.println("----------------------------------------------------------------");
				} else {
					if(random == 2){
					System.out.println();
					System.out.println("----------------------------------------------------------------");
					System.out.println("Vuzpolzvaite se ot nashite visokokachestveni produkti sega !");
					System.out.println("----------------------------------------------------------------");
					}else {
						System.out.println();
						System.out.println("----------------------------------------------------------------");
						System.out.println("Kupete samo sega na udivitelna cena: ");
						try {
							SearchBar.getRandomProduct();
						} catch (Exception e) {
							System.out.println("Nqma nalichni namaleniq! Sajalqvame za tova.");
							e.printStackTrace();
						}
						System.out.println("----------------------------------------------------------------");
					}
				}
			}
		}
	}

}
