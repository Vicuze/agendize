package mx.agendize.api.v2.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.clients.reference.Activity;
import mx.agendize.api.v2.clients.reference.AgendizeClientsObjectHelper;
import mx.agendize.api.v2.clients.reference.Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for clients management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ClientsManager extends AgendizeApiManager {
	
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	private static final String CLIENT_ID = "clientId";
	private static final String SEARCH = "search";

	static final Logger logger = LogManager.getLogger(ClientsManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public ClientsManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}
	
	/**
	 * Client search.
	 * @param search Search clients by first name, last name, phone number and email address. Incomplete value can by used.
	 * @return list of clients found with the criteria.
	 * @throws IOException
	 */
	public List<Client> getClients(String search) throws IOException{
		logger.info("getClients. search = \"" + search+"\".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		if(search != null && !"".equals(search.trim())){
			properties.put(SEARCH, search.trim());
		}
        String clientsString = APIUtils.getRequest(CLIENTS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(clientsString);
		List<Client> result = AgendizeClientsObjectHelper.jsonArrayToClientList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " clients found.");
		return result;
	}
	
	/**
	 * Get a client by its id. Will return null if there is no client with this id.
	 * @param id id of the client to look for.
	 * @return Client object.
	 * @throws IOException
	 */
	public Client get(int id) {
		logger.info("Get client with id = " + id + ".");
		Client result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String clientString = null;
		try {
			clientString = APIUtils.getRequest(CLIENTS_API_URL + "/" + id, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(clientString!=null){
	        JSONObject jsonObject = new JSONObject(clientString);
	        if(jsonObject.has("error")){
	  			JSONObject errorJson = jsonObject.getJSONObject("error");
	  			logger.error("Error in get client with id " + id + ": " + errorJson.getInt("code")+" "+errorJson.getString("reason")+" - "+errorJson.getString("message"));
	  		} else {
	  			result = AgendizeClientsObjectHelper.jsonObjectToClient(jsonObject);
	  		}
		}
		if(result != null){
			logger.info("Client found.");
		}
		return result;
	}

	/**
	 * Creates a client. 
	 * @param client to create.
	 * @return the updated client.
	 * @throws AgendizeException 
	 */
	public Client create(Client client) throws AgendizeException{
		logger.info("Create new client.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeClientsObjectHelper.clientToJSONObject(client).toString();
		String clientString = APIUtils.postRequest(CLIENTS_API_URL, json, properties);
		JSONObject jsonObject = new JSONObject(clientString);
		Client result = AgendizeClientsObjectHelper.jsonObjectToClient(jsonObject);
		logger.info("Client created succesfully. id = " + result.getId() + ".");
        return result;
	}

	/**
	 * Import of a client list.
	 * @param clients Client list to import.
	 * @return List of created clients (with ids).
	 * @throws AgendizeException 
	 */
	public List<Client> importClients(List<Client> clients) throws AgendizeException{
		logger.info("Import of a client list.");
		List<Client> result = null;
		if(clients != null){
			logger.info("import a list of " + clients.size()+" clients.");
			result = new ArrayList<Client>();
			for(Client c: clients){
				result.add(create(c));
			}
		}
		return result;
	}
	
	/**
	 * Deletes a client.
	 * @param clientId Identifier of client to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int clientId) throws AgendizeException {
		logger.info("Delete client with id = " + clientId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(CLIENTS_API_URL+"/"+clientId, properties);
	}
	
	/**
	 * Updates an client.
	 * @param client Client. Must have an id. 
	 * @return updated client.
	 * @throws AgendizeException
	 */
	public Client update(Client client) throws AgendizeException {
		logger.info("Update client with id = " + client.getId() + ".");
		if(client.getId()==null){
			throw new AgendizeException("The client object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = CLIENTS_API_URL + "/" + client.getId();
		String json = AgendizeClientsObjectHelper.clientToJSONObject(client).toString();
		String clientString = APIUtils.putRequest(urlString, json, properties);
		JSONObject clientJson =  new JSONObject(clientString);
		return AgendizeClientsObjectHelper.jsonObjectToClient(clientJson);
	}
	
	/**
	 * Returns activities of a specific client based on its ID
	 * @param clientId Client identifier. Required.
	 * @param startDate Upper bound (exclusive) for an appointment's start time (as a RFC 3339 timestamp) to filter by. Optional. The default is not to filter by start time.
	 * @param endDate Lower bound (exclusive) for an appointment's end time (as a RFC 3339 timestamp) to filter by. Optional. The default is not to filter by start time.
	 * @return list of Activity objects.
	 * @throws IOException
	 * @throws JSONException
	 * @throws AgendizeException
	 */
	public List<Activity> getActivities(int clientId, String startDate, String endDate) throws IOException, JSONException, AgendizeException{
		logger.info("getActivities. clientId = " + clientId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		properties.put(CLIENT_ID, String.valueOf(clientId));
		if(startDate != null && !startDate.equals("")){
			properties.put(START_DATE, startDate);
		}
		if(endDate != null && !endDate.equals("")){
			properties.put(END_DATE, endDate);
		}
        String activitiesString = APIUtils.getRequest(ACTIVITIES_API_URL, properties);
		JSONObject jsonObject = new JSONObject(activitiesString);
		List<Activity> result = AgendizeClientsObjectHelper.jsonArrayToActivitiesList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " activities found.");
		return result;
	}
}