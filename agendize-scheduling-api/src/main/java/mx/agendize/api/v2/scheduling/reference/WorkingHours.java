package mx.agendize.api.v2.scheduling.reference;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a set of working hours for a given . Example: Monday - 9am to 1pm, 2pm to 6pm.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class WorkingHours {

	private DayOfTheWeek day;
	private List<Hours> hours = new ArrayList<Hours>();
	
	/** Default constructor. */
	public WorkingHours(){
	}
	
	/**
	 * Days of the week.
	 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
	 *
	 */
	public enum DayOfTheWeek{
		MONDAY("monday"),
		TUESDAY("tuesday"),
		WEDNESDAY("wednesday"),
		THURSDAY("thursday"),
		FRIDAY("friday"),
		SATURDAY("saturday"),
		SUNDAY("sunday");
		
		private String name;

	    /**
		 * @param name
		 */
		private DayOfTheWeek(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		private static final Map<String, DayOfTheWeek> lookup = new HashMap<String, DayOfTheWeek>();
	    
	    static {
	        for (DayOfTheWeek d : EnumSet.allOf(DayOfTheWeek.class))
	            lookup.put(d.getName(), d);
	    }
	 
	    // This method can be used for reverse lookup purpose
	    public static DayOfTheWeek get(String s) {
	        return lookup.get(s);
	    }
	}
	
	/**
	 * Constructor
	 * @param day Day. 
	 * @param hours List of hours.
	 */
	public WorkingHours(DayOfTheWeek day, List<Hours> hours) {
		super();
		this.day = day;
		this.hours = hours;
	}
	/**
	 * @return the day
	 */
	public DayOfTheWeek getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(DayOfTheWeek day) {
		this.day = day;
	}
	/**
	 * @return the hours
	 */
	public List<Hours> getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(List<Hours> hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (day != null)
			builder.append(day.getName().substring(0, 3)).append(" ");
		if (hours != null)
			builder.append(hours);
		return builder.toString();
	}
}