package ereignisse;

public class TreffenEreignis extends Ereignis{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875064655024619048L;

	public TreffenEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		super.setTermin(termin);
		// TODO Auto-generated constructor stub
	}

	public TreffenEreignis(int id, String titel, String inhalt, long erstellt, long termin) {
		// TODO Auto-generated constructor stub
		super(id, titel, inhalt);
		super.setTermin(termin);
		super.getCreated().set(erstellt);
	}

}
