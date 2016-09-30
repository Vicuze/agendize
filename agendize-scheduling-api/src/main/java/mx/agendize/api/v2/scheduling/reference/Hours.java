package mx.agendize.api.v2.scheduling.reference;

/**
 * Class representing a set of hours, with a start hour and an end hour.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Hours {

	/* TODO maybe change this to manage better the hours*/
	private String start;
	private String end;
	
	/**
	 * @param start
	 * @param end
	 */
	public Hours(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	/** Default constructor. */
	public Hours() {
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (start != null)
			builder.append(start).append("-");
		if (end != null)
			builder.append(end);
		return builder.toString();
	}
}
