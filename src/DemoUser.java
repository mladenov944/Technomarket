
public class DemoUser {
	public static void main(String[] args) {
		Registration registration;
		try {
			registration = new Registration("Mircho", "Minchev", "dsadss@abv.bg", true);
			System.out.println(registration.getDateOfBirth());
		} catch (UserException e) {
			e.getMessage();
			e.printStackTrace();
		}
		System.out.println("programata si produljava");
		User user = User.generateUser();
		System.out.println(user.toString());
		try {
			User u2=new User(new Registration("Milcho", " ", "milcho.abv@bg", true));
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
