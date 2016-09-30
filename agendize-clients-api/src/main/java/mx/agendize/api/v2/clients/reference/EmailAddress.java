package mx.agendize.api.v2.clients.reference;

/**
 * Class representing an email address, and the information whether it's the primary address or not.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class EmailAddress {

	/** Email address. */
	private String email;
	/** Whether the email address is the primary email address of the client. */
	private Boolean primary;
	
	/**
	 * @param email Email address. 
	 * @param primary Whether the email address is the primary email address of the client.
	 */
	public EmailAddress(String email, Boolean primary) {
		super();
		this.email = email;
		this.primary = primary;
	}
	
	/**
	 * Default constructor.
	 */
	public EmailAddress() {
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the primary
	 */
	public final Boolean getPrimary() {
		return primary;
	}
	/**
	 * @param primary the primary to set
	 */
	public final void setPrimary(Boolean primary) {
		this.primary = primary;
	} 
}
