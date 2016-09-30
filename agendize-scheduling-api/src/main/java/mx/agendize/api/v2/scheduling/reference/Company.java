package mx.agendize.api.v2.scheduling.reference;

import java.util.List;
import java.util.TimeZone;

import mx.agendize.api.v2.reference.Address;
import mx.agendize.api.v2.reference.AgendizeObject;
import mx.agendize.api.v2.reference.Picture;

/**
 * Class representing a company.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Company extends AgendizeObject{

	/** Name of the company. */
	private String name;
	/** The time zone of the company. Possible values can be found here. The default value is "US/Eastern". */
	private String timeZone;
	/** Email address of the company. This will appear in the automatic emails signature. */
	private String email;
	/** Phone number of the company. This will appear in the automatic emails signature. */
	private String phone;
	/** Postal address of the company. */
	private Address address;
	/** Company picture url and mime type. This will appear in the automatic emails signature. */
	private Picture picture;
	/** Company geolocation latitude coordinate. */
	private String geolocationLat;
	/** Company geolocation longitude coordinate. */
	private String geolocationLong;
	/** Web site address (URL) of the company. */
	private String websiteLink;
	/** Working hours of the company. */
	private List<WorkingHours> workingHours;

	/** Default constructor */
	public Company(){
	}

	public Company(Integer id, String name){
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
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	 * @return the geolocationLat
	 */
	public String getGeolocationLat() {
		return geolocationLat;
	}
	/**
	 * @param geolocationLat the geolocationLat to set
	 */
	public void setGeolocationLat(String geolocationLat) {
		this.geolocationLat = geolocationLat;
	}
	/**
	 * @return the geolocationLong
	 */
	public String getGeolocationLong() {
		return geolocationLong;
	}
	/**
	 * @param geolocationLong the geolocationLong to set
	 */
	public void setGeolocationLong(String geolocationLong) {
		this.geolocationLong = geolocationLong;
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
		builder.append("Company [");
		if(getId()!=null){
			builder.append(super.toString()).append(", ");
		}
		if (name != null && !"".equals(name))
			builder.append("name=").append(name).append(", ");
		if (timeZone != null && !"".equals(timeZone))
			builder.append("timeZone=").append(timeZone).append(", ");
		if (email != null && !"".equals(email))
			builder.append("email=").append(email).append(", ");
		if (phone != null && !"".equals(phone))
			builder.append("phone=").append(phone).append(", ");
		if (address != null)
			builder.append("address=").append(address).append(", ");
		if (picture != null)
			builder.append("picture=").append(picture).append(", ");
		if (geolocationLat != null && !"".equals(geolocationLat))
			builder.append("geolocationLat=").append(geolocationLat).append(", ");
		if (geolocationLong != null && !"".equals(geolocationLong))
			builder.append("geolocationLong=").append(geolocationLong).append(", ");
		if (workingHours != null)
			builder.append("workingHours=").append(workingHours);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the websiteLink
	 */
	public final String getWebsiteLink() {
		return websiteLink;
	}

	/**
	 * @param websiteLink the websiteLink to set
	 */
	public final void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}
}
