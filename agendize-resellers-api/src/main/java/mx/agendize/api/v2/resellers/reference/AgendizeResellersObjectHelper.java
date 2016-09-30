package mx.agendize.api.v2.resellers.reference;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.reference.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for management of Account objects as a reseller.
 * Contains methods to convert JSONObject and JSONArray to object and vice versa.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizeResellersObjectHelper {
	
	private static final String LOGO_URL = "logoURL";
	private static final String EMAIL_ADDRESS = "emailAddress";
	private static final String HEADER_BACKGROUND_COLOR = "headerBackgroundColor";
	private static final String WHITE_LABEL = "whiteLabel";
	private static final String TOOLS = "tools";
	private static final String RESELLER_ID = "resellerId";
	private static final String PASSWORD = "password";
	private static final String CLIENT_NAME = "clientName";
	private static final String NAME = "name";
	private static final String PROFILE = "profile";
	private static final String CREDIT = "credits";
	private static final String SSO_TOKEN = "ssoToken";
	private static final String CREATED = "created";
	private static final String PREFERENCES = "preferences";
	private static final String ORGANIZATION = "organization";
	private static final String ID = "id";
	private static final String EMAIL = "email";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String CURRENCY = "currency";
	private static final String LANGUAGE = "language";
	private static final String TIME_ZONE = "timeZone";

	/**
	 * Converts a JSONArray into a list of Account objects
	 * @param accountsJson json representing the list of accounts. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return the list of Account objects.
	 * @throws AgendizeException 
	 * @throws JSONException
	 */
	public static List<Account> jsonArrayToAccountList(JSONArray accountsJson) throws JSONException, AgendizeException {
		List<Account> result = new ArrayList<Account>();
		for(int j= 0; j<accountsJson.length(); j++){
    		result.add(jsonObjectToAccount((JSONObject) accountsJson.get(j)));
    	}
    	return result;
	}
	
	/**
	 * Converts a JSONObject from the API into an Account object
	 * @param accountJson json representing the account. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return The account object.
	 * @throws AgendizeException 
	 * @throws JSONException 
	 */
	public static Account jsonObjectToAccount(JSONObject accountJson) throws JSONException, AgendizeException {
		Account result = new Account();
		if(accountJson.has(ID)){
			result.setId(accountJson.getInt(ID));
		}
		if(accountJson.has(CLIENT_NAME)){
			result.setClientName(accountJson.getString(CLIENT_NAME));
		}
		if(accountJson.has(FIRST_NAME)){
			result.setFirstName(accountJson.getString(FIRST_NAME));
		}
		if(accountJson.has(LAST_NAME)){
			result.setLastName(accountJson.getString(LAST_NAME));
		}
		if(accountJson.has(EMAIL)){
			result.setEmail(accountJson.getString(EMAIL));
		}
		if(accountJson.has(PASSWORD)){
			result.setPassword(accountJson.getString(PASSWORD));
		}
		if(accountJson.has(RESELLER_ID)){
			result.setResellerId(accountJson.getString(RESELLER_ID));
		}
		if(accountJson.has(ORGANIZATION)){
			result.setOrganization(accountJson.getString(ORGANIZATION));
		}
		if(accountJson.has(PREFERENCES)){
			result.setPreferences(jsonObjectToPreferences(accountJson.getJSONObject(PREFERENCES)));
		}
		if(accountJson.has(CURRENCY)){
			result.setCurrency(Currency.getInstance(accountJson.getString(CURRENCY)));
		}
		if(accountJson.has(CREATED)){
			result.setCreated(mx.agendize.api.v2.reference.AgendizeObjectHelper.jsonObjectToTime(accountJson.getJSONObject(CREATED)));
		}
		if(accountJson.has(SSO_TOKEN)){
			result.setSsoToken(accountJson.getString(SSO_TOKEN));
		}
		if(accountJson.has("credit")){
			result.setCredit(accountJson.getInt("credit"));
		}
		if(accountJson.has(PROFILE)){
			result.setProfile(jsonObjectToProfile(accountJson.getJSONObject(PROFILE)));
		}
		if(accountJson.has(TOOLS)){
			result.setTools(JsonArrayToToolsList(accountJson.getJSONArray(TOOLS)));
		}
		if(accountJson.has(WHITE_LABEL)){
			result.setWhiteLabel(JsonObjectToWhiteLabelPreferences(accountJson.getJSONObject(WHITE_LABEL)));
		}
		return result;
	}

	/**
	 * Converts a JSONObject from the API into an WhiteLabel object.
	 * @param profileJson json representing the white label settings. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/">http://developers.agendize.com/v2/resellers/reference/accounts/</a>
	 * @return the WhiteLabelSettings object.
	 */
	private static WhiteLabelSettings JsonObjectToWhiteLabelPreferences(JSONObject whiteLabelJson) {
		WhiteLabelSettings result = new WhiteLabelSettings();
		result.setLogoURL(whiteLabelJson.getString(LOGO_URL));
		result.setEmailAddress(whiteLabelJson.getString(EMAIL_ADDRESS));
		result.setHeaderBackgroundColor(whiteLabelJson.getString(HEADER_BACKGROUND_COLOR));
		return result; 
	}

	/**
	 * Converts a JSONArray into a list of Tool objects
	 * @param jsonArray json representing the list of tools. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return the list of tools.
	 */
	private static List<String> JsonArrayToToolsList(JSONArray toolsJson) {
		List<String> result = new ArrayList<String>();
		for(int j= 0; j<toolsJson.length(); j++){
    		result.add((String) toolsJson.get(j));
    	}
		return result; 
	}

	/**
	 * Converts a JSONObject from the API into an Profile object.
	 * @param profileJson json representing the profile. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return the Profile object.
	 */
	private static Profile jsonObjectToProfile(JSONObject profileJson) {
		Profile result = new Profile();
		if(profileJson.has(ID)){
			result.setId(profileJson.getString(ID));
		}
		if(profileJson.has(NAME)){
			result.setName(profileJson.getString(NAME));
		}
		return result; 
	}

	/**
	 * Converts a JSONObject from the API into an Preferences (timezone, language) object
	 * @param preferencesJson
	 * @return
	 */
	private static Preferences jsonObjectToPreferences(JSONObject preferencesJson) {
		Preferences result = new Preferences();
		if(preferencesJson.has(TIME_ZONE)){
			result.setTimeZone(preferencesJson.getString(TIME_ZONE));
		}
		if(preferencesJson.has(LANGUAGE)){
			result.setLanguage(Language.get(preferencesJson.getString(LANGUAGE)));
		}
		return result; 
	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	public static JSONObject accountToJSONObject(Account account) {
		JSONObject result = new JSONObject();
		if(account.getId() != null){
			result.put(ID, account.getId());
		}
		if(account.getClientName() != null && !"".equals(account.getClientName())){
			result.put(CLIENT_NAME, account.getClientName());
		}
		if(account.getFirstName() != null && !"".equals(account.getFirstName())){
			result.put(FIRST_NAME, account.getFirstName());
		}
		if(account.getLastName() != null && !"".equals(account.getLastName())){
			result.put(LAST_NAME, account.getLastName());
		}
		if(account.getEmail() != null && !"".equals(account.getEmail())){
			result.put(EMAIL, account.getEmail());
		}
		if(account.getPassword() != null && !"".equals(account.getPassword())){
			result.put(PASSWORD, account.getPassword());
		}
		if(account.getCurrency() != null){
			result.put(CURRENCY, account.getCurrency().getCurrencyCode());
		}
		if(account.getResellerId() != null && !"".equals(account.getResellerId())){
			result.put(RESELLER_ID, account.getResellerId());
		} 
		if(account.getPreferences() != null){
			result.put(PREFERENCES, preferencesToJsonObject(account.getPreferences()));
		}
		if(account.getCredit() != null){
			result.put(CREDIT, account.getCredit());
		}
		if(account.getOrganization() != null && !"".equals(account.getOrganization())){
			result.put(ORGANIZATION, account.getOrganization());
		}
		if(account.getProfile() != null && !"".equals(account.getProfile())){
			result.put(PROFILE, profileToJsonObject(account.getProfile()));
		}
		if(account.getTools() != null && !"".equals(account.getTools())){
			result.put(TOOLS, toolsListToJSONArray(account.getTools()));
		}
		if(account.getWhiteLabel() != null){
			result.put(WHITE_LABEL, whiteLabelSettingsToJSONObject(account.getWhiteLabel()));
		}
		return result;
	}

	private static JSONObject whiteLabelSettingsToJSONObject(WhiteLabelSettings whiteLabel) {
		JSONObject result = new JSONObject(); 
		if(whiteLabel.getLogoURL() != null && !"".equals(whiteLabel.getLogoURL())){
			result.put(LOGO_URL, whiteLabel.getLogoURL());
		}
		if(whiteLabel.getEmailAddress() != null && !"".equals(whiteLabel.getEmailAddress())){
			result.put(EMAIL_ADDRESS, whiteLabel.getEmailAddress());
		}
		if(whiteLabel.getHeaderBackgroundColor() != null && !"".equals(whiteLabel.getHeaderBackgroundColor())){
			result.put(HEADER_BACKGROUND_COLOR, whiteLabel.getHeaderBackgroundColor());
		}
		return result; 
	}

	private static JSONArray toolsListToJSONArray(List<String> tools) {
		JSONArray result = new JSONArray();
		if(tools!=null){
			for(String tool: tools){
				result.put(tool);
			}
		}
		return result;
	}

	private static JSONObject preferencesToJsonObject(Preferences preferences) {
		JSONObject result = new JSONObject();
		if(preferences.getTimeZone() != null){
			result.put(TIME_ZONE, preferences.getTimeZone());
		}
		if(preferences.getLanguage() != null){
			result.put(LANGUAGE, preferences.getLanguage().getCode());
		}
		return result;
	}

	private static JSONObject profileToJsonObject(Profile profile) {
		JSONObject result = new JSONObject();
		if(profile.getId() != null){
			result.put(ID, profile.getId());
		}
		if(profile.getName() != null){
			result.put(NAME, profile.getName());
		}
		return result;
	}

	public static List<Account> jsonArrayToSimpleAccount(JSONArray accountsJson) {
		List<Account> result = new ArrayList<Account>();
		for(int j= 0; j<accountsJson.length(); j++){
    		result.add(jsonObjectToSimpleAccount((JSONObject) accountsJson.get(j)));
    	}
    	return result;
	}

	private static Account jsonObjectToSimpleAccount(JSONObject accountJson) {
		Account result = new Account();
		if(accountJson.has(ID)){
			result.setId(accountJson.getInt(ID));
		}
		if(accountJson.has(SSO_TOKEN)){
			result.setSsoToken(accountJson.getString(SSO_TOKEN));
		}
		return result;
	}
}
