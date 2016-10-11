package mx.agendize.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for API requesting.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class APIUtils {
	
	private static final String PUT = "PUT";
	private static final String POST = "POST";
	private static final String DELETE = "DELETE";
	static final Logger logger = LogManager.getLogger(APIUtils.class);

	/**
	 * HTTP GET Request to the specified URL. Returns a String
	 * @param urlString The URL to which the request will be made
	 * @param properties properties will be passed in the URL. 
	 * @return The request response as a String
	 * @throws IOException
	 */
	public static String getRequest(String urlString, Properties properties) throws IOException{
		String result = IOUtils.toString(getRequest_is(urlString, properties), "ISO-8859-1");
        logger.debug("Response: " + shortString(result));
		return result;
	}

	/**
	 * HTTP GET Request to the specified URL. Returns an InputStream.
	 * @param urlString The URL to which the request will be made
	 * @param properties properties will be passed in the URL. 
	 * @return The request response as an InputStream
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static InputStream getRequest_is(String urlString, Properties properties) throws IOException {
        Enumeration enumeration = properties.propertyNames();
        char c = '?';
        while (enumeration.hasMoreElements()) {
          String key = (String) enumeration.nextElement();
          urlString += c + key + "=" + properties.getProperty(key);
          c= '&';
        }
        return getRequest_is(urlString);
	}
	
	/**
	 * HTTP GET Request to the specified URL. Returns an InputStream.
	 * @param urlString The URL to which the request will be made
	 * @return The request response as an InputStream
	 * @throws IOException
	 */
	private static InputStream getRequest_is(String urlString) throws IOException{
		URL url = new URL(urlString);//TODO utiliser autre constructeur pour gérer les caracteres bizarres
		logger.debug("GET Request: "+url.toString());
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		return urlConnection.getInputStream();
	}
	
	/**
	 * HTTP PUT request to the specified URL.
	 * @param urlString The URL to which the request will be made
	 * @param json Request body.
	 * @param properties properties will be passed in the URL.
	 * @return The request response as a String
	 * @throws AgendizeException 
	 */
	@SuppressWarnings("rawtypes")
	public static String putRequest(String urlString, String json, Properties properties) throws AgendizeException{
		Enumeration enumeration = properties.propertyNames();
		char c = '?';
	    while (enumeration.hasMoreElements()) {
	      String key = (String) enumeration.nextElement();
	      urlString += c + key + "=" + properties.getProperty(key);
	      c= '&';
	    }

		URL url = null;
	    HttpURLConnection urlConnection = null;
	    OutputStreamWriter osw = null;
		String result = null;
		
		try {
			url = new URL(urlString);
			logger.debug("PUT Request: "+url.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod(PUT);
		    urlConnection.setDoOutput(true);
		    urlConnection.setRequestProperty("Content-Type", "application/json");
		    urlConnection.setRequestProperty("Accept", "application/json");
		    osw = new OutputStreamWriter(urlConnection.getOutputStream());
		    json = convertJson(json);
		    logger.debug("request JSON: "+json);
	    
		    osw.write(json);
		    osw.flush();
		    osw.close();
			logger.debug("Response: " + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage());
			if(urlConnection.getResponseCode() == 501){
		    	throw new AgendizeException("This method has not been implemented yet.");
		    }
			result = IOUtils.toString(urlConnection.getInputStream(), "ISO-8859-1");
			logger.debug("response JSON: " + shortString(result));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * HTTP POST request to the specified URL.
	 * @param urlString The URL to which the request will be made
	 * @param json Request body.
	 * @param properties properties will be passed in the URL.
	 * @return The request response as a String
	 * @throws AgendizeException 
	 */
	@SuppressWarnings("rawtypes")
	public static String postRequest(String urlString, String json, Properties properties) throws AgendizeException {
		Enumeration enumeration = properties.propertyNames();
		char c = '?';
	    while (enumeration.hasMoreElements()) {
	      String key = (String) enumeration.nextElement();
	      urlString += c + key + "=" + properties.getProperty(key);
	      c= '&';
	    }

		URL url = null;
	    HttpURLConnection urlConnection = null;
	    OutputStreamWriter osw = null;
		String result = null;
		
		try {
			url = new URL(urlString);
			logger.debug("POST Request: "+url.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod(POST);
		    urlConnection.setDoOutput(true);
		    urlConnection.setRequestProperty("Content-Type", "application/json");
		    urlConnection.setRequestProperty("Accept", "application/json");
		    osw = new OutputStreamWriter(urlConnection.getOutputStream());
		    json = convertJson(json);
		    logger.debug("request JSON: "+json);
		    
		    osw.write(json);
		    osw.flush();
		    osw.close();
			logger.debug("Response: " + urlConnection.getResponseCode() +" " + urlConnection.getResponseMessage());
			if(urlConnection.getResponseCode() == 501){
		    	throw new AgendizeException("This method has not been implemented yet.");
		    }
			result = IOUtils.toString(urlConnection.getInputStream(), "ISO-8859-1");
			logger.debug("response JSON: " + shortString(result));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Replaces special characters with html encoding. Example: "déjà" will become "d&#233;j&#224;"
	 * @param s The string to process
	 * @return the String with special characters replaced by html encoding
	 */
	private static String convertJson(String s) {
		String result = s;
		if(s!=null && !"".equals(s)){
			List<Character> charsToReplace = new ArrayList<Character>();
			for(int i=0; i<s.length(); i++){
				if(s.charAt(i)>127){
					charsToReplace.add(s.charAt(i));
				}
			}
			for(Character c: charsToReplace){
				result = result.replaceAll(String.valueOf(c), "&#"+(int)c+";");
			}
		}
		return result; 
	}

	/**
	 * HTTP DELETE request to the specified URL.
	 * @param urlString The URL to which the request will be made
	 * @param properties properties will be passed in the URL.
	 * @throws AgendizeException 
	 */
	@SuppressWarnings("rawtypes")
	public static void deleteRequest(String urlString, Properties properties) throws AgendizeException {
		//System.out.println(properties);
        Enumeration enumeration = properties.propertyNames();
        char c = '?';
        while (enumeration.hasMoreElements()) {
          String key = (String) enumeration.nextElement();
          urlString += c + key + "=" + properties.getProperty(key);
          // System.out.println(key+" - "+properties.getProperty(key));
          c= '&';
        }

		URL url = null;
	    HttpURLConnection urlConnection = null;
		
		try {
			url = new URL(urlString);
			logger.debug("DELETE Request: "+url.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestProperty(
			    "Content-Type", "application/x-www-form-urlencoded" );
			urlConnection.setRequestMethod(DELETE);
			urlConnection.connect();
			urlConnection.getResponseCode();
			logger.debug("Response: " + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage());
			if(urlConnection.getResponseCode() == 501){
		    	throw new AgendizeException("This method has not been implemented yet.");
		    }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Pour limiter les logs à 10k caractères
	private static String shortString(String s){
		String result = s.replace("\n", "");
		if(result.length()>10000){
			result = result.substring(0, 10000) + "..."; 
		}
		return result; 
	}
}

