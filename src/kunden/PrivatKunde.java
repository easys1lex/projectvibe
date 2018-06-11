package kunden;

import java.util.Date;

public class PrivatKunde extends Kunde{
	
	public PrivatKunde(long kundenid, String email) {
		super(kundenid, email);
		// TODO Auto-generated constructor stub
	}
	private String vorname;
	private String nachname;
	private Date geburtstag;

}
