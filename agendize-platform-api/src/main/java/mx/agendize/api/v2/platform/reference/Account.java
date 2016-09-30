package mx.agendize.api.v2.platform.reference;

import java.util.Currency;
import java.util.TimeZone;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Language;

public class Account extends AgendizeObject {

	/** First name of the user's account. */
	private String firstName;
	/** Last name of the user's account. */ 
	private String lastName;
	/** Email address of the user's account. */ 
	private String email;
	/** User's account status. Values: "disabled" or "enabled". */
	private String status;
	/** Currency of the account. Used for payments. */ 
	private Currency currency;
	/** Web site URL domain for Agendize dashboard access. */ 
	private String domain;
	/** Tools of the user's account. */
	private String tools;
	/** Default account language used for Agendize dashboard. */ 
	private Language language;
	/** Account time zone. */ 
	private TimeZone timeZone;
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param status
	 * @param currency
	 * @param domain
	 * @param tools
	 * @param language
	 * @param timeZone
	 */
	public Account(String firstName, String lastName, String email, String status, Currency currency, String domain, String tools, Language language, TimeZone timeZone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = status;
		this.currency = currency;
		this.domain = domain;
		this.tools = tools;
		this.language = language;
		this.timeZone = timeZone;
	}
	
	public Account() {
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * @return the tools
	 */
	public String getTools() {
		return tools;
	}
	/**
	 * @param tools the tools to set
	 */
	public void setTools(String tools) {
		this.tools = tools;
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
	
}
