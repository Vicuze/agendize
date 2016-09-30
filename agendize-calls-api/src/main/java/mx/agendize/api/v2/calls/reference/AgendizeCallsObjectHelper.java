package mx.agendize.api.v2.calls.reference;

import java.util.ArrayList;
import java.util.List;

import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.calls.reference.Call.CallStatus;
import mx.agendize.api.v2.calls.reference.Call.CallType;
import mx.agendize.api.v2.reference.AgendizeObjectHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AgendizeCallsObjectHelper {

	private static final String COUNTRIES = "countries";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String STATUS = "status";
	private static final String DURATION = "duration";
	private static final String START = "start";
	private static final String TRACKING_NUMBER = "trackingNumber";
	private static final String CALLED_NUMBER = "calledNumber";
	private static final String CALLER_NUMBER = "callerNumber";
	private static final String CLIENT_ID = "clientId";
	private static final String ID = "id";
	private static final String BUTTON_ID = "buttonId";

	/**
	 * Converts a JSONArray in a list of Call objects
	 * @param callsJson json representing the list of calls. See <a target="_blank" href="http://developers.agendize.com/v2/calls/reference/index.jsp">http://developers.agendize.com/v2/calls/reference/index.jsp</a>
	 * @return The list of call objects.
	 * @throws AgendizeException 
	 * @throws JSONException 
	 */
	public static List<Call> jsonArrayToCallsList(JSONArray callsJson) throws JSONException, AgendizeException {
		List<Call> result = new ArrayList<Call>();
		for(int j= 0; j<callsJson.length(); j++){
    		result.add(jsonObjectToCall((JSONObject) callsJson.get(j)));
    	}
    	return result;
	}

	public static Call jsonObjectToCall(JSONObject callJson) throws JSONException, AgendizeException {
		Call result = new Call();
		result.setId(callJson.getInt(ID));
		if(callJson.has(BUTTON_ID)){
			result.setButtonId(callJson.getString(BUTTON_ID));
		}
		if(callJson.has(CLIENT_ID)){
			if(callJson.getString(CLIENT_ID) != null && !"".equals(callJson.getString(CLIENT_ID))){
				result.setClientId(Integer.parseInt(callJson.getString(CLIENT_ID)));
			}
		}
		if(callJson.has(CALLER_NUMBER)){
			result.setCallerNumber(callJson.getString(CALLER_NUMBER));
		}
		if(callJson.has(CALLED_NUMBER)){
			result.setCalledNumber(callJson.getString(CALLED_NUMBER));
		}
		if(callJson.has(TRACKING_NUMBER)){
			result.setTrackingNumber(callJson.getString(TRACKING_NUMBER));
		}
		if(callJson.has(START)){
			result.setStart(AgendizeObjectHelper.jsonObjectToTime(callJson.getJSONObject(START)));
		}
		if(callJson.has(DURATION)){
			result.setDuration(callJson.getInt(DURATION));
		}
		if(callJson.has(STATUS)){
			result.setStatus(CallStatus.get(callJson.getString(STATUS)));
		}
		if(callJson.has(TYPE)){
			result.setType(CallType.get(callJson.getString(TYPE)));
		}
		return result;
	}

	/**
	 * Converts a Call object into a JSONObject for API use.
	 * @param call the Call object.
	 * @return The JSONObject representing the call. See <a target="_blank" href="http://developers.agendize.com/v2/calls/reference/index.jsp">http://developers.agendize.com/v2/calls/reference/index.jsp</a>
	 * @throws JSONException
	 */
	public static JSONObject callToJSONObject(Call call) throws JSONException {
		JSONObject result = new JSONObject();
		if(call.getId() != null){
			result.put(ID, call.getId());
		}
		if(call.getButtonId() != null && !"".equals(call.getButtonId())){
			result.put(BUTTON_ID, call.getButtonId());
		}
		result.put(CLIENT_ID, String.valueOf(call.getClientId()));
		if(call.getCallerNumber() != null && !"".equals(call.getCallerNumber())){
			result.put(CALLER_NUMBER, call.getCallerNumber());
		}
		if(call.getCalledNumber() != null && !"".equals(call.getCalledNumber())){
			result.put(CALLED_NUMBER, call.getCalledNumber());
		}
		if(call.getTrackingNumber() != null && !"".equals(call.getTrackingNumber())){
			result.put(TRACKING_NUMBER, call.getTrackingNumber());
		}
		if(call.getStart() != null){
			result.put(START, AgendizeObjectHelper.timeToJSONObject(call.getStart()));
		}
		result.put(DURATION, call.getDuration());
		if(call.getStatus() != null && !"".equals(call.getStatus())){
			result.put(STATUS, call.getStatus().getCode());
		}
		if(call.getType() != null && !"".equals(call.getType())){
			result.put(TYPE, call.getType().getCode());
		}
		return result;
	}

	public static List<ClickToCallButton> jsonArrayToClickToCallButtonList(JSONArray clickToCallButtonsJson) {
		List<ClickToCallButton> result = new ArrayList<ClickToCallButton>();
		for(int j= 0; j<clickToCallButtonsJson.length(); j++){
    		result.add(jsonObjectToClickToCallButton((JSONObject) clickToCallButtonsJson.get(j)));
    	}
    	return result;
	}

	public static ClickToCallButton jsonObjectToClickToCallButton(JSONObject clickToCallButtonJson) {
		ClickToCallButton result = new ClickToCallButton();
		if(clickToCallButtonJson.has(ID)){
			result.setId(clickToCallButtonJson.getInt(ID));
		}
		if(clickToCallButtonJson.has(NAME)){
			result.setName(clickToCallButtonJson.getString(NAME));
		}
		if(clickToCallButtonJson.has(PHONE_NUMBER)){
			result.setPhoneNumber(clickToCallButtonJson.getString(PHONE_NUMBER));
		}
		if(clickToCallButtonJson.has(COUNTRIES)){
			result.setCountries(jsonArrayToStringList(clickToCallButtonJson.getJSONArray(COUNTRIES)));
		}
		return result; 
	}

	private static List<String> jsonArrayToStringList(JSONArray jsonArray) {
		List<String> result = new ArrayList<String>();
		for(int i = 0; i<jsonArray.length(); i++){
			result.add(jsonArray.getString(i));
		}
		return result; 
	}

	public static JSONObject clickToCallButtonToJSONObject(ClickToCallButton clickToCallButton) {
		JSONObject result = new JSONObject(); 
		if(clickToCallButton.getId() != null){
			result.put(ID, clickToCallButton.getId()); 
		}
		if(clickToCallButton.getName() != null && !clickToCallButton.getName().equals("")){
			result.put(NAME, clickToCallButton.getName()); 
		}
		if(clickToCallButton.getPhoneNumber() != null && !clickToCallButton.getPhoneNumber().equals("")){
			result.put(PHONE_NUMBER, clickToCallButton.getPhoneNumber()); 
		}
		if(clickToCallButton.getCountries() != null && !clickToCallButton.getCountries().isEmpty()){
			result.put(COUNTRIES, stringListToJSONArray(clickToCallButton.getCountries())); 
		}
		return result;
	}

	private static JSONArray stringListToJSONArray(List<String> strings) {
		JSONArray result = new JSONArray();
		for(String s: strings){
			result.put(s);
		}
		return result;
	}

}
