package mx.agendize.api.v2.reference;

/**
 * Class representing the preferences in an account.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 * 
 */
public class AccountPreferences {

	private String timeZone;
	private Language language;

	/** Default constructor. */
	public AccountPreferences(){
	}
	/**
	 * @param timeZone
	 * @param language
	 */
	public AccountPreferences(String timeZone, Language language) {
		super();
		this.timeZone = timeZone;
		this.language = language;
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
}
