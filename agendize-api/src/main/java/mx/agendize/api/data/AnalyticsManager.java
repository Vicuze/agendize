package mx.agendize.api.data;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.TimeHelper;
import mx.agendize.api.data.objects.CallTrackingDetails;
import mx.agendize.api.data.objects.ChatSession;
import mx.agendize.api.data.objects.FormResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for Analytics management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AnalyticsManager extends AgendizeApiManager{
	
	static final Logger logger = LogManager.getLogger(AnalyticsManager.class);
	
	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://app.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public AnalyticsManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/** Parameters names */
	private static final String PHONE = "phone";
	private static final String ID = "id";

	/**
	 * Details of Call Tracking
	 * @param startDate Start of Date Range. Format: yyyy-MM-dd. Required.
	 * @param endDate End of Date Range. Format: yyyy-MM-dd. Required.
	 * @param phone Call tracking phone number, caller phone number or called phone number. Can be null
	 * @return List of call tracking details.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws AgendizeException if one of the dates is not valid.
	 */
	public List<CallTrackingDetails> getCallTrackingDetails(String startDate, String endDate, String phone) 
			throws IOException, AgendizeException, SAXException, ParserConfigurationException{
		TimeHelper.checkDate(startDate); 
		TimeHelper.checkDate(endDate); 
		
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		if(phone!=null && !phone.equals("")){
			properties.put(PHONE, phone); 
		}
		
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest(startDate, endDate, DataApiHelper.CALL_TRACKING_DETAILS_SCOPE, properties);
		NodeList nl = DataApiHelper.stringToNodeList(response);

		List<CallTrackingDetails> result = new ArrayList<CallTrackingDetails>(); //Liste des appels 
		for (int i = 0; i < nl.getLength(); i++) {
			Node nNode = nl.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				result.add(new CallTrackingDetails(nNode.getChildNodes()));
			}
		}
		return result;
	}
	
	/**
	 * Returns the chat history
	 * @param startDate Start of Date Range. Format: yyyy-MM-dd. Required.
	 * @param endDate End of Date Range. Format: yyyy-MM-dd. Required.
	 * @param buttonId Specific Chat Button ID. Can be null.
	 * @return List of ChatSession objects representing all the chat sessions in the time range and for the button ID if specified.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public List<ChatSession> getChatHistory(String startDate, String endDate, String buttonId) throws IOException, SAXException, ParserConfigurationException{
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		if(buttonId!=null && !buttonId.equals("")){
			properties.put(ID, buttonId); 
		}
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest(startDate, endDate, DataApiHelper.CHAT_HISTORY_SCOPE, properties);
		NodeList nl = DataApiHelper.stringToNodeList(response);

		List<ChatSession> result = new ArrayList<ChatSession>(); //Liste des sessions de chat  
		for (int i = 0; i < nl.getLength(); i++) {
			Node nNode = nl.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				result.add(new ChatSession(nNode.getChildNodes()));
			}
		}
		return result;
	}
	
	/**
	 * Form results 
	 * @param startDate Start of Date Range. Format: yyyy-MM-dd. Required.
	 * @param endDate End of Date Range. Format: yyyy-MM-dd. Required.
	 * @param id Specific Form ID. Can be null.
	 * @return List of form results
	 * @throws IOException
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * @throws ParseException 
	 */
	public List<FormResult> getFormResults(String startDate, String endDate, String id) throws IOException, SAXException, ParserConfigurationException, ParseException{
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		if(id!=null && !id.equals("")){
			properties.put(ID, id); 
		}
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest(startDate, endDate, DataApiHelper.FORM_RESULTS_SCOPE, properties);
		NodeList nl = DataApiHelper.stringToNodeList(response);
		List<FormResult> result = new ArrayList<FormResult>(); //Liste des résultats de formulaire 
		for (int i = 0; i < nl.getLength(); i++) {
			Node nNode = nl.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				result.add(new FormResult(nNode.getChildNodes()));
			}
		}
		return result;
	}
}
