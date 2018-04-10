package com.technomarket.users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;

public class Registration {
	private static final int MAX_PSW_LENGTH = 17;
	private static final int MIN_PSW_LENGTH = 7;
	// private static int unique_id = 1;

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isMale;
	private LocalDate dateOfBirth;
	private LocalDate dateOfRegistration;

	public Registration(String firstName, String lastName, String email, String password, boolean isMale)
			throws UserException, RegistrationException {
		if (!IData.checkString(firstName) || !IData.checkString(lastName) || !IData.checkEMail(email)) {
			throw new UserException();
		}
		if (registrationExists(email)) {
			throw new RegistrationException("NEUSPESHNA REGISTRACIQ! User s email: " + email + " veche sushtestvuva!");
		}
		try {
			setPassword(password);
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		try {
			this.id = getNextID();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMale = isMale;
		this.dateOfBirth = generateRandomDate();
		this.dateOfRegistration = LocalDate.now();
		try {
			addRegToFile(this.id, this.firstName, this.lastName, this.email, this.password, this.isMale,
					this.dateOfBirth, this.dateOfRegistration);
		} catch (RegistrationException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Registration(Long id, String name, String email, String password, boolean isMale) {
		this.id = id;
		this.firstName = name;
		this.email = email;
		this.password = password;
		this.isMale = isMale;

	}

	private LocalDate generateRandomDate() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(1940, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2018, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

		return randomBirthDate;
	}

	private void addRegToFile(long id, String firstName, String lastName, String email, String password, boolean isMale,
			LocalDate dateOfBirth, LocalDate dateOfRegistration) throws IOException, RegistrationException {
		File file = new File("Registrations.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		if (registrationExists(email)) {
			throw new RegistrationException("NEUSPESHNA REGISTRACIQ! User s email: " + email + " veche sushtestvuva!");
		}
		JsonObject jsonObject = new JsonObject();
		FileWriter fileWriter = new FileWriter(file, true);

		jsonObject.addProperty("User id: ", id);
		jsonObject.addProperty("Name: ", firstName + " " + lastName);
		jsonObject.addProperty("Email: ", email);
		jsonObject.addProperty("Password: ", password);
		jsonObject.addProperty("Date of birth: ", dateOfBirth.toString());
		jsonObject.addProperty("Registration date: ", dateOfRegistration.toString());
		jsonObject.addProperty("Is male: ", isMale);
		fileWriter.append(jsonObject.toString());
		fileWriter.append("\r\n");
		fileWriter.flush();
		fileWriter.close();
		System.out.println("Vie se registrirahte uspeshno!");
	}

	public static boolean registrationExists(String email) {
		try {
			ArrayList<JSONObject> registrations = getAllRegistrations();
			for (JSONObject jo : registrations) {
				if (email.equals(jo.get("Email: "))) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static ArrayList<JSONObject> getAllRegistrations() throws Exception {
		ArrayList<JSONObject> json = new ArrayList<JSONObject>();
		JSONObject obj;
		String line = null;
		File file = new File("Registrations.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			obj = (JSONObject) new JSONParser().parse(line);
			json.add(obj);
		}
		bufferedReader.close();
		return json;
	}

	private Long getNextID() throws Exception {
		ArrayList<JSONObject> json = getAllRegistrations();
		if (json.isEmpty()) {
			return 1l;
		}
		return (Long) json.get(json.size() - 1).get("User id: ") + 1;
	}

	// NEW User !
	// public long getId() throws Exception {
	// ArrayList<JSONObject> json = getAllRegistrations();
	// for (JSONObject jo : json) {
	// if (jo.get("Email: ").equals(this.email)) {
	// return (Long) jo.get("User id: ");
	// }
	// }
	// return -1;
	// }

	// NEW User !
	// public static long getId(String email) throws Exception {
	// ArrayList<JSONObject> json = getAllRegistrations();
	// for (JSONObject jo : json) {
	// if (jo.get("Email: ").equals(email)) {
	// return (Long) jo.get("User id: ");
	// }
	// }
	// return -1;
	// }

	static Registration getRegistration(String email) throws Exception {
		ArrayList<JSONObject> json = getAllRegistrations();
		for (JSONObject jo : json) {
			if (jo.get("Email: ").equals(email)) {
				Long id = (Long) jo.get("User id: ");
				String name = (String) jo.get("Name: ");
				String mail = (String) jo.get("Email: ");
				String password = (String) jo.get("Password: ");
				boolean isMale = (Boolean) jo.get("Is male: ");
				Registration registration = new Registration(id, name, mail, password, isMale);
				return registration;
			}
		}
		return null;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isMale() {
		return isMale;
	}

	public long getId() {
		return id;
	}

	public void setPassword(String password) throws UserException {
		if (IData.checkString(password) && (password.length() >= MIN_PSW_LENGTH)
				&& (password.length() <= MAX_PSW_LENGTH))
			this.password = password;
		else {
			throw new UserException("Parolata trqbva da e medu 7 i 17 simvola");
		}
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + ", email: " + this.email + " date of birth: " + this.dateOfBirth
				+ " psw: " + this.password;
	}

}
