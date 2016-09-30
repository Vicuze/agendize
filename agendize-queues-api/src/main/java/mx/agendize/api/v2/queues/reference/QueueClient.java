package mx.agendize.api.v2.queues.reference;

import mx.agendize.api.v2.reference.AgendizeObject;

public class QueueClient extends AgendizeObject {

	private String firstName;
	private String lastName; 
	private String emailAddress; 
	private String selfLink;
	
	
	/**
	 * 
	 */
	public QueueClient() {
		super();
	}
	/**
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param selfLink
	 */
	public QueueClient(String firstName, String lastName, String emailAddress, String selfLink) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
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
}
