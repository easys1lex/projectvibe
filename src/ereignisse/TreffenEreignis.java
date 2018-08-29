package ereignisse;

public class TreffenEreignis extends Ereignis{

	public TreffenEreignis(int ereignisID, String ereignisTitel, String ereignisInhalt, long termin) {
		super(ereignisID, ereignisTitel, ereignisInhalt);
		super.setTermin(termin);
		// TODO Auto-generated constructor stub
	}

}
