package mx.agendize.api.v2.queues.reference;

import mx.agendize.api.v2.forms.reference.Form;
import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.scheduling.reference.Company;

public class Queue extends AgendizeObject {

	private String author;
	private String name; 
	private String thankMessage; 
	private String welcomeMessage;
	private Company company;
	private Form form;
	
	/**
	 * @return the author
	 */
	public final String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public final void setAuthor(String author) {
		this.author = author;
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
	 * @return the thankMessage
	 */
	public final String getThankMessage() {
		return thankMessage;
	}
	/**
	 * @param thankMessage the thankMessage to set
	 */
	public final void setThankMessage(String thankMessage) {
		this.thankMessage = thankMessage;
	}
	/**
	 * @return the welcomeMessage
	 */
	public final String getWelcomeMessage() {
		return welcomeMessage;
	}
	/**
	 * @param welcomeMessage the welcomeMessage to set
	 */
	public final void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	/**
	 * @return the company
	 */
	public final Company getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public final void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * @return the form
	 */
	public final Form getForm() {
		return form;
	}
	/**
	 * @param form the form to set
	 */
	public final void setForm(Form form) {
		this.form = form;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Queue [");
		if (author != null)
			builder.append("author=").append(author).append(", ");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (thankMessage != null)
			builder.append("thankMessage=").append(thankMessage).append(", ");
		if (welcomeMessage != null)
			builder.append("welcomeMessage=").append(welcomeMessage).append(", ");
		if (company != null)
			builder.append("company=").append(company).append(", ");
		if (form != null)
			builder.append("form=").append(form);
		builder.append("]");
		return builder.toString();
	}
}
