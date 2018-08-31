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

	public InfoMessage(long created, String nachricht) {
		// TODO Auto-generated constructor stub
		super(nachricht);
		super.type = "AUSGEFÜHRT";
		super.setCreated(created);
	}
	

}
