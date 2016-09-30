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
import mx.agendize.api.v2.scheduling.reference.Staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Staff management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class StaffsManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(StaffsManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public StaffsManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Returns all staff for a company.
	 * @param companyId Company identifier.
	 * @return List of Staff objects.
	 * @throws JSONException
	 */
	public List<Staff> getStaffs(int companyId) throws JSONException {
		logger.info("getStaffs. Company id = " + companyId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		
		String appointmentsString = null;
		try {
			appointmentsString = APIUtils.getRequest(SCHEDULING_API_URL+"companies/"+companyId+"/staff", properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray result = null;
    	try {
    		if(appointmentsString!=null){
    			result = new JSONObject(appointmentsString).getJSONArray("items");
        	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return AgendizeSchedulingObjectHelper.jsonArrayToStaffList(result);
	}
	
	/**
	 * Gets a staff by its id
	 * @param companyId Company identifier. 
	 * @param staffId Staff member identifier.
	 * @return the Staff member.
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public Staff get(int companyId, Integer staffId) throws IOException, AgendizeException {
		logger.info("Get staff with id = " + staffId + ".");
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		Company c = cm.get(companyId);
		if(c == null){
			throw new AgendizeException("There is no company for this id.");
		}
		
		Staff result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String staffString = null;
		try {
			staffString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/staff/" + staffId, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(staffString!=null){
        	result = AgendizeSchedulingObjectHelper.jsonObjectToStaff(new JSONObject(staffString));
		}
		if(result != null){
			logger.info("Staff member found.");
		} else {
			logger.info("No staff member found for this id.");
		}
		return result;
	}
	
	/**
	 * Sets a different color to all the staff members.
	 * @param companyId
	 * @return List of staff with updated colors.
	 * @throws AgendizeException 
	 */
	public List<Staff> colorStaff(int companyId) throws AgendizeException{
		logger.info("colorStaff for company with id = " + companyId + ".");
		List<Staff> result = new ArrayList<Staff>();
		List<Staff> staffMembers = getStaffs(companyId);
		int step = 16777215/staffMembers.size();
		for(int i= 0; i<staffMembers.size();i++){
			staffMembers.get(i).setColor(Integer.toHexString(step*(i+1)));
			result.add(update(companyId, staffMembers.get(i)));
		}
		return result;
	}

	/**
	 * Updates a Staff member.
	 * @param companyId Company identifier.
	 * @param staff the staff to update.
	 * @return updated staff.
	 * @throws AgendizeException 
	 */
	public Staff update(int companyId, Staff staff) throws AgendizeException {
		logger.info("Update staff with id = " + staff.getId() + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/staff/"+staff.getId();
		String json = AgendizeSchedulingObjectHelper.staffToJSONObject(staff).toString();
		String staffString = APIUtils.putRequest(urlString, json, properties);
		Staff result = null;
		if(staffString != null && !"".equals(staffString)){
			JSONObject staffJson =  new JSONObject(staffString);
			result = AgendizeSchedulingObjectHelper.jsonObjectToStaff(staffJson);
		}
		return result;
	}
	
	/**
	 * Creates a staff member
	 * @param companyId Company identifier.
	 * @param staff Staff to create. The last name of the staff is mandatory, the rest is optional.
	 * @return the created staff with its id.
	 * @throws IOException 
	 * @throws AgendizeException 
	 */
	public Staff create(int companyId, Staff staff) throws IOException, AgendizeException{
		logger.info("Create new staff.");
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		Company c = cm.get(companyId);
		if(c == null){
			throw new AgendizeException("There is no company for this id.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/staff";
		String json = AgendizeSchedulingObjectHelper.staffToJSONObject(staff).toString();
		String staffString = APIUtils.postRequest(urlString, json, properties);
		JSONObject jsonObject = new JSONObject(staffString);
		Staff result = AgendizeSchedulingObjectHelper.jsonObjectToStaff(jsonObject);
		logger.info("Staff created succesfully. id = " + result.getId());
        return result;
	}
	
	/* TODO documenter */
	/**
	 * Creates a staff member
	 * @param companyId Company identifier.
	 * @param staff Staff to create. The last name of the staff is mandatory, the rest is optional.
	 * @return the created staff with its id.
	 * @throws IOException 
	 * @throws AgendizeException 
	 */
	public Staff fastcreate(int companyId, Staff staff) throws IOException, AgendizeException{
		logger.info("Fast-Create new staff.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/staff";
		String json = AgendizeSchedulingObjectHelper.staffToJSONObject(staff).toString();
		String staffString = APIUtils.postRequest(urlString, json, properties);
		JSONObject jsonObject = new JSONObject(staffString);
		Staff result = AgendizeSchedulingObjectHelper.jsonObjectToStaff(jsonObject);
		logger.info("Staff created succesfully. id = " + result.getId());
        return result;
	}
	
	/**
	 * Deletes a staff member.
	 * @param companyId Company identifier.
	 * @param staffId identifier of staff to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int companyId, int staffId) throws AgendizeException {
		logger.info("Delete staff with id = " + staffId);
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/staff/" + staffId, properties);
	}
}
