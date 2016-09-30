package mx.agendize.api.v2.scheduling.reference;

import java.util.TimeZone;

import mx.agendize.api.v2.reference.Address;
import mx.agendize.api.v2.reference.Language;
import mx.agendize.api.v2.reference.Person;
import mx.agendize.api.v2.reference.Picture;

/**
 * Class representing a client.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 * @deprecated Please use the Clients API
 */
@Deprecated
public class Client extends Person {

	/** Gender of the client. Possible values: "male" or "female". */
	private String gender;
	/** Time zone of the client. Only if time zone has been specified. The default value is "US/Eastern". */
	private TimeZone timeZone;
	/** Spoken language of the client. Only if client language has been specified. The default value is none. none value is the account language. */
	private Language language;
	/** Postal address of the client. */ 
	private Address address;
	
	/**
	 * Constructor of the client object with all the fields.
	 * @param firstName First name of the client.
	 * @param lastName Last name of the client.
	 * @param email Email address of the client.
	 * @param phone Phone number of the client.
	 * @param mobilePhone Mobile phone number of the client.
	 * @param description Description of the client.
	 * @param picture Picture of the client.
	 * @param gender Gender of the client. Values: "male" or "female".
	 * @param timeZone Time zone of the client. Only if time zone has been specified.
	 * @param language Spoken language of the client. Only if client language has been specified.
	 * @param address Postal address of the client.
	 */
	public Client(String firstName, String lastName, String email, String phone, String mobilePhone, String description,
			Picture picture, String gender, TimeZone timeZone, Language language, Address address) {
		super(firstName, lastName, email, phone, mobilePhone, description, picture);
		this.gender = gender;
		this.timeZone = timeZone;
		this.language = language;
		this.address = address;
	}

	public Client() {
		super();
	}
	
	public Client(int id, String firstName, String lastName) {
		super(firstName, lastName, null, null, null, null, null);
	}

	public Client(String firstName, String lastName, String email) {
		super(firstName, lastName, email, null, null, null, null);
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the timeZone
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}
	/**
	 * Possible values: "en", "fr", "es", "de", "pt", "ja" and "none". The default value is "none". "none" value is the account language.
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String completeToString() {
		StringBuilder builder = new StringBuilder();
		if (gender != null)
			builder.append("gender=").append(gender).append(", ");
		if (timeZone != null)
			builder.append("timeZone=").append(timeZone).append(", ");
		if (language != null)
			builder.append("language=").append(language).append(", ");
		if (address != null)
			builder.append("address=").append(address).append(", ");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (getFirstName() != null)
			builder.append("getFirstName()=").append(getFirstName()).append(", ");
		if (getLastName() != null)
			builder.append("getLastName()=").append(getLastName()).append(", ");
		if (getEmail() != null)
			builder.append("getEmail()=").append(getEmail()).append(", ");
		if (getPhone() != null)
			builder.append("getPhone()=").append(getPhone()).append(", ");
		if (getMobilePhone() != null)
			builder.append("getMobilePhone()=").append(getMobilePhone()).append(", ");
		if (getDescription() != null)
			builder.append("getDescription()=").append(getDescription()).append(", ");
		if (getPicture() != null)
			builder.append("getPicture()=").append(getPicture()).append(", ");
		if (getId() != null)
			builder.append("getId()=").append(getId()).append(", ");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (getFirstName() != null)
			builder.append(getFirstName()).append(" ");
		if (getLastName() != null)
			builder.append(getLastName());
		return builder.toString();
	}

	
}
