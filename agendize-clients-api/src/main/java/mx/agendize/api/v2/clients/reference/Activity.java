package mx.agendize.api.v2.clients.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Time;

/**
 * Class representing an activity.
 * @author Victor
 *
 */
public class Activity extends AgendizeObject {

	/** Type of the activity. Values: 'appointment', 'call', 'note', 'email', 'sms' */
	private String type; 
	/** Title of the activity. */
	private Title title; 
	/** The date time of the activity. */
	private Time date; 
	/** URL link to the activity resource description. */
	private String selfLink; 
	
	/**
	 * @param title
	 * @param type
	 * @param date
	 * @param selfLink
	 */
	public Activity(Title title, String type, Time date, String selfLink) {
		super();
		this.title = title;
		this.type = type;
		this.date = date;
		this.selfLink = selfLink;
	}

	/**
	 * 
	 */
	public Activity() {
		super();
	}

	public enum Title{
	 	APPOINTMENT("appointment"),
	 	CALL("call"),
	 	NOTE("note"),
	 	EMAIL("email"),
	 	SMS("sms");
	 	
	 	private String code;

		/**
		 * @param code
		 */
		private Title(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, Title> lookup = new HashMap<String, Title>();
	    
	    static {
	        for (Title c : EnumSet.allOf(Title.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    // This method can be used for reverse lookup purpose
	    public static Title get(String s) {
	        return lookup.get(s);
	    }
}

	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public final Title getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(Title title) {
		this.title = title;
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
	 * @return the selfLink
	 */
	public final String getSelfLink() {
		return selfLink;
	}

	/**
	 * @param selfLink the selfLink to set
	 */
	public final void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}
}
