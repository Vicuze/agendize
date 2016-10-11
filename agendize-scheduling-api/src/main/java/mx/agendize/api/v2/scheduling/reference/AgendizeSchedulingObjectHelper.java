package mx.agendize.api.v2.scheduling.reference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.reference.AgendizeObjectHelper;
import mx.agendize.api.v2.reference.Language;
import mx.agendize.api.v2.reference.Time;
import mx.agendize.api.v2.scheduling.reference.Appointment.AppointmentStatus;
import mx.agendize.api.v2.scheduling.reference.Appointment.AppointmentType;
import mx.agendize.api.v2.scheduling.reference.Setting.SettingGroup;
import mx.agendize.api.v2.scheduling.reference.WorkingHours.DayOfTheWeek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for management of Agendize objects: Appointments, clients, companies, services, staff, settings, resources, widget.
 * Contains method to convert JSONObject and JSONArray to object and vice versa.
 * Info about the JSON structure here <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/index.jsp">http://developers.agendize.com/v2/scheduling/reference/index.jsp</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizeSchedulingObjectHelper {

	/** parameter names */
	private static final String TYPE = "type";
	private static final String WEBSITE_LINK = "websiteLink";
	private static final String FORM = "form";
	private static final String TITLE = "title";
	private static final String STATUS = "status";
	private static final String CREATED = "created";
	private static final String LANGUAGE = "language";
	private static final String REFERENCE = "reference";
	private static final String RESOURCE = "resource";
	private static final String NOTES = "notes";
	private static final String CLIENT = "client";
	private static final String SERVICE = "service";
	private static final String COMPANY = "company";
	private static final String GEOLOCATION = "geolocation";
	private static final String PICTURE = "picture";
	private static final String TIME_ZONE = "timeZone";
	private static final String GENDER = "gender";
	private static final String ADDRESS = "address";
	private static final String SERVICES = "services";
	private static final String MOBILE_PHONE = "mobilePhone";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String BUFFER_DURATION = "bufferDuration";
	private static final String STAFF = "staff";
	private static final String HOURS = "hours";
	private static final String END = "end";
	private static final String START = "start";
	private static final String DAY = "day";
	private static final String WORKING_HOURS = "workingHours";
	private static final String PAYABLE = "payable";
	private static final String PRICE = "price";
	private static final String DESCRIPTION = "description";
	private static final String COLOR = "color";
	private static final String COUNT = "count";
	private static final String DURATION = "duration";
	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String VALUE = "value";
	private static final String GROUP = "group";
	
	/** Date format used in JSON */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * Converts a Resource object into a JSONObject for API use.
	 * @param resource the Resource object.
	 * @return The JSONObject representing the resource. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/resources/index.jsp">http://developers.agendize.com/v2/scheduling/reference/resources/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject resourceToJSONObject(Resource resource) throws JSONException{
		JSONObject result = new JSONObject();
		if(resource.getId()!=null){
			result.put(ID, resource.getId());
		}
		result.put(NAME, resource.getName());
		result.put(DURATION, resource.getDuration());
		result.put(COUNT, resource.getCount());
		if(resource.getColor()!=null && !"".equals(resource.getColor())){
			result.put(COLOR, resource.getColor());
		}
		result.put(DESCRIPTION, resource.getDescription());
		result.put(PRICE, resource.getPrice());
		result.put(PAYABLE, resource.isPayable());
		if(resource.getWorkingHours() != null && !resource.getWorkingHours().isEmpty()){
			result.put(WORKING_HOURS, workingHoursHoursToJSONArray(resource.getWorkingHours()));
		}
		return result;
	}

	/**
	 * Converts a JSONObject from the API to a Resource object 
	 * @param resourceJson The JSONObject representing the resource. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/resources/index.jsp">http://developers.agendize.com/v2/scheduling/reference/resources/index.jsp</a>
	 * @return The Resource object.
	 * @throws JSONException
	 */
	public static Resource jsonObjectToResource(JSONObject resourceJson) throws JSONException {
		Resource result = new Resource();
		result.setId(resourceJson.getInt(ID));
		result.setName(resourceJson.getString(NAME));
		result.setDuration(resourceJson.getInt(DURATION));
		result.setCount(resourceJson.getInt(COUNT));
		result.setColor(resourceJson.getString(COLOR));
		result.setDescription(resourceJson.getString(DESCRIPTION));
		result.setPrice(resourceJson.getInt(PRICE));
		result.setPayable(resourceJson.getBoolean(PAYABLE));
		if(resourceJson.has(WORKING_HOURS)){
			result.setWorkingHours(workingHoursFromJSONArray(resourceJson.getJSONArray(WORKING_HOURS)));
		}
		return result;
	}

	/**
	 * Converts a JSONArray in a list of Resource objects
	 * @param resourcesJson json representing the resource list. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/resources/index.jsp">http://developers.agendize.com/v2/scheduling/reference/resources/index.jsp</a>
	 * @return The list of resource objects.
	 * @throws JSONException
	 */
	public static List<Resource> jsonArrayToResourceList(JSONArray resourcesJson) throws JSONException {
		List<Resource> result = new ArrayList<Resource>();
		for(int j= 0; j<resourcesJson.length(); j++){
    		result.add(jsonObjectToResource((JSONObject) resourcesJson.get(j)));
    	}
    	return result;
	}

	/**
	 * Converts a Service object into a JSONObject for API use.
	 * @param service The service object.
	 * @return The JSONObject representing the service. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/services/index.jsp">http://developers.agendize.com/v2/scheduling/reference/services/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject serviceToJSONObject(Service service) throws JSONException{
		JSONObject result = new JSONObject();
		if(service.getId() != null){
			result.put(ID, service.getId());
		}
		if(service.getName() != null){
			result.put(NAME, service.getName());
		}
		if(service.getDuration() != null){
			result.put(DURATION, service.getDuration());
		}
		if(service.getBufferDuration() != null){
			result.put(BUFFER_DURATION, service.getBufferDuration());
		}
		if(service.getPrice()!=null){
			result.put(PRICE, service.getPrice());
		}
		if(service.getPayable() != null){
			result.put(PAYABLE, service.getPayable().toString());
		}
		if(service.getColor()!=null && !"".equals(service.getColor())){
			result.put(COLOR, service.getColor());
		}
		JSONArray JSONstaffs = new JSONArray();
		if(service.getStaff()!=null){
			for(Staff staff: service.getStaff()){
				JSONstaffs.put(staffToJSONObject(staff));
			}
			result.put(STAFF, JSONstaffs);
		}
		result.put(DESCRIPTION, service.getDescription());
		return result; 
	}

	/**
	 * Converts a JSONObject from the API to a Service object 
	 * @param serviceJson json representing the service. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/services/index.jsp">http://developers.agendize.com/v2/scheduling/reference/services/index.jsp</a>
	 * @return The Service object.
	 * @throws JSONException
	 */
	public static Service jsonObjectToService(JSONObject serviceJson) throws JSONException {
		Service result = new Service();
		result.setId(serviceJson.getInt(ID));
		result.setName(serviceJson.getString(NAME));
		if(serviceJson.has(DURATION)){
			result.setDuration(serviceJson.getInt(DURATION));
		}
		if(serviceJson.has(BUFFER_DURATION)){
		result.setBufferDuration(serviceJson.getInt(BUFFER_DURATION));
		}
		if(serviceJson.has(PRICE)){
		result.setPrice(serviceJson.getInt(PRICE));
		}
		if(serviceJson.has(PAYABLE)){
		result.setPayable(serviceJson.getBoolean(PAYABLE));
		}
		if(serviceJson.has(COLOR)){
		result.setColor(serviceJson.getString(COLOR));
		}
		if(serviceJson.has(DESCRIPTION)){
		result.setDescription(serviceJson.getString(DESCRIPTION));
		}
		if(serviceJson.has(STAFF)){
			JSONArray staffsJson = serviceJson.getJSONArray(STAFF);
			result.setStaff(jsonArrayToStaffList(staffsJson));
		}
		if(serviceJson.has(PICTURE)){
			result.setPicture(AgendizeObjectHelper.jsonObjectToPicture(serviceJson.getJSONObject(PICTURE)));
		}
		return result;
	}

	/**
	 * Converts a JSONArray representing a list of services to a List of Services objects
	 * @param servicesJson json representing the service list. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/services/index.jsp">http://developers.agendize.com/v2/scheduling/reference/services/index.jsp</a>
	 * @return The list of services. 
	 * @throws JSONException
	 */
	public static List<Service> jsonArrayToServiceList(JSONArray servicesJson) throws JSONException {
		List<Service> result = new ArrayList<Service>();
		for(int j= 0; j<servicesJson.length(); j++){
    		result.add(jsonObjectToService((JSONObject) servicesJson.get(j)));
    	}
    	return result;
	}

	/**
	 * Converts a Staff object into a JSONObject for API use.
	 * @param staff The staff object.
	 * @return The JSONObject representing the staff. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/staff/index.jsp">http://developers.agendize.com/v2/scheduling/reference/staff/index.jsp</a>
	 * @throws JSONException 
	 */
	public static JSONObject staffToJSONObject(Staff staff) throws JSONException {
		JSONObject result = AgendizeObjectHelper.personToJSONObject(staff);
		if(staff.getColor()!=null && !"".equals(staff.getColor())){
			result.put(COLOR, staff.getColor());
		}
		if(staff.getWorkingHours()!=null && !staff.getWorkingHours().isEmpty()){
			result.put(WORKING_HOURS, workingHoursHoursToJSONArray(staff.getWorkingHours()));
		}
		return result;
	}

	/**
	 * Converts a JSONObject from the API to a Staff object 
	 * @param staffJson json representing the staff. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/staff/index.jsp">http://developers.agendize.com/v2/scheduling/reference/staff/index.jsp</a>
	 * @return The Staff object.
	 * @throws JSONException
	 */
	public static Staff jsonObjectToStaff(JSONObject staffJson) throws JSONException {
		Staff result = new Staff();
		if(staffJson.has(ID)){
			result.setId(staffJson.getInt(ID));
		}
		if(staffJson.has(FIRST_NAME)){
			result.setFirstName(staffJson.getString(FIRST_NAME));
		}
		if(staffJson.has(LAST_NAME)){
			result.setLastName(staffJson.getString(LAST_NAME));
		}
		if(staffJson.has(EMAIL)){
			result.setEmail(staffJson.getString(EMAIL));
		}
		if(staffJson.has(PHONE)){
			result.setPhone(staffJson.getString(PHONE));
		}
		if(staffJson.has(MOBILE_PHONE)){
			result.setMobilePhone(staffJson.getString(MOBILE_PHONE));
		}
		if(staffJson.has(COLOR)){
			result.setColor(staffJson.getString(COLOR));
		}
		if(staffJson.has(WORKING_HOURS)){
			result.setWorkingHours(workingHoursFromJSONArray((JSONArray) staffJson.get(WORKING_HOURS)));
		}
		if(staffJson.has(SERVICES)){
			JSONArray servicesJson = staffJson.getJSONArray(SERVICES);
			result.setServices(jsonArrayToServiceList(servicesJson));
		}
		if(staffJson.has(PICTURE)){
			result.setPicture(AgendizeObjectHelper.jsonObjectToPicture(staffJson.getJSONObject(PICTURE)));
		}
		return result;
	}
	
	/**
	 * Converts a JSONArray representing a list of staffs to a List of Staff objects
	 * @param staffsJson json array representing the staff list. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/staff/index.jsp">http://developers.agendize.com/v2/scheduling/reference/staff/index.jsp</a>
	 * @return The list of staff objects.
	 * @throws JSONException
	 */
	public static List<Staff> jsonArrayToStaffList(JSONArray staffsJson) throws JSONException {
		List<Staff> result = new ArrayList<Staff>();
		if(staffsJson!=null){
			for(int j= 0; j<staffsJson.length(); j++){
	    		result.add(jsonObjectToStaff((JSONObject) staffsJson.get(j)));
	    	}
		}
    	return result;
	}

	/**
	 * Converts a JSONObject from the API into a Company object
	 * @param companyJson json representing the company. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/companies/index.jsp">http://developers.agendize.com/v2/scheduling/reference/companies/index.jsp</a>
	 * @return The company object.
	 * @throws JSONException
	 */
	public static Company jsonObjectToCompany(JSONObject companyJson) {
		Company result = new Company();
		result.setId(companyJson.getInt(ID));
		result.setName((String) companyJson.getString(NAME));
		if(companyJson.has(TIME_ZONE)){
			result.setTimeZone(companyJson.getString(TIME_ZONE));
		}
		if(companyJson.has(EMAIL)){
			result.setEmail(companyJson.getString(EMAIL));
		}
		if(companyJson.has(PHONE)){
			result.setPhone(companyJson.getString(PHONE));
		}
		if(companyJson.has(WEBSITE_LINK)){
			result.setWebsiteLink(companyJson.getString(WEBSITE_LINK));
		}
		if(companyJson.has(WORKING_HOURS)){
			result.setWorkingHours(workingHoursFromJSONArray((JSONArray) companyJson.get(WORKING_HOURS)));
		}
		return result;
	}

	/**
	 * Converts a JSONArray in a list of Company objects
	 * @param companiesJson json representing the list of companies. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/companies/index.jsp">http://developers.agendize.com/v2/scheduling/reference/companies/index.jsp</a>
	 * @return The list of company objects.
	 */
	public static List<Company> jsonArrayToCompanyList(JSONArray companiesJson) {
		List<Company> result = new ArrayList<Company>();
		for(int j= 0; j<companiesJson.length(); j++){
    		result.add(jsonObjectToCompany((JSONObject) companiesJson.get(j)));
    	}
    	return result;
	}
	
	/**
	 * Converts a Company object into a JSONObject for API use.
	 * @param company the Company onject.
	 * @return The JSONObject representing the company. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/companies/index.jsp">http://developers.agendize.com/v2/scheduling/reference/companies/index.jsp</a>
	 * @throws JSONException 
	 */
	public static JSONObject companyToJSONObject(Company company) throws JSONException {
		JSONObject result = new JSONObject();
		if(company.getId()!=null){
			result.put(ID, company.getId());
		}
		if(company.getName()!=null){
			result.put(NAME, company.getName());
		}
		if(company.getTimeZone() != null){
			result.put(TIME_ZONE, company.getTimeZone());
		}
		if(company.getEmail() != null){
			result.put(EMAIL, company.getEmail());
		}
		if(company.getPhone() != null){
			result.put(PHONE, company.getPhone());
		}
		if(company.getWebsiteLink() != null){
			result.put(WEBSITE_LINK, company.getWebsiteLink());
		}
		if(company.getAddress() != null){
			result.put(ADDRESS, AgendizeObjectHelper.addressToJSONObject(company.getAddress()));
		}
		if(company.getPicture() != null){
			result.put(PICTURE, AgendizeObjectHelper.pictureToJSONObject(company.getPicture()));
		}
		if(company.getGeolocationLat() != null || company.getGeolocationLong() != null){
			result.put(GEOLOCATION, AgendizeObjectHelper.gelocationToJSONObject(company.getGeolocationLat(), company.getGeolocationLong()));
		}
		if(company.getWorkingHours() != null && !company.getWorkingHours().isEmpty()){
			result.put(WORKING_HOURS, workingHoursHoursToJSONArray(company.getWorkingHours()));	
		}
		return result;
	}

	/**
	 * Converts a JSONObject from the API into a Appointment object. 
	 * @param appointmentJson json representing the Appointment. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/appointments/index.jsp">http://developers.agendize.com/v2/scheduling/reference/appointments/index.jsp</a>
	 * @return The Appointment object
	 * @throws AgendizeException 
	 */
	public static Appointment jsonObjectToAppointment(JSONObject appointmentJson) throws AgendizeException {
		Appointment result = new Appointment();
		result.setId(appointmentJson.getInt(ID));
		result.setReference(appointmentJson.getString(REFERENCE));
		if(appointmentJson.has(COMPANY)){
			JSONObject companyJson = appointmentJson.getJSONObject(COMPANY);
			result.setCompany(new Company(companyJson.getInt(ID), companyJson.getString(NAME)));
		}
		if(appointmentJson.has(SERVICE)){
			JSONObject serviceJson = appointmentJson.getJSONObject(SERVICE);
			result.setService(new Service(serviceJson.getInt(ID), serviceJson.getString(NAME)));
		}
		if(appointmentJson.has(RESOURCE)){
			JSONObject resourceJson = appointmentJson.getJSONObject(RESOURCE);
			result.setResource(new Resource(resourceJson.getInt(ID), resourceJson.getString(NAME)));
		}
		if(appointmentJson.has(STAFF)){
			JSONObject staffJson = appointmentJson.getJSONObject("staff");
			result.setStaff(new Staff(staffJson.getInt(ID), staffJson.getString(FIRST_NAME), staffJson.getString(LAST_NAME)));
		}
		if(appointmentJson.has(CLIENT)){
			JSONObject clientJson = appointmentJson.getJSONObject(CLIENT);
			result.setClient(new Client(clientJson.getInt(ID), clientJson.getString(FIRST_NAME), clientJson.getString(LAST_NAME)));
		}
		if(appointmentJson.has(START)){
			result.setStart(AgendizeObjectHelper.jsonObjectToTime(appointmentJson.getJSONObject(START)));
		}
		if(appointmentJson.has(END)){
			result.setEnd(AgendizeObjectHelper.jsonObjectToTime(appointmentJson.getJSONObject(END)));
		}
		if(appointmentJson.has(CREATED)){
			result.setCreated(AgendizeObjectHelper.jsonObjectToTime(appointmentJson.getJSONObject(CREATED)));
		}
		if(appointmentJson.has(FORM)){
			result.setForm(jsonArrayToSchedulingFormFieldList(appointmentJson.getJSONArray(FORM)));
		}
		if(appointmentJson.has(STATUS)){
			result.setStatus(AppointmentStatus.get(appointmentJson.getString(STATUS)));
		}
		if(appointmentJson.has(NOTES)){
			result.setNotes(appointmentJson.getString(NOTES));
		}
		if(appointmentJson.has(TYPE)){
			result.setType(AppointmentType.get(appointmentJson.getString(TYPE)));
		}
		return result;
	}

	/**
	 * Converts a JSONArray from the API into a list of SchedulingFormField Object
	 * @param formJson json representing the list of form fields.
	 * @return the list of SchedulingFormField objects
	 */
	private static List<SchedulingFormField> jsonArrayToSchedulingFormFieldList(JSONArray formJson) {
		List<SchedulingFormField> result = new ArrayList<SchedulingFormField>();
		for(int i=0;i<formJson.length();i++){
			result.add(jsonObjectToSchedulingFormField(formJson.getJSONObject(i)));
		}
		return result;
	}

	/**
	 * Converts a JSONObject to a SchedulingFormField object.
	 * @param formFieldJson json representing the SchedulingFormField. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/forms">http://developers.agendize.com/v2/scheduling/reference/forms</a>
	 * @return the SchedulingFormField object
	 */
	private static SchedulingFormField jsonObjectToSchedulingFormField(JSONObject formFieldJson) {
		SchedulingFormField result = new SchedulingFormField();
		result.setTitle(formFieldJson.getString(TITLE));
		result.setValue(formFieldJson.getString(VALUE));
		return result;
	}

	/**
	 * Converts a Appointment object into a JSONObject for API use. /!\ Only works with Service mode
	 * @param appointment The appointment object.
	 * @return The JSONObject representing the Appointment. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/appointments/index.jsp">http://developers.agendize.com/v2/scheduling/reference/appointments/index.jsp</a>
	 * @throws JSONException
	 * @throws AgendizeException if the appointment is not valid (no service and no resource, for example).
	 */
	public static JSONObject appointmentToJSONObject(Appointment appointment) throws JSONException, AgendizeException { 
		JSONObject result = new JSONObject();
		if(appointment.getId()!=null){
			result.put(ID, appointment.getId());
		}
		if(appointment.getCompany() != null){
			JSONObject companyJson = companyToJSONObject(appointment.getCompany());
			result.put(COMPANY, companyJson);
		}
		if(appointment.getStaff() != null){
			JSONObject staffJson = staffToJSONObject(appointment.getStaff());
			result.put(STAFF, staffJson);
		}
		if(appointment.getService() != null){
			JSONObject serviceJson = serviceToJSONObject(appointment.getService());
			result.put(SERVICE, serviceJson);
		} else if(appointment.getResource() != null){
			JSONObject resourceJson = resourceToJSONObject(appointment.getResource());
			result.put("resource", resourceJson);
		} else {
			/* service && ressource sont nuls => ça dégage. */
			throw new AgendizeException("You have to specify a service or a resource for the appointment."); 
		}
		if(appointment.getClient() != null){
			JSONObject clientJson = clientToJSONObject(appointment.getClient());
			result.put(CLIENT, clientJson);
		}
		if(appointment.getStart() != null){
			JSONObject startJson = AgendizeObjectHelper.timeToJSONObject(appointment.getStart());
			result.put(START, startJson);
		}
		if(appointment.getEnd() != null){
			JSONObject endJson = AgendizeObjectHelper.timeToJSONObject(appointment.getEnd());
			result.put(END, endJson);
		}
		if(appointment.getStatus() != null){
			result.put(STATUS, appointment.getStatus().getCode());
		}
		if(appointment.getNotes() != null){
			result.put(NOTES, appointment.getNotes());
		}
		if(appointment.getType() != null){
			result.put(TYPE, appointment.getType().getCode());
		}
		return result;
	}
	
	/**
	 * Converts a Client object into a JSONObject for API use.
	 * @param client the Client object.
	 * @return The JSONObject representing the client. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/clients/index.jsp">http://developers.agendize.com/v2/scheduling/reference/clients/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject clientToJSONObject(Client client) throws JSONException {
		JSONObject result = AgendizeObjectHelper.personToJSONObject(client);
		result.put(GENDER, client.getGender());
		if(client.getTimeZone() != null){
			result.put(TIME_ZONE, client.getTimeZone().getDisplayName(Locale.ENGLISH));
		}
		if(client.getLanguage() != null){
			result.put(LANGUAGE, client.getLanguage().getCode());
		} else {
			result.put(LANGUAGE, Language.NONE.getCode());
		}
		if(client.getAddress() != null){
			result.put(ADDRESS, AgendizeObjectHelper.addressToJSONObject(client.getAddress()));
		}
		return result;
	}

	/**
	 * Converts a JSONArray from the API into a list of WorkingHours Objects
	 * @param workingHoursJson json representing the working hours
	 * @return lis of WorkingHours objects.
	 * @throws JSONException
	 */
	public static List<WorkingHours> workingHoursFromJSONArray(JSONArray workingHoursJson) throws JSONException{
		List<WorkingHours> result = new ArrayList<WorkingHours>();
		for(int i= 0; i<workingHoursJson.length(); i++){
			WorkingHours wh = new WorkingHours();
			JSONObject whJson = (JSONObject) workingHoursJson.get(i);
			wh.setDay(DayOfTheWeek.get(whJson.getString(DAY)));
			
			JSONArray hours= whJson.getJSONArray(HOURS);
			for(int j=0;j<hours.length();j++){
				JSONObject hoursJson = hours.getJSONObject(j);
				Hours h = new Hours();
				h.setEnd(hoursJson.getString(END));
				h.setStart(hoursJson.getString(START));
				wh.getHours().add(h);
			}
			result.add(wh);
    	}
		return result;
	}

	/**
	 * Converts a list of working hours into a JSONArray for API use.
	 * @param workingHoursList the list of WorkingHours objects.
	 * @return JSONArray representing the list of workingHours
	 * @throws JSONException
	 */
	public static JSONArray workingHoursHoursToJSONArray(List<WorkingHours> workingHoursList) throws JSONException{
		JSONArray result = new JSONArray();
		if(workingHoursList!=null){
			for(WorkingHours wh: workingHoursList){
				JSONObject whJson = new JSONObject();
				whJson.put(DAY, wh.getDay().getName());
				JSONArray hours= new JSONArray();
				for(Hours h: wh.getHours()){
					JSONObject hoursJson = new JSONObject();
					hoursJson.put(START, h.getStart());
					hoursJson.put(END, h.getEnd());
					hours.put(hoursJson);
				}
				whJson.put(HOURS, hours);
				result.put(whJson);
			}
		}
		return result;
	}

	/**
	 * Converts a JSONArray to a list of free slots
	 * @param freeSlotsJson json representing the list of free slots.
	 * @return List of Time objects representing the free slots.
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static List<Time> jsonArrayToFreeSlotList(JSONArray freeSlotsJson) throws JSONException, ParseException {
		List<Time> result = new ArrayList<Time>();
		if(freeSlotsJson!=null){
			for(int i= 0; i<freeSlotsJson.length(); i++){
				JSONObject daySlots =  (JSONObject) freeSlotsJson.get(i);
				JSONArray slots = (JSONArray) daySlots.get("slots");
				String timeZone = daySlots.getString("timeZone");
				sdf.getCalendar().setTimeZone(TimeZone.getTimeZone(daySlots.getString(TIME_ZONE)));
				for(int j=0;j<slots.length(); j++){
					sdf.parse(slots.getString(j));
					result.add(new Time(timeZone, sdf.getCalendar().getTime()));
				}
	    	}
		}
    	return result;
	}
	
	/**
	 * Converts a Setting (group - id - value) object into a JSONObject
	 * @param setting the Setting object
	 * @return the JSONObject representing the setting
	 * @throws JSONException
	 */
	public static JSONObject settingToJSONObject(Setting setting) throws JSONException {
		JSONObject result = new JSONObject();
		result.put(ID, setting.getId());
		result.put(GROUP, setting.getGroup().getCode());
		result.put(VALUE, setting.getValue());
		return result;
	}
	
	/**
	 * Converts a settings list in a JSONArray.
	 * @param settings settings list.
	 * @return The JSONArray representing the settings list
	 */
	public static JSONObject settingListToJSONObject(List<Setting> settings){
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		if(settings!=null){
			for(Setting s: settings){
				array.put(settingToJSONObject(s));
			}
		}
		result.put("items", array);
		return result;
	}
	
	/**
	 * Converts a JSONObject to a Setting object.
	 * @param settingJson json representing the Setting. Example: 
	 * 	<pre>
	 * {@code
	 * 	{
	 *       "group": "notifications",
	 *       "id": "staffSMSWidgetNew",
	 *       "value": "true"
	 * 	}
	 * }
	 * </pre>
	 * @return the Setting object. 
	 * @throws JSONException
	 */
	public static Setting jsonObjectToSetting(JSONObject settingJson) throws JSONException {
		Setting result = new Setting();
		result.setId(settingJson.getString(ID));
		result.setGroup(SettingGroup.get(settingJson.getString(GROUP)));
		//TODO Change this
		if(settingJson.getString(ID).equals("form")){
			result=null; 
		} else {
			result.setValue(settingJson.getString(VALUE));
		}
		return result;
	}

	/**
	 * Converts a JSONArray into a list of Setting objects
	 * @param settingsJson json representing the list of settings. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp">http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp</a>
	 * @return the list of Settings objects.
	 * @throws JSONException
	 */
	public static List<Setting> jsonArrayToSettingList(JSONArray settingsJson) throws JSONException {
		List<Setting> result = new ArrayList<Setting>();
		for(int i=0;i<settingsJson.length();i++){
			Setting s = jsonObjectToSetting(settingsJson.getJSONObject(i));
			if(s != null){
				result.add(s);
			}
		}
		return result;
	}

	/**
	  * Converts a JSONArray into a list of Client objects
	 * @param clientsJson json representing the list of clients. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/clients/index.jsp">http://developers.agendize.com/v2/scheduling/reference/clients/index.jsp</a>
	 * @return the list of Client objects.
	 * @throws JSONException
	 */
	public static List<Client> jsonArrayToClientList(JSONArray clientsJson) {
		List<Client> result = new ArrayList<Client>();
		for(int j= 0; j<clientsJson.length(); j++){
    		result.add(jsonObjectToClient((JSONObject) clientsJson.get(j)));
    	}
    	return result;
	}

	/**
	 * Converts a JSONObject from the API into a Client object
	 * @param clientJson json representing the client. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/clients/index.jsp">http://developers.agendize.com/v2/scheduling/reference/clients/index.jsp</a>
	 * @return The client object.
	 */
	public static Client jsonObjectToClient(JSONObject clientJson) {
		Client result = new Client();
		if(clientJson.has(ID)){
			result.setId(clientJson.getInt(ID));
		}
		if(clientJson.has(FIRST_NAME)){
			result.setFirstName(clientJson.getString(FIRST_NAME));
		}
		if(clientJson.has(LAST_NAME)){
			result.setLastName(clientJson.getString(LAST_NAME));
		}
		if(clientJson.has(EMAIL)){
			result.setEmail(clientJson.getString(EMAIL));
		}
		if(clientJson.has(PHONE)){
			result.setPhone(clientJson.getString(PHONE));
		}
		if(clientJson.has(MOBILE_PHONE)){
			result.setMobilePhone(clientJson.getString(MOBILE_PHONE));
		}
		if(clientJson.has(GENDER)){
			result.setGender(clientJson.getString(GENDER));
		}
		if(clientJson.has(ADDRESS)){
			result.setAddress(AgendizeObjectHelper.jsonObjectToAddress(clientJson.getJSONObject(ADDRESS)));
		}
		if(clientJson.has(PICTURE)){
			result.setPicture(AgendizeObjectHelper.jsonObjectToPicture(clientJson.getJSONObject(PICTURE)));
		}
		return result;
	}

	/**
	  * Converts a JSONArray into a list of Appointment objects
	 * @param appointmentsJson json representing the list of appointments. See <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/appointments/index.jsp">http://developers.agendize.com/v2/scheduling/reference/appointments/index.jsp</a>
	 * @return the list of Appointment objects.
	 * @throws JSONException
	 */
	public static List<Appointment> jsonArrayToAppointmentList(JSONArray appointmentsJson) throws JSONException, AgendizeException {
		List<Appointment> result = new ArrayList<Appointment>();
		for(int i = 0; i<appointmentsJson.length(); i++){
    		result.add(jsonObjectToAppointment((JSONObject) appointmentsJson.get(i)));
    	}
    	return result;
	}

}
