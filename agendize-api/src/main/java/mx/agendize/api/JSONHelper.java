package mx.agendize.api;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Helper for JSON objects or arrays management
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class JSONHelper {

	/**
	 * Converts a JSONArray to a String for console displaying
	 * @param array JSONArray to convert
	 * @return String representing the JSONArray. Can be printed in console.
	 * @throws JSONException
	 */
	public static String JSONArrayToString(JSONArray array) throws JSONException{
		StringBuffer buffer= new StringBuffer();
    	for(int j= 0; j<array.length(); j++){
    		if(array.get(j) instanceof String){
    			buffer.append(array.get(j));
    		} else if(array.get(j) instanceof JSONObject){
    			buffer.append(JSONObjectToString((JSONObject) array.get(j)));
    		}
    		buffer.append("\n");
    	}
    	return buffer.toString();
	}
	
	/**
	 * Converts a JSONObject to a String for console displaying
	 * @param object JSONObject to convert 
	 * @return String representing the JSONObject. Can be printed in console.
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	public static String JSONObjectToString(JSONObject object) throws JSONException{
		StringBuffer buffer= new StringBuffer();
		Iterator<String> it = object.keys();
		while (it.hasNext()) {
			String s = it.next();
			if(object.get(s) instanceof JSONObject){
				buffer.append("--------beginning JSONObject "+s+"--------\n");
				buffer.append(JSONObjectToString((JSONObject) object.get(s)));
				buffer.append("--------end JSONObject "+s+"--------\n");
			} else if(object.get(s) instanceof JSONArray){
				buffer.append("--------beginning JSONArray "+s+"--------\n");
				buffer.append(JSONArrayToString((JSONArray) object.get(s)));
				buffer.append("--------end JSONArray "+s+"--------\n");
			} else {
				buffer.append(s + ": " + object.get(s));
				if(it.hasNext())
					buffer.append("\n");
			}
		}
		buffer.append("\n");
		return buffer.toString();
	}
}
