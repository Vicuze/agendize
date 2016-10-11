package mx.agendize.api.data;

import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.data.objects.AgendizeAccount;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for Agendize Account management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AccountManager extends AgendizeApiManager{

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public AccountManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}
	
	/**
	 * Get details of an existing account
	 * @return The AgendizeAccount object.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public AgendizeAccount getAccountDetails() throws IOException, ParserConfigurationException, SAXException {
		
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest(DataApiHelper.ACCOUNT_SCOPE, properties);
		NodeList nList = DataApiHelper.stringToNodeList(response);
		NodeList nl = nList.item(0).getChildNodes();
		return new AgendizeAccount(nl);
	}
}
