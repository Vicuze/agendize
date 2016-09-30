package mx.agendize.api.action;

import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class calling the agendize action api.
 * <a target="_blank" href="http://developers.agendize.com/en/p/action">http://developers.agendize.com/en/p/action</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ActionManager extends AgendizeApiManager{

	static final Logger logger = LogManager.getLogger(ActionManager.class);

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 */
	public ActionManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/** Parameters names */
	private static final String FORMAT = "format";
	private static final String PIM = "pim";
	private static final String DELAY = "delay";
	private static final String PHONE_NUMBER = "phone";
	private static final String BUTTON_ID = "id";
	private static final String SENDER_NAME = "sender-name";
	private static final String EMAIL_FROM = "email-from";
	private static final String EMAIL = "email";
	private static final String MESSAGE = "message";
	private static final String MESSENGER = "messenger";
	private static final String CONTACT = "contact";
	private static final String MEDIA = "media";
	
	/**
	 * Click to Call method.
	 * @param buttonId Button ID.
	 * @param phone User phone number, in international format (+xxxxxxxxxxxxx).
	 * @param delay Delay period before initiating call, in minutes. (0 to initiate the call right away).
	 */
	public void clickToCall (String buttonId, String phone, Integer delay) {
		logger.info("clickToCall. buttonId = " + buttonId+"; phone = " + phone + "; delay = " + delay);
		Properties properties = new Properties();
		properties.put(MEDIA, "call");
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		properties.put(BUTTON_ID, buttonId);
		properties.put(PHONE_NUMBER, phone);
		if(delay!=null){
			properties.put(DELAY, delay);
		}
		postRequest(properties);
	}
	
	/**
	 * Send by SMS/Text Message method.
	 * @param buttonId Button ID.
	 * @param phoneNumber User phone number
	 * @param message Additional user message (can be null or empty).
	 */
	public void sendBySMS(String buttonId, String phoneNumber, String message) {
		Properties properties = new Properties();
		properties.put(MEDIA, "sms");
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		properties.put(BUTTON_ID, buttonId);
		properties.put(PHONE_NUMBER, phoneNumber);
		if(message!=null && !"".equals(message)){
			properties.put(MESSAGE,  message.replaceAll(" ", "%20"));
		}
		postRequest(properties);
	}
	
	/**
	 * Send by Email method.
	 * @param buttonId Button ID.
	 * @param recipientMail Recipient email address.
	 * @param senderMail Sender email address (can be null or empty).
	 * @param senderName Sendr name (can be null or empty).
	 * @param message Additional user message (can be null or empty).
	 */
	public void sendByMail(String buttonId, String recipientMail, String senderMail, String senderName, String message) {
		Properties properties = new Properties();
		properties.put(MEDIA, EMAIL);
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		properties.put(BUTTON_ID, buttonId);
		properties.put(EMAIL, recipientMail);
		if(senderMail!=null && !"".equals(senderMail)){
			properties.put(EMAIL_FROM, senderMail);
		}
		if(senderName!=null && !"".equals(senderName)){
			properties.put(SENDER_NAME, senderName);
		}
		if(message!=null && !"".equals(message)){
			properties.put(MESSAGE,  message.replaceAll(" ", "%20"));
		}
		
		postRequest(properties);
	}
	
	/**
	 * Send by Instant Message method.
	 * @param buttonId Button ID.
	 * @param contact Recipient messenging account ID
	 * @param messenger Instant messaging service. 2: Yahoo! Messenger, 3: AOL Messenger (AIM), 4: ICQ, 5: Google Tal
	 * @param message Additional user message (can be null or empty).
	 */
	public void sendByInstantsessenger (String buttonId, String contact, int messenger, String message) {
		Properties properties = new Properties();
		properties.put(MEDIA, "messengers");
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		properties.put(BUTTON_ID, buttonId);
		properties.put(CONTACT, contact);
		properties.put(MESSENGER, messenger);
		properties.put(MESSAGE, message.replaceAll(" ", "%20"));
		
		postRequest(properties);
	}
	
	/**
	 * Save to Address Book/Save to Calendar method.
	 * @param buttonId Button ID.
	 * @param pim Address book/calendar software. Possible values: oultook, oexpress, notes, ical
	 */
	public void saveToAddressBook (String buttonId, String pim) {
		Properties properties = new Properties();
		properties.put(MEDIA, "pim");
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		properties.put(BUTTON_ID, buttonId);
		properties.put(PIM, pim);
		postRequest(properties);
	}
	
	/**
	 * 
	 * @param buttonId Button ID.
	 * @param format File format. Possible values: pdf, rtf.
	 */
	public void saveToDesktop (String buttonId, String format) {
		Properties properties = new Properties();
		properties.put(MEDIA, "desktop");
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		properties.put(BUTTON_ID, buttonId);
		properties.put(FORMAT, format);
		postRequest(properties);
	}
	
	private static void postRequest(Properties properties) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();         
        Document doc = null;
		try {
			DocumentBuilder build = factory.newDocumentBuilder();
			doc = build.parse(APIUtils.getRequest_is(ACTION_API_URL, properties));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
             
        NodeList nodeList = doc.getElementsByTagName("result");
        if (nodeList.getLength() > 0)
        {
            int code = Integer.parseInt(nodeList.item(0).getFirstChild().getNodeValue());
            System.out.println("Return code: " + code);
        }
	}
}