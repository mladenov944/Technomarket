import java.time.LocalDate;
import java.util.Random;

public class Registration {
	private static final int MAX_PSW_LENGTH = 17;
	private static final int MIN_PSW_LENGTH = 7;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isMale;
	private LocalDate dateOfBirth;

	
	public boolean isMale() {
		return isMale;
	}

	public Registration(String firstName, String lastName, String email, String password, boolean isMale)
			throws UserException {
		if (!checkString(firstName) || !checkString(lastName) || !checkEMail(email)) {
			throw new UserException();
		}
		try {
			setPassword(password);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.dateOfBirth = generateRandomDate();
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
		int minDay = (int) LocalDate.of(1940, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2018, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

		return randomBirthDate;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws UserException {
		if (checkString(password) && (password.length() >= MIN_PSW_LENGTH) && (password.length() <= MAX_PSW_LENGTH))
			this.password = password;
		else {
			throw new UserException("Parolata trqbva da e medu 7 i 17 simvola");
		}
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + ", email: " + this.email + " date of birth: " + this.dateOfBirth+" psw: "+this.password;
	}
}
