package alerts;

public class SuccessMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7954120664615047529L;

	public SuccessMessage(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		super.type = "AUSGEFÜHRT";
	}

}
