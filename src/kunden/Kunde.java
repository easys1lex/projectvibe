package kunden;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.util.calendar.LocalGregorianCalendar.Date;

public abstract class Kunde implements Serializable {

	private transient IntegerProperty kundenID;
	private transient StringProperty vorname;
	private transient StringProperty nachname;
	private transient BooleanProperty isFavorit;
	private transient BooleanProperty isMale;
	private transient StringProperty email;
	private transient StringProperty anschrift;
	private transient LongProperty telefonnummer;

	public Kunde(int kundenID, String vorname, String nachname, boolean isFavorit, boolean isMale, String email,
			String anschrift, long telefonnummer) {
		super();
		this.kundenID = new SimpleIntegerProperty(kundenID);
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.isFavorit = new SimpleBooleanProperty(isFavorit);
		this.isMale = new SimpleBooleanProperty(isMale);
		this.email = new SimpleStringProperty(email);
		this.anschrift = new SimpleStringProperty(anschrift);
		this.telefonnummer = new SimpleLongProperty(telefonnummer);
	}

	public abstract void setFirma(String firma);

	public abstract StringProperty getFirma();

	public abstract void setGeburtstag(String geburtstag);

	public abstract StringProperty getGeburtstag();

	public abstract void setNewsletter(boolean newsletter);

	public abstract BooleanProperty getNewsletter();

	@Override
	public String toString() {
		return "Kunde [kundenID=" + kundenID.get() + ", vorname=" + vorname.get() + ", nachname=" + nachname.get()
				+ ", isFavorit=" + isFavorit.get() + ", isMale=" + isMale.get() + ", email=" + email.get()
				+ ", anschrift=" + anschrift.get() + ", telefonnummer=" + telefonnummer.get() + "]";
	}

	public IntegerProperty getKundenID() {
		return kundenID;
	}

	public void setKundenID(IntegerProperty kundenID) {
		this.kundenID = kundenID;
	}

	public StringProperty getVorname() {
		return vorname;
	}

	public void setVorname(StringProperty vorname) {
		this.vorname = vorname;
	}

	public StringProperty getNachname() {
		return nachname;
	}

	public void setNachname(StringProperty nachname) {
		this.nachname = nachname;
	}

	public BooleanProperty getIsFavorit() {
		return isFavorit;
	}

	public void setIsFavorit(BooleanProperty isFavorit) {
		this.isFavorit = isFavorit;
	}

	public BooleanProperty getIsMale() {
		return isMale;
	}

	public void setIsMale(BooleanProperty isMale) {
		this.isMale = isMale;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(StringProperty anschrift) {
		this.anschrift = anschrift;
	}

	public LongProperty getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(LongProperty telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
//	private abstract void writeObject(ObjectOutputStream s) throws IOException;
}
