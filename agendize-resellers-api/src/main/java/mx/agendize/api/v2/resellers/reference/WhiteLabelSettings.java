package mx.agendize.api.v2.resellers.reference;

/**
 * Class representing the White label settings of the user account.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class WhiteLabelSettings {

	/** White label Logo of the user account. */
	private String logoURL; 
	/** White label email address of the user account. */ 
	private String emailAddress;
	/** White label header background color. */
	private String headerBackgroundColor;
	
	/**
	 * @param logoURL White label Logo of the user account.
	 * @param emailAddress White label email address of the user account.
	 * @param headerBackgroundColor White label header background color.
	 */
	public WhiteLabelSettings(String logoURL, String emailAddress, String headerBackgroundColor) {
		super();
		this.logoURL = logoURL;
		this.emailAddress = emailAddress;
		this.headerBackgroundColor = headerBackgroundColor;
	}
	
	/**
	 * Default Constructor
	 */
	public WhiteLabelSettings(){
	}
	
	/**
	 * @return the logoURL
	 */
	public final String getLogoURL() {
		return logoURL;
	}
	/**
	 * @param logoURL the logoURL to set
	 */
	public final void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
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
	 * @return the headerBackgroundColor
	 */
	public final String getHeaderBackgroundColor() {
		return headerBackgroundColor;
	}
	/**
	 * @param headerBackgroundColor the headerBackgroundColor to set
	 */
	public final void setHeaderBackgroundColor(String headerBackgroundColor) {
		this.headerBackgroundColor = headerBackgroundColor;
	} 
	
	
}
