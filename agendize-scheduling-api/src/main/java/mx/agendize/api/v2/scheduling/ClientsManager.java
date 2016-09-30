package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class for clients management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 * @deprecated Please use the Clients API
 *
 */
@Deprecated 
public class ClientsManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(ClientsManager.class);

	private static final String SEARCH = "search";

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
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
        String clientsString = APIUtils.getRequest(SCHEDULING_API_CLIENTS_URL, properties);
		JSONObject jsonObject = new JSONObject(clientsString);
		List<Client> result = AgendizeSchedulingObjectHelper.jsonArrayToClientList(jsonObject.getJSONArray("items"));
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
			clientString = APIUtils.getRequest(SCHEDULING_API_CLIENTS_URL + "/" + id, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(clientString!=null){
	        JSONObject jsonObject = new JSONObject(clientString);
            result = AgendizeSchedulingObjectHelper.jsonObjectToClient(jsonObject);
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
		String json = AgendizeSchedulingObjectHelper.clientToJSONObject(client).toString();
		String clientString = APIUtils.postRequest(SCHEDULING_API_CLIENTS_URL, json, properties);
		JSONObject jsonObject = new JSONObject(clientString);
		Client result = AgendizeSchedulingObjectHelper.jsonObjectToClient(jsonObject);
		logger.info("Client created succesfully. id = " + result.getId() + ".");
        return result;
	}

	/**
	 * Import of a client list.
	 * @param clients Client list to import.
	 * @return List of created clients (with ids).
	 * @throws AgendizeException 
	 */
	public List<Client> importCLients(List<Client> clients) throws AgendizeException{
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
		APIUtils.deleteRequest(SCHEDULING_API_CLIENTS_URL+"/"+clientId, properties);
	}
	
	/**
	 * updates an client.
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
		String urlString = SCHEDULING_API_CLIENTS_URL + "/" + client.getId();
		String json = AgendizeSchedulingObjectHelper.clientToJSONObject(client).toString();
		String clientString = APIUtils.putRequest(urlString, json, properties);
		JSONObject clientJson =  new JSONObject(clientString);
		return AgendizeSchedulingObjectHelper.jsonObjectToClient(clientJson);
	}
}
