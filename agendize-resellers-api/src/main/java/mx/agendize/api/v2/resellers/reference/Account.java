package mx.agendize.api.v2.resellers.reference;

import java.util.Currency;
import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Time;

/**
 * Agendize account related to the reseller account.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Account extends AgendizeObject {

	/** User name of the user account. His email address. */
	private String clientName;
	/** First name of the user's account. */
	private String firstName;
	/** Last name of the user account. */ 
	private String lastName;
	/** Email address of the user account. */ 
	private String email;
	/** Password of the user account. */ 
	private String password;
	/** Currency of the user account. */
	private Currency currency;
	/** Internal reseller client identifier. Have to be unique for each client account. */
	private String resellerId;
	/** Profile settings of the user account. */
	private Preferences preferences;
	/** Account credits. Read only */
	private Integer credit;
	/** The name of the organization of the user account. */
	private String organization;
	/** The created time of the user account. Read-only. */
	private Time created;
	/** The SSO token to use to connect to API with the user account. Read-only. */ 
	private String ssoToken;
	/** The account profile type of the user account. */ 
	private Profile profile;
	/** List of tools available for the user account. Values: save,scheduling,chat,call,calltracking,form. */
	private List<String> tools;
	/** White label settings of the user account. */
	private WhiteLabelSettings whiteLabel; 

	/** Default constructor. */
	public Account() {
	}

	/**
	 * @param clientName The account name of the user account. Displayed in the reseller console.
	 * @param firstName First name of the user account.
	 * @param lastName Last name of the user account.
	 * @param email Email address of the user account. Used as user name, it is not possible to change after account creation.
	 * @param password Password of the user account. Work only with insert and update methods.
	 * @param currency Currency of the user account. Values: USD, CAD, EUR, GBP or JPY.
	 * @param resellerId Internal reseller client identifier. Have to be unique for each client account.
	 * @param preferences Profile settings of the user account.
	 * @param credit Account credits. Read-Only
	 * @param organization The name of the organization of the user account.
	 * @param profile The account profile type of the user account.
	 * @param tools List of tools availables for the user account. Values: save,scheduling,chat,call,calltracking,form.
	 * @param whiteLabel White label settings of the user account.
	 */
	public Account(String clientName, String firstName, String lastName, String email, String password, Currency currency, String resellerId, Preferences preferences,
			Integer credit, String organization, Profile profile, List<String> tools, WhiteLabelSettings whiteLabel) {
		super();
		this.clientName = clientName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.currency = currency;
		this.resellerId = resellerId;
		this.preferences = preferences;
		this.credit = credit;
		this.organization = organization;
		this.profile = profile;
		this.tools = tools;
		this.whiteLabel = whiteLabel;
	}

	/**
	 * Returns true if the call tracking is activated for this account.
	 * @return
	 */
	public boolean hasCallTracking(){
		if (tools != null && !tools.isEmpty()){
			for(String tool: tools){
				if(tool.equals("calltracking")){
					return true; 
				}
			}
		}
		return false; 
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
	 * @return the organization
	 */
	public final String getOrganization() {
		return organization;
	}
	/**
	 * @param organization the organization to set
	 */
	public final void setOrganization(String organization) {
		this.organization = organization;
	}
	/**
	 * @return the preferences
	 */
	public final Preferences getPreferences() {
		return preferences;
	}
	/**
	 * @param preferences the preferences to set
	 */
	public final void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	/**
	 * @return the currency
	 */
	public final Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public final void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the created
	 */
	public final Time getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public final void setCreated(Time created) {
		this.created = created;
	}
	/**
	 * @return the ssoToken
	 */
	public final String getSsoToken() {
		return ssoToken;
	}
	/**
	 * @param ssoToken the ssoToken to set
	 */
	public final void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}
	/**
	 * @return the credit
	 */
	public final Integer getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public final void setCredit(Integer credit) {
		this.credit = credit;
	}
	/**
	 * @return the profile
	 */
	public final Profile getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public final void setProfile(Profile profile) {
		this.profile = profile;
	}
	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the resellerId
	 */
	public final String getResellerId() {
		return resellerId;
	}
	/**
	 * @param resellerId the resellerId to set
	 */
	public final void setResellerId(String resellerId) {
		this.resellerId = resellerId;
	}
	/**
	 * @return the tools
	 */
	public final List<String> getTools() {
		return tools;
	}
	/**
	 * @param tools the tools to set
	 */
	public final void setTools(List<String> tools) {
		this.tools = tools;
	}
	/**
	 * @return the whiteLabel
	 */
	public final WhiteLabelSettings getWhiteLabel() {
		return whiteLabel;
	}
	/**
	 * @param whiteLabel the whiteLabel to set
	 */
	public final void setWhiteLabel(WhiteLabelSettings whiteLabel) {
		this.whiteLabel = whiteLabel;
	}
	/**
	 * @return the clientName
	 */
	public final String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public final void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [");
		if (clientName != null)
			builder.append("clientName=").append(clientName).append(", ");
		if (firstName != null)
			builder.append("firstName=").append(firstName).append(", ");
		if (lastName != null)
			builder.append("lastName=").append(lastName).append(", ");
		if (email != null)
			builder.append("email=").append(email).append(", ");
		if (password != null)
			builder.append("password=").append(password).append(", ");
		if (currency != null)
			builder.append("currency=").append(currency).append(", ");
		if (resellerId != null)
			builder.append("resellerId=").append(resellerId).append(", ");
		if (preferences != null)
			builder.append("preferences=").append(preferences).append(", ");
		if (credit != null)
			builder.append("credit=").append(credit).append(", ");
		if (organization != null)
			builder.append("organization=").append(organization).append(", ");
		if (created != null)
			builder.append("created=").append(created).append(", ");
		if (ssoToken != null)
			builder.append("ssoToken=").append(ssoToken).append(", ");
		if (profile != null)
			builder.append("profile=").append(profile).append(", ");
		if (tools != null)
			builder.append("tools=").append(tools).append(", ");
		if (whiteLabel != null)
			builder.append("whiteLabel=").append(whiteLabel);
		builder.append("]");
		return builder.toString();
	}
}
