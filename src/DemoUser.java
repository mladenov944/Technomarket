
public class DemoUser {
	public static void main(String[] args) {
		User user = User.generateUser();
		System.out.println(user.toString());
		User u2 = null;
		try {
			u2 = new User(new Registration("Harry", "Potter", "harry@abv.bg", "1234567", true));
		} catch (UserException e) {
			e.printStackTrace();
		}
		System.out.println(u2.toString());
		System.out.println("test");
		u2.login();
		System.out.println(u2.isLoged());
		u2.logout();
		System.out.println(u2.isLoged());
		for (int i = 0; i < 10; i++) {
			User u = User.generateUser();
			System.out.println(u.toString());
		}
		
	}
}
