package mx.agendize.api.v2.reference;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Start or End time for an appointment. Has a date and a timezone
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Time {

	/** The time, as a combined date-time value (formatted according to RFC 3339, without time zone information). */
	private Date dateTime;
	/** The time zone. Possible values can be found here. The default value is the time zone of the company. */
	private String timeZone;
	
	/**
	 * @param timeZone The time zone. Possible values can be found <a href="http://developers.agendize.com/v2/scheduling/values.jsp#timezones">here</a>. The default value is the time zone of the company
	 * @param dateTime The time, as a combined date-time value (formatted according to <a href="http://www.ietf.org/rfc/rfc3339.txt">RFC 3339</a>, without time zone information).
	 */
	public Time(String timeZone, Date dateTime) {
		this.dateTime = dateTime; 
		this.timeZone = timeZone;
	}

	/** Default constructor. */
	public Time(){
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		if (dateTime != null)
			builder.append(sdf.format(dateTime));
		if (timeZone != null)
			builder.append(" - ").append(timeZone);
		return builder.toString();
	}

}
