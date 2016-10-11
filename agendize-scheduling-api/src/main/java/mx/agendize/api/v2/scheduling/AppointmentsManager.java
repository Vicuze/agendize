package mx.agendize.api.v2.scheduling;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.TimeHelper;
import mx.agendize.api.v2.reference.Time;
import mx.agendize.api.v2.scheduling.reference.AgendizeSchedulingObjectHelper;
import mx.agendize.api.v2.scheduling.reference.Appointment;
import mx.agendize.api.v2.scheduling.reference.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Appointments management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AppointmentsManager extends AgendizeApiManager{
	
	private static final String RESOURCE_COUNT = "resourceCount";
	static final Logger logger = LogManager.getLogger(AppointmentsManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public AppointmentsManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	private static final String RESOURCE_ID = "resourceId";
	private static final String STAFF_ID = "staffId";
	private static final String SERVICE_ID = "serviceId";
	private static final String CLIENT_ID = "clientId";
	private static final String STATUS = "status";
	private static final String END_DATE = "endDate";
	private static final String START_DATE = "startDate";

	/**
	 * Gets all the appointments for a given client.
	 * @param companyId Company identifier.
	 * @param clientId Client identifier.
	 * @return Appointment list for the provided criteria.
	 * @throws JSONException
	 * @throws ParseException
	 * @throws AgendizeException
	 * @throws IOException 
	 */
	public List<Appointment> getAppointmentsByClient(int companyId, Integer clientId) throws JSONException, ParseException, AgendizeException, IOException{
		logger.info("getAppointmentsByClient with client id = " + clientId);
		if (clientId == null || clientId <= 0){
			throw new AgendizeException("Please provide a valide client Id.");
		}
		CompaniesManager cm = new CompaniesManager(apiKey, token);
		if(cm.get(companyId) == null){
			throw new AgendizeException("There is no company for this id.");
		}
		ClientsManager clm = new ClientsManager(apiKey, token);
		if(clm.get(clientId) == null){
			throw new AgendizeException("There is no client for this id.");
		}
		return getAppointments(companyId, null, null, null, clientId, null, null, null);
	}

	/**
	 * Gets all the appointments for a service
	 * @param companyId Company identifier.
	 * @param startDate Start date. 
	 * @param endDate End date
	 * @param serviceId Service identifier. 
	 * @return Appointment list for the provided criteria.
	 * @throws JSONException
	 * @throws ParseException
	 * @throws AgendizeException
	 * @throws IOException
	 */
	public List<Appointment> getAppointmentsByService(int companyId, String startDate, 
			String endDate, Integer serviceId) throws JSONException, ParseException, AgendizeException, IOException{
		logger.info("getAppointmentsByService with service id = " + serviceId);
		if (serviceId == null || serviceId <= 0){
			throw new AgendizeException("Please provide a valide service Id.");
		} 
		ServicesManager sm = new ServicesManager(apiKey, token);
		Service s = sm.get(companyId, serviceId);
		if(s == null){
			throw new AgendizeException("There is no service for this id.");
		}
		return getAppointments(companyId, null, null, null, null, serviceId, null, null);
	}
	
	/**
	 * Return all appointments given the provided criteria.
	 * @param companyId Identifier of the company. Mandatory
	 * @param startDate Upper bound (exclusive) for an appointment's start time (as a <a target="_blank" href="https://www.ietf.org/rfc/rfc3339.txt">RFC 3339</a> timestamp) to filter by. Optional. The default is not to filter by start time. Example: 2014-10-21. Optional.
	 * @param endDate Lower bound (exclusive) for an appointment's end time (as a <a target="_blank" href="https://www.ietf.org/rfc/rfc3339.txt">RFC 3339</a> timestamp) to filter by. Optional. The default is not to filter by start time. Example: 2014-10-21. Optional.
	 * @param status Status of appointments. Acceptable values are: "noShow", "completed". Optional.
	 * @param clientId Client identifier. Optional.
	 * @param serviceId Service identifier. Optional.
	 * @param staffId Staff member identifier. Optional.
	 * @param resourceId Resource identifier. Optional.
	 * @return List of Appointment objects for the provided criteria.
	 * @throws JSONException 
	 * @throws ParseException 
	 * @throws AgendizeException if there is a problem with the parameters (invalid date format for example)
	 */
	public List<Appointment> getAppointments(int companyId, String startDate, 
			String endDate, String status, Integer clientId, Integer serviceId, Integer staffId, Integer resourceId) throws JSONException, ParseException, AgendizeException {
		logger.info("getAppointments." );
		TimeHelper.checkDate(startDate);
		TimeHelper.checkDate(endDate);
		
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		if(startDate!=null && !"".equals(startDate)){
			properties.put(START_DATE, startDate);
		}
		if(endDate!=null && !"".equals(endDate)){
			properties.put(END_DATE, endDate);
		}
		if(status != null && !"".equals(status)){
			properties.put(STATUS, status);
		}
		if(clientId != null && !"".equals(clientId)){
			properties.put(CLIENT_ID, clientId);
		}
		if(serviceId != null && !"".equals(serviceId)){
			properties.put(SERVICE_ID, serviceId);
		}
		if(staffId != null && !"".equals(staffId)){
			properties.put(STAFF_ID, staffId);
		}
		if(resourceId != null && !"".equals(resourceId)){
			properties.put(RESOURCE_ID, resourceId);
		}
		String appointmentsString = null;
		try {
			appointmentsString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL + "/" + companyId + "/appointments", properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray appointmentsJson = null;
    	try {
    		if(appointmentsString!=null){
    			appointmentsJson = new JSONObject(appointmentsString).getJSONArray("items");
        	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	List<Appointment> result = new ArrayList<Appointment>();//TODO check for errors
    	result = AgendizeSchedulingObjectHelper.jsonArrayToAppointmentList(appointmentsJson); 
    	logger.info(result.size() + " appointments found.");
		return result;
	}

	/**
	 * Gets an appointment by its id
	 * @param appointmentId Appointment identifier or appointment reference.
	 * @param companyId Company identifier.
	 * @return Appointment for id. null if not found. 
	 * @throws AgendizeException
	 */
	public Appointment get(int appointmentId, int companyId) throws AgendizeException{
		logger.info("getAppointment with appointmentId = " + appointmentId + " and companyId = " + companyId + ".");
		Appointment result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String appString = null;
		try {
			appString = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL+ "/" + companyId+"/appointments/"+appointmentId, properties);
		} catch (IOException e) {
			throw new AgendizeException("There was a problem in the request for company " + companyId + " and appointment "+appointmentId, e);
		}
		if(appString!=null){
			result = AgendizeSchedulingObjectHelper.jsonObjectToAppointment(new JSONObject(appString));
		}
		if(result != null){
			logger.info("Appointment found.");
		}
		return result;
	}
	
	/**
	 * Creates an appointment. 
	 * @param appointment the Appointment object. Must have company id.
	 * @return The appointment created.
	 * @throws JSONException
	 * @throws ParseException
	 * @throws AgendizeException 
	 */
	public Appointment create(Appointment appointment) throws JSONException, ParseException, AgendizeException  {
		logger.info("Create new appointment.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeSchedulingObjectHelper.appointmentToJSONObject(appointment).toString();
		String appointnmentString = APIUtils.postRequest(SCHEDULING_API_COMPANIES_URL+"/"+appointment.getCompany().getId()+"/appointments", json, properties);
		JSONObject jsonObject = new JSONObject(appointnmentString);
		Appointment result = AgendizeSchedulingObjectHelper.jsonObjectToAppointment(jsonObject);
		logger.info("Appointment created succesfully. id = " + result.getId() + ".");
        return result;
	}
	
	/**
	 * Deletes an appointment.
	 * @param companyId Company identifier.
	 * @param appointmentId identifier od the appointment to delete.
	 * @throws AgendizeException
	 */
	public void delete(int companyId, int appointmentId) throws AgendizeException {
		logger.info("Delete appointment with id = " + appointmentId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(SCHEDULING_API_URL+"companies/"+companyId+"/appointments/"+appointmentId, properties);
	}
	
	/**
	 * Returns the free slots.
	 * @param companyId Company identifier.
	 * @param startDate Start date. format: yyyy-MM-dd
	 * @param endDate End date. format: yyyy-MM-dd
	 * @param serviceId Service identifier.
	 * @param staffId Staff member identifier (optional).
	 * @return The list of free slots for the provided criteria.
	 * @throws JSONException
	 * @throws ParseException
	 */
	public List<Time> getFreeSlots(int companyId, String startDate, String endDate, Integer serviceId, Integer staffId) throws JSONException, ParseException{
		logger.info("getFreeSlots.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		properties.put(START_DATE, startDate);
		properties.put(END_DATE, endDate);
		properties.put(SERVICE_ID, serviceId.toString());
		if(staffId!=null){
			properties.put(STAFF_ID, staffId.toString());
		}
		String freeSlots = null;
		try {
			freeSlots = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL + "/"+companyId+"/widget/freeSlots", properties);
			logger.debug("Free slots JSON received: " + freeSlots.replace("\n", ""));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray freeSlotsJson = null;
    	try {
    		if(freeSlots!=null && !"".equals(freeSlots)){
    			freeSlotsJson = new JSONObject(freeSlots).getJSONArray("items");
        	}
		} catch (JSONException e) {
			logger.error(e.getMessage());
		}
    	List<Time> result = AgendizeSchedulingObjectHelper.jsonArrayToFreeSlotList(freeSlotsJson);
    	logger.info(result.size()+" free slots found.");
    	return result;
	}

	/**
	 * Returns the free slots for a company in resource mode.
	 * @param companyId Company identifier.
	 * @param startDate Start date. format: yyyy-MM-dd
	 * @param endDate End date. format: yyyy-MM-dd
	 * @param resourceId Resource identifier
	 * @param resourceCount Number of resources to book (optional).
	 * @return the free slots for a company in resource mode
	 * @throws JSONException
	 * @throws ParseException
	 */
	public List<Time> getFreeSlots_resourceMode(Integer companyId, String startDate, String endDate, Integer resourceId, Integer resourceCount) throws JSONException, ParseException {
		logger.info("getFreeSlots (Resource mode).");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		properties.put(START_DATE, startDate);
		properties.put(END_DATE, endDate);
		properties.put(RESOURCE_ID, resourceId.toString());
		if(resourceCount != null){
			properties.put(RESOURCE_COUNT, resourceCount.toString());
		}
		String freeSlots = null;
		try {
			freeSlots = APIUtils.getRequest(SCHEDULING_API_COMPANIES_URL + "/"+companyId+"/widget/freeSlots", properties);
			logger.debug("Free slots JSON received: " + freeSlots.replace("\n", ""));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONArray freeSlotsJson = null;
    	try {
    		if(freeSlots!=null && !"".equals(freeSlots)){
    			freeSlotsJson = new JSONObject(freeSlots).getJSONArray("items");
        	}
		} catch (JSONException e) {
			logger.error(e.getMessage());
		}
    	List<Time> result = AgendizeSchedulingObjectHelper.jsonArrayToFreeSlotList(freeSlotsJson);
    	logger.info(result.size()+" free slots found.");
    	return result;
	}

	
}
