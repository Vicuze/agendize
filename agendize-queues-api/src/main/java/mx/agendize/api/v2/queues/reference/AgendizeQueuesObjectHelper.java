package mx.agendize.api.v2.queues.reference;

import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.forms.reference.Form;
import mx.agendize.api.v2.queues.reference.Registration.RegistrationStatus;
import mx.agendize.api.v2.reference.AgendizeObjectHelper;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for management of Agendize Queuing API objects: Queues, Registrations.
 * Contains methods to convert JSONObject and JSONArray to object and vice versa.
 * Info about the JSON structure here <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/index.jsp">http://developers.agendize.com/v2/queues/reference/index.jsp</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizeQueuesObjectHelper {

	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String THANK_MESSAGE = "thankMessage";
	private static final String WELCOME_MESSAGE = "welcomeMessage";
	private static final String COMPANY = "company";
	private static final String FORM = "form";
	private static final String NAME = "name";
	private static final String STATUS = "status";
	private static final String CLIENT = "client";
	private static final String SELF_LINK = "selfLink";
	private static final String EMAIL_ADDRESS = "emailAddress";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String REGISTERED = "registered";
	private static final String QUEUE = "queue";
	private static final String AUTHOR = "author";
	private static final String ID = "id";

	/**
	 * Converts a JSONArray in a list of Queue objects
	 * @param queuesJson json representing the list of queues. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/queues/index.jsp">http://developers.agendize.com/v2/queues/reference/queues/index.jsp</a>
	 * @return The list of queue objects.
	 * @throws AgendizeException 
	 * @throws JSONException 
	 */
	public static List<Queue> jsonArrayToQueuesList(JSONArray queuesJson) throws JSONException, AgendizeException {
		List<Queue> result = new ArrayList<Queue>();
		for(int j= 0; j<queuesJson.length(); j++){
    		result.add(jsonObjectToQueue((JSONObject) queuesJson.get(j)));
    	}
    	return result;
	}

	/**
	 * Converts a JSONObject from the API to a Queue object 
	 * @param queueJson The JSONObject representing the queue. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/queues/">http://developers.agendize.com/v2/queues/reference/queues/</a>
	 * @return the Queue object
	 * @throws JSONException
	 * @throws AgendizeException
	 */
	public static Queue jsonObjectToQueue(JSONObject queueJson) throws JSONException, AgendizeException {
		Queue result = new Queue();
		result.setId(queueJson.getInt(ID));
		if(queueJson.has(AUTHOR)){
			result.setAuthor(queueJson.getString(AUTHOR));
		}
		if(queueJson.has(NAME)){
			result.setName(queueJson.getString(NAME));
		}
		if(queueJson.has(THANK_MESSAGE)){
			result.setThankMessage(queueJson.getString(THANK_MESSAGE));
		}
		if(queueJson.has(WELCOME_MESSAGE)){
			result.setWelcomeMessage(queueJson.getString(WELCOME_MESSAGE));
		}
		if(queueJson.has(COMPANY)){
			JSONObject companyJson = queueJson.getJSONObject(COMPANY);
			Company company = new Company(companyJson.getInt(ID), companyJson.getString(NAME));
			result.setCompany(company);
		}
		if(queueJson.has(FORM)){
			JSONObject formJson = queueJson.getJSONObject(FORM);
			Form form = new Form(formJson.getInt(ID), formJson.getString(NAME));
			result.setForm(form);
		}
		return result;
	}

	/**
	 * Converts a Queue object into a JSONObject for API use.
	 * @param queue the Queue object.
	 * @return The JSONObject representing the queue. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/index.jsp">http://developers.agendize.com/v2/queues/reference/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject queueToJSONObject(Queue queue) throws JSONException {
		JSONObject result = new JSONObject();
		if(queue.getId() != null){
			result.put(ID, queue.getId());
		}
		if(queue.getAuthor() != null && !"".equals(queue.getAuthor())){
			result.put(AUTHOR, queue.getAuthor());
		}
		if(queue.getName() != null && !"".equals(queue.getName())){
			result.put(NAME, queue.getName());
		}
		if(queue.getThankMessage() != null && !"".equals(queue.getThankMessage())){
			result.put(THANK_MESSAGE, queue.getThankMessage());
		}
		if(queue.getWelcomeMessage() != null && !"".equals(queue.getWelcomeMessage())){
			result.put(WELCOME_MESSAGE, queue.getWelcomeMessage());
		}
		if(queue.getCompany() != null){
			JSONObject companyJson = AgendizeSchedulingObjectHelper.companyToJSONObject(queue.getCompany());
			result.put(COMPANY, companyJson);
		}
		if(queue.getForm() != null){
			JSONObject formJson = new JSONObject(); 
			formJson.put(ID, queue.getForm().getId());
			formJson.put(NAME, queue.getForm().getName());
			result.put(FORM, formJson);
		}
		return result;
	}

	/**
	 * Converts a JSONArray in a list of Registration objects
	 * @param registrationsJson json representing the registration list. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/registrations">http://developers.agendize.com/v2/queues/reference/registrations</a>
	 * @return The list of Registration objects.
	 * @throws JSONException
	 * @throws AgendizeException
	 */
	public static List<Registration> jsonArrayToRegistrationList(JSONArray registrationsJson) throws JSONException, AgendizeException {
		List<Registration> result = new ArrayList<Registration>();
		for(int j= 0; j<registrationsJson.length(); j++){
    		result.add(jsonObjectToRegistration((JSONObject) registrationsJson.get(j)));
    	}
    	return result;
	}

	/**
	 * Converts a JSONObject from the API to a Registration object 
	 * @param registrationJson The JSONObject representing the registration. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/registrations">http://developers.agendize.com/v2/queues/reference/registrations</a>
	 * @return the Registration object
	 * @throws JSONException
	 * @throws AgendizeException
	 */
	public static Registration jsonObjectToRegistration(JSONObject registrationJson) throws JSONException, AgendizeException {
		Registration result = new Registration();
		result.setId(registrationJson.getInt(ID));
		if(registrationJson.has(AUTHOR)){
			result.setAuthor(registrationJson.getString(AUTHOR));
		}
		if(registrationJson.has(QUEUE)){
			result.setQueue(jsonObjectToQueue(registrationJson.getJSONObject(QUEUE)));
		}
		if(registrationJson.has(REGISTERED)){
			result.setRegistered(AgendizeObjectHelper.jsonObjectToTime(registrationJson.getJSONObject(REGISTERED)));
		}
		if(registrationJson.has(CLIENT)){
			result.setClient(jsonObjectToQueueClient(registrationJson.getJSONObject(CLIENT)));
		}
		if(registrationJson.has(STATUS)){
			result.setStatus(RegistrationStatus.get(registrationJson.getString(STATUS)));
		}
		return result; 
	}
	
	/**
	 * Converts a JSONObject from the API to a QueueClient object 
	 * @param queueClientJson The JSONObject representing the queue client. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference/registrations">http://developers.agendize.com/v2/queues/reference/registrations</a>
	 * @return the QueueClient object
	 */
	private static QueueClient jsonObjectToQueueClient(JSONObject queueClientJson) {
		QueueClient result = new QueueClient();
		if(queueClientJson.has(ID)){
			result.setId(queueClientJson.getInt(ID));
		}
		if(queueClientJson.has(FIRST_NAME)){
			result.setFirstName(queueClientJson.getString(FIRST_NAME));
		}
		if(queueClientJson.has(LAST_NAME)){
			result.setLastName(queueClientJson.getString(LAST_NAME));
		}
		if(queueClientJson.has(PHONE_NUMBER)){
			result.setPhoneNumber(queueClientJson.getString(PHONE_NUMBER));
		}
		if(queueClientJson.has(EMAIL_ADDRESS)){
			result.setEmailAddress(queueClientJson.getString(EMAIL_ADDRESS));
		}
		if(queueClientJson.has(SELF_LINK)){
			result.setSelfLink(queueClientJson.getString(SELF_LINK));
		}
		return null;
	}

	/**
	 * Converts a Registration object into a JSONObject for API use.
	 * @param registration the Registration object
	 * @return The JSONObject representing the registration. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference">http://developers.agendize.com/v2/queues/reference</a>
	 */
	public static JSONObject registrationToJSONObject(Registration registration) {
		JSONObject result = new JSONObject(); 
		if(registration.getId() != null){
			result.put(ID, registration.getId());
		}
		if(registration.getAuthor() != null && !"".equals(registration.getAuthor())){
			result.put(AUTHOR, registration.getAuthor());
		}
		if(registration.getQueue() != null){
			result.put(QUEUE, queueToJSONObject(registration.getQueue()));
		}
		if(registration.getRegistered() != null){
			result.put(REGISTERED, AgendizeObjectHelper.timeToJSONObject(registration.getRegistered()));
		}
		if(registration.getClient() != null){
			result.put(CLIENT, queueClientToJSONObject(registration.getClient()));
		}
		if(registration.getStatus() != null){
			result.put(STATUS, registration.getStatus().getCode());
		}
		return result;
	}

	/**
	 * Converts a QueueClient object into a JSONObject for API use.
	 * @param client the QueueClient object
	 * @return The JSONObject representing the QueueClient. See <a target="_blank" href="http://developers.agendize.com/v2/queues/reference">http://developers.agendize.com/v2/queues/reference</a>
	 */
	private static JSONObject queueClientToJSONObject(QueueClient client) {
		JSONObject result = new JSONObject(); 
		if(client.getId() != null && !"".equals(client.getId())){
			result.put(ID, client.getId());
		}
		if(client.getFirstName() != null && !"".equals(client.getFirstName())){
			result.put(FIRST_NAME, client.getFirstName());
		}
		if(client.getLastName() != null && !"".equals(client.getLastName())){
			result.put(LAST_NAME, client.getLastName());
		}
		if(client.getEmailAddress() != null && !"".equals(client.getEmailAddress())){
			result.put(EMAIL_ADDRESS, client.getEmailAddress());
		}
		if(client.getPhoneNumber() != null && !"".equals(client.getPhoneNumber())){
			result.put(PHONE_NUMBER, client.getPhoneNumber());
		}
		if(client.getSelfLink() != null && !"".equals(client.getSelfLink())){
			result.put(SELF_LINK, client.getSelfLink());
		}
		return result;
	}
}
