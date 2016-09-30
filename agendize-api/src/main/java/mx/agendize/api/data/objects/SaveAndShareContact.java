package mx.agendize.api.data.objects;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a "Save And Share an Address" button.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class SaveAndShareContact extends ButtonDetails {

	private List<String> medias = new ArrayList<String>();
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String postalCode;
	private String city;
	private String country;
	private String company;
	private String companyAddress;
	private String companyPostalCode;
	private String companyCity;
	private String companyCountry;
	private String companyPhone;
	private String companyFax;
	private String companyEmail;
	private String siteUrl;
	private String jobTitle;

	public SaveAndShareContact(NodeList nl) {
		super(nl);
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				switch (element.getAttribute("name")) {
				case "media":
					String[] mediasArray = element.getAttribute("media").split(",");
					for(int j = 0;j<mediasArray.length;j++){
						medias.add(mediasArray[j]);
					}
					break;
				case "firstname":
					firstName = element.getAttribute("value");
					break;
				case "lastname":
					lastName = element.getAttribute("value");
					break;
				case "email":
					email = element.getAttribute("value");
					break;
				case "phone":
					phone = element.getAttribute("value");
					break;
				case "mobile":
					mobile = element.getAttribute("value");
					break;
				case "address":
					address = element.getAttribute("value");
					break;
				case "postal-code":
					postalCode = element.getAttribute("value");
					break;
				case "city":
					city = element.getAttribute("value");
					break;
				case "country":
					country = element.getAttribute("value");
					break;
				case "company":
					company = element.getAttribute("value");
					break;
				case "company-address":
					companyAddress = element.getAttribute("value");
					break;
				case "company-postal-code":
					companyPostalCode = element.getAttribute("value");
					break;
				case "company-city":
					companyCity = element.getAttribute("value");
					break;
				case "company-country":
					companyCountry = element.getAttribute("value");
					break;
				case "company-phone":
					companyPhone = element.getAttribute("value");
					break;
				case "company-fax":
					companyFax = element.getAttribute("value");
					break;
				case "company-email":
					companyEmail = element.getAttribute("value");
					break;
				case "site-url":
					siteUrl = element.getAttribute("value");
					break;
				case "job-title":
					jobTitle = element.getAttribute("value");
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * @return the medias
	 */
	public List<String> getMedias() {
		return medias;
	}
	/**
	 * @param medias the medias to set
	 */
	public void setMedias(List<String> medias) {
		this.medias = medias;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * @return the companyPostalCode
	 */
	public String getCompanyPostalCode() {
		return companyPostalCode;
	}
	/**
	 * @param companyPostalCode the companyPostalCode to set
	 */
	public void setCompanyPostalCode(String companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}
	/**
	 * @return the companyCity
	 */
	public String getCompanyCity() {
		return companyCity;
	}
	/**
	 * @param companyCity the companyCity to set
	 */
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	/**
	 * @return the companyCountry
	 */
	public String getCompanyCountry() {
		return companyCountry;
	}
	/**
	 * @param companyCountry the companyCountry to set
	 */
	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}
	/**
	 * @return the companyPhone
	 */
	public String getCompanyPhone() {
		return companyPhone;
	}
	/**
	 * @param companyPhone the companyPhone to set
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	/**
	 * @return the companyFax
	 */
	public String getCompanyFax() {
		return companyFax;
	}
	/**
	 * @param companyFax the companyFax to set
	 */
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}
	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}
	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
}
