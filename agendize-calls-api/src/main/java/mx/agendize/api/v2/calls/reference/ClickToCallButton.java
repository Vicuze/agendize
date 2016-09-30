package mx.agendize.api.v2.calls.reference;

import java.util.List;

import mx.agendize.api.v2.reference.AgendizeObject;

public class ClickToCallButton extends AgendizeObject {

	private String name;
	private String phoneNumber; 
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
