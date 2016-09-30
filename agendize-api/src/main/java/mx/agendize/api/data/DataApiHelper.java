package mx.agendize.api.data;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mx.agendize.api.APIUtils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Helper for the data API (https://www.agendize.com/api/1.0/data)
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class DataApiHelper {

	protected static final String DATA_API_URL = /*"http://az2."+*/"https://www."+"agendize.com/api/1.0/data";

	private static final String SCOPE = "scope";
	private static final String START_DATE = "start-date";
	private static final String END_DATE = "end-date";
	private static final String ENTRY = "entry";

	/* Scopes */
	/** Account scope */
	protected static final String ACCOUNT_SCOPE = "account";
	/** Call tracking details scope */
	protected static final String CALL_TRACKING_DETAILS_SCOPE = "callTrackingDetails"; 
	/** Click to Call details scope */
	protected static final String CALL_DETAILS_SCOPE = "callDetails"; 
	/** Comment & Rating Details scope */
	protected static final String REVIEW_DETAILS_SCOPE = "reviewDetails";
	/** Engagement by Feature scope */
	protected static final String MEDIAS_SCOPE = "medias";
	/** Form results scope */
	protected static final String FORM_RESULTS_SCOPE = "formResults";
	/** Chat History scope */
	protected static final String CHAT_HISTORY_SCOPE = "chatHistory"; 
	/** Send Report by Email scope */
	protected static final String ANALYTICS_EMAIL_SCOPE = "analyticsEmail"; 
	/** Export Report scope */
	protected static final String ANALYTICS_EXPORT_SCOPE = "analyticsExport"; 
	
	/**
	 * HTTP GET request to https://www.agendize.com/api/1.0/data with the provided scope, parameters and properties.
	 * @param startDate Start of Date Range. Format: yyyy-MM-dd
	 * @param endDate End of Date Range. Format: yyyy-MM-dd
	 * @param scope Scope of the request.
	 * @param properties List of properties, will be passed in the request URL.
	 * @return Request response as String.
	 * @throws IOException
	 */
	protected String dataRequest(String startDate, String endDate, String scope, Properties properties) throws IOException{
		properties.put(START_DATE, startDate);
		properties.put(END_DATE, endDate);
		return dataRequest(scope, properties);
	}
	
	/**
	 * HTTP GET request to https://www.agendize.com/api/1.0/data with the provided scope and properties.
	 * @param scope Scope of the request.
	 * @param properties List of properties, will be passed in the request URL.
	 * @return Request response as String.
	 * @throws IOException
	 */
	protected String dataRequest(String scope, Properties properties) throws IOException{
		properties.put(SCOPE, scope); //TODO faire des enums pour les scopes
		return APIUtils.getRequest(DATA_API_URL, properties);
	}
	
	/**
	 * Returns the list of the "entry" nodes from an XML with this form: 
	 *	<pre>
	 * {@code
	 *<?xml version="1.0" encoding="UTF-8"?>
	 *	<agendize>
	 *		<entry scope="..." />
	 *	</agendize>
	 *}
	 *</pre>
	 * @param xml xml returned by API 
	 * @return list of nodes 
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static NodeList stringToNodeList(String xml) throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(ENTRY);
	}
	
}
