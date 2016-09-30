package mx.agendize.api.v2.platform.reference;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import mx.agendize.api.v2.reference.Language;

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
public class AgendizePlatformObjectHelper {

	/** parameter names */
	private static final String TOOLS = "tools";
	private static final String DOMAIN = "domain";
	private static final String CURRENCY = "currency";
	private static final String STATUS = "status";
	private static final String LANGUAGE = "language";
	private static final String TIME_ZONE = "timeZone";
	private static final String EMAIL = "email";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String ID = "id";
	
	/**
	 * Converts a JSONArray into a list of Account objects
	 * @param accountsJson json representing the list of accounts. See <a target="_blank" href="http://developers.agendize.com/v2/platform/reference/accounts/index.jsp">http://developers.agendize.com/v2/platform/reference/accounts/index.jsp</a>
	 * @return the list of Account objects.
	 * @throws JSONException
	 */
	public static List<Account> jsonArrayToAccountList(JSONArray accountsJson) {
		List<Account> result = new ArrayList<Account>();
		for(int j= 0; j<accountsJson.length(); j++){
    		result.add(jsonObjectToAccount((JSONObject) accountsJson.get(j)));
    	}
    	return result;
	}
	
	/**
	 * Converts a JSONObject from the API into an Account object
	 * @param accountJson json representing the account. See <a target="_blank" href="http://developers.agendize.com/v2/platform/reference/accounts/index.jsp">http://developers.agendize.com/v2/platform/reference/accounts/index.jsp</a>
	 * @return The account object.
	 */
	public static Account jsonObjectToAccount(JSONObject accountJson) {
		Account result = new Account();
		if(accountJson.has(ID)){
			result.setId(accountJson.getInt(ID));
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
		if(accountJson.has(STATUS)){
			result.setStatus(accountJson.getString(STATUS));
		}
		if(accountJson.has(CURRENCY)){
			result.setCurrency(Currency.getInstance(accountJson.getString(CURRENCY)));
		}
		if(accountJson.has(DOMAIN)){
			result.setDomain(accountJson.getString(DOMAIN));
		}
		if(accountJson.has(TOOLS)){
			result.setTools(accountJson.getString(TOOLS));
		}
		if(accountJson.has(LANGUAGE)){
			result.setLanguage(Language.get(accountJson.getString(LANGUAGE)));
		}
		if(accountJson.has(TIME_ZONE)){
			result.setTimeZone(TimeZone.getTimeZone(accountJson.getString(TIME_ZONE)));
		}
		return result;
	}

	public static JSONObject accountToJSONObject(Account account) {
		JSONObject result = new JSONObject();
		if(account.getId() != null){
			result.put(ID, account.getId());
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
		if(account.getStatus() != null && !"".equals(account.getStatus())){
			result.put(STATUS, account.getStatus());
		}
		if(account.getCurrency() != null && !"".equals(account.getCurrency())){
			result.put(CURRENCY, account.getCurrency().getCurrencyCode());
		}
		if(account.getDomain() != null && !"".equals(account.getDomain())){
			result.put(DOMAIN, account.getDomain());
		}
		if(account.getTools() != null && !"".equals(account.getTools())){
			result.put(TOOLS, account.getTools());
		}
		if(account.getLanguage() != null){
			result.put(LANGUAGE, account.getLanguage().getCode());
		}
		if(account.getTimeZone() != null){
			result.put(TIME_ZONE, account.getTimeZone().getDisplayName(Locale.ENGLISH));
		}
		return result;
	}
}
