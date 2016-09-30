package mx.agendize.api.v2.scheduling.reference;

import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Time;

/**
 * Class representing an appointment.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Appointment extends AgendizeObject{

	/**
	 * List of statuses.
	 */
	public enum AppointmentStatus{
		COMPLETED("completed"),
		NO_SHOW("noShow"),
		ACCEPTED("accepted"),
		PENDING("pending"),
		DECLINED("declined"),
		CANCELLED("cancelled");
		
		private String code;

		/**
		 * @param code
		 */
		private AppointmentStatus(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, AppointmentStatus> lookup = new HashMap<String, AppointmentStatus>();
	    
	    static {
	        for (AppointmentStatus c : EnumSet.allOf(AppointmentStatus.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Appointment Status by its code.
	     * @param s code. ex: "accepted".
	     * @return The Country.
	     */
	    public static AppointmentStatus get(String s) {
	        return lookup.get(s);
	    }
	}

	/**
	 * List of statuses.
	 */
	public enum AppointmentType{
		NORMAL("normal"),
		PERSONAL("personal");
		
		private String code;

		/**
		 * @param code
		 */
		private AppointmentType(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, AppointmentType> lookup = new HashMap<String, AppointmentType>();
	    
	    static {
	        for (AppointmentType c : EnumSet.allOf(AppointmentType.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a Appointment Type by its code.
	     * @param s code. ex: "normal".
	     * @return The Country.
	     */
	    public static AppointmentType get(String s) {
	        return lookup.get(s);
	    }
	}
	
	/** Rerefence code of the appointment */
	private String reference;
	/** Company details */ 
	private Company company;
	/** Service details */ 
	private Service service;
	/** Resource details */
	private Resource resource;
	/** Staff details */
	private Staff staff;
	/** Client details */ 
	private Client client;
	/** Start time */
	private Time start;
	/** End time */
	private Time end;
	/** Creation time of the event. Read-only */
	private Time created;
	/** The filled form of the event */
	private List<SchedulingFormField> form;
	/** The status of the event */
	private AppointmentStatus status;
	/** Notes of the appointment */
	private String notes;
	/** Appointment history */
	private AppointmentHistory history;
	/** The type of the event. */
	private AppointmentType type;
	
	/**
	 * Constructor of an appointment.
	 * @param company The company.
	 * @param service The service.
	 * @param resource The resource.
	 * @param staff The staff member.
	 * @param client The client.
	 * @param start The start time.
	 * @param notes The notes.
	 */
	public Appointment(Company company, Service service, Resource resource, Staff staff, Client client, Time start, String notes, AppointmentHistory history) {
		super();
		this.company = company;
		this.service = service;
		this.resource = resource;
		this.staff = staff;
		this.client = client;
		this.start = start;
		this.notes = notes;
		this.history = history;
	}
	
	/**
	 * Empty constructor.
	 */
	public Appointment() {
	}
	
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}
	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}
	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	/**
	 * @return the staff
	 */
	public Staff getStaff() {
		return staff;
	}
	/**
	 * @param staff the staff to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	/**
	 * @return the start
	 */
	public Time getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Time start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public Time getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Time end) {
		this.end = end;
	}
	/**
	 * @return the created
	 */
	public Time getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Time created) {
		this.created = created;
	}
	/**
	 * @return the form
	 */
	public List<SchedulingFormField> getForm() {
		return form;
	}
	/**
	 * @param form the form to set
	 */
	public void setForm(List<SchedulingFormField> form) {
		this.form = form;
	}
	/**
	 * @return the status
	 */
	public AppointmentStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		if (service != null)
			builder.append(service.getName()).append(" with ");
		if (staff != null)
			builder.append(staff).append(" on ");
		if (start != null)
			builder.append(sdf.format(start.getDateTime())).append(". ");
		if (client != null)
			builder.append("Client=").append(client);
		return builder.toString();
	}

	/**
	 * @return the type
	 */
	public final AppointmentType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(AppointmentType type) {
		this.type = type;
	}

	/**
	 * @return the history
	 */
	public final AppointmentHistory getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public final void setHistory(AppointmentHistory history) {
		this.history = history;
	}
	
}
