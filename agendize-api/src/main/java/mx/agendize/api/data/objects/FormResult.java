package mx.agendize.api.data.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the details of a Form result
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class FormResult {

	private Integer formId;
	private Integer resultId;
	private Date date;

	private List<FormFieldResult> fieldsResults;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormResult(NodeList nl) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getNodeName()) {
				case "entry:value":
					switch (element.getAttribute("name")) {
					case "form-id":
						formId = Integer.parseInt(element.getAttribute("value"));
						break;
					case "result-id":
						resultId = Integer.parseInt(element.getAttribute("value"));
						break;
					case "date":
						date = sdf.parse(element.getAttribute("value"));
						break;
					default:
						break;
					}
					break;
				case "form:field":
					FormFieldResult ffr = new FormFieldResult(element);
					if(fieldsResults==null){
						fieldsResults = new ArrayList();
					}
					fieldsResults.add(ffr);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public FormResult() {
	}

	/**
	 * @return the formId
	 */
	public Integer getFormId() {
		return formId;
	}
	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	/**
	 * @return the resultId
	 */
	public Integer getResultId() {
		return resultId;
	}
	/**
	 * @param resultId the resultId to set
	 */
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the fields
	 */
	public List<FormFieldResult> getFields() {
		return fieldsResults;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<FormFieldResult> fieldsResults) {
		this.fieldsResults = fieldsResults;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("FormResult [");
		if (formId != null)
			builder.append("formId=").append(formId).append(", ");
		if (resultId != null)
			builder.append("resultId=").append(resultId).append(", ");
		if (date != null)
			builder.append("date=").append(sdf.format(date)).append(", ");
		if (fieldsResults != null){
			for (FormFieldResult ffr: fieldsResults){
				builder.append("\n"+ffr.getFormField().getId()+" - "+ffr.getFormField().getLabel()+": "+ffr.getResult());
			}
		}
		builder.append("]");
		return builder.toString();
	}
	

}
