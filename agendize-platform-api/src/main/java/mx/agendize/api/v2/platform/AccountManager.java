package mx.agendize.api.v2.platform;

import java.io.IOException;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.v2.platform.reference.Account;
import mx.agendize.api.v2.platform.reference.AgendizePlatformObjectHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 * Class for Account management. Only to use to manage own user account. Does not work yet. TODO adapt it to API and test.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AccountManager extends AgendizeApiManager{
	
	static final Logger logger = LogManager.getLogger(AccountManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public AccountManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Gives the current account.
	 * @return The Account object.
	 * @throws IOException
	 */
	public Account getAccount() throws IOException  {
		logger.info("getAccount.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String accountsString = APIUtils.getRequest(ACCOUNTS_API_URL, properties);
		JSONObject jsonObject = new JSONObject(accountsString);
		Account result = AgendizePlatformObjectHelper.jsonObjectToAccount(jsonObject);
		logger.info("Account "+ result.getUserName() +" found.");
		return result;
	}
}
