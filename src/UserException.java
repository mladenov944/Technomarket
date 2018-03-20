
public class UserException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}
	public UserException() {
		super("Registraciqta ne e uspeshna. Vuvedeni sa nevalidni danni!");
	}
}
