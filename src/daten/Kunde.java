package daten;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kunde implements Serializable {

	private static final long serialVersionUID = 8377994629608746988L;
	private transient SimpleIntegerProperty kundenNummer;
	private transient SimpleStringProperty name;
	private transient SimpleStringProperty vorName;
	private transient SimpleStringProperty email;
	private transient SimpleStringProperty anschrift;
	private transient SimpleStringProperty firma;
	private transient SimpleLongProperty telefonnummer;
	private transient SimpleBooleanProperty isFavorit;

	public SimpleIntegerProperty getKundenNummer() {
		return kundenNummer;
	}

	public void setKundenNummer(SimpleIntegerProperty kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(String string) {
		this.name.set(string);;
	}

	public SimpleStringProperty getVorName() {
		return vorName;
	}

	public void setVorName(String vorName) {
		this.vorName.set(vorName);;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email.set(email);;
	}

	public SimpleStringProperty getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift.set(anschrift);
	}

	public SimpleStringProperty getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma.set(firma);;
	}

	public SimpleLongProperty getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(long telefonnummer) {
		this.telefonnummer.set(telefonnummer);
	}

	public SimpleBooleanProperty getIsFavorit() {
		return isFavorit;
	}

	public void setIsFavorit(boolean isFavorit) {
		this.isFavorit.set(isFavorit);
	}

	public Kunde(int kundenNummer, String name, String vorName,
			String email, String anschrift, String firma,
			long telefonnummer, boolean isFavorit) {
		super();
		this.kundenNummer = new SimpleIntegerProperty(kundenNummer);
		this.name = new SimpleStringProperty(name);
		this.vorName = new SimpleStringProperty(vorName);
		this.email = new SimpleStringProperty(email);
		this.anschrift = new SimpleStringProperty(anschrift);
		this.firma = new SimpleStringProperty(firma);
		this.telefonnummer = new SimpleLongProperty(telefonnummer);
		this.isFavorit = new SimpleBooleanProperty(isFavorit);
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(this.kundenNummer.get());
		s.writeObject(this.name.get());
		s.writeObject(this.vorName.get());
		s.writeObject(this.email.get());
		s.writeObject(this.anschrift.get());
		s.writeObject(this.firma.get());
		s.writeLong(this.telefonnummer.get());
		s.writeBoolean(this.isFavorit.get());
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.kundenNummer = new SimpleIntegerProperty(s.readInt());
		this.name = new SimpleStringProperty((String) s.readObject());
		this.vorName = new SimpleStringProperty((String) s.readObject());
		this.email = new SimpleStringProperty((String) s.readObject());
		this.anschrift = new SimpleStringProperty((String) s.readObject());
		this.firma = new SimpleStringProperty((String) s.readObject());
		this.telefonnummer = new SimpleLongProperty(s.readLong());
		this.isFavorit = new SimpleBooleanProperty(s.readBoolean());
	}
}