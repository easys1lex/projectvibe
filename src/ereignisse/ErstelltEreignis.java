package ereignisse;

public class ErstelltEreignis extends Ereignis{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9176927401311250779L;

	public ErstelltEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		super.setTermin(termin);
	}

	public ErstelltEreignis(int id, String titel, String inhalt, long erstellt, long termin) {
		// TODO Auto-generated constructor stub
		
		super(id, titel, inhalt);
		super.setTermin(termin);
		super.getCreated().set(erstellt);
	}

}
