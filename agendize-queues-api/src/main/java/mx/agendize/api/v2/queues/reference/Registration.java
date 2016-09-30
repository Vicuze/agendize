package mx.agendize.api.v2.queues.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Time;

public class Registration extends AgendizeObject {

	private String author; 
	private Queue queue; 
	private Time registered;
	private QueueClient client;
	private RegistrationStatus status; 
	
	/**
	 * List of statuses.
	 */
	public enum RegistrationStatus{
		REGISTERED("registered"),
		NO_SHOW("noShow"),
		INGOING("ingoing"),
		FINISHED("finished");
		
		private String code;

		/**
		 * @param code
		 */
		private RegistrationStatus(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, RegistrationStatus> lookup = new HashMap<String, RegistrationStatus>();
	    
	    static {
	        for (RegistrationStatus c : EnumSet.allOf(RegistrationStatus.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Registration Status by its code.
	     * @param s code. ex: "ingoing".
	     * @return The Country.
	     */
	    public static RegistrationStatus get(String s) {
	        return lookup.get(s);
	    }
	}

	/**
	 * 
	 */
	public Registration() {
		super();
	}

	/**
	 * @param author
	 * @param queue
	 * @param registered
	 * @param client
	 * @param status
	 */
	public Registration(String author, Queue queue, Time registered, QueueClient client, RegistrationStatus status) {
		super();
		this.author = author;
		this.queue = queue;
		this.registered = registered;
		this.client = client;
		this.status = status;
	}

	/**
	 * @return the author
	 */
	public final String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public final void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the queue
	 */
	public final Queue getQueue() {
		return queue;
	}
	/**
	 * @param queue the queue to set
	 */
	public final void setQueue(Queue queue) {
		this.queue = queue;
	}
	/**
	 * @return the registered
	 */
	public final Time getRegistered() {
		return registered;
	}
	/**
	 * @param registered the registered to set
	 */
	public final void setRegistered(Time registered) {
		this.registered = registered;
	}
	/**
	 * @return the client
	 */
	public final QueueClient getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public final void setClient(QueueClient client) {
		this.client = client;
	}

	/**
	 * @return the status
	 */
	public final RegistrationStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(RegistrationStatus status) {
		this.status = status;
	} 
}
