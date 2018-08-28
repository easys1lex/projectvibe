package alerts;

public class ErrorMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5734383836922364512L;

	public ErrorMessage(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		super.type = "ERROR";
	}

}
