import java.time.LocalDate;
import java.util.Random;

public class Registration {
	private String firstName;
	private String lastName;
	private String email;
	private boolean isMale;
	private LocalDate dateOfBirth;

	
	public boolean isMale() {
		return isMale;
	}

	public Registration(String firstName, String lastName, String email, boolean isMale) throws UserException {
		if (!checkString(firstName) || !checkString(lastName) || !checkEMail(email)) {
			throw new UserException();
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.dateOfBirth = generateRandomDate();
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	boolean checkString(String str) {
		return ((str != null) && (str.trim().length() > 0)) ? true : false;
	}

	boolean checkEMail(String mail) {
		if ((checkString(mail)) && (mail.contains("@")) && (mail.contains("."))) {
			int indexQ = 0;
			int indexDot = 0;
			for (int i = 0; i < mail.length(); i++) {
				if (mail.charAt(i) == '@') {
					indexQ = i;
				}
				if (mail.charAt(i) == '.') {
					indexDot = i;
				}
			}
			if (indexQ < indexDot) {
				return true;
			} else {
				System.out.println("Vuveli ste nevaliden email");
				return false;
			}
		} else {
			System.out.println("Vuveli ste nevaliden email");
			return false;
		}
	}

	private LocalDate generateRandomDate() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

		return randomBirthDate;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + ", email: " + this.email + " date of birth: " + this.dateOfBirth;
	}
}
