package mx.agendize.api.v2.emails.reference;

import java.util.List;

public class MarketingEmail {

	private Integer templateId; 
	private String subject;  
	private String html;  
	private List<Integer> recipients;
	
	/**
	 * @param subject The subject of the email. Only required if you don't use an email template.
	 * @param html The content of the email. Only required if you don't use an email template.
	 * @param recipients List of recipients.
	 */
	public MarketingEmail(String subject, String html, List<Integer> recipients) {
		super();
		this.subject = subject;
		this.html = html;
		this.recipients = recipients;
	}
	
	/**
	 * @param templateId The id of the email template to use.
	 * @param recipients List of recipients.
	 */
	public MarketingEmail(Integer templateId, List<Integer> recipients) {
		super();
		this.templateId = templateId;
		this.recipients = recipients;
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
	 * @return the templateId
	 */
	public final Integer getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId the templateId to set
	 */
	public final void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	/**
	 * @return the recipients
	 */
	public final List<Integer> getRecipients() {
		return recipients;
	}
	/**
	 * @param recipients the recipients to set
	 */
	public final void setRecipients(List<Integer> recipients) {
		this.recipients = recipients;
	} 
	
	
}
