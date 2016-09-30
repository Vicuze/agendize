package mx.agendize.api.v2.clients.reference;

/**
 * Class representing a phone number, with the information whether it's mobile or not, and whether it's the client's primary number or not.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class PhoneNumber {

	/** Phone number. */
	private String number; 
	/** Whether the phone number is the primary phone number of the client. */
	private Boolean primary; 
	/** Whether the phone number is a mobile phone number. */
	private Boolean mobile;
	
	/**
	 * @param number Phone number.
	 * @param primary Whether the phone number is the primary phone number of the client.
	 * @param mobile Whether the phone number is a mobile phone number.
	 */
	public PhoneNumber(String number, Boolean primary, Boolean mobile) {
		super();
		this.number = number;
		this.primary = primary;
		this.mobile = mobile;
	}
	
	/**
	 * Default comstructor.
	 */
	public PhoneNumber() {
	}

	/**
	 * @return the number
	 */
	public final String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public final void setNumber(String number) {
		this.number = number;
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
	/**
	 * @return the mobile
	 */
	public final Boolean getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public final void setMobile(Boolean mobile) {
		this.mobile = mobile;
	} 
}
