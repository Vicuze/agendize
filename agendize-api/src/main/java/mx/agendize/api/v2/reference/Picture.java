package mx.agendize.api.v2.reference;

/**
 * Class representing a picture. URL and Mimetype are read only. 
 * To upload a picture, fill the "data" info with the base64 encoded png file. Example: "base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==".
 * @author <a href="mailto:victor@agendize.com">victor@agendize.com</a>
 *
 */
public class Picture {

	/** URL of the picture */
	private String url;
	/** Mime type of the picture file. */
	private String mimeType;

	/** base64 encoded png file. Use it to upload the pic. */
	private String data;
	
	/** Default constructor. */
	public Picture() {
	}
	
	/**
	 * @param data
	 */
	public Picture(String data) {
		super();
		this.data = data;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}
	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Picture [");
		if (mimeType != null)
			builder.append("mimeType=").append(mimeType).append(", ");
		if (url != null)
			builder.append("url=").append(url);
		builder.append("]");
		return builder.toString();
	}
}
