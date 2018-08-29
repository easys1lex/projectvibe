package ereignisse;

public class KaufEreignis extends Ereignis{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2867346279793353227L;

	public KaufEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		// TODO Auto-generated constructor stub
		super.setTermin(termin);
	}

	public KaufEreignis(int id, String titel, String inhalt, long erstellt, long termin) {
		// TODO Auto-generated constructor stub
		super(id, titel, inhalt);
		super.setTermin(termin);
		super.getCreated().set(erstellt);
	}

}
