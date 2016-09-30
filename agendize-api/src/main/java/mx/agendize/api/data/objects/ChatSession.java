package mx.agendize.api.data.objects;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a chat session. See <a href="http://developers.agendize.com/en/p/analytics#get-chat-history">http://developers.agendize.com/en/p/analytics#get-chat-history</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ChatSession {

	private String chatId;
	private String nickname;
	private String startPage;
	private String browser;
	private String operatingSystem;
	private String country;
	private String city;
	private String date;
	private String buttonId; 
	private List<ChatMessage> messages = new ArrayList<ChatMessage>();
	
	/**
	 * @param chatId id of the chat session
	 * @param nickname Nickname entered by the user before chatting.
	 * @param startPage URL of the page where the chat started.
	 * @param browser User's browser and version
	 * @param operatingSystem User's operating system
	 * @param country User's country. 
	 * @param city User's city
	 * @param date date of the chat session
	 * @param buttonId specific chat button ID.
	 * @param messages List of chat messages exchanged during the session.
	 */
	public ChatSession(String chatId, String nickname, String startPage, String browser, String operatingSystem, String country, String city, String date,
			String buttonId, List<ChatMessage> messages) {
		super();
		this.chatId = chatId;
		this.nickname = nickname;
		this.startPage = startPage;
		this.browser = browser;
		this.operatingSystem = operatingSystem;
		this.country = country;
		this.city = city;
		this.date = date;
		this.buttonId = buttonId;
		this.messages = messages;
	}
	
	/**
	 * Default constructor.
	 */
	public ChatSession(){
		
	}

	/**
	 * @return the chatId
	 */
	public final String getChatId() {
		return chatId;
	}
	/**
	 * @param chatId the chatId to set
	 */
	public final void setChatId(String chatId) {
		this.chatId = chatId;
	}
	/**
	 * @return the nickname
	 */
	public final String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the startPage
	 */
	public final String getStartPage() {
		return startPage;
	}
	/**
	 * @param startPage the startPage to set
	 */
	public final void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	/**
	 * @return the browser
	 */
	public final String getBrowser() {
		return browser;
	}
	/**
	 * @param browser the browser to set
	 */
	public final void setBrowser(String browser) {
		this.browser = browser;
	}
	/**
	 * @return the operatingSystem
	 */
	public final String getOperatingSystem() {
		return operatingSystem;
	}
	/**
	 * @param operatingSystem the operatingSystem to set
	 */
	public final void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	/**
	 * @return the country
	 */
	public final String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public final void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public final String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public final void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the buttonId
	 */
	public final String getButtonId() {
		return buttonId;
	}
	/**
	 * @param buttonId the buttonId to set
	 */
	public final void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	/**
	 * @return the messages
	 */
	public final List<ChatMessage> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public final void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	} 

	public ChatSession(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getAttribute("name")) {
				case "chat-id":
					chatId = element.getAttribute("value");
				case "nickname":
					nickname = element.getAttribute("value");
				case "start-page":
					startPage = element.getAttribute("value");
				case "user-agent-browser":
					browser = element.getAttribute("value");
				case "user-agent-operating-system":
					operatingSystem = element.getAttribute("value");
				case "location-country":
					country = element.getAttribute("value");
				case "location-city":
					city = element.getAttribute("value");
				case "date":
					date = element.getAttribute("value");
				case "button-id":
					buttonId = element.getAttribute("value");
					break;
				default:
					if(node.getNodeName().equals("chat:message")){
						messages.add(new ChatMessage(element.getAttribute("message-id"), element.getAttribute("mode"), element.getAttribute("date"), element.getTextContent()));
					}
					break;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (chatId != null)
			builder.append("id=").append(chatId).append(", ");
		if (nickname != null)
			builder.append("nickname=").append(nickname).append(", ");
		if (startPage != null)
			builder.append("startPage=").append(startPage).append(", ");
		if (browser != null)
			builder.append("browser=").append(browser).append(", ");
		if (operatingSystem != null)
			builder.append("operatingSystem=").append(operatingSystem).append(", ");
		if (country != null)
			builder.append("country=").append(country).append(", ");
		if (city != null)
			builder.append("city=").append(city).append(", ");
		if (date != null)
			builder.append("date=").append(date).append(", ");
		if (buttonId != null)
			builder.append("buttonId=").append(buttonId).append(", ");
		if (messages != null)
			builder.append("messages=").append(messages);
		return builder.toString();
	}
}
