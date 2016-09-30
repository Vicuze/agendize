package mx.agendize.api.data.objects;


/**
 * Class representing a form field
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class FormField {

	private String id;
	private Integer order;
	private String label;
	private String type;
	private String mandatory;
	
	/**
	 * @param id
	 * @param order
	 * @param label
	 * @param type
	 * @param mandatory
	 */
	public FormField(String id, Integer order, String label, String type, String mandatory) {
		super();
		this.id = id;
		this.order = order;
		this.label = label;
		this.type = type;
		this.mandatory = mandatory;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the mandatory
	 */
	public String getMandatory() {
		return mandatory;
	}
	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (order != null)
			builder.append("order=").append(order).append(", ");
		if (label != null)
			builder.append("label=").append(label).append(", ");
		if (type != null)
			builder.append("type=").append(type).append(", ");
		if (mandatory != null)
			builder.append("mandatory=").append(mandatory).append(", ");
		return builder.toString();
	}
	
}
