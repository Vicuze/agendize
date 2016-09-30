package mx.agendize.api.v2.emails.reference;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AgendizeEmailsObjectHelper {

	private static final String NAME = "name";
	private static final String SUBJECT = "subject";
	private static final String HTML = "html";
	private static final String ID = "id";

	/**
	 * Converts a JSONArray into a list of EmailTemplate objects.
	 * @param emailTemplatesJson json representing the list of EmailTemplates. See <a target="_blank" href="http://devx.agendize.com/v2/messages/emails/reference">http://devx.agendize.com/v2/messages/emails/reference</a>
	 * @return the list of Client objects.
	 * @throws JSONException
	 */
	public static List<EmailTemplate> jsonArrayToEmailTemplateList(JSONArray emailTemplatesJson) {
		List<EmailTemplate> result = new ArrayList<EmailTemplate>();
		for(int j= 0; j<emailTemplatesJson.length(); j++){
    		result.add(jsonObjectToEmailTemplate((JSONObject) emailTemplatesJson.get(j)));
    	}
    	return result;
	}

	/**
	 * Converts a JSONObject from the API into a EmailTemplate object.
	 * @param templateJson json representing the Email Template. See <a target="_blank" href="http://devx.agendize.com/v2/messages/emails/reference">http://devx.agendize.com/v2/messages/emails/reference</a>
	 * @return The EmailTemplate object.
	 */
	public static EmailTemplate jsonObjectToEmailTemplate(JSONObject templateJson) {
		EmailTemplate result = new EmailTemplate(); 
		if(templateJson.has(ID)){
			result.setId(templateJson.getInt(ID));
		}
		if(templateJson.has(HTML)){
			result.setHtml(templateJson.getString(HTML));
		}
		if(templateJson.has(SUBJECT)){
			result.setSubject(templateJson.getString(SUBJECT));
		}
		if(templateJson.has(NAME)){
			result.setName(templateJson.getString(NAME));
		}
		return result; 
	}
	
	/**
	 * Converts an Email Template object into a JSONObject for API use.
	 * @param emailTemplate the EmailTemplate object.
	 * @return JSONObject representing the Email Template.
	 */
	public static JSONObject emailTemplateToJSONObject(EmailTemplate emailTemplate) {
		JSONObject result = new JSONObject(); 
		if(emailTemplate.getId() != null){
			result.put(ID, emailTemplate.getId()); 
		}
		if(emailTemplate.getHtml() != null){
			result.put(HTML, emailTemplate.getHtml()); 
		}
		if(emailTemplate.getSubject() != null){
			result.put(SUBJECT, emailTemplate.getSubject()); 
		}
		if(emailTemplate.getName() != null){
			result.put(NAME, emailTemplate.getName()); 
		}
		return result; 
	}
	
	/**
	 * Converts an MarketingEmail object into a JSONObject for API use.
	 * @param marketingEmail the MarketingEmail object.
	 * @return JSONObject representing the MarketingEmail.
	 */
	public static Object marketingEmailToJSONObject(MarketingEmail marketingEmail) {
		JSONObject result = new JSONObject(); 
		if(marketingEmail.getTemplateId() != null){
			result.put("templateId", marketingEmail.getTemplateId()); 
		} else {
			if(marketingEmail.getSubject() != null){
				result.put(SUBJECT, marketingEmail.getSubject()); 
			}
			if(marketingEmail.getHtml() != null){
				result.put(HTML, marketingEmail.getHtml()); 
			}
		}
		if(marketingEmail.getRecipients() != null && !marketingEmail.getRecipients().isEmpty()){
			JSONArray recipients = new JSONArray();
			for(Integer recipientId: marketingEmail.getRecipients()){
				JSONObject recipientJson = new JSONObject(); 
				recipientJson.put(ID, recipientId);
				recipients.put(recipientJson);
			}
			result.put("recipients", recipients);
		}
		return result; 
	}
}
