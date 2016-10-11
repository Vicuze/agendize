package mx.agendize.api.v2.calls.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Time;

/**
 * Class representing a call. 
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Call extends AgendizeObject {

	/**
	 * List of statuses.
	 */
	public enum CallStatus{
		COMPLETED("completed"),
		NO_ANSWERED("noAnswered"),
		MESSAGE("message"),
		WRONG_NUMBER("wrongNumber"),
		BUSY("busy"),
		ABANDONED("abandoned");
		
		private String code;

		/**
		 * @param code
		 */
		private CallStatus(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, CallStatus> lookup = new HashMap<String, CallStatus>();
	    
	    static {
	        for (CallStatus c : EnumSet.allOf(CallStatus.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Call Status by its code.
	     * @param s code. ex: "completed".
	     * @return The Call Status.
	     */
	    public static CallStatus get(String s) {
	        return lookup.get(s);
	    }
	}
	
	/**
	 * List of types.
	 */
	public enum CallType{
		CLICK_TO_CALL("clickToCall"),
		CALL_TRACKING("callTracking");
		
		private String code;

		/**
		 * @param code
		 */
		private CallType(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, CallType> lookup = new HashMap<String, CallType>();
	    
	    static {
	        for (CallType c : EnumSet.allOf(CallType.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Call Type by its code.
	     * @param s code. ex: "clickToCall".
	     * @return The Country.
	     */
	    public static CallType get(String s) {
	        return lookup.get(s);
	    }
	}
	
	private String buttonId;
	private Integer clientId;
	private String callerNumber;
	private String calledNumber;
	private String trackingNumber;
	private Time start;
	private int duration;
	private CallStatus status;
	private CallType type;
	
	/**
	 * 
	 */
	public Call() {
		super();
	}
	
	/**
	 * @param buttonId
	 * @param clientId
	 * @param callerNumber
	 * @param calledNumber
	 * @param trackingNumber
	 * @param start
	 * @param duration
	 * @param status
	 * @param type
	 */
	public Call(String buttonId, Integer clientId, String callerNumber, String calledNumber, String trackingNumber, Time start, int duration, CallStatus status,
			CallType type) {
		super();
		this.buttonId = buttonId;
		this.clientId = clientId;
		this.callerNumber = callerNumber;
		this.calledNumber = calledNumber;
		this.trackingNumber = trackingNumber;
		this.start = start;
		this.duration = duration;
		this.status = status;
		this.type = type;
	}



	/**
	 * @return the buttonId
	 */
	public final String getButtonId() {
		return buttonId;
	}
	/**
	 * @param buttonId the buttonId to set
	 */
	public final void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	/**
	 * @return the clientId
	 */
	public final Integer getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public final void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the callerNumber
	 */
	public final String getCallerNumber() {
		return callerNumber;
	}
	/**
	 * @param callerNumber the callerNumber to set
	 */
	public final void setCallerNumber(String callerNumber) {
		this.callerNumber = callerNumber;
	}
	/**
	 * @return the trackingNumber
	 */
	public final String getTrackingNumber() {
		return trackingNumber;
	}
	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public final void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	/**
	 * @return the start
	 */
	public final Time getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public final void setStart(Time start) {
		this.start = start;
	}
	/**
	 * @return the duration
	 */
	public final int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public final void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the status
	 */
	public final CallStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public final void setStatus(CallStatus status) {
		this.status = status;
	}

	/**
	 * @return the calledNumber
	 */
	public final String getCalledNumber() {
		return calledNumber;
	}

	/**
	 * @param calledNumber the calledNumber to set
	 */
	public final void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Call [");
		if (id != null)
			builder.append("call id=").append(id).append(", ");
		if (buttonId != null)
			builder.append("buttonId=").append(buttonId).append(", ");
		builder.append("clientId=").append(clientId).append(", ");
		if (callerNumber != null)
			builder.append("callerNumber=").append(callerNumber).append(", ");
		if (calledNumber != null)
			builder.append("calledNumber=").append(calledNumber).append(", ");
		if (trackingNumber != null)
			builder.append("trackingNumber=").append(trackingNumber).append(", ");
		if (start != null)
			builder.append("start=").append(start).append(", ");
		builder.append("duration=").append(duration).append(", ");
		if (status != null)
			builder.append("status=").append(status.getCode());
		if (type != null)
			builder.append("type=").append(type.getCode());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the type
	 */
	public final CallType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(CallType type) {
		this.type = type;
	}
	
}
