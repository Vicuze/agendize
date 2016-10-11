	package mx.agendize.api.v2.forms.reference;

import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;

public class Form extends AgendizeObject {

	private String name; 
	private String title; 
	private String submitMessage; 
	private List<Field> fields;
	
	public Form() {
		super();
	}

	public Form(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the submitMessage
	 */
	public final String getSubmitMessage() {
		return submitMessage;
	}
	/**
	 * @param submitMessage the submitMessage to set
	 */
	public final void setSubmitMessage(String submitMessage) {
		this.submitMessage = submitMessage;
	}
	/**
	 * @return the fields
	 */
	public final List<Field> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public final void setFields(List<Field> fields) {
		this.fields = fields;
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
}
