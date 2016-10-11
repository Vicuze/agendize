package mx.agendize.api;



/**
 * Abstract class, must be instantiated with API key and SSO token.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public abstract class AgendizeApiManager {
	
	/** API key value */
	protected String apiKey;
	/** Token. */
	protected String token;

	/** true if you test on az2, false if api */
	protected static final boolean az2 = true; 
	
	/** api.agendize.com or az2.agendize.com */
	protected static final String API_URL = "http://"+(az2?"az2":"api") +".agendize.com/api/";
	
	/** key of the token property. */
	protected static final String TOKEN = "token";
	/** key of the Api key property in API v1. */
	protected static final String API_KEY_V1 = "key";
	/** key of the Api key property in API v2. */
	protected static final String API_KEY_V2 = "apiKey";
	
	/** Action API URL */
	protected static final String ACTION_API_URL = API_URL + "1.0/action";
	/** Scheduling API URL*/
	protected static final String SCHEDULING_API_URL = API_URL + "2.0/scheduling/";
	/** Companies Scheduling API URL*/
	protected static final String SCHEDULING_API_COMPANIES_URL = SCHEDULING_API_URL+"companies";
	/** Clients API URL*/
	protected static final String SCHEDULING_API_CLIENTS_URL = SCHEDULING_API_URL+"clients";
	/** Platform API URL*/
	protected static final String ACCOUNTS_API_URL = API_URL + "2.0/accounts";
	/** Clients API URL */
	protected static final String CLIENTS_API_URL = API_URL + "2.0/clients";
	/** Activities API URL */
	protected static final String ACTIVITIES_API_URL = API_URL + "2.0/activities";
	/** Reseller accounts API URL*/
	protected static final String RESELLER_ACCOUNTS_API_URL = API_URL + "2.0/resellers/accounts";
	/** Click to Call buttons API URL */
	protected static final String CALLS_API_URL = API_URL + "2.0/calls";
	/** Calls API URL */
	protected static final String CLICK_TO_CALL_BUTTONS_API_URL = CALLS_API_URL + "/buttons";
	/** Queues API URL */
	protected static final String QUEUES_API_URL = API_URL + "2.0/queues";
	/** Forms API URL */
	protected static final String FORMS_API_URL = API_URL + "2.0/forms";
	/** Emails API URL */
	protected static final String EMAILS_API_URL = API_URL + "2.0/messages/emails";
	
	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 */
	public AgendizeApiManager(String apiKey, String token) {
		super();
		this.apiKey = apiKey;
		this.token = token;
	}
}
