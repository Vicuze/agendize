package mx.agendize.api.v2.emails;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.emails.reference.AgendizeEmailsObjectHelper;
import mx.agendize.api.v2.emails.reference.EmailTemplate;
import mx.agendize.api.v2.emails.reference.MarketingEmail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailsManager extends AgendizeApiManager {
	
	private static final String TEMPLATES = "/templates";
	private static final String SEND = "/send";
	static final Logger logger = LogManager.getLogger(EmailsManager.class);

	public EmailsManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/**
	 * Gives the list of emailTemplates for an agendize account
	 * @return The list of emailTemplate objects.
	 * @throws IOException
	 * @throws JSONException
	 */
	public List<EmailTemplate> getEmailTemplates() throws IOException {
		logger.info("getEmailTemplates.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String emailTemplatesString = APIUtils.getRequest(EMAILS_API_URL + TEMPLATES, properties);
		JSONObject jsonObject = new JSONObject(emailTemplatesString);
		List<EmailTemplate> result = AgendizeEmailsObjectHelper.jsonArrayToEmailTemplateList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " emailTemplates found.");
		return result;
	} 
	
	/**
	 * Returns emailTemplate object given the emailTemplate identifier
	 * @param id EmailTemplate identifier.
	 * @return The emailTemplate object.
	 * @throws JSONException
	 * @throws IOException
	 */
	public EmailTemplate get(int id) throws JSONException, IOException{
		logger.info("Get emailTemplate with id = " + id + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String emailTemplateString = APIUtils.getRequest(EMAILS_API_URL + TEMPLATES + "/" + id, properties);
		JSONObject jsonObject = new JSONObject(emailTemplateString);
		EmailTemplate result = null;
  		if(jsonObject.has("error")){
  			JSONObject errorJson = jsonObject.getJSONObject("error");
  			logger.error("Error in get email template: " + errorJson.getInt("code")+" "+errorJson.getString("reason")+" - "+errorJson.getString("message"));
  		} else {
		    result = AgendizeEmailsObjectHelper.jsonObjectToEmailTemplate(jsonObject);
			if(result != null){
				logger.info("EmailTemplate found.");
			} else {
				logger.info("No emailTemplate found for this id.");
			}
  		}
		return result;
	}
	
	/**
	 * Creates a emailTemplate. 
	 * @param emailTemplate the emailTemplate to create. 
	 * @return The emailTemplate created.
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public EmailTemplate create(EmailTemplate emailTemplate) throws JSONException, AgendizeException{
		logger.info("Create new emailTemplate.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeEmailsObjectHelper.emailTemplateToJSONObject(emailTemplate).toString();
		String emailTemplateString = APIUtils.postRequest(EMAILS_API_URL + TEMPLATES, json, properties);
		JSONObject jsonObject = new JSONObject(emailTemplateString);
		EmailTemplate result = AgendizeEmailsObjectHelper.jsonObjectToEmailTemplate(jsonObject);
		logger.info("EmailTemplate created succesfully. id = " + result.getId());
        return result;
	}
	
	/**
	 * Updates a emailTemplate
	 * @param emailTemplate EmailTemplate to update. Must have an id.
	 * @return udpated emailTemplate.
	 * @throws AgendizeException
	 */
	public EmailTemplate update(EmailTemplate emailTemplate) throws AgendizeException {
		logger.info("Update emailTemplate with id = " + emailTemplate.getId() + ".");
		if(emailTemplate.getId() == null){
			throw new AgendizeException("The emailTemplate object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = EMAILS_API_URL + TEMPLATES + "/" + emailTemplate.getId();
		String json = AgendizeEmailsObjectHelper.emailTemplateToJSONObject(emailTemplate).toString();
		String emailTemplateString = APIUtils.putRequest(urlString, json, properties);
		JSONObject emailTemplateJson =  new JSONObject(emailTemplateString);
		return AgendizeEmailsObjectHelper.jsonObjectToEmailTemplate(emailTemplateJson);
	}

	/**
	 * Deletes a emailTemplate. /!\ Will delete all the staff, services, resources, appointments of the emailTemplate.
	 * @param emailTemplateId identifier of the emailTemplate to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int emailTemplateId) throws AgendizeException {
		logger.info("Delete emailTemplate with id = " + emailTemplateId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(EMAILS_API_URL + TEMPLATES + "/" + emailTemplateId, properties);
	}
	
	/**
	 * Sends a marketing email. 
	 * @param marketingEmail marketing email to send. Must have template + list of recipients or subject + html + list of recipients.
	 * @throws AgendizeException
	 */
	public void send(MarketingEmail marketingEmail) throws AgendizeException{
		logger.info("send Marketing Email.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeEmailsObjectHelper.marketingEmailToJSONObject(marketingEmail).toString();
		String marketingEmailString = APIUtils.postRequest(EMAILS_API_URL + SEND, json, properties);
		JSONObject jsonObject = new JSONObject(marketingEmailString);
  		if(jsonObject.has("error")){
  			JSONObject errorJson = jsonObject.getJSONObject("error");
  			logger.error("Error in sending marketing email: " + errorJson.getInt("code")+" "+errorJson.getString("reason")+" - "+errorJson.getString("message"));
  		} else {
  			logger.info("MarketingEmail sent succesfully.");
  		}
		//JSONObject jsonObject = new JSONObject(emailTemplateString);
		//EmailTemplate result = AgendizeEmailsObjectHelper.jsonObjectToEmailTemplate(jsonObject);
        //return result;
	}
}
