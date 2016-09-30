package mx.agendize.api;

public class AgendizeException extends Exception {

	/**
	 * 
	 */
	public AgendizeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AgendizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AgendizeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AgendizeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AgendizeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
