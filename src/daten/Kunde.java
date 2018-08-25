package daten;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kunde implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8377994629608746988L;
	protected transient SimpleIntegerProperty kundenNummer;
	protected transient SimpleStringProperty name;
	private transient SimpleStringProperty firma;
	public Kunde(int kundenNummer, String name, String firma) {
		this.kundenNummer = new SimpleIntegerProperty(kundenNummer);
		this.name = new SimpleStringProperty(name);
		this.firma = new SimpleStringProperty(firma);
	}

	public int getKundenNummer() {
		return this.kundenNummer.get();
	}

	public IntegerProperty getIDProperty() {
		return this.kundenNummer;
	}

	public String getName() {
		return this.name.get();
	}

	public StringProperty getNameProperty() {
		return this.name;
	}

	public String getFirma() {
		return this.firma.get();
	}

	public StringProperty getFirmaProperty() {
		return this.firma;
	}
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(this.getKundenNummer());
		s.writeObject(this.name.get());
		s.writeObject(this.firma.get());
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.kundenNummer = new SimpleIntegerProperty(s.readInt());
		this.name = new SimpleStringProperty((String) s.readObject());
		this.firma = new SimpleStringProperty((String) s.readObject());
	}

	public void setName(String x) {
		// TODO Auto-generated method stub
		this.name.set(x);
	}

	public void setFirma(String x) {
		// TODO Auto-generated method stub
		this.firma.set(x);
	}
}