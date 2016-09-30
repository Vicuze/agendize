package mx.agendize.api.v2.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing an address object.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Address {

	/**
	 * List of possible countries.
	 */
	public enum Country{
		ALBANIA("AL"),
		ALGERIA("DZ"),
		ANDORRA("AD"),
		ARGENTINA("AR"),
		ARMENIA("AM"),
		AUSTRALIA("AU"),
		AUSTRIA("AT"),
		BELGIUM("BE"),
		BANGLADESH("BD"),
		BOLIVIA("BO"),
		BOSNIA_HERZEGOVINA("BH"),
		BERMUDA("BM"),
		BOTSWANA("BW"),
		BRAZIL("BR"),
		BULGARIA("BG"),
		CANADA("CA"),
		IVORY_COAST("CI"),
		COSTA_RICA("CR"),
		CHAD("TD"),
		CHILE("CL"),
		CHINA("CN"),
		COLOMBIA("CO"),
		CROATIA("HR"),
		CYPRUS("CY"),
		CZECH_REPUBLIC("CZ"),
		DENMARK("DK"),
		ECUADOR("EC"),
		ENGLAND("EN"),
		ESTONIA("EE"),
		FINLAND("FI"),
		FRANCE("FR"),
		GREAT_BRITAIN("GB"),
		GUADELOUPE("GP"),
		GERMANY("DE"),
		GREECE("GR"),
		HONG_KONG("HK"),
		HUNGARY("HU"),
		ICELAND("IS"),
		INDIA("IN"),
		INDONESIA("ID"),
		IRAQ("IQ"),
		IRELAND("IE"),
		ISRAEL("IL"),
		IRAN("IR"),
		ITALY("IT"),
		JORDAN("JO"),
		JAPAN("JP"),
		KENYA("KE"),
		KOREA("KO"),
		KAZAKSTAN("KZ"),
		LEBANON("LB"),
		LIECHTENSTEIN("LI"),
		LITHUANIA("LT"),
		LUXEMBOURG("LU"),
		MACEDONIA("SK"),
		MADAGASCAR("MD"),
		MALAYSIA("MY"),
		MALI_REPUBLIC("ML"),
		MEXICO("MX"),
		NAMIBIA("NA"),
		NETHERLANDS("NL"),
		NORWAY("NO"),
		NEW_ZEALAND("NZ"),
		PERU("PE"),
		PHILIPINNES("PH"),
		PANAMA("PM"),
		PAKISTAN("PK"),
		POLAND("PL"),
		PORTUGAL("PT"),
		ROMANIA("RO"),
		RUSSIA("RU"),
		SENEGAL("SN"),
		SINGAPORE("SG"),
		SLOVENIA("SI"),
		SOUTH_AFRICA("ZA"),
		SOUTH_KOREA("KR"),
		SPAIN("ES"),
		SWAZILAND("SZ"),
		SWEDEN("SE"),
		SWITZERLAND("CH"),
		SAUDI_ARABIA("SA"),
		SALVADOR("SV"),
		THAILAND("TH"),
		TAIWAN("TW"),
		TANZANIA("TZ"),
		TUNISIA("TN"),
		TURKEY("TR"),
		TURKMENISTAN("TM"),
		UNITED_ARAB_EMIRATES("AE"),
		UNITED_KINGDOM("UK"),
		UNITED_STATES("US"),
		ZAMBIA("ZM"),
		ZIMBABWE("ZW");
		
		private String code;

		/**
		 * @param code
		 */
		private Country(String code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		private static final Map<String, Country> lookup = new HashMap<String, Country>();
	    
	    static {
	        for (Country c : EnumSet.allOf(Country.class))
	            lookup.put(c.getCode(), c);
	    }
	 
	    /**
	     * Get a country by its code.
	     * @param s code. ex: "US".
	     * @return The Country.
	     */
	    public static Country get(String s) {
	        return lookup.get(s);
	    }
	}
	
	/** Main street address. */
	private String street;
	/** Optional street address. */
	private String otherStreet;
	/** Zip code of address. */
	private String zipCode;
	/** State of address. */
	private String state;
	/** City of address. */
	private String city;
	/** Country of address. */
	private Country country; 

	/**
	 * Default constructor.
	 */
	public Address() {
		super();
	}
	
	/**
	 * Address constructor with all the fields.
	 * @param street Main street address.
	 * @param otherStreet Optional street address.
	 * @param zipCode Zip code of address.
	 * @param state State of address.
	 * @param city City of address.
	 * @param country Country of address.
	 */
	public Address(String street, String otherStreet, String zipCode, String state, String city, Country country) {
		super();
		this.street = street;
		this.otherStreet = otherStreet;
		this.zipCode = zipCode;
		this.state = state;
		this.city = city;
		this.country = country;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the otherStreet
	 */
	public String getOtherStreet() {
		return otherStreet;
	}
	/**
	 * @param otherStreet the otherStreet to set
	 */
	public void setOtherStreet(String otherStreet) {
		this.otherStreet = otherStreet;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	public Country getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (street != null)
			builder.append(street);
		if (otherStreet != null)
			builder.append(" - ").append(otherStreet);
		if (zipCode != null)
			builder.append(", ").append(zipCode);
		if (city != null)
			builder.append(" - ").append(city);
		if (state != null)
			builder.append(" - ").append(state);
		if (country != null)
			builder.append(", ").append(country);
		return builder.toString();
	}

}
