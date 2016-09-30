package mx.agendize.api.v2.scheduling.reference;

public class SchedulingFormField {

	private String title; 
	private String value;
	
	/**
	 * 
	 */
	public SchedulingFormField() {
		super();
	}
	/**
	 * @param title
	 * @param value
	 */
	
	public SchedulingFormField(String title, String value) {
		super();
		this.title = title;
		this.value = value;
	}
	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	} 
	
	
	
}
