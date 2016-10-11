package mx.agendize.api.v2.queues;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import mx.agendize.api.APIUtils;
import mx.agendize.api.AgendizeApiManager;
import mx.agendize.api.AgendizeException;
import mx.agendize.api.v2.queues.reference.AgendizeQueuesObjectHelper;
import mx.agendize.api.v2.queues.reference.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for queues management.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class QueuesManager extends AgendizeApiManager {
	
	static final Logger logger = LogManager.getLogger(QueuesManager.class);

	public QueuesManager(String apiKey, String token) {
		super(apiKey, token);
	}

	/**
	 * Returns all queues of the account.
	 * @return List of Queue objects.
	 * @throws JSONException
	 * @throws AgendizeException 
	 */
	public List<Queue> getQueues() throws JSONException, AgendizeException {
		logger.info("getQueues.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		
		String queuesString = null;
		try {
			queuesString = APIUtils.getRequest(QUEUES_API_URL, properties);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject queuesJson = new JSONObject(queuesString);
		List<Queue> result = AgendizeQueuesObjectHelper.jsonArrayToQueuesList(queuesJson.getJSONArray("items"));
		logger.info(result.size() + " queues found.");
		return result; 
	}
	
	/**
	 * Get a queue by its id. Will return null if there is no queue with this id.
	 * @param id id of the queue to look for.
	 * @return Queue object.
	 * @throws AgendizeException 
	 * @throws JSONException 
	 * @throws IOException
	 */
	public Queue get(int id) throws JSONException, AgendizeException {
		logger.info("Get queue with id = " + id + ".");
		Queue result = null;
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
        String queueString = null;
		try {
			queueString = APIUtils.getRequest(QUEUES_API_URL + "/" + id, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(queueString!=null){
            result = AgendizeQueuesObjectHelper.jsonObjectToQueue(new JSONObject(queueString));
		}
		if(result != null){
			logger.info("Queue found.");
		}
		return result;
	}

	/**
	 * Creates a queue. 
	 * @param queue to create.
	 * @return the updated queue.
	 * @throws AgendizeException 
	 */
	public Queue create(Queue queue) throws AgendizeException{
		logger.info("Create new queue.");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String json = AgendizeQueuesObjectHelper.queueToJSONObject(queue).toString();
		String queueString = APIUtils.postRequest(QUEUES_API_URL, json, properties);
		JSONObject jsonObject = new JSONObject(queueString);
		Queue result = AgendizeQueuesObjectHelper.jsonObjectToQueue(jsonObject);
		logger.info("Queue created succesfully. id = " + result.getId() + ".");
        return result;
	}
	
	/**
	 * Deletes a queue.
	 * @param queueId Identifier of queue to delete.
	 * @throws AgendizeException 
	 */
	public void delete(int queueId) throws AgendizeException {
		logger.info("Delete queue with id = " + queueId + ".");
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		APIUtils.deleteRequest(QUEUES_API_URL+"/"+queueId, properties);
	}
	
	/**
	 * Updates an queue.
	 * @param queue Queue. Must have an id. 
	 * @return updated queue.
	 * @throws AgendizeException
	 */
	public Queue update(Queue queue) throws AgendizeException {
		logger.info("Update queue with id = " + queue.getId() + ".");
		if(queue.getId()==null){
			throw new AgendizeException("The queue object must have an id to be updated.");
		}
		Properties properties = new Properties();
		properties.put(API_KEY_V2, apiKey);
		properties.put(TOKEN, token);
		String urlString = QUEUES_API_URL + "/" + queue.getId();
		String json = AgendizeQueuesObjectHelper.queueToJSONObject(queue).toString();
		String queueString = APIUtils.putRequest(urlString, json, properties);
		System.out.println("++++"+queueString);
		JSONObject queueJson =  new JSONObject(queueString);
		return AgendizeQueuesObjectHelper.jsonObjectToQueue(queueJson);
	}
}
