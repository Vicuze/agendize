package mx.agendize.api.v2.forms.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {

	private String id; 
	private FormFieldType type; 
	private String label; 
	private boolean mandatory;
	private int order; 
	private String key; 
	private List<String> values;
	
	/**
	 * List of Form field types.
	 */
	public enum FormFieldType{
		ADDRESS("address"),
		CHECKBOX("checkbox"),
		DATE("date"),
		EMAIL("email"),
		INPUT("input"),
		NAME("name"),
		NUMBER("number"),
		PHONE("phone"),
		PRICE("price"),
		RADIO("radio"),
		SCALERATING("scalerating"),
		SELECT("select"),
		START("start"),
		TEXTAREA("textarea"),
		WEBSITE("website");
		
		private String code;

		/**
		 * @param code
		 */
		private FormFieldType(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, FormFieldType> lookup = new HashMap<String, FormFieldType>();
	    
	    static {
	        for (FormFieldType c : EnumSet.allOf(FormFieldType.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Appointment Status by its code.
	     * @param s code. ex: "accepted".
	     * @return The Country.
	     */
	    public static FormFieldType get(String s) {
	        return lookup.get(s);
	    }
	}
	
	/**
	 * 
	 */
	public Field() {
		super();
	}
	/**
	 * @param type
	 * @param label
	 * @param mandatory
	 * @param order
	 * @param key
	 * @param values
	 */
	public Field(FormFieldType type, String label, boolean mandatory, int order, String key, List<String> values) {
		super();
		this.type = type;
		this.label = label;
		this.mandatory = mandatory;
		this.order = order;
		this.key = key;
		this.values = values;
	}
	/**
	 * @return the type
	 */
	public final FormFieldType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public final void setType(FormFieldType type) {
		this.type = type;
	}
	/**
	 * @return the label
	 */
	public final String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public final void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the mandatory
	 */
	public final boolean isMandatory() {
		return mandatory;
	}
	/**
	 * @param mandatory the mandatory to set
	 */
	public final void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	/**
	 * @return the order
	 */
	public final int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public final void setOrder(int order) {
		this.order = order;
	}
	/**
	 * @return the key
	 */
	public final String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public final void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the values
	 */
	public final List<String> getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public final void setValues(List<String> values) {
		this.values = values;
	}
	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	} 
}
