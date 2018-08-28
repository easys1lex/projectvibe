package alerts;

public class InfoMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 377212834559433953L;

	public InfoMessage(String message) {
		super(message);
		super.type = "INFO";
	}
	

}
