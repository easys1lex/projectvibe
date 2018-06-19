package kunden;


import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.util.calendar.LocalGregorianCalendar.Date;

public class PrivatKunde extends Kunde {
	private transient StringProperty geburtstag;
	private transient BooleanProperty newsletter;
	public PrivatKunde(int kundenID, String vorname, String nachname, boolean isFavorit, boolean isMale, String email,
			String anschrift, long telefonnummer, String geburtstag, boolean newsletter) {
		super(kundenID, vorname, nachname, isFavorit, isMale, email, anschrift, telefonnummer);
		this.geburtstag.set(geburtstag);
		this.newsletter.set(newsletter);
	}
	
	@Override
	public void setFirma(String firma) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public StringProperty getFirma() {
		// TODO Auto-generated method stub
		return new SimpleStringProperty("Privatkunde");
	}
	
	@Override
	public void setNewsletter(boolean newsletter) {
		// TODO Auto-generated method stub
		this.newsletter.set(newsletter);
	}
	@Override
	public BooleanProperty getNewsletter() {
		// TODO Auto-generated method stub
		return newsletter;
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		// TODO Auto-generated method stub
		s.defaultWriteObject();
        s.writeInt(getKundenID().get());
        s.writeObject(getVorname().get());
        s.writeObject(getNachname().get());
        s.writeBoolean(getIsFavorit().get());
        s.writeBoolean(getIsMale().get());
        s.writeObject(getEmail().get());
        s.writeObject(getAnschrift().get());
        s.writeLong(getTelefonnummer().get());
        s.writeObject(getGeburtstag().get());
        s.writeBoolean(getNewsletter().get());
	}

	@Override
	public void setGeburtstag(String geburtstag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StringProperty getGeburtstag() {
		// TODO Auto-generated method stub
		return geburtstag;
	}

}
