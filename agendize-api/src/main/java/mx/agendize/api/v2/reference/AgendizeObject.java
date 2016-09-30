package mx.agendize.api.v2.reference;

/**
 * All Agendize objects. Contains the id.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public abstract class AgendizeObject {

	/** Identifier of the Agendize object. Read-only. */
	protected Integer id;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (id != null)
			builder.append("id=").append(id);
		return builder.toString();
	}
	
}
