package mx.agendize.api.data.objects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing an Agendize account.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class AgendizeAccount {

	private String email;
	private String firstName;
	private String lastName;
	private String ssoToken;

	/**
	 * Creates an AgendizeAccount object from the NodeList of an XML like the one in <a target="_blank" href="http://developers.agendize.com/en/p/account#get-existing-account-details">this example</a>
	 * @param list the NodeList.
	 */
	public AgendizeAccount(NodeList list){
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getAttribute("name")) {
				case "email":
					email = element.getAttribute("value");
					break;
				case "firstname":
					firstName = element.getAttribute("value");
					break;
				case "lastname":
					lastName = element.getAttribute("value");
					break;
				case "token":
					ssoToken = element.getAttribute("value");
					break;
				default:
					break;
				}
			}
		}
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the ssoToken
	 */
	public String getSsoToken() {
		return ssoToken;
	}
	/**
	 * @param ssoToken the ssoToken to set
	 */
	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agendize Account [");
		if (firstName != null)
			builder.append(firstName);
		if (lastName != null)
			builder.append(" ").append(lastName);
		if (email != null)
			builder.append(" - ").append(email);
		if (ssoToken != null)
			builder.append(" - ").append(ssoToken);
		builder.append("]");
		return builder.toString();
	}
}
