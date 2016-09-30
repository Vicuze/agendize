package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Company;
import mx.agendize.api.v2.scheduling.reference.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Resources management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ResourcesManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(ResourcesManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public ResourcesManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Returns all resources for a company.
	 * @param companyId Company identifier.
	 * @return List of Resource objects.
	 * @throws JSONException
	 */
	public List<Resource> getResources(int companyId) throws JSONException {
		logger.info("getResources. Company id = " + companyId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		
		String resourcesString = null;
		try {
			resourcesString = APIUtils.getRequest(SCHEDULING_API_URL+"companies/"+companyId+"/resources", properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray resourceJson = null;
    	try {
    		if(resourcesString!=null){
    			resourceJson = new JSONObject(resourcesString).getJSONArray("items");
        	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return AgendizeSchedulingObjectHelper.jsonArrayToResourceList(resourceJson);
	}
	
	/**
	 * Returns a resource by its id.
	 * @param companyId Company identifier.
	 * @param resourceId Resource identifier.
	 * @return the resource. null if not found.
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public Resource get(int companyId, Integer resourceId) throws IOException, AgendizeException {
		logger.info("Get resource with id = " + resourceId + ".");
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		Company c = cm.get(companyId);
		if(c == null){
			throw new AgendizeException("There is no company for this id.");
		}
		
		Resource result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String resourceString = null;
		try {
			resourceString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/resources/" + resourceId, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(resourceString!=null){
            result = AgendizeSchedulingObjectHelper.jsonObjectToResource(new JSONObject(resourceString));
		}
		if(result != null){
			logger.info("Resource found.");
		} else {
			logger.info("No resource found for this id.");
		}
		return result;
	}

	/**
	 * Deletes a resource.
	 * @param companyId Company identifier.
	 * @param resourceId identifier of resource to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int companyId, int resourceId) throws AgendizeException {
		logger.info("Delete resource with id = " + resourceId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(SCHEDULING_API_URL+"companies/"+companyId+"/resources/"+resourceId, properties);
	}

	/**
	 * Updates a resource.
	 * @param companyId Company identifier.
	 * @param resource the resource to update. Must have an id.
	 * @return updated resource.
	 * @throws AgendizeException 
	 */
	public Resource update(int companyId, Resource resource) throws AgendizeException {
		logger.info("Update resource with id = " + resource.getId() + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/resources/"+resource.getId();
		String json = AgendizeSchedulingObjectHelper.resourceToJSONObject(resource).toString();
		String resourceString = APIUtils.putRequest(urlString, json, properties);
		JSONObject resourceJson =  new JSONObject(resourceString);
		return AgendizeSchedulingObjectHelper.jsonObjectToResource(resourceJson);
	}
	
	/**
	 * Creates a resource.
	 * @param companyId Company identifier.
	 * @param resource Resource to create. The name of the resource is mandatory, the rest is optional.
	 * @return the created resource with its id.
	 * @throws IOException 
	 * @throws AgendizeException 
	 */
	public Resource create(int companyId, Resource resource) throws IOException, AgendizeException{
		logger.info("Create new resource.");
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		Company c = cm.get(companyId);
		if(c == null){
			throw new AgendizeException("There is no company for this id.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/resources";
		String json = AgendizeSchedulingObjectHelper.resourceToJSONObject(resource).toString();
		String resourceString = APIUtils.postRequest(urlString, json, properties);
		JSONObject jsonObject = new JSONObject(resourceString);
		Resource result = AgendizeSchedulingObjectHelper.jsonObjectToResource(jsonObject);
		logger.info("Resource created succesfully. id = " + result.getId() + ".");
        return result;
	}
}