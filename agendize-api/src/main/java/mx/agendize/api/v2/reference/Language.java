package mx.agendize.api.v2.reference;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Possible languages for the clients
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public enum Language{
	ENGLISH("en"),
	FRENCH("fr"),
	SPANISH("es"),
	GERMAN("de"),
	PORTUGUESE("pt"),
	JAPANESE("ja"),
	NONE("none");
	
	private String code;

	/**
	 * @param code
	 */
	private Language(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	private static final Map<String, Language> lookup = new HashMap<String, Language>();
    
    static {
        for (Language c : EnumSet.allOf(Language.class))
            lookup.put(c.getCode(), c);
    }
 
    // This method can be used for reverse lookup purpose
    public static Language get(String s) {
        return lookup.get(s);
    }
}