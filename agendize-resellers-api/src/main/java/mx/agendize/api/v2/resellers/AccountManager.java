package mx.agendize.api.v2.resellers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.reference.AgendizeObjectHelper;
import mx.agendize.api.v2.resellers.reference.Account;
import mx.agendize.api.v2.resellers.reference.AgendizeResellersObjectHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/** Class to manage account accounts as a reseller. */
public class AccountManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(AccountManager.class);

	private static final String SEARCH = "search";
	
	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public AccountManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/**
	 * Search of clients accounts.
	 * @param search Search accounts by first name, last name, phone number and email address. Incompleted value can by used.
	 * @return a list of Account objects resulting from the search.
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public List<Account> getAccounts(String search) throws IOException, AgendizeException{
		logger.info("getAccounts. search = \"" + search+"\".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		if(search != null && !"".equals(search.trim())){
			properties.put(SEARCH, search.trim());
		}
        String accountsString = APIUtils.getRequest(RESELLER_ACCOUNTS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(accountsString);
		List<Account> result = AgendizeResellersObjectHelper.jsonArrayToAccountList(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " accounts found.");
		return result;
	}

	/**
	 * Search of clients accounts.
	 * @return a list of Account objects resulting from the search.
	 * @throws IOException
	 * @throws AgendizeException
	 */
	public List<Account> fastGetAccounts() throws IOException, AgendizeException{
		logger.info("fastGetAccounts.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String accountsString = APIUtils.getRequest(RESELLER_ACCOUNTS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(accountsString);
		List<Account> result = AgendizeResellersObjectHelper.jsonArrayToSimpleAccount(jsonObject.getJSONArray("items"));
		logger.info(result.size() + " accounts found.");
		return result;
	}
	
	/**
	 * Get an account by its id. Will return null if there is no account with this id.
	 * @param id id of the account to look for.
	 * @return Account object.
	 * @throws AgendizeException 
	 * @throws JSONException 
	 * @throws IOException
	 */
	public Account get(int id) throws JSONException, AgendizeException {
		logger.info("Get account with id = " + id + ".");
		Account result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String accountString = null;
		try {
			accountString = APIUtils.getRequest(RESELLER_ACCOUNTS_API_URL + "/" + id, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(accountString!=null){
	        JSONObject jsonObject = new JSONObject(accountString);
	        if(jsonObject.length()>=0){
	            result = AgendizeResellersObjectHelper.jsonObjectToAccount(jsonObject);
	        }
		}
		if(result != null){
			logger.info("Account found.");
		}
		return result;
	}
	
	/**
	 * Creates a account. 
	 * @param account to create.
	 * @return the updated account.
	 * @throws AgendizeException 
	 */
	public Account create(Account account) throws AgendizeException{
		logger.info("Create new account with email " + account.getEmail() + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeResellersObjectHelper.accountToJSONObject(account).toString();
		String accountString = APIUtils.postRequest(RESELLER_ACCOUNTS_API_URL, json, properties);
		if(accountString == null || "".equals(accountString)){
			logger.error("Error in account creation: Response is empty.");
			return null; 
		}
		JSONObject jsonObject = new JSONObject(accountString);
		//TODO duplicate this in other object creations
		if(jsonObject.has("error")){
			JSONObject errorJson = jsonObject.getJSONObject("error");
			logger.error("Error in account creation: " + errorJson.getInt("code")+" "+errorJson.getString("reason")+" - "+errorJson.getString("message"));
			return null; 
		}
		Account result = AgendizeResellersObjectHelper.jsonObjectToAccount(jsonObject);
		logger.info("Account created succesfully. id = " + result.getId() + "."); 
        return result;
	}

	/**
	 * Import of a account list.
	 * @param accounts Account list to import.
	 * @return List of created accounts (with ids).
	 * @throws AgendizeException 
	 */
	public List<Account> importCLients(List<Account> accounts) throws AgendizeException{
		logger.info("Import of a account list.");
		List<Account> result = null;
		if(accounts != null){
			logger.info("import a list of " + accounts.size()+" accounts.");
			result = new ArrayList<Account>();
			for(Account c: accounts){
				result.add(create(c));
			}
		}
		return result;
	}
	
	/**
	 * Deletes a account.
	 * @param accountId Identifier of account to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int accountId) throws AgendizeException {
		logger.info("Delete account with id = " + accountId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(RESELLER_ACCOUNTS_API_URL+"/"+accountId, properties);
	}
	
	/**
	 * Updates an account.
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
		String urlString = RESELLER_ACCOUNTS_API_URL + "/" + account.getId();
		String json = AgendizeResellersObjectHelper.accountToJSONObject(account).toString();
		String accountString = APIUtils.putRequest(urlString, json, properties);
		JSONObject accountJson =  new JSONObject(accountString);
		return AgendizeResellersObjectHelper.jsonObjectToAccount(accountJson);
	}
	
}
