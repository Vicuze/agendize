package mx.agendize.api.v2.clients.reference;

import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.clients.reference.Activity.Title;
import mx.agendize.api.v2.clients.reference.Client.Gender;
import mx.agendize.api.v2.reference.AgendizeObjectHelper;
import mx.agendize.api.v2.reference.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for management of Agendize Clients API objects: Clients, Activities, Phone numbers, Email Addresses, Tags.
 * Contains method to convert JSONObject and JSONArray to object and vice versa.
 * Info about the JSON structure here <a target="_blank" href="http://developers.agendize.com/v2/clients/reference">http://developers.agendize.com/v2/clients/reference</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizeClientsObjectHelper {

	/** Parameter names */
	private static final String DATE = "date";
	private static final String TITLE = "title";
	private static final String TYPE = "type";
	private static final String COLOR = "color";
	private static final String TAG = "tag";
	private static final String TAGS = "tags";
	private static final String MOBILE = "mobile";
	private static final String PRIMARY = "primary";
	private static final String NUMBER = "number";
	private static final String LANGUAGE = "language";
	private static final String PICTURE = "picture";
	private static final String TIME_ZONE = "timeZone";
	private static final String GENDER = "gender";
	private static final String ADDRESS = "address";
	private static final String PHONE_NUMBERS = "phoneNumbers";
	private static final String EMAIL = "email";
	private static final String EMAIL_ADDRESSES = "emailAddresses";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String ID = "id";
	private static final String DESCRIPTION = "description";
	
	/**
	 * Converts a Client object into a JSONObject for API use.
	 * @param client the Client object.
	 * @return The JSONObject representing the client. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject clientToJSONObject(Client client) throws JSONException {
		JSONObject result = new JSONObject();
		if(client.getId() != null){
			result.put(ID, client.getId());
		}
		if(client.getFirstName() != null && !"".equals(client.getFirstName())){
			result.put(FIRST_NAME, client.getFirstName());
		}
		if(client.getLastName() != null && !"".equals(client.getLastName())){
			result.put(LAST_NAME, client.getLastName());
		}
		if(client.getEmailAddresses() != null && !client.getEmailAddresses().isEmpty()){
			result.put(EMAIL_ADDRESSES, emailAdressesToJsonArray(client.getEmailAddresses()));
		}
		if(client.getPhoneNumbers() != null && !client.getPhoneNumbers().isEmpty()){
			result.put(PHONE_NUMBERS, phoneNumbersToJsonArray(client.getPhoneNumbers()));
		}
		result.put(GENDER, client.getGender().getCode());
		if(client.getTimeZone() != null){
			result.put(TIME_ZONE, client.getTimeZone());
		}
		if(client.getLanguage() != null){
			result.put(LANGUAGE, client.getLanguage().getCode());
		} else {
			result.put(LANGUAGE, Language.NONE.getCode());
		}
		if(client.getDescription() != null && !"".equals(client.getDescription())){
			result.put(DESCRIPTION, client.getDescription());
		}
		if(client.getPicture() != null){
			result.put(PICTURE, AgendizeObjectHelper.pictureToJSONObject(client.getPicture()));
		}
		if(client.getAddress() != null){
			result.put(ADDRESS, AgendizeObjectHelper.addressToJSONObject(client.getAddress()));
		}
		if(client.getTags() != null && !client.getTags().isEmpty()){
			result.put(TAGS, tagsToJsonArray(client.getTags()));
		}
		return result;
	}

	/**
	 * Converts a list of tags into a JSONArray for API use.
	 * @param tags the list of tags objects.
	 * @return JSONArray representing the list of tags.
	 */
	private static JSONArray tagsToJsonArray(List<Tag> tags) {
		JSONArray result = new JSONArray();
		if(tags!=null){
			for(Tag tag: tags){
				result.put(tagToJSONObject(tag));
			}
		}
		return result;
	}

	/**
	 * Converts a Tag object into a JSONObject for API use. 
	 * @param tag the Tag object.
	 * @return The JSONObject representing the tag.
	 */
	private static JSONObject tagToJSONObject(Tag tag) {
		JSONObject result = new JSONObject();
		result.put(TAG, tag.getTag());
		result.put(COLOR, tag.getColor());
		return result;
	}

	/**
	 * Converts a list of phone numbers into a JSONArray for API use.
	 * @param phoneNumbers the list of phone numbers objects.
	 * @return JSONArray representing the list of phone numbers.
	 */
	private static JSONArray phoneNumbersToJsonArray(List<PhoneNumber> phoneNumbers) {
		JSONArray result = new JSONArray();
		if(phoneNumbers!=null){
			for(PhoneNumber phoneNumber: phoneNumbers){
				result.put(phoneNumberToJSONObject(phoneNumber));
			}
		}
		return result;
	}
	
	/**
	 * Converts a Phone number object into a JSONObject for API use. 
	 * @param phoneNumber the Phone number object.
	 * @return JSONObject representing the Phone number.
	 */
	private static JSONObject phoneNumberToJSONObject(PhoneNumber phoneNumber){
		JSONObject result = new JSONObject();
		result.put(NUMBER, phoneNumber.getNumber());
		if(phoneNumber.getPrimary()!=null){
			result.put(PRIMARY, phoneNumber.getPrimary().toString());
		}
		if(phoneNumber.getMobile()!=null){
			result.put(MOBILE, phoneNumber.getMobile().toString());
		}
		return result;
	}

	/**
	 * Converts a list of phone numbers into a JSONArray for API use.
	 * @param emailAddresses the list of Email addresses objects.
	 * @return JSONArray representing the list of email addresses.
	 */
	private static JSONArray emailAdressesToJsonArray(List<EmailAddress> emailAddresses) {
		JSONArray result = new JSONArray();
		if(emailAddresses!=null){
			for(EmailAddress address: emailAddresses){
				result.put(emailAddressToJSONObject(address));
			}
		}
		return result;
	}
	
	/**
	 * Converts an Email Address object into a JSONObject for API use.
	 * @param address the Email address object.
	 * @return JSONObject representing the Email address.
	 */
	private static JSONObject emailAddressToJSONObject(EmailAddress address){
		JSONObject result = new JSONObject();
		result.put(EMAIL, address.getEmail());
		if(address.getPrimary()!=null){
			result.put(PRIMARY, address.getPrimary().toString());
		}
		return result;
	}

	/**
	 * Converts a JSONArray into a list of Client objects.
	 * @param clientsJson json representing the list of clients. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
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
	 * Converts a JSONObject from the API into a Client object.
	 * @param clientJson json representing the client. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
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
		if(clientJson.has(EMAIL_ADDRESSES)){
			result.setEmailAddresses(JSONArrayToEmailAddresses(clientJson.getJSONArray(EMAIL_ADDRESSES)));
		}
		if(clientJson.has(PHONE_NUMBERS)){
			result.setPhoneNumbers(JSONArrayToPhoneNumbers(clientJson.getJSONArray(PHONE_NUMBERS)));
		}
		if(clientJson.has(GENDER)){
			result.setGender(Gender.get(clientJson.getString(GENDER)));
		}
		if(clientJson.has(ADDRESS)){
			result.setAddress(AgendizeObjectHelper.jsonObjectToAddress(clientJson.getJSONObject(ADDRESS)));
		}
		if(clientJson.has(PICTURE)){
			result.setPicture(AgendizeObjectHelper.jsonObjectToPicture(clientJson.getJSONObject(PICTURE)));
		}
		if(clientJson.has(TAGS)){
			result.setTags(JSONArrayToTags(clientJson.getJSONArray(TAGS)));
		}
		return result;
	}

	/**
	 * Converts a JSONArray into a list of Tag objects.
	 * @param tagsJson json representing the list of tags. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @return JSONArray representing the list of tags.
	 */
	private static List<Tag> JSONArrayToTags(JSONArray tagsJson) {
		List<Tag> result = new ArrayList<Tag>();
		for(int j= 0; j<tagsJson.length(); j++){
    		result.add(jsonObjectToTag((JSONObject) tagsJson.get(j)));
    	}
		return result; 
	}

	/**
	 * Converts a JSONObject from the API into a Tag object.
	 * @param tagJson json representing the client. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @return the Tag object.
	 */
	private static Tag jsonObjectToTag(JSONObject tagJson) {
		Tag result = new Tag();
		result.setTag(tagJson.getString(TAG));
		result.setColor(tagJson.getString(COLOR));
		return result; 
	}

	/**
	 * Converts a JSONArray into a list of Phone numbers objects.
	 * @param phoneNumbersJson json representing the list of phone numbers. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @return the list of Phone Numbers objects.
	 */
	private static List<PhoneNumber> JSONArrayToPhoneNumbers(JSONArray phoneNumbersJson) {
		List<PhoneNumber> result = new ArrayList<PhoneNumber>();
		for(int j= 0; j<phoneNumbersJson.length(); j++){
    		result.add(jsonObjectToPhoneNumber((JSONObject) phoneNumbersJson.get(j)));
    	}
		return result;
	}

	/**
	 * Converts JSONObject from the API into a Phone number object.
	 * @param phoneNumberJson json representing the phone number. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @return the Phone number object.
	 */
	private static PhoneNumber jsonObjectToPhoneNumber(JSONObject phoneNumberJson) {
		PhoneNumber result = new PhoneNumber();
		result.setNumber(phoneNumberJson.getString(NUMBER));
		if(phoneNumberJson.has(PRIMARY)){
			result.setPrimary(phoneNumberJson.getBoolean(PRIMARY));
		}
		if(phoneNumberJson.has(MOBILE)){
			result.setMobile(phoneNumberJson.getBoolean(MOBILE));
		}
		return result; 
	}

	/**
	 * Converts a JSONArray into a list of Email addresses objects.
	 * @param emailAddressesJson json representing the list of phone numbers. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @return the list of Email Addresses objects.
	 */
	private static List<EmailAddress> JSONArrayToEmailAddresses(JSONArray emailAddressesJson) {
		List<EmailAddress> result = new ArrayList<EmailAddress>();
		for(int j= 0; j<emailAddressesJson.length(); j++){
    		result.add(jsonObjectToEmailAddress((JSONObject) emailAddressesJson.get(j)));
    	}
		return result; 
	}

	/**
	 * Converts JSONObject from the API into an Address object.
	 * @param emailAddressJson json representing the address. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/index.jsp">http://developers.agendize.com/v2/clients/reference/index.jsp</a>
	 * @return the Address object.
	 */
	private static EmailAddress jsonObjectToEmailAddress(JSONObject emailAddressJson) {
		EmailAddress result = new EmailAddress();
		result.setEmail(emailAddressJson.getString(EMAIL));
		if(emailAddressJson.has(PRIMARY)){
			result.setPrimary(emailAddressJson.getBoolean(PRIMARY));
		}
		return result; 
	}

	/**
	 * Converts a JSONArray into a list of Activity objects.
	 * @param activitiesJson json representing the list of activities. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/activities.jsp">httphttp://developers.agendize.com/v2/clients/reference/activities.jsp</a>
	 * @return the list of Activities objects.
	 */
	public static List<Activity> jsonArrayToActivitiesList(JSONArray activitiesJson) throws JSONException, AgendizeException {
		List<Activity> result = new ArrayList<Activity>();
		for(int i= 0; i<activitiesJson.length(); i++){
    		result.add(jsonObjectToActivity((JSONObject) activitiesJson.get(i)));
    	}
    	return result;
	}
	/**
	 * Converts JSONObject from the API into an Activity object.
	 * @param activityJson json representing the activity. See <a target="_blank" href="http://developers.agendize.com/v2/clients/reference/activities.jsp">http://developers.agendize.com/v2/clients/reference/activities.jsp</a>
	 * @return the Activity object.
	 */
	private static Activity jsonObjectToActivity(JSONObject activityJson) throws JSONException, AgendizeException {
		Activity result = new Activity();
		if(activityJson.has(ID)){
			result.setId(activityJson.getInt(ID));
		}
		if(activityJson.has(TYPE)){
			result.setType(activityJson.getString(TYPE));
		}
		if(activityJson.has(TITLE)){
			result.setTitle(Title.get(activityJson.getString(TITLE)));
		}
		if(activityJson.has(DATE)){
			result.setDate(AgendizeObjectHelper.jsonObjectToTime(activityJson.getJSONObject(DATE)));
		}
		if(activityJson.has("selfLink")){
			result.setSelfLink(activityJson.getString("selfLink"));
		}
		return result;
	}
}
