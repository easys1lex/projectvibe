package ereignisse;

public class KaufEreignis extends Ereignis{

	public KaufEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		// TODO Auto-generated constructor stub
		super.setTermin(termin);
	}

}
