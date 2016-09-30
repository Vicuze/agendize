package mx.agendize.api.v2.emails.reference;

import mx.agendize.api.v2.reference.AgendizeObject;

public class EmailTemplate extends AgendizeObject {

	private String subject; 
	private String html;
	private String name;

	public EmailTemplate(){
		
	}; 

	/**
	 * @param subject
	 * @param html
	 * @param name
	 */
	public EmailTemplate(String subject, String html, String name) {
		super();
		this.subject = subject;
		this.html = html;
		this.name = name;
	}

	/**
	 * @return the subject
	 */
	public final String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public final void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the html
	 */
	public final String getHtml() {
		return html;
	}
	/**
	 * @param html the html to set
	 */
	public final void setHtml(String html) {
		this.html = html;
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
	
	
}
