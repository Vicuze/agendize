package mx.agendize.api.v2.platform;

import java.io.IOException; 
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.platform.reference.Account;
import mx.agendize.api.v2.platform.reference.AgendizePlatformObjectHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for Account management. /!\ DOES NOT WORK YET!
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AccountManager extends AgendizeApiManager{
	
	static final Logger logger = LogManager.getLogger(AccountManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public AccountManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Gives the list of accounts for an agendize master account
	 * @return The list of company objects.
	 * @throws IOException
	 */
	public List<Account> getAccounts() throws IOException  {
		logger.info("getAccounts.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String accountsString = APIUtils.getRequest(PLATFORM_ACCOUNTS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(accountsString);
		List<Account> result = AgendizePlatformObjectHelper.jsonArrayToAccountList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " accounts found.");
		return result;
	}

	/**
	 * Gets an account by its id
	 * @param accountId Account identifier.
	 * @return Account for id. null if not found. 
	 * @throws AgendizeException
	 */
	public Account get(int accountId) throws AgendizeException{
		logger.info("getAccount with id = " + accountId + ".");
		Account result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String accountString = null;
		try {
			accountString = APIUtils.getRequest(PLATFORM_ACCOUNTS_API_URL+ "/" + accountId, properties);
		} catch (IOException e) {
			throw new AgendizeException("There was a problem in the request for account " + accountId, e);
		}
		if(accountString!=null){
			result = AgendizePlatformObjectHelper.jsonObjectToAccount(new JSONObject(accountString));
		}
		return result;
	}
	
	/**
	 * Creates an account. 
	 * @param account the Account object.
	 * @return The account created.
	 * @throws JSONException
	 * @throws ParseException
	 * @throws AgendizeException 
	 */
	public Account create(Account account) throws AgendizeException   {
		logger.info("Create new account with email " + account.getEmail() + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizePlatformObjectHelper.accountToJSONObject(account).toString();
		String accountString = APIUtils.postRequest(PLATFORM_ACCOUNTS_API_URL, json, properties);
		JSONObject jsonObject = new JSONObject(accountString);
		//TODO duplicate this in other object creations
		if(jsonObject.has("error")){
			JSONObject errorJson = jsonObject.getJSONObject("error");
			logger.error("Error in account creation: " + errorJson.getInt("code")+" "+errorJson.getString("reason")+" - "+errorJson.getString("message"));
		}
		Account result = AgendizePlatformObjectHelper.jsonObjectToAccount(jsonObject);
		logger.info("Account created succesfully. id = " + result.getId() + ".");
        return result;
	}
	
	/**
	 * Deletes an account.
	 * @param accountId Account identifier.
	 * @throws AgendizeException 
	 */
	public void delete(int accountId) throws AgendizeException {
		logger.info("Delete account with id = " + accountId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(PLATFORM_ACCOUNTS_API_URL + "/" + accountId, properties);
	}
	
	/**
	 * updates an account.
	 * @param account Account. Must have an id.
	 * @return updated account.
	 * @throws AgendizeException
	 */
	public Account update(Account account) throws AgendizeException {
		logger.info("Update account with id = " + account.getId() + ".");
		if(account.getId()==null){
			throw new AgendizeException("The account object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = PLATFORM_ACCOUNTS_API_URL + "/" + account.getId();
		String json = AgendizePlatformObjectHelper.accountToJSONObject(account).toString();
		String accountString = APIUtils.putRequest(urlString, json, properties);
		JSONObject accountJson =  new JSONObject(accountString);
		return AgendizePlatformObjectHelper.jsonObjectToAccount(accountJson);
	}
}
