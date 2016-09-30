package mx.agendize.api.v2.reference;


/**
 * Can be a client or a staff member.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public abstract class Person extends AgendizeObject {

	/** First name of the person. */
	private String firstName;
	/** Last name of the person. */ 
	private String lastName;
	/** Email address of the person. */ 
	private String email;
	/** Phone number of the person. */
	private String phone;
	/** Mobile phone number of the person. */
	private String mobilePhone;
	/** Description of the person. */
	private String description;
	/** Picture of the person. */
	private Picture picture;
	
	/**
	 * @param firstName First name of the person.
	 * @param lastName Last name of the person.
	 * @param email Email address of the person.
	 * @param phone Phone number of the person.
	 * @param mobilePhone Mobile phone number of the person.
	 * @param description Description of the person.
	 * @param picture Picture of the person.
	 */
	public Person(String firstName, String lastName, String email, String phone, String mobilePhone, String description, Picture picture) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.description = description;
		this.picture = picture;
	}
	
	/**
	 * 
	 */
	public Person() {
		super();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
}
