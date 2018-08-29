package ereignisse;

public class KontaktEreignis extends Ereignis{

	public KontaktEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		// TODO Auto-generated constructor stub
		super.setTermin(termin);
	}

}
