package mx.agendize.api.v2.clients.reference;

/**
 * Class representing a tag object.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Tag {

	/** Text of tag. */
	private String tag;
	/** Color of tag. Web format (#xxxxxx) */
	private String color;
	
	/**
	 * @param tag
	 * @param color
	 */
	public Tag(String tag, String color) {
		super();
		this.tag = tag;
		this.color = color;
	}

	/**
	 * Default constructor.
	 */
	public Tag() {
	}
	
	/**
	 * @return the tag
	 */
	public final String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public final void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return the color
	 */
	public final String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public final void setColor(String color) {
		this.color = color;
	} 
}
