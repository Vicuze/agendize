package mx.agendize.api.v2.calls.reference;

import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;

/**
 * Class representing a Click to Call button. 
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */

public class ClickToCallButton extends AgendizeObject {

	/** Name of the click-to-call button */
	private String name;
	/** Destination number of the click-to-call button */
	private String phoneNumber; 
	/** Authorized country list of caller. Uppercase Alpha-2 codes of <a href="https://en.wikipedia.org/wiki/ISO_3166-1">ISO-3166-1 standard</a>. */
	private List<String> countries;

	public ClickToCallButton(String name, String phoneNumber, List<String> countries) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.countries = countries;
	}
	public ClickToCallButton() {
		super();
	} 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<String> getCountries() {
		return countries;
	}
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
}
