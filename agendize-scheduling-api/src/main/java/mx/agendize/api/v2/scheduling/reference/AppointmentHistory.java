package mx.agendize.api.v2.scheduling.reference;

import mx.agendize.api.v2.reference.Time;

public class AppointmentHistory {

	private String text;
	private Time date; 
	private User user;

	/**
	 * 
	 */
	public AppointmentHistory() {
		super();
	}
	/**
	 * @param text
	 * @param date
	 * @param user
	 */
	public AppointmentHistory(String text, Time date, User user) {
		super();
		this.text = text;
		this.date = date;
		this.user = user;
	} 
	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public final void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the date
	 */
	public final Time getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(Time date) {
		this.date = date;
	}
	/**
	 * @return the user
	 */
	public final User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public final void setUser(User user) {
		this.user = user;
	}
}
