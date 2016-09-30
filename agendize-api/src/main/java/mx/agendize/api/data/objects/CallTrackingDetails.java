package mx.agendize.api.data.objects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the details of a Call
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class CallTrackingDetails {

	private String buttonId;
	private String callerNumber;
	private String calledNumber;
	private String trackingNumber;
	private int duration;
	private String date;
	private String status;
	private String voiceFile;
	private String type;
	private String support;
	private String misc1;
	private String misc2;
	private String misc3;
	private String misc4;
	private String misc5;
	private String misc6;
	private String misc7;
	private String misc8;
	private String misc9;
	private String misc10;
	private String notes;
	private String costAmount;
	private String costPeriod;
	private String publicationStartDate;
	private String publicationEndDate;

	/**
	 * Construction d'une Entry à partir d'un des résultats du XML
	 * @param list
	 */
	public CallTrackingDetails(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getAttribute("name")) {
				case "button-id":
					setButtonId(element.getAttribute("value"));
					break;
				case "caller-number":
					callerNumber = element.getAttribute("value");
					break;
				case "called-number":
					calledNumber = element.getAttribute("value");
					break;
				case "tracking-number":
					trackingNumber = element.getAttribute("value");
					break;
				case "duration":
					duration = Integer.parseInt(element.getAttribute("value"));
					break;
				case "date":
					date = element.getAttribute("value");
					break;
				case "status":
					status = element.getAttribute("value");
					break;
				case "voice-file":
					voiceFile = element.getAttribute("value");
					break;
				case "support":
					support = element.getAttribute("value");
					break;
				case "misc1":
					misc1 = element.getAttribute("value");
					break;
				case "misc2":
					misc2 = element.getAttribute("value");
					break;
				case "misc3":
					misc3 = element.getAttribute("value");
					break;
				case "misc4":
					misc4 = element.getAttribute("value");
					break;
				case "misc5":
					misc5 = element.getAttribute("value");
					break;
				case "misc6":
					misc6 = element.getAttribute("value");
					break;
				case "misc7":
					misc7 = element.getAttribute("value");
					break;
				case "misc8":
					misc8 = element.getAttribute("value");
					break;
				case "misc9":
					misc9 = element.getAttribute("value");
					break;
				case "misc10":
					misc10 = element.getAttribute("value");
					break;
				case "notes":
					notes = element.getAttribute("value");
					break;
				case "cost-amount":
					costAmount = element.getAttribute("value");
					break;
				case "cost-period":
					costPeriod = element.getAttribute("value");
					break;
				case "publication-start-date":
					publicationStartDate = element.getAttribute("value");
					break;
				case "publication-end-date":
					publicationEndDate = element.getAttribute("value");
					break;
				default:
					break;
				}
			}
		}
	}

	public CallTrackingDetails() {

	}
	
	/**
	 * @return the buttonId
	 */
	public String getButtonId() {
		return buttonId;
	}

	/**
	 * @param buttonId the buttonId to set
	 */
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	/**
	 * @return the callerNumber
	 */
	public String getCallerNumber() {
		return callerNumber;
	}

	/**
	 * @param callerNumber the callerNumber to set
	 */
	public void setCallerNumber(String callerNumber) {
		this.callerNumber = callerNumber;
	}

	/**
	 * @return the calledNumber
	 */
	public String getCalledNumber() {
		return calledNumber;
	}

	/**
	 * @param calledNumber the calledNumber to set
	 */
	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the voiceFile
	 */
	public String getVoiceFile() {
		return voiceFile;
	}

	/**
	 * @param voiceFile the voiceFile to set
	 */
	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
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
	 * @return the support
	 */
	public String getSupport() {
		return support;
	}

	/**
	 * @param support the support to set
	 */
	public void setSupport(String support) {
		this.support = support;
	}

	/**
	 * @return the misc1
	 */
	public String getMisc1() {
		return misc1;
	}

	/**
	 * @param misc1 the misc1 to set
	 */
	public void setMisc1(String misc1) {
		this.misc1 = misc1;
	}

	/**
	 * @return the misc2
	 */
	public String getMisc2() {
		return misc2;
	}

	/**
	 * @param misc2 the misc2 to set
	 */
	public void setMisc2(String misc2) {
		this.misc2 = misc2;
	}

	/**
	 * @return the misc3
	 */
	public String getMisc3() {
		return misc3;
	}

	/**
	 * @param misc3 the misc3 to set
	 */
	public void setMisc3(String misc3) {
		this.misc3 = misc3;
	}

	/**
	 * @return the misc4
	 */
	public String getMisc4() {
		return misc4;
	}

	/**
	 * @param misc4 the misc4 to set
	 */
	public void setMisc4(String misc4) {
		this.misc4 = misc4;
	}

	/**
	 * @return the misc5
	 */
	public String getMisc5() {
		return misc5;
	}

	/**
	 * @param misc5 the misc5 to set
	 */
	public void setMisc5(String misc5) {
		this.misc5 = misc5;
	}

	/**
	 * @return the misc6
	 */
	public String getMisc6() {
		return misc6;
	}

	/**
	 * @param misc6 the misc6 to set
	 */
	public void setMisc6(String misc6) {
		this.misc6 = misc6;
	}

	/**
	 * @return the misc7
	 */
	public String getMisc7() {
		return misc7;
	}

	/**
	 * @param misc7 the misc7 to set
	 */
	public void setMisc7(String misc7) {
		this.misc7 = misc7;
	}

	/**
	 * @return the misc8
	 */
	public String getMisc8() {
		return misc8;
	}

	/**
	 * @param misc8 the misc8 to set
	 */
	public void setMisc8(String misc8) {
		this.misc8 = misc8;
	}

	/**
	 * @return the misc9
	 */
	public String getMisc9() {
		return misc9;
	}

	/**
	 * @param misc9 the misc9 to set
	 */
	public void setMisc9(String misc9) {
		this.misc9 = misc9;
	}

	/**
	 * @return the misc10
	 */
	public String getMisc10() {
		return misc10;
	}

	/**
	 * @param misc10 the misc10 to set
	 */
	public void setMisc10(String misc10) {
		this.misc10 = misc10;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the costAmount
	 */
	public String getCostAmount() {
		return costAmount;
	}

	/**
	 * @param costAmount the costAmount to set
	 */
	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
	}

	/**
	 * @return the costPeriod
	 */
	public String getCostPeriod() {
		return costPeriod;
	}

	/**
	 * @param costPeriod the costPeriod to set
	 */
	public void setCostPeriod(String costPeriod) {
		this.costPeriod = costPeriod;
	}

	/**
	 * @return the publicationStartDate
	 */
	public String getPublicationStartDate() {
		return publicationStartDate;
	}

	/**
	 * @param publicationStartDate the publicationStartDate to set
	 */
	public void setPublicationStartDate(String publicationStartDate) {
		this.publicationStartDate = publicationStartDate;
	}

	/**
	 * @return the publicationEndDate
	 */
	public String getPublicationEndDate() {
		return publicationEndDate;
	}

	/**
	 * @param publicationEndDate the publicationEndDate to set
	 */
	public void setPublicationEndDate(String publicationEndDate) {
		this.publicationEndDate = publicationEndDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entry [");
		if (buttonId != null)
			builder.append("buttonId=").append(buttonId).append(", ");
		if (callerNumber != null)
			builder.append("callerNumber=").append(callerNumber).append(", ");
		if (calledNumber != null)
			builder.append("calledNumber=").append(calledNumber).append(", ");
		if (trackingNumber != null)
			builder.append("trackingNumber=").append(trackingNumber).append(", ");
		builder.append("duration=").append(duration).append(", ");
		if (date != null)
			builder.append("date=").append(date).append(", ");
			builder.append("status=").append(status==null?"null":status).append(", ");
		if (voiceFile != null)
			builder.append("voiceFile=").append(voiceFile).append(", ");
		if (type != null)
			builder.append("type=").append(type).append(", ");
		if (support != null)
			builder.append("support=").append(support).append(", ");
		if (misc1 != null)
			builder.append("misc1=").append(misc1).append(", ");
		if (misc2 != null)
			builder.append("misc2=").append(misc2).append(", ");
		if (misc3 != null)
			builder.append("misc3=").append(misc3).append(", ");
		if (misc4 != null)
			builder.append("misc4=").append(misc4).append(", ");
		if (misc5 != null)
			builder.append("misc5=").append(misc5).append(", ");
		if (misc6 != null)
			builder.append("misc6=").append(misc6).append(", ");
		if (misc7 != null)
			builder.append("misc7=").append(misc7).append(", ");
		if (misc8 != null)
			builder.append("misc8=").append(misc8).append(", ");
		if (misc9 != null)
			builder.append("misc9=").append(misc9).append(", ");
		if (misc10 != null)
			builder.append("misc10=").append(misc10).append(", ");
		if (notes != null)
			builder.append("notes=").append(notes).append(", ");
		if (costAmount != null)
			builder.append("costAmount=").append(costAmount).append(", ");
		if (costPeriod != null)
			builder.append("costPeriod=").append(costPeriod).append(", ");
		if (publicationStartDate != null)
			builder.append("publicationStartDate=")
					.append(publicationStartDate).append(", ");
		if (publicationEndDate != null)
			builder.append("publicationEndDate=").append(publicationEndDate);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(String status) {
		this.status = status;
	}

}
