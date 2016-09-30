package mx.agendize.api.v2.resellers.reference;

import java.util.TimeZone;

import mx.agendize.api.v2.reference.Language;

public class Preferences {

	private String timeZone;
	private Language language;

	/** Default constructor. */
	public Preferences(){
	}
	/**
	 * @param timeZone
	 * @param language
	 */
	public Preferences(String timeZone, Language language) {
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
