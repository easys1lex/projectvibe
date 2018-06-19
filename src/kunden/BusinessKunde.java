package kunden;

import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.util.calendar.LocalGregorianCalendar.Date;

public class BusinessKunde extends Kunde{
	private transient StringProperty firma;

	public BusinessKunde(int kundenID, String vorname, String nachname, boolean isFavorit, boolean isMale, String email,
			String anschrift, long telefonnummer, String firma) {
		super(kundenID, vorname, nachname, isFavorit, isMale, email, anschrift, telefonnummer);
		this.firma = new SimpleStringProperty(firma);
	}

	public void setFirma(StringProperty firma) {
		this.firma = firma;
	}

	@Override
	public void setFirma(String firma) {
		// TODO Auto-generated method stub
		this.firma.set(firma);
	}

	@Override
	public StringProperty getFirma() {
		// TODO Auto-generated method stub
		return firma;
	}

	@Override
	public void setGeburtstag(String geburtstag) {
		// TODO Auto-generated method stub
	}

	@Override
	public StringProperty getGeburtstag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNewsletter(boolean newsletter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public BooleanProperty getNewsletter() {
		// TODO Auto-generated method stub
		return new SimpleBooleanProperty(false);
	}
	
	private void writeObject(ObjectOutputStream s)throws IOException{
		s.defaultWriteObject();
        s.writeInt(getKundenID().get());
        s.writeObject(getVorname().get());
        s.writeObject(getNachname().get());
        s.writeBoolean(getIsFavorit().get());
        s.writeBoolean(getIsMale().get());
        s.writeObject(getEmail().get());
        s.writeObject(getAnschrift().get());
        s.writeLong(getTelefonnummer().get());
        s.writeObject(getFirma().get());
        System.out.println("Erfolgreich gespeichert");
	}

}
