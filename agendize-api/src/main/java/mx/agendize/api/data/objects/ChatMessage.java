package mx.agendize.api.data.objects;

/**
 * Class representing a chat message.
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class ChatMessage {

	private String messageId;
	private String mode; 
	private String date; 
	private String message;
	
	/**
	 * @param messageId message id
	 * @param mode mode. 0 = User → Operator, 1 = Operator → User, 5 = Shortcut.
	 * @param date message date. 
	 * @param message message text.
	 */
	public ChatMessage(String messageId, String mode, String date, String message) {
		super();
		this.messageId = messageId;
		this.mode = mode;
		this.date = date;
		this.message = message;
	}
	
	/**
	 * Default constructor.
	 */
	public ChatMessage(){
		
	}
	
	/**
	 * @return the messageId
	 */
	public final String getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public final void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the mode
	 */
	public final String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public final void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	} 
}
