package mx.agendize.api.data.objects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a scheduling widget.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class SchedulingWidget extends ButtonDetails {

	String widgetUrl;
	
	public SchedulingWidget(NodeList nl) {
		super(nl);
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getAttribute("name")) {
				case "widget-url":
					widgetUrl = element.getAttribute("value");
				default:
					break;
				}
			}
		}
	}

	/**
	 * Widget URL. Not editable.
	 * @return the widgetUrl
	 */
	public String getWidgetUrl() {
		return widgetUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SchedulingWidget [");
		if (buttonId != null)
			builder.append(buttonId).append(" - ");
		if (name != null)
			builder.append(name).append(", ");
		if (icon != null)
			builder.append("icon=").append(icon);
		if (widgetUrl != null)
			builder.append("widgetUrl=").append(widgetUrl);
		builder.append("]");
		return builder.toString();
	}

}
