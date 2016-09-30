package mx.agendize.api.v2.resellers.reference;

/**
 * Class representing the account profile type of the user account.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Profile {

	/** Identifier of the account profile. */
	private String id;
	/** Name of the account profile. */
	private String name;
	
	/** Default constructor. */
	public Profile() {
		super();
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public Profile(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	} 
}
