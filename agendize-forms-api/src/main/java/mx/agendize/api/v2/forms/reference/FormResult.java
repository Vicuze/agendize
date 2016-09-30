package mx.agendize.api.v2.forms.reference;

import mx.agendize.api.v2.reference.AgendizeObject;

public class FormResult extends AgendizeObject {

	private String author; 
	private String result; 
	private Integer formId; 
	private String ipAddress; 
	private int clientId; 
	private String date;
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
	 * @param form the form to set
	 */
	public final void setForm(Integer form) {
		this.formId = form;
	}
	/**
	 * @return the ipAddress
	 */
	public final String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public final void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the formId
	 */
	public final Integer getFormId() {
		return formId;
	}
	/**
	 * @param formId the formId to set
	 */
	public final void setFormId(Integer formId) {
		this.formId = formId;
	}
	/**
	 * @return the clientId
	 */
	public final int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public final void setClientId(int clientId) {
		this.clientId = clientId;
	} 
}
