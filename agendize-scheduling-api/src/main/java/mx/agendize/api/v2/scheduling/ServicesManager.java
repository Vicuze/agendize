package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Company;
import mx.agendize.api.v2.scheduling.reference.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Services management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ServicesManager extends AgendizeApiManager{
	
	static final Logger logger = LogManager.getLogger(ServicesManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public ServicesManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Return all services for a company.
	 * @param companyId Company identifier.
	 * @return List of Service objects.
	 * @throws JSONException
	 */
	public List<Service> getServices(int companyId) throws JSONException {
		logger.info("getServices. Company id = " + companyId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		
		String servicesString = null;
		try {
			servicesString = APIUtils.getRequest(SCHEDULING_API_URL+"companies/"+companyId+"/services", properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray servicesJson = null;
    	try {
    		if(servicesString!=null){
    			servicesJson = new JSONObject(servicesString).getJSONArray("items");
        	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Service> result = AgendizeSchedulingObjectHelper.jsonArrayToServiceList(servicesJson);
		logger.info(result.size() + " services found.");
    	return result;
	}

	/**
	 * Gets a service by its id
	 * @param companyId Company identifier.
	 * @param serviceId Service identifier.
	 * @return The service. Null if it does not exists
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public Service get(int companyId, Integer serviceId) throws IOException, AgendizeException  {
		logger.info("Get service with id = " + serviceId + ".");
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		Company c = cm.get(companyId);
		if(c == null){
			throw new AgendizeException("There is no company for this id.");
		}
		
		Service result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String serviceString = null;
		try {
			serviceString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/services/" + serviceId, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(serviceString!=null){
            result = AgendizeSchedulingObjectHelper.jsonObjectToService(new JSONObject(serviceString));
		}
		if(result != null){
			logger.info("Service found.");
		} else {
			logger.info("No service found for this id.");
		}
		return result;
	}
	
	/**
	 * Sets a different color to all the company services.
	 * @param companyId Company identifier.
	 * @return list of services with updated colors.
	 * @throws IOException
	 * @throws AgendizeException 
	 */
	public List<Service> colorServices(int companyId) throws IOException, AgendizeException{
		logger.info("colorServices for company with id = " + companyId + ".");
		List<Service> result = new ArrayList<Service>();
		List<Service> services = getServices(companyId);
		if(services == null){
			throw new AgendizeException("No services or no company for this company id.");
		}
		int step = 16777215/services.size();
		for(int i = 0; i<services.size();i++){
			services.get(i).setColor(Integer.toHexString(step*(i+1)));
			result.add(update(companyId, services.get(i)));
		}
		return result;
	}

	/**
	 * Updates a service.
	 * @param companyId Company identifier.
	 * @param service the service to update.
	 * @return updated service.
	 * @throws AgendizeException 
	 */
	public Service update(int companyId, Service service) throws AgendizeException {
		logger.info("Update service with id = " + service.getId() + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/services/"+service.getId();
		String json = AgendizeSchedulingObjectHelper.serviceToJSONObject(service).toString();
		String serviceString = APIUtils.putRequest(urlString, json, properties);
		JSONObject serviceJson =  new JSONObject(serviceString);
		return AgendizeSchedulingObjectHelper.jsonObjectToService(serviceJson);
	}
	
	/**
	 * Creates a service
	 * @param companyId Company identifier.
	 * @param service Service to create. The name of the service is mandatory, the rest is optional.
	 * @return the created service with its id.
	 * @throws IOException 
	 * @throws AgendizeException 
	 */
	public Service create(int companyId, Service service) throws IOException, AgendizeException{
		logger.info("Create new service.");
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		Company c = cm.get(companyId);
		if(c == null){
			throw new AgendizeException("There is no company for this id.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/services";
		String json = AgendizeSchedulingObjectHelper.serviceToJSONObject(service).toString();
		String serviceString = APIUtils.postRequest(urlString, json, properties);
		JSONObject jsonObject = new JSONObject(serviceString);
		Service result = AgendizeSchedulingObjectHelper.jsonObjectToService(jsonObject);
		logger.info("Service created succesfully. id = " + result.getId() + ".");
        return AgendizeSchedulingObjectHelper.jsonObjectToService(jsonObject);
	}
	
	/**
	 * Creates a service
	 * @param companyId Company identifier.
	 * @param service Service to create. The name of the service is mandatory, the rest is optional.
	 * @return the created service with its id.
	 * @throws IOException 
	 * @throws AgendizeException 
	 */
	public Service fastcreate(int companyId, Service service) throws IOException, AgendizeException{
		logger.info("Fast-Create new service.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/services";
		String json = AgendizeSchedulingObjectHelper.serviceToJSONObject(service).toString();
		String serviceString = APIUtils.postRequest(urlString, json, properties);
		JSONObject jsonObject = new JSONObject(serviceString);
		Service result = AgendizeSchedulingObjectHelper.jsonObjectToService(jsonObject);
		logger.info("Service created succesfully. id = " + result.getId() + ".");
        return AgendizeSchedulingObjectHelper.jsonObjectToService(jsonObject);
	}

	/**
	 * Deletes a service.
	 * @param companyId Company identifier.
	 * @param serviceId identifier of service to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int companyId, int serviceId) throws AgendizeException {
		logger.info("Delete service with id = " + serviceId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(SCHEDULING_API_URL+"companies/"+companyId+"/services/"+serviceId, properties);
	}
}