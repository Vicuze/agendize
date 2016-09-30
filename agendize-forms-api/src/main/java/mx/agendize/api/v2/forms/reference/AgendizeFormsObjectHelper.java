package mx.agendize.api.v2.forms.reference;

import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.v2.forms.reference.Field.FormFieldType;
import mx.agendize.api.v2.reference.AgendizeObjectHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AgendizeFormsObjectHelper {
	private static final String TITLE = "title";
	private static final String VALUES = "values";
	private static final String KEY = "key";
	private static final String ORDER = "order";
	private static final String MANDATORY = "mandatory";
	private static final String LABEL = "label";
	private static final String TYPE = "type";
	private static final String SUBMIT_MESSAGE = "submitMessage";
	private static final String FIELDS = "fields";
	private static final String DESCRIPTION = "description";
	private static final String ID = "id";
	private static final String AUTHOR = "author";
	private static final String NAME = "name";

	public static List<Form> jsonArrayToFormsList(JSONArray formsJson) {
		List<Form> result = new ArrayList<Form>();
		for(int j= 0; j<formsJson.length(); j++){
    		result.add(jsonObjectToForm((JSONObject) formsJson.get(j)));
    	}
    	return result;
	}

	public static Form jsonObjectToForm(JSONObject formJson) {
		Form result = new Form();
		result.setId(formJson.getInt(ID));
		if(formJson.has(NAME)){
			result.setName(formJson.getString(NAME));
		}
		if(formJson.has(TITLE)){
			result.setTitle(formJson.getString(TITLE));
		}
		if(formJson.has(SUBMIT_MESSAGE)){
			result.setSubmitMessage(formJson.getString(SUBMIT_MESSAGE));
		}
		if(formJson.has(FIELDS)){
			result.setFields(jsonArrayToFieldList(formJson.getJSONArray(FIELDS)));
		}
		return result;
	}

	private static List<Field> jsonArrayToFieldList(JSONArray fieldsJson) {
		List<Field> result = new ArrayList<Field>();
		for(int j= 0; j<fieldsJson.length(); j++){
    		result.add(jsonObjectToField((JSONObject) fieldsJson.get(j)));
    	}
    	return result;
	}

	private static Field jsonObjectToField(JSONObject fieldJson) {
		Field result = new Field();
		result.setId(fieldJson.getString(ID));//TODO open JIRA: format n'est pas bon.
		if(fieldJson.has(TYPE)){
			result.setType(FormFieldType.get(fieldJson.getString(TYPE)));
		}
		if(fieldJson.has(LABEL)){
			result.setLabel(fieldJson.getString(LABEL));
		}
		if(fieldJson.has(MANDATORY)){
			result.setMandatory(fieldJson.getBoolean(MANDATORY));
		}
		if(fieldJson.has(ORDER)){
			result.setOrder(fieldJson.getInt(ORDER));
		}
		if(fieldJson.has(KEY)){
			result.setKey(fieldJson.getString(KEY));
		}
		if(fieldJson.has(VALUES)){
			result.setValues(AgendizeObjectHelper.jsonArrayToStringList(fieldJson.getJSONArray(VALUES)));
		}
		return result;
	}

	/**
	 * Converts a Form object into a JSONObject for API use.
	 * @param form the Form object.
	 * @return The JSONObject representing the form. See <a target="_blank" href="http://developers.agendize.com/v2/forms/reference/index.jsp">http://developers.agendize.com/v2/forms/reference/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject formToJSONObject(Form form) throws JSONException {
		JSONObject result = new JSONObject();
		if(form.getId() != null){
			result.put(ID, form.getId());
		}
		if(form.getName() != null && !"".equals(form.getName())){
			result.put(NAME, form.getName());
		}
		if(form.getTitle() != null && !"".equals(form.getTitle())){
			result.put(TITLE, form.getTitle());
		}
		if(form.getSubmitMessage() != null && !"".equals(form.getSubmitMessage())){
			result.put(SUBMIT_MESSAGE, form.getSubmitMessage());
		}
		if(form.getFields() != null){
			result.put(FIELDS, fieldListToJSONArray(form.getFields()));
		}
		return result;
	}

	/**
	 * Converts a list of fields into a JSONArray for API use.
	 * @param fields the list of fields objects.
	 * @return JSONArray representing the list of fields.
	 */
	private static JSONArray fieldListToJSONArray(List<Field> fields) {
		JSONArray result = new JSONArray();
		if(fields!=null){
			for(Field field: fields){
				result.put(fieldToJSONObject(field));
			}
		}
		return result;
	}
	
	/**
	 * Converts a field object into a JSONObject for API use.
	 * @param field the field object.
	 * @return JSONObject representing the field.
	 */
	private static JSONObject fieldToJSONObject(Field field){
		JSONObject result = new JSONObject();
		if(field.getType() != null && !field.getType().equals("")){
			result.put(TYPE, field.getType().getCode());
		}
		if(field.getLabel() != null && !field.getLabel().equals("")){
			result.put(LABEL, field.getLabel());
		}
		result.put(MANDATORY, field.isMandatory());
		result.put(ORDER, field.getOrder());
		if(field.getKey() != null && !field.getKey().equals("")){
			result.put(KEY, field.getKey());
		}
		if(field.getValues() != null){
			result.put(VALUES, AgendizeObjectHelper.stringListToJSONArray(field.getValues()));
		}
		return result;
	}

	public static List<FormResult> jsonArrayToFormResultList(JSONArray formResultsJson) {
		List<FormResult> result = new ArrayList<FormResult>();
		for(int j= 0; j<formResultsJson.length(); j++){
    		result.add(jsonObjectToFormResult((JSONObject) formResultsJson.get(j)));
    	}
    	return result;
	}

	public static FormResult jsonObjectToFormResult(JSONObject formResultJson) {
		FormResult result = new FormResult(); 
		result.setId(formResultJson.getInt(ID));
		if(formResultJson.has(AUTHOR)){
			result.setAuthor(formResultJson.getString(AUTHOR));
		}
		if(formResultJson.has("result")){
			result.setResult(formResultJson.getString("result"));
		}
		if(formResultJson.has("form")){
			result.setFormId(formResultJson.getInt("form"));
		}
		if(formResultJson.has("ipAddress")){
			result.setIpAddress(formResultJson.getString("ipAddress"));
		}
		if(formResultJson.has("client")){
			result.setClientId(formResultJson.getInt("client"));
		}
		if(formResultJson.has("date")){
			result.setDate(formResultJson.getString("date"));
		}
		return result;
	}

}
