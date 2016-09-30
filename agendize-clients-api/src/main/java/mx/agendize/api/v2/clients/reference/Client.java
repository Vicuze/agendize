package mx.agendize.api.v2.clients.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.agendize.api.v2.reference.Address;
import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Language;
import mx.agendize.api.v2.reference.Picture;

/**
 * Class representing a client.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Client extends AgendizeObject {

	/** First name of the client. */
	private String firstName;
	/** Last name of the client. */ 
	private String lastName;
	/** Email addresses of the client. */ 
	private List<EmailAddress> emailAddresses;
	/** Phone numbers of the client. */
	private List<PhoneNumber> phoneNumbers;
	/** Gender of the client. */
	private Gender gender;
	/** Time zone of the client. Only if time zone has been specified. The default value is "US/Eastern". */
	private String timeZone;
	/** Spoken language of the client. Only if client language has been specified. The default value is none. none value is the account language. */
	private Language language;
	/** Description of the person. */
	private String description;
	/** Picture of the person. */
	private Picture picture;
	/** Postal address of the client. */ 
	private Address address;
	/** Tags of the client */
	private List<Tag> tags;
	
	public enum Gender{
		MALE("male"),
		FEMALE("female"),
		UNDEFINED("undefined");
	
		private String code;

		/**
		 * @param code
		 */
		private Gender(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, Gender> lookup = new HashMap<String, Gender>();
	    
	    static {
	        for (Gender c : EnumSet.allOf(Gender.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a gender by its code.
	     * @param s code. ex: "male".
	     * @return The Gender.
	     */
	    public static Gender get(String s) {
	        return lookup.get(s);
	    }
	}
	
	public Client() {
		super();
	}

	/**
	 * @param firstName First name of the client.
	 * @param lastName Last name of the client.
	 * @param emailAddresses Email addresses of the client.
	 * @param phoneNumbers Phone numbers of the client.
	 * @param gender Gender of the client. Values: "male", "female" or "undefined".
	 * @param timeZone Time zone of the client. Only if time zone has been specified.
	 * @param language Spoken language of the client. Only if client language has been specified.
	 * @param description Description of the client.
	 * @param picture Client picture url and mime type.
	 * @param address Postal address of the client.
	 * @param tags Tags of the client.
	 */
	public Client(String firstName, String lastName, List<EmailAddress> emailAddresses, List<PhoneNumber> phoneNumbers, Gender gender, String timeZone,
			Language language, String description, Picture picture, Address address, List<Tag> tags) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddresses = emailAddresses;
		this.phoneNumbers = phoneNumbers;
		this.gender = gender;
		this.timeZone = timeZone;
		this.language = language;
		this.description = description;
		this.picture = picture;
		this.address = address;
		this.tags = tags;
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
	 * @return the emailAddresses
	 */
	public final List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	/**
	 * @param emailAddresses the emailAddresses to set
	 */
	public final void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	/**
	 * @return the phoneNumbers
	 */
	public final List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * @param phoneNumbers the phoneNumbers to set
	 */
	public final void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	/**
	 * @return the gender
	 */
	public final Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public final void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the timeZone
	 */
	public final String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public final void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the language
	 */
	public final Language getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public final void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the picture
	 */
	public final Picture getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public final void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * @return the address
	 */
	public final Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public final void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the tags
	 */
	public final List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public final void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
