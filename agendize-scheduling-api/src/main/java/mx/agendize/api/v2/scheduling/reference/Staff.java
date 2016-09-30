package mx.agendize.api.v2.scheduling.reference;

import java.util.List;

import mx.agendize.api.v2.reference.Person;

/**
 * Class representing a staff member.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Staff extends Person{
	
	/** Staff member color, for dashboard displaying. Format: xxxxxx. The default value is EFEFEF. */
	private String color; 
	/** Working hours of the staff member. */ 
	private List<WorkingHours> workingHours;
	/** Service list of the staff member. */
	private List<Service> services;

	/** Default constructor. */
	public Staff() {
	}
	
	public Staff(int id, String firstName, String lastName) {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the workingHours
	 */
	public List<WorkingHours> getWorkingHours() {
		return workingHours;
	}
	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(List<WorkingHours> workingHours) {
		this.workingHours = workingHours;
	}
	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return services;
	}
	/**
	 * @param services the services to set
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (getFirstName() != null)
			builder.append(getFirstName()).append(" ");
		if (getLastName() != null)
			builder.append(getLastName()).append("(");
		if (id != null)
			builder.append("id=").append(id).append(")");
		if (color != null)
			builder.append("color=").append(color).append(", ");
		if (workingHours != null)
			builder.append("workingHours=").append(workingHours).append(", ");
		if (services != null)
			builder.append("services=").append(services).append(", ");
		return builder.toString();
	}
}
