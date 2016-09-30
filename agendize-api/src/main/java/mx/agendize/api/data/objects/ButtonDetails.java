package mx.agendize.api.data.objects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the details of a button
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ButtonDetails {

	/** Button ID. */
	protected Integer buttonId;
	/** Button name. */
	protected String name;
	/** Button type. */
	protected String type;
	/** URL of button icon. */
	protected String icon;
	
	/**
	 * @param buttonId
	 * @param name
	 */
	public ButtonDetails(Integer buttonId, String name) {
		super();
		this.buttonId = buttonId;
		this.name = name;
	}

	/**
	 * Constructs a ButtonDetails object from a NodeList, like this one
	 * 	<pre>
	 * {@code
	 * <entry scope="buttons">
	 * 	<entry:value name="button-id" type="string" value="11111111"/>
	 * 	<entry:value name="name" type="string" value="My Chat button"/>
	 * 	<entry:value name="icon" type="string" value="http://www.agendize.com/shared/img/chat.png"/>
	 * 	<entry:value name="type" type="string" value="chat"/>
	 * </entry>
	 * }
	 * </pre>
	 * @param nl
	 */
	public ButtonDetails(NodeList nl) {
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getAttribute("name")) {
				case "button-id":
					setButtonId(Integer.parseInt(element.getAttribute("value")));
					break;
				case "name":
					setName(element.getAttribute("value"));
					break;
				case "icon":
					setIcon(element.getAttribute("value"));
					break;
				case "type":
					setType(element.getAttribute("value"));
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * @return the buttonId
	 */
	public Integer getButtonId() {
		return buttonId;
	}
	/**
	 * @param buttonId the buttonId to set
	 */
	public void setButtonId(Integer buttonId) {
		this.buttonId = buttonId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ButtonDetails [");
		if (buttonId != null)
			builder.append(buttonId).append(" - ");
		if (name != null)
			builder.append(name).append(", ");
		if (type != null)
			builder.append("type=").append(type).append(", ");
		if (icon != null)
			builder.append("icon=").append(icon);
		builder.append("]");
		return builder.toString();
	}
}
