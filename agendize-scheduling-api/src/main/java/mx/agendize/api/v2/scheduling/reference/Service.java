package mx.agendize.api.v2.scheduling.reference;

import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Picture;

/**
 * Class representing a Service.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Service extends AgendizeObject {

	/** Name of the service. */
	private String name;
	/** Service duration, in minutes. */
	private Integer duration;
	/** Service buffer duration, in minutes. */
	private Integer bufferDuration;
	/** Service price.*/ 
	private Integer price;
	/** If true, the client can pay with paypal. */
	private Boolean payable;
	/** Service color, for dashboard displaying. Format: xxxxxx. The default value is EFEFEF. */
	private String color; 
	/** List of staff members who have the skills for the service. */
	private List<Staff> staff; 
	/** Description of the service. */
	private String description;
	/** Service picture url and mime type. */
	private Picture picture;

	/** Default constructor. */
	public Service() {
	}
	
	public Service(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * @return the bufferDuration
	 */
	public Integer getBufferDuration() {
		return bufferDuration;
	}
	/**
	 * @param bufferDuration the bufferDuration to set
	 */
	public void setBufferDuration(Integer bufferDuration) {
		this.bufferDuration = bufferDuration;
	}
	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return the payable
	 */
	public Boolean getPayable() {
		return payable;
	}
	/**
	 * @param payable the payable to set
	 */
	public void setPayable(Boolean payable) {
		this.payable = payable;
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
	 * @return the staff
	 */
	public List<Staff> getStaff() {
		return staff;
	}
	/**
	 * @param staff the staff to set
	 */
	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(super.toString()).append(", ");
		if (name != null)
			builder.append(name);
		if (duration != null)
			builder.append(" - ").append("duration=").append(duration);
		if (bufferDuration != null)
			builder.append(" - ").append("bufferDuration=").append(bufferDuration).append(", ");
		if (price != null)
			builder.append(", ").append("price=").append(price);
		if (payable != null)
			builder.append(", ").append("payable=").append(payable);
		if (color != null)
			builder.append(", ").append("color=").append(color);
		if (staff != null)
			builder.append(", ").append("staff=").append(staff);
		if (description != null)
			builder.append(", ").append("description=").append(description);
		if (picture != null)
			builder.append(", ").append("picture=").append(picture);
		builder.append("]");
		return builder.toString();
	}
}
