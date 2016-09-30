package mx.agendize.api.data.objects;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the details of an Agendize form.
 * 
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 * 
 */
public class FormDetails extends ButtonDetails {

	private String description;
	private List<FormField> formFields = new ArrayList<FormField>(); 

	/**
	 * @param id
	 * @param name
	 */
	public FormDetails(Integer id, String name) {
		super(id, name);
	}

	public FormDetails(NodeList list) {
		super(list);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName() == "entry:value") {
					Element element = (Element) node;
					switch (element.getAttribute("name")) {
					case "form-id":
						setButtonId(Integer.parseInt(element.getAttribute("value")));
						break;
					case "name":
						setName(element.getAttribute("value"));
						break;
					case "description":
						this.description = element.getAttribute("value");
						break;
					default:
						break;
					}
				} else if (node.getNodeName() == "form:field"){
					Element element = (Element) node;
					FormField ff = new FormField(element.getAttribute("id"), Integer.parseInt(element.getAttribute("order")), element.getAttribute("label"), element.getAttribute("type"), element.getAttribute("mandatory"));
					formFields.add(ff);
				}
			}
		}
	}
	
	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the formFields
	 */
	public final List<FormField> getFormFields() {
		return formFields;
	}

	/**
	 * @param formFields the formFields to set
	 */
	public final void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormDetails [");
		if (getButtonId() != null)
			builder.append("id=").append(getButtonId()).append(", ");
		if (getName() != null)
			builder.append("name=").append(getName()).append(", ");
		if (description != null)
			builder.append("description=").append(description).append(", ");
		if (formFields != null)
			builder.append("formFields=").append(formFields);
		builder.append("]");
		return builder.toString();
	}
}
