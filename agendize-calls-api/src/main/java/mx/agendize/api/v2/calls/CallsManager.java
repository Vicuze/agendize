package mx.agendize.api.v2.calls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.calls.reference.AgendizeCallsObjectHelper;
import mx.agendize.api.v2.calls.reference.Call;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CallsManager extends AgendizeApiManager {


	private static final String END_DATE = "endDate";
	private static final String START_DATE = "startDate";
	private static final String SEARCH2 = "search";
	static final Logger logger = LogManager.getLogger(CallsManager.class);
	
	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public CallsManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/**
	 * Gives a list of calls according to the criteria
	 * @param startDate Start date.
	 * @param endDate End date.
	 * @param search Free text search terms to find call that match caller number or called number.
	 * @return
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public List<Call> getCalls(String startDate, 
			String endDate,String search) throws IOException, AgendizeException{
		logger.info("getCalls.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		if(search != null && !"".equals(search.trim())){
			properties.put(SEARCH2, search.trim());
		}
		if(startDate != null && !"".equals(startDate.trim())){
			properties.put(START_DATE, startDate.trim());
		}
		if(endDate != null && !"".equals(endDate.trim())){
			properties.put(END_DATE, endDate.trim());
		}
        String callsString = APIUtils.getRequest(CALLS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(callsString);
		List<Call> result = AgendizeCallsObjectHelper.jsonArrayToCallsList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " calls found.");
		return result;
	}

	/**
	 * Returns call object given the call identifier.
	 * @param id Call identifier.
	 * @return the call object.
	 * @throws IOException 
	 * @throws AgendizeException 
	 * @throws JSONException 
	 */
	public Call get(int id) throws IOException, JSONException, AgendizeException{
		logger.info("Get call with id = " + id + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String callString = APIUtils.getRequest(CALLS_API_URL+"/"+id, properties);
        Call result = null;
        if(callString != null && !"".equals(callString)){
	        JSONObject jsonObject= new JSONObject(callString);
        	result = AgendizeCallsObjectHelper.jsonObjectToCall(jsonObject);
        }
		if(result != null){
			logger.info("Call found.");
		} else {
			logger.info("No call found for this id.");
		}
		return result;
	}

	/**
	 * Creates a call. 
	 * @param call to create.
	 * @return the updated call.
	 * @throws AgendizeException 
	 */
	public Call create(Call call) throws AgendizeException{
		logger.info("Create new call.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeCallsObjectHelper.callToJSONObject(call).toString();
		String callString = APIUtils.postRequest(CALLS_API_URL, json, properties);
		JSONObject jsonObject = new JSONObject(callString);
		Call result = AgendizeCallsObjectHelper.jsonObjectToCall(jsonObject);
		logger.info("Call created succesfully. id = " + result.getId() + ".");
        return result;
	}

	/**
	 * Import of a call list.
	 * @param calls Call list to import.
	 * @return List of created calls (with ids).
	 * @throws AgendizeException 
	 */
	public List<Call> importCalls(List<Call> calls) throws AgendizeException{
		logger.info("Import of a call list.");
		List<Call> result = null;
		if(calls != null){
			logger.info("import a list of " + calls.size()+" calls.");
			result = new ArrayList<Call>();
			for(Call c: calls){
				result.add(create(c));
			}
		}
		return result;
	}
	
	/**
	 * Deletes a call.
	 * @param callId Identifier of call to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int callId) throws AgendizeException {
		logger.info("Delete call with id = " + callId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(CALLS_API_URL+"/"+callId, properties);
	}
	
	/**
	 * Updates an call.
	 * @param call Call. Must have an id. 
	 * @return updated call.
	 * @throws AgendizeException
	 */
	public Call update(Call call) throws AgendizeException {
		logger.info("Update call with id = " + call.getId() + ".");
		if(call.getId()==null){
			throw new AgendizeException("The call object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = CALLS_API_URL + "/" + call.getId();
		String json = AgendizeCallsObjectHelper.callToJSONObject(call).toString();
		String callString = APIUtils.putRequest(urlString, json, properties);
		JSONObject callJson =  new JSONObject(callString);
		return AgendizeCallsObjectHelper.jsonObjectToCall(callJson);
	}
	
}
