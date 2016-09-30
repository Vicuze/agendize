package mx.agendize.api.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.data.objects.ButtonDetails;
import mx.agendize.api.data.objects.CallTrackingNumber;
import mx.agendize.api.data.objects.FormDetails;
import mx.agendize.api.data.objects.SaveAndShareContact;
import mx.agendize.api.data.objects.SchedulingWidget;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for tools management
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ToolsManager extends AgendizeApiManager{

	/**
	 * @param apiKey API Key. No API Key? <a target="_blank" href="http://www.agendize.com/account#app" >Get one here</a>
	 * @param token SSO token. See <a target="_blank" href="http://developers.agendize.com/en/p/authentication" >http://developers.agendize.com/en/p/authentication</a>
	 * @throws IOException in case the API key or SSO token are not valid
	 */
	public ToolsManager(String apiKey, String token) throws IOException {
		super(apiKey, token);
	}

	/**
	 * Returns the list of buttons for an account
	 * @return List of ButtonDetails objects.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	@SuppressWarnings("unchecked")
	public List<ButtonDetails> getButtonDetails() throws IOException, ParserConfigurationException, SAXException {
		
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest("buttons", properties);
		NodeList nList = DataApiHelper.stringToNodeList(response);

		List<ButtonDetails> result = new ArrayList<ButtonDetails>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl = nNode.getChildNodes();
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						if(element.getAttribute("name").equals("type")){
							switch (element.getAttribute("value")) {
							case "call-tracking":
								result.add(new CallTrackingNumber(nl));
								break;
							case "chat":
								result.add(new ButtonDetails(nl));
								break;
							case "scheduling":
								result.add(new SchedulingWidget(nl));
								break;
							case "contact":
								result.add(new SaveAndShareContact(nl));
								break;
							case "event":
								break;
							case "call":
								break;
							case "none":
								break;
							default:
								result.add(new ButtonDetails(nl));
								break;
							}
							break;
						}
					}
				}
			}
		}
		return (List<ButtonDetails>) sortByButtonId(result);
	} 
	
	public List<FormDetails> getFormDetails() throws IOException, ParserConfigurationException, SAXException {
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest("forms", properties);
		NodeList nList = DataApiHelper.stringToNodeList(response);

		List<FormDetails> result = new ArrayList<FormDetails>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl = nNode.getChildNodes();
				result.add(new FormDetails(nl));
			}
		}
		return result; 
	}
	
	/**
	 * Lists all the call tracking numbers for an account
	 * @return All the call tracking numbers for the account.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	@SuppressWarnings("unchecked")
	public List<CallTrackingNumber> getCallTrackingNumbers() throws IOException, ParserConfigurationException, SAXException {
		
		Properties properties = new Properties();
		properties.put(API_KEY_V1, apiKey);
		properties.put(TOKEN, token);
		
		DataApiHelper dah = new DataApiHelper();
		String response = dah.dataRequest("buttons", properties);
		NodeList nList = DataApiHelper.stringToNodeList(response);
		
		List<CallTrackingNumber> result = new ArrayList<CallTrackingNumber>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl = nNode.getChildNodes();
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						if(element.getAttribute("name").equals("type")){
							if (element.getAttribute("value").equals("call-tracking")){
								result.add(new CallTrackingNumber(nl));
							}
							break;
						}
					}
				}
			}
		}
		return (List<CallTrackingNumber>) sortByButtonId(result);
	}
	
	/**
	 * sorting by buttonId a list of objects that extend ButtonDetails
	 * @param list
	 * @return sorted list.
	 */
	private static List<? extends ButtonDetails> sortByButtonId(List<? extends ButtonDetails> list){
		List<ButtonDetails> listeTriee = new ArrayList<ButtonDetails>();
		for(ButtonDetails b: list){
			if(listeTriee.size() == 0){
				listeTriee.add(b);
			} else {
				int i = 0;
				while (i<listeTriee.size() && b.getButtonId().compareTo(listeTriee.get(i).getButtonId())>0){
					i++;
				}
				listeTriee.add(i, b);
			}
		}
		return listeTriee;
	}
	
}
