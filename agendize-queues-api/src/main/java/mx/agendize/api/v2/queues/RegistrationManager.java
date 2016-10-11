package mx.agendize.api.v2.queues;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.queues.reference.AgendizeQueuesObjectHelper;
import mx.agendize.api.v2.queues.reference.Queue;
import mx.agendize.api.v2.queues.reference.Registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for queues registrations management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class RegistrationManager extends AgendizeApiManager {

	static final Logger logger = LogManager.getLogger(RegistrationManager.class);

	public RegistrationManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/**
	 * Return all registrations for a queue.
	 * @param queueId Company identifier.
	 * @return List of Registration objects.
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public List<Registration> getRegistrations(int queueId) throws JSONException, AgendizeException {
		logger.info("getRegistrations. Queue id = " + queueId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		
		String registrationsString = null;
		try {
			registrationsString = APIUtils.getRequest(QUEUES_API_URL+"/"+queueId+"/registrations", properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray registrationsJson = null;
    	try {
    		if(registrationsString!=null){
    			registrationsJson = new JSONObject(registrationsString).getJSONArray("items");
        	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Registration> result = AgendizeQueuesObjectHelper.jsonArrayToRegistrationList(registrationsJson);
		logger.info(result.size() + " registrations found.");
    	return result;
	}

	/**
	 * Gets a registration by its id
	 * @param queueId Queue identifier.
	 * @param registrationId Registration identifier.
	 * @return The registration. null if it does not exists
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public Registration get(int queueId, int registrationId) throws IOException, AgendizeException  {
		logger.info("Get registration with id = " + registrationId + ".");
		QueuesManager cm = new QueuesManager(apiKey, token);
		Queue q = cm.get(queueId);
		if(q == null){
			throw new AgendizeException("There is no queue for this id.");
		}
		
		Registration result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String registrationString = null;
		try {
			registrationString = APIUtils.getRequest(QUEUES_API_URL + "/" + queueId + "/registrations/" + registrationId, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(registrationString!=null){
            result = AgendizeQueuesObjectHelper.jsonObjectToRegistration(new JSONObject(registrationString));
		}
		if(result != null){
			logger.info("Registration found.");
		} else {
			logger.info("No registration found for this id.");
		}
		return result;
	}
	
	/**
	 * Updates a registration.
	 * @param queueId Queue identifier.
	 * @param registration the registration to update.
	 * @return updated registration.
	 * @throws AgendizeException 
	 */
	public Registration update(int queueId, Registration registration) throws AgendizeException {
		logger.info("Update registration with id = " + registration.getId() + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = QUEUES_API_URL + "/" + queueId + "/registrations/"+registration.getId();
		String json = AgendizeQueuesObjectHelper.registrationToJSONObject(registration).toString();
		String registrationString = APIUtils.putRequest(urlString, json, properties);
		JSONObject registrationJson =  new JSONObject(registrationString);
		return AgendizeQueuesObjectHelper.jsonObjectToRegistration(registrationJson);
	}
	
	/**
	 * Creates a registration
	 * @param queueId Queue identifier.
	 * @param registration Registration to create. The queue Id of the registration is mandatory, the rest is optional.
	 * @return the created registration with its id.
	 * @throws IOException 
	 * @throws AgendizeException 
	 */
	public Registration create(int queueId, Registration registration) throws IOException, AgendizeException{
		logger.info("Create new registration.");
		QueuesManager qm = new QueuesManager(apiKey, token);
		Queue q = qm.get(queueId);
		if(q == null){
			throw new AgendizeException("There is no queue for this id.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = QUEUES_API_URL + "/" + queueId + "/registrations";
		String json = AgendizeQueuesObjectHelper.registrationToJSONObject(registration).toString();
		String registrationString = APIUtils.postRequest(urlString, json, properties);
		System.out.println(registrationString);
		JSONObject jsonObject = new JSONObject(registrationString);
		Registration result = AgendizeQueuesObjectHelper.jsonObjectToRegistration(jsonObject);
		logger.info("Registration created succesfully. id = " + result.getId() + ".");
        return AgendizeQueuesObjectHelper.jsonObjectToRegistration(jsonObject);
	}

	/**
	 * Deletes a registration.
	 * @param queueId Queue identifier.
	 * @param registrationId identifier of registration to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int queueId, int registrationId) throws AgendizeException {
		logger.info("Delete registration with id = " + registrationId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(QUEUES_API_URL+"/"+queueId+"/registrations/"+registrationId, properties);
	}
	
	/**
	 * Next registration of the queue.
	 * @param queueId
	 * @return Registration the next Registration object.
	 * @throws JSONException
	 * @throws AgendizeException
	 */
	public Registration next(int queueId) throws JSONException, AgendizeException{
		logger.info("next registration on queue with id = " + queueId + ".");
		QueuesManager qm = new QueuesManager(apiKey, token);
		Queue q = qm.get(queueId);
		if(q == null){
			throw new AgendizeException("There is no queue for this id.");
		}
		Registration result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String registrationString = null;
		try {
			registrationString = APIUtils.getRequest(QUEUES_API_URL + "/" + queueId + "/registrations/next", properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(registrationString!=null){
            result = AgendizeQueuesObjectHelper.jsonObjectToRegistration(new JSONObject(registrationString));
		}
		if(result != null){
			logger.info("Registration found. id = " + result.getId());
		} else {
			logger.info("No next registration found for this queue.");
		}
		return result;
	}
}
