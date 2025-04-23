package ec.com.account.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = -9115654802536328986L;

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
