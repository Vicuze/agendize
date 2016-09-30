package mx.agendize.api.v2.reference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.reference.Address.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for management of Agendize objects: Appointments, clients, companies, services, staff, settings, resources, widget.
 * Contacins method to convert JSONObject and JSONArray to object and vice versa.
 * Info about the JSON structure here <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/index.jsp">http://developers.agendize.com/v2/scheduling/reference/index.jsp</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizeObjectHelper {

	/** parameter names */
	private static final String DATA = "data";
	private static final String COUNTRY = "country";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String ZIP_CODE = "zipCode";
	private static final String OTHER_STREET = "otherStreet";
	private static final String STREET = "street";
	private static final String MIME_TYPE = "mimeType";
	private static final String URL = "url";
	private static final String LONG = "lng";
	private static final String LAT = "lat";
	private static final String DATE_TIME = "dateTime";
	private static final String PICTURE = "picture";
	private static final String TIME_ZONE = "timeZone";
	private static final String MOBILE_PHONE = "mobilePhone";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String DESCRIPTION = "description";
	private static final String ID = "id";
	
	/** Date format used in JSON */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * Converts a Time object into a JSONObject for API use.
	 * @param time The Time object.
	 * @return JSONObject representing the time.
	 * @throws JSONException
	 */
	public static JSONObject timeToJSONObject(Time time) throws JSONException {
		JSONObject result = new JSONObject();
		result.put(DATE_TIME, sdf.format(time.getDateTime()));
		result.put(TIME_ZONE, time.getTimeZone());
		return result;
	}

	/**
	 * Converts a JSONObject to a Time object.
	 * @param timeJson json representing the time. Exemple: 
	 * {
         "dateTime": "2014-08-22T12:30:00",
         "timeZone": "US/Eastern"
      } 
	 * @return The Time object.
	 * @throws AgendizeException 
	 */
	public static Time jsonObjectToTime(JSONObject timeJson) throws AgendizeException {
		try {
			sdf.parse(timeJson.getString(DATE_TIME));
		} catch (JSONException e) {
			/** Not possible, only happens if there is no string value for the key.*/
		} catch (ParseException e) {
			throw new AgendizeException("There was a problem in the parsing of the time.", e);
		}
		return new Time(timeJson.getString(TIME_ZONE), sdf.getCalendar().getTime());
	}
	
	public static JSONObject personToJSONObject(Person person) {
		JSONObject result = new JSONObject();
		if(person.getId() != null){
			result.put(ID, person.getId());
		}
		if(person.getFirstName() != null && !"".equals(person.getFirstName())){
			result.put(FIRST_NAME, person.getFirstName());
		}
		if(person.getLastName() != null && !"".equals(person.getLastName())){
			result.put(LAST_NAME, person.getLastName());
		}
		if(person.getEmail() != null && !"".equals(person.getEmail())){
			result.put(EMAIL, person.getEmail());
		}
		if(person.getPhone() != null && !"".equals(person.getPhone())){
			result.put(PHONE, person.getPhone());
		}
		if(person.getMobilePhone() != null && !"".equals(person.getMobilePhone())){
			result.put(MOBILE_PHONE, person.getMobilePhone());
		}
		if(person.getDescription() != null && !"".equals(person.getDescription())){
			result.put(DESCRIPTION, person.getDescription());
		}
		if(person.getPicture() != null){
			result.put(PICTURE, pictureToJSONObject(person.getPicture()));
		}
		return result;
	}

	/**
	 * Converts lat - long into a JSONObject for API use.
	 * @param geolocationLat latitude of the geolocation.
	 * @param geolocationLong longitude of the geolocation. 
	 * @return JSONObject representing the geolocation.
	 * @throws JSONException
	 */
	public static JSONObject gelocationToJSONObject(String geolocationLat, String geolocationLong) throws JSONException {
		JSONObject result = new JSONObject();
		if(geolocationLat != null){
			result.put(LAT, geolocationLat);
		}
		if(geolocationLong != null){
			result.put(LONG, geolocationLong);
		}
		return result;
	}

	/**
	 * Converts a Picture (url - mimeType) object into a JSONObject for API use.
	 * @param picture the Picture object. Only the "data" attribute will be put in the JSON, because URL and mime type are read only attributes.
	 * @return JSONObject representing the picture
	 * @throws JSONException
	 */
	public static JSONObject pictureToJSONObject(Picture picture) throws JSONException {
		JSONObject result = null;
		if(picture != null && picture.getData() != null && !"".equals(picture.getData())){
			result = new JSONObject();
			result.put(DATA, picture.getData());
		}
		return result;
	}

	/**
	 * Converts an Address object into a JSONObject for API use.
	 * @param address The Address object
	 * @return the JSONObject representing the Address. 
	 * @throws JSONException
	 */
	public static JSONObject addressToJSONObject(Address address) throws JSONException {
		JSONObject result = new JSONObject();
		if(address != null){
			if(address.getStreet()!=null && !"".equals(address.getStreet())){
				result.put(STREET, address.getStreet());
			}
			if(address.getOtherStreet()!=null && !"".equals(address.getOtherStreet())){
				result.put(OTHER_STREET, address.getOtherStreet());
			}
			if(address.getStreet()!=null && !"".equals(address.getStreet())){
				result.put(ZIP_CODE, address.getZipCode());
			}
			if(address.getZipCode()!=null && !"".equals(address.getZipCode())){
				result.put(STATE, address.getState());
			}
			if(address.getCity()!=null && !"".equals(address.getCity())){
				result.put(CITY, address.getCity());
			}
			if(address.getCountry()!=null && !"".equals(address.getCountry())){
				result.put(COUNTRY, address.getCountry().getCode());
			}
		}
		return result;
	}
	
	/**
	 * Converts a JSONObject to a Picture object.
	 * @param pictureJson json representing the Picture. Example: 
	 * 	<pre>
	 * {@code
	 * 	{
	 * 		"mimeType":"image/png",
	 * 		"url":"http://www.agendize.com/img/logo_agendize.png"
	 * 	}
	 * }
	 * </pre>
	 * @return the Picture object.
	 */
	public static Picture jsonObjectToPicture(JSONObject pictureJson) {
		Picture result = null;
		if(pictureJson.has(URL) && !"".equals(pictureJson.getString(URL)) &&
				pictureJson.has(MIME_TYPE) && !"".equals(pictureJson.getString(MIME_TYPE))){
			result = new Picture();
			if(pictureJson.has(URL)){
				result.setUrl(pictureJson.getString(URL));
			}
			if(pictureJson.has(MIME_TYPE)){
				result.setMimeType(pictureJson.getString(MIME_TYPE));
			}
		}
		return result;
	}

	/**
	 * Converts a JSONObject to a Address object.
	 * @param addressJson json representing the Address. Example: 
	 * 	<pre>
	 * {@code
	 * 	{
	 * 		"zipCode":"06600",
	 * 		"otherStreet":"Col. Juárez, Del. Cuauhtémoc",
	 * 		"street":"Av. Reforma 243, Oficina 1857",
	 * 		"state":"Distrito Federal",
	 * 		"country":"MX",
	 * 		"city":"D.F."
	 * 	}
	 * }
	 * </pre>
	 * @return the Address object.
	 */
	
	public static Address jsonObjectToAddress(JSONObject addressJson) {
		Address result = new Address();
		if(addressJson.has(STREET)){
			result.setStreet(addressJson.getString(STREET));
		}
		if(addressJson.has(OTHER_STREET)){
			result.setOtherStreet(addressJson.getString(OTHER_STREET));
		}
		if(addressJson.has(ZIP_CODE)){
			result.setZipCode(addressJson.getString(ZIP_CODE));
		}
		if(addressJson.has(STATE)){
			result.setState(addressJson.getString(STATE));
		}
		if(addressJson.has(CITY)){
			result.setCity(addressJson.getString(CITY));
		}
		if(addressJson.has(COUNTRY)){
			result.setCountry(Country.get(addressJson.getString(COUNTRY)));
		}
		return result;
	}

	public static List<String> jsonArrayToStringList(JSONArray stringsJson) {
		List<String> result = new ArrayList<String>();
		for(int j= 0; j<stringsJson.length(); j++){
    		result.add(stringsJson.getString(j));
    	}
    	return result;
	}

	public static JSONArray stringListToJSONArray(List<String> strings) {
		JSONArray result = new JSONArray();
		if(strings!=null){
			for(String s: strings){
				result.put(new JSONObject(s));
			}
		}
		return result;
	}

}
