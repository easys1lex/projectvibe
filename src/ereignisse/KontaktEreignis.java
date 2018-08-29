package ereignisse;

public class KontaktEreignis extends Ereignis{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4956957245833236609L;

	public KontaktEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		// TODO Auto-generated constructor stub
		super.setTermin(termin);
	}

	public KontaktEreignis(int id, String titel, String inhalt, long erstellt, long termin) {
		// TODO Auto-generated constructor stub
		super(id, titel, inhalt);
		super.setTermin(termin);
		super.getCreated().set(erstellt);
	}

}
