package mx.agendize.api.v2.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.forms.reference.AgendizeFormsObjectHelper;
import mx.agendize.api.v2.forms.reference.Form;
import mx.agendize.api.v2.forms.reference.FormResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormsManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(FormsManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public FormsManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/**
	 * Returns all forms of the account.
	 * @return List of Form objects.
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public List<Form> getForms() throws JSONException, AgendizeException {
		logger.info("getForms.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		
		String formsString = null;
		try {
			formsString = APIUtils.getRequest(FORMS_API_URL, properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject formsJson = new JSONObject(formsString);
		List<Form> result = new ArrayList<Form>();
		if(formsJson.has("items")){
			result = AgendizeFormsObjectHelper.jsonArrayToFormsList(formsJson.getJSONArray("items"));
		}
		logger.info(result.size() + " forms found.");
		return result; 
	}
	
	/**
	 * Get a form by its id. Will return null if there is no form with this id.
	 * @param id id of the form to look for.
	 * @return Form object.
	 * @throws AgendizeException 
	 * @throws JSONException 
	 * @throws IOException
	 */
	public Form get(int id) throws JSONException, AgendizeException {
		logger.info("Get form with id = " + id + ".");
		Form result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String formString = null;
		try {
			formString = APIUtils.getRequest(FORMS_API_URL + "/" + id, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(formString!=null){
            result = AgendizeFormsObjectHelper.jsonObjectToForm(new JSONObject(formString));
		}
		if(result != null){
			logger.info("Form found.");
		}
		return result;
	}

	/**
	 * Creates a form. 
	 * @param form to create.
	 * @return the updated form.
	 * @throws AgendizeException 
	 */
	public Form create(Form form) throws AgendizeException{
		logger.info("Create new form.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeFormsObjectHelper.formToJSONObject(form).toString();
		String formString = APIUtils.postRequest(FORMS_API_URL, json, properties);
		JSONObject jsonObject = new JSONObject(formString);
		Form result = AgendizeFormsObjectHelper.jsonObjectToForm(jsonObject);
		logger.info("Form created succesfully. id = " + result.getId() + ".");
        return result;
	}
	
	/**
	 * Deletes a form.
	 * @param formId Identifier of form to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int formId) throws AgendizeException {
		logger.info("Delete form with id = " + formId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(FORMS_API_URL+"/"+formId, properties);
	}
	
	/**
	 * Updates an form.
	 * @param form Form. Must have an id. 
	 * @return updated form.
	 * @throws AgendizeException
	 */
	public Form update(Form form) throws AgendizeException {
		logger.info("Update form with id = " + form.getId() + ".");
		if(form.getId()==null){
			throw new AgendizeException("The form object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = FORMS_API_URL + "/" + form.getId();
		String json = AgendizeFormsObjectHelper.formToJSONObject(form).toString();
		String formString = APIUtils.putRequest(urlString, json, properties);
		System.out.println("++++"+formString);
		JSONObject formJson =  new JSONObject(formString);
		return AgendizeFormsObjectHelper.jsonObjectToForm(formJson);
	}

	/**
	 * Result Form result search.
	 * @param formId Form identifier.
	 * @return list of formResults found with the criteria.
	 * @throws IOException
	 */
	public List<FormResult> getResults(int formId) throws IOException{
		logger.info("getResults for form with id = \"" + formId+"\".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String formResultsString = APIUtils.getRequest(FORMS_API_URL+"/"+formId+"/results", properties);
		JSONObject jsonObject = new JSONObject(formResultsString);
		List<FormResult> result = AgendizeFormsObjectHelper.jsonArrayToFormResultList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " results found.");
		return result;
	}
	
	/**
	 * Get a formResult by its id. Will return null if there is no formResult with this id.
	 * @param id id of the formResult to look for.
	 * @return FormResult object.
	 * @throws IOException
	 */
	public FormResult get(int id, int formId) {
		logger.info("Get formResult with id = " + id + " and formId "+formId);
		FormResult result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String formResultString = null;
		try {
			formResultString = APIUtils.getRequest(FORMS_API_URL + "/" + formId + "/results/" + id, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(formResultString!=null){
	        JSONObject jsonObject = new JSONObject(formResultString);
            result = AgendizeFormsObjectHelper.jsonObjectToFormResult(jsonObject);
		}
		if(result != null){
			logger.info("FormResult found.");
		}
		return result;
	}


}
