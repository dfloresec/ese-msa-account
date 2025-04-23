package ec.com.account.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5422432614541673267L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
