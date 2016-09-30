package mx.agendize.api.v2.scheduling.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a setting. See <a href="http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp">http://developers.agendize.com/v2/scheduling/reference/settings/index.jsp</a>
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 */
public class Setting {

	/**
	 * List of scheduling modes.
	 */
	public enum SchedulingMode{
		SERVICE("service"),
		SERVICE_STAFF("service-staff"),
		STAFF_SERVICE("staff-service"),
		RESOURCE("resource"),
		QUICKSTART("quickStart");
		
		private String code;

		/**
		 * @param code
		 */
		private SchedulingMode(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, SchedulingMode> lookup = new HashMap<String, SchedulingMode>();
	    
	    static {
	        for (SchedulingMode c : EnumSet.allOf(SchedulingMode.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Scheduling Mode by its code.
	     * @param s code. ex: "staff-service".
	     * @return The Country.
	     */
	    public static SchedulingMode get(String s) {
	        return lookup.get(s);
	    }
	}
	
	/**
	 * List of setting groups.
	 */
	public enum SettingGroup{
		NOTIFICATIONS("notifications"),
		ITEMS("items"),
		CONTACT("contact"),
		RULES("rules"),
		WIDGET("widget");
		
		private String code;
		
		/**
		 * @param code
		 */
		private SettingGroup(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, SettingGroup> lookup = new HashMap<String, SettingGroup>();
	    
	    static {
	        for (SettingGroup c : EnumSet.allOf(SettingGroup.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Setting Group by its code.
	     * @param s code. ex: "rules".
	     * @return The Country.
	     */
	    public static SettingGroup get(String s) {
	        return lookup.get(s);
	    }
	}

	private SettingGroup group;
	private String id;
	private String value;

	/**
	 * @param id
	 * @param group
	 * @param value
	 */
	public Setting(SettingGroup group, String id, String value) {
		super();
		this.id = id;
		this.group = group;
		this.value = value;
	}

	/** Default constructor. */
	public Setting() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the group
	 */
	public SettingGroup getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(SettingGroup group) {
		this.group = group;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Setting [");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (group != null)
			builder.append("group=").append(group).append(", ");
		if (value != null)
			builder.append("value=").append(value);
		builder.append("]");
		return builder.toString();
	}

}
