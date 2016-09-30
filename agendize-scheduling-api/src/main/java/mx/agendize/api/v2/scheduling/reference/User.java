package mx.agendize.api.v2.scheduling.reference;

public class User {

	private String firstName; 
	private String lastName; 
	private String email; 
	private String userName;
	
	/**
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param userName
	 */
	public User(String firstName, String lastName, String email, String userName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
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
	 * @return the userName
	 */
	public final String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public final void setUserName(String userName) {
		this.userName = userName;
	} 
	
}
