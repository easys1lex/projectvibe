package ereignisse;

public class ErstelltEreignis extends Ereignis{

	public ErstelltEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		super.setTermin(termin);
	}

}
