package mx.agendize.api.data.objects;

import org.w3c.dom.Element;

public class FormFieldResult {

	private FormField formField; 
	private String result;
	
	/**
	 * 
	 */
	public FormFieldResult() {
		super();
	}
	/**
	 * @param formField
	 * @param result
	 */
	public FormFieldResult(FormField formField, String result) {
		super();
		this.formField = formField;
		this.result = result;
	} 
	/**
	 * @return the formField
	 */
	public final FormField getFormField() {
		return formField;
	}
	/**
	 * @param formField the formField to set
	 */
	public final void setFormField(FormField formField) {
		this.formField = formField;
	}
	/**
	 * @return the result
	 */
	public final String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public final void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * Constructs a FormField with the XML node. Example of the XML node:
	 * <pre>
	 * {@code
	 * <form:field id="XXXXXXX" order="0" label="Message" type="textarea" mandatory="false">
	 * 	<form:result>Hello, I want to contact you!</form:result>
	 * </form:field>
	 * }
	 * </pre>
	 * @param element XML element.
	 */
	public FormFieldResult(Element element) {
		this.formField = new FormField(element.getAttribute("id"), Integer.parseInt(element.getAttribute("order")), element.getAttribute("label"), element.getAttribute("type"), element.getAttribute("mandatory"));
		Element result = (Element) element.getFirstChild();
		this.result = result.getTextContent();
	}
}
