package mx.agendize.api.v2.queues.reference;

import mx.agendize.api.v2.reference.AgendizeObject;

/**
 * Class representing the client of a queue.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 * TODO see if possible to extend Person
 */
public class QueueClient extends AgendizeObject {

	/** First name of the client. */
	private String firstName;
	/**  Last name of the client. */
	private String lastName; 
	/** Email address of the client. */
	private String emailAddress; 
	/** Phone number of the client. */
	private String phoneNumber; 
	/** URL link to the client resource description. */
	private String selfLink;
	
	/**
	 * 
	 */
	public QueueClient() {
		super();
	}

	public QueueClient(String firstName, String lastName, String emailAddress, String phoneNumber, String selfLink) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.selfLink = selfLink;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the emailAddress
	 */
	public final String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public final void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the selfLink
	 */
	public final String getSelfLink() {
		return selfLink;
	}
	/**
	 * @param selfLink the selfLink to set
	 */
	public final void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
