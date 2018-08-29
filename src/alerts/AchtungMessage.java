package alerts;

public class AchtungMessage extends Message{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5406501550336074274L;

	public AchtungMessage(String message) {
		super(message);
		super.type = "ACHTUNG";
	}

	public AchtungMessage(long created, String nachricht) {
		// TODO Auto-generated constructor stub
		super(nachricht);
		super.type = "AUSGEFÜHRT";
		super.setCreated(created);
	}

}
