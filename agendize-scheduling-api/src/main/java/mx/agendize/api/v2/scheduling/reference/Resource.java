package mx.agendize.api.v2.scheduling.reference;

import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Picture;

/**
 * Class representing a Resource.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Resource extends AgendizeObject{

	/** Name of the resource. */
	private String name; 
	/** Resource duration, in minutes. */
	private int duration;
	/** Resource quantity available. */
	private int count;
	/** Resource color, for dashboard displaying. */
	private String color;	
	/** Description of the resource. */
	private String description;
	/** Resource picture url and mime type. */
	private Picture picture;
	/** Resource pricing. */
	private int price;
	/** Sets if the client can pay with paypal. */
	private boolean payable = false;	
	/** Working hours of the company's resource. */
	private List<WorkingHours> workingHours;

	/** Default constructor. */
	public Resource() {
	}
	
	public Resource(int id, String name) {
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
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
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
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the payable
	 */
	public boolean isPayable() {
		return payable;
	}
	/**
	 * @param payable the payable to set
	 */
	public void setPayable(boolean payable) {
		this.payable = payable;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (name != null)
			builder.append(name);
		return builder.toString();
	}
}
