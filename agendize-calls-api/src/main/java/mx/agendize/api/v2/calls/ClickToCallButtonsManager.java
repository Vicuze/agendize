package mx.agendize.api.v2.calls;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.calls.reference.AgendizeCallsObjectHelper;
import mx.agendize.api.v2.calls.reference.ClickToCallButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Click to call buttons management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ClickToCallButtonsManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(ClickToCallButtonsManager.class);

	public ClickToCallButtonsManager(String apiKey, String token) {
		super(apiKey, token);
	}
	
	/**
	 * Gives the list of click to call buttons for an Agendize account
	 * @return The list of clickToCallButton objects.
	 * @throws IOException
	 * @throws JSONException
	 */
	public List<ClickToCallButton> getClickToCallButtons() throws IOException {
		logger.info("getClickToCallButtons.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String clickToCallButtonsString = APIUtils.getRequest(CLICK_TO_CALL_BUTTONS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(clickToCallButtonsString);
		List<ClickToCallButton> result = AgendizeCallsObjectHelper.jsonArrayToClickToCallButtonList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " clickToCallButtons found.");
		return result;
	} 
	
	/**
	 * Returns clickToCallButton object given the clickToCallButton identifier
	 * @param id ClickToCallButton identifier.
	 * @return The clickToCallButton object.
	 * @throws JSONException
	 * @throws IOException
	 */
	public ClickToCallButton get(int id) throws JSONException, IOException{
		logger.info("Get clickToCallButton with id = " + id + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String clickToCallButtonString = APIUtils.getRequest(CLICK_TO_CALL_BUTTONS_API_URL + "/" + id, properties);
		JSONObject jsonObject = new JSONObject(clickToCallButtonString);
		ClickToCallButton result = null;
  		if(jsonObject.has("error")){
  			JSONObject errorJson = jsonObject.getJSONObject("error");
  			logger.error("Error in get email template: " + errorJson.getInt("code")+" "+errorJson.getString("reason")+" - "+errorJson.getString("message"));
  		} else {
		    result = AgendizeCallsObjectHelper.jsonObjectToClickToCallButton(jsonObject);
			if(result != null){
				logger.info("ClickToCallButton found.");
			} else {
				logger.info("No clickToCallButton found for this id.");
			}
  		}
		return result;
	}
	
	/**
	 * Creates a clickToCallButton. 
	 * @param clickToCallButton the clickToCallButton to create. 
	 * @return The clickToCallButton created.
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public ClickToCallButton create(ClickToCallButton clickToCallButton) throws JSONException, AgendizeException{
		logger.info("Create new clickToCallButton.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeCallsObjectHelper.clickToCallButtonToJSONObject(clickToCallButton).toString();
		String clickToCallButtonString = APIUtils.postRequest(CLICK_TO_CALL_BUTTONS_API_URL, json, properties);
		JSONObject jsonObject = new JSONObject(clickToCallButtonString);
		ClickToCallButton result = AgendizeCallsObjectHelper.jsonObjectToClickToCallButton(jsonObject);
		logger.info("ClickToCallButton created succesfully. id = " + result.getId());
        return result;
	}
	
	/**
	 * Updates a clickToCallButton
	 * @param clickToCallButton ClickToCallButton to update. Must have an id.
	 * @return udpated clickToCallButton.
	 * @throws AgendizeException
	 */
	public ClickToCallButton update(ClickToCallButton clickToCallButton) throws AgendizeException {
		logger.info("Update clickToCallButton with id = " + clickToCallButton.getId() + ".");
		if(clickToCallButton.getId() == null){
			throw new AgendizeException("The clickToCallButton object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = CLICK_TO_CALL_BUTTONS_API_URL + "/" + clickToCallButton.getId();
		String json = AgendizeCallsObjectHelper.clickToCallButtonToJSONObject(clickToCallButton).toString();
		String clickToCallButtonString = APIUtils.putRequest(urlString, json, properties);
		JSONObject clickToCallButtonJson =  new JSONObject(clickToCallButtonString);
		return AgendizeCallsObjectHelper.jsonObjectToClickToCallButton(clickToCallButtonJson);
	}

	/**
	 * Deletes a clickToCallButton. /!\ Will delete all the staff, services, resources, appointments of the clickToCallButton.
	 * @param clickToCallButtonId identifier of the clickToCallButton to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int clickToCallButtonId) throws AgendizeException {
		logger.info("Delete clickToCallButton with id = " + clickToCallButtonId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(CLICK_TO_CALL_BUTTONS_API_URL + "/" + clickToCallButtonId, properties);
	}

}
