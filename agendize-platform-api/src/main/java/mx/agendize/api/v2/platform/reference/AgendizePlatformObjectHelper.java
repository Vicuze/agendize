package mx.agendize.api.v2.platform.reference;

import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObjectHelper;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class for management of Agendize platform API objects: .
 * Contains method to convert JSONObject and JSONArray to object and vice versa.
 * Info about the JSON structure here <a target="_blank" href="http://developers.agendize.com/v2/scheduling/reference/index.jsp">http://developers.agendize.com/v2/scheduling/reference/index.jsp</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizePlatformObjectHelper {

	/** parameter names */
	private static final String USER_NAME = "userName";
	private static final String TARGET = "target";
	private static final String ROLE = "role";
	private static final String SELF_ACCOUNT = "selfAccount";
	private static final String NAME = "name";
	private static final String EMAIL_ADDRESS = "emailAddress";
	private static final String ACLS = "acls";
	private static final String ACCOUNT_STATUS = "accountStatus";
	private static final String PROFILE_SETTINGS = "profileSettings";
	private static final String RIGHTS = "rights";
	private static final String PREFERENCES = "preferences";
	private static final String SSO_TOKEN = "ssoToken";
	private static final String EMAIL = "email";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String ID = "id";
	
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
		if(accountJson.has(USER_NAME)){
			result.setUserName(accountJson.getString(USER_NAME));
		}
		if(accountJson.has(SSO_TOKEN)){
			result.setSsoToken(accountJson.getString(SSO_TOKEN));
		}
		if(accountJson.has(PROFILE_SETTINGS)){
			result.setProfileSettings(jsonObjectToProfileSettings(accountJson.getJSONObject(PROFILE_SETTINGS)));
		}
		if(accountJson.has(RIGHTS)){
			result.setRights(jsonArrayToRightsList(accountJson.getJSONArray(RIGHTS)));
		}
		return result;
	}

	/**
	 * Converts a JSONArray in a list of Right objects
	 * @param rightsJson json representing the rights list. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return The list of Right objects.
	 */
	private static List<Right> jsonArrayToRightsList(JSONArray rightsJson) {
		List<Right> result = new ArrayList<Right>(); 
		for(int j= 0; j<rightsJson.length(); j++){
    		result.add(jsonObjectToRight((JSONObject) rightsJson.get(j)));
    	}
		return result;
	}

	/**
	 * Converts a JSONObject from the API to a Right object 
	 * @param rightJson json representing the right. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return the Right object
	 */
	private static Right jsonObjectToRight(JSONObject rightJson) {
		Right result = new Right(); 
		if(rightJson.has(ACCOUNT_STATUS)){
			result.setAccountStatus(rightJson.getString(ACCOUNT_STATUS));
		}
		if(rightJson.has(ACLS)){
			result.setAcls(jsonArrayToAclList(rightJson.getJSONArray(ACLS)));
		}
		if(rightJson.has(EMAIL_ADDRESS)){
			result.setEmailAddress(rightJson.getString(EMAIL_ADDRESS));
		}
		if(rightJson.has(NAME)){
			result.setName(rightJson.getString(NAME));
		}
		if(rightJson.has(SELF_ACCOUNT)){
			result.setSelfAccount(rightJson.getBoolean(SELF_ACCOUNT));
		}
		return result; 
	}

	/**
	 * Converts a JSONArray in a list of Acl objects
	 * @param aclJson json representing the acl list. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return The list of Acl objects.
	 */
	private static List<Acl> jsonArrayToAclList(JSONArray aclJson) {
		List<Acl> result = new ArrayList<Acl>();
		for(int j= 0; j<aclJson.length(); j++){
    		result.add(jsonObjectToAcl((JSONObject) aclJson.get(j)));
    	}
		return null;
	}

	/**
	 * Converts a JSONObject from the API to an Acl object 
	 * @param aclJson json representing the acl. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return the Acl object
	 */
	private static Acl jsonObjectToAcl(JSONObject aclJson) {
		Acl result = new Acl(); 
		if(aclJson.has(ROLE)){
			result.setRole(aclJson.getString(ROLE));
		}
		if(aclJson.has(TARGET)){
			result.setRole(aclJson.getString(TARGET));
		}
		return result;
	}

	/**
	 * Converts a JSONObject from the API to an ProfileSettings object 
	 * @param profileSettingsJson json representing the profile settings. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp">http://developers.agendize.com/v2/resellers/reference/accounts/index.jsp</a>
	 * @return the ProfileSettings object
	 */
	private static ProfileSettings jsonObjectToProfileSettings(JSONObject profileSettingsJson) {
		ProfileSettings result = new ProfileSettings(); 
		result.setPreferences(AgendizeObjectHelper.jsonObjectToPreferences(profileSettingsJson.getJSONObject(PREFERENCES)));
		return result;
	}

	/**
	 * Converts an Account object into a JSONObject for API use.
	 * @param account The account object.
	 * @return The JSONObject representing the account. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts">http://developers.agendize.com/v2/resellers/reference/accounts</a>
	 */
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
		if(account.getUserName() != null && !"".equals(account.getUserName())){
			result.put(USER_NAME, account.getEmail());
		}
		if(account.getSsoToken() != null && !"".equals(account.getSsoToken())){
			result.put(SSO_TOKEN, account.getSsoToken());
		}
		if(account.getProfileSettings() != null){
			result.put(PROFILE_SETTINGS, profileSettingsToJSONObject(account.getProfileSettings()));
		}
		if(account.getRights() != null){
			result.put(RIGHTS, rightListToJSONArray(account.getRights()));
		}
		return result;
	}

	/**
	 * Converts a list of Right objects into a JSONArray for API use.
	 * @param rights the list of Right objects.
	 * @return JSONArray representing the list of rights
	 */
	private static JSONArray rightListToJSONArray(List<Right> rights) {
		JSONArray result = new JSONArray();
		if(rights != null){
			for(Right right: rights){
				result.put(RightToJsonObject(right)); 	
			}
		}
		return result;
	}

	/**
	 * Converts a Right object into a JSONObject for API use.
	 * @param profileSettings The Right object.
	 * @return The JSONObject representing the Right. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts">http://developers.agendize.com/v2/resellers/reference/accounts</a>
	 */
	private static JSONObject RightToJsonObject(Right right) {
		JSONObject result = new JSONObject(); 
		if(right.getAccountStatus() != null && !"".equals(right.getAccountStatus())){
			result.put(ACCOUNT_STATUS, right.getAccountStatus());
		}
		if(right.getEmailAddress() != null && !"".equals(right.getEmailAddress())){
			result.put(EMAIL_ADDRESS, right.getEmailAddress());
		}
		if(right.getName() != null && !"".equals(right.getName())){
			result.put(NAME, right.getName());
		}
		result.put(SELF_ACCOUNT, right.isSelfAccount());
		if(right.getAcls() != null && !"".equals(right.getAcls())){
			result.put(ACLS, aclListToJSONArray(right.getAcls()));
		}
		return result;
	}

	/**
	 * Converts a list of Acl objects into a JSONArray for API use.
	 * @param acls the list of Acl objects.
	 * @return JSONArray representing the list of Acl
	 */
	private static JSONArray aclListToJSONArray(List<Acl> acls) {
		JSONArray result = new JSONArray();
		if(acls != null){
			for(Acl acl: acls){
				result.put(aclToJsonObject(acl)); 	
			}
		}
		return result;
	}

	/**
	 * Converts a Acl object into a JSONObject for API use.
	 * @param acl The Acl object.
	 * @return The JSONObject representing the Acl. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts">http://developers.agendize.com/v2/resellers/reference/accounts</a>
	 */
	private static JSONObject aclToJsonObject(Acl acl) {
		JSONObject result = new JSONObject(); 
		if(acl.getRole() != null){
			result.put(ROLE, acl.getRole());
		}
		if(acl.getTarget() != null && !"".equals(acl.getTarget())){
			result.put(TARGET, acl.getTarget().toString());
		}
		return result;
	}

	/**
	 * Converts a ProfileSettings object into a JSONObject for API use.
	 * @param profileSettings The ProfileSettings object.
	 * @return The JSONObject representing the Profile / Settings. See <a target="_blank" href="http://developers.agendize.com/v2/resellers/reference/accounts">http://developers.agendize.com/v2/resellers/reference/accounts</a>
	 */
	private static JSONObject profileSettingsToJSONObject(ProfileSettings profileSettings) {
		JSONObject result = new JSONObject(); 
		if (profileSettings.getPreferences() != null){
			result.put(PREFERENCES, AgendizeObjectHelper.preferencesToJsonObject(profileSettings.getPreferences()));
		}
		return result;
	}
}
