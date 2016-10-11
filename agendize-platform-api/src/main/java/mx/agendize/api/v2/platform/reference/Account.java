package mx.agendize.api.v2.platform.reference;

import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;

public class Account extends AgendizeObject {

	/** First name of the user's account. */
	private String firstName;
	/** Last name of the user's account. */ 
	private String lastName;
	/** Email address of the user's account. */ 
	private String email;
	/** user name of the user account. The same value as the email address. Read-only. */
	private String userName;
	/** The SSO token to use to connect to API with the user account. Read-only. */
	private String ssoToken;
	/** Profile settings of the user account. */
	private ProfileSettings profileSettings;
	/** Rights of the account*/
	private List<Right> rights;
	
	public Account() {
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getSsoToken() {
		return ssoToken;
	}

	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}

	public ProfileSettings getProfileSettings() {
		return profileSettings;
	}

	public void setProfileSettings(ProfileSettings profileSettings) {
		this.profileSettings = profileSettings;
	}

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
}
