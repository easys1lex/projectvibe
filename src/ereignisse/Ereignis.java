package ereignisse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import daten.Notiz;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Ereignis implements Serializable{

	private static final long serialVersionUID = 7468968851031954761L;
	private transient LocalDateTime time;
	private transient LocalDateTime timeTermin;
	private transient SimpleIntegerProperty ereignisID;
	private transient SimpleStringProperty ereignisTitel;
	private transient SimpleStringProperty ereignisInhalt;
	private transient SimpleLongProperty created;
	private transient SimpleLongProperty termin;
	public transient ObservableList<Notiz> notizListe;
	private  Notiz[] notizArray;
	
	public SimpleIntegerProperty getEreignisID() {
		return ereignisID;
	}

	public void setEreignisID(SimpleIntegerProperty ereignisID) {
		this.ereignisID = ereignisID;
	}

	public SimpleStringProperty getEreignisTitel() {
		return ereignisTitel;
	}

	public void setEreignisTitel(SimpleStringProperty ereignisTitel) {
		this.ereignisTitel = ereignisTitel;
	}

	public SimpleStringProperty getEreignisInhalt() {
		return ereignisInhalt;
	}

	public void setEreignisInhalt(SimpleStringProperty ereignisInhalt) {
		this.ereignisInhalt = ereignisInhalt;
	}

	public SimpleLongProperty getCreated() {
		return created;
	}

	public SimpleLongProperty getTermin() {
		return termin;
	}

	public void setTermin(SimpleLongProperty termin) {
		this.termin = termin;
	}

	public void setCreated(SimpleLongProperty created) {
		this.created = created;
	}

	public Notiz[] getNotizArray() {
		return notizArray;
	}

	public void setNotizArray(Notiz[] notizArray) {
		this.notizArray = notizArray;
	}

	public ObservableList<Notiz> getNotizListe() {
		return notizListe;
	}
	
	public void setTermin(long termin) {
		this.termin = new SimpleLongProperty(termin);
		
	}
	
	public Ereignis(int ereignisID, String ereignisTitel, String ereignisInhalt) {
		super();
		this.ereignisID = new SimpleIntegerProperty(ereignisID);
		this.ereignisTitel = new SimpleStringProperty(ereignisTitel);
		this.ereignisInhalt = new SimpleStringProperty(ereignisInhalt);
		this.created = new SimpleLongProperty(System.currentTimeMillis());
		this.termin = new SimpleLongProperty(System.currentTimeMillis());
		timeTermin = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.termin.get()), ZoneId.systemDefault());
		time = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.created.get()), ZoneId.systemDefault());
		notizListe = FXCollections.observableArrayList();
	}
	
	public Ereignis(int id, String titel, String inhalt, long erstellt, long termin2) {
		this(id, titel, inhalt);
		this.setTermin(termin2);
		this.getCreated().set(erstellt);
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(this.ereignisID.get());
		s.writeObject(this.ereignisTitel.get());
		s.writeObject(this.ereignisInhalt.get());
		s.writeLong(this.created.get());
		s.writeLong(this.termin.get());
		notizArray = notizListe.toArray(new Notiz[notizListe.size()]);
		s.writeObject(notizArray);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.ereignisID = new SimpleIntegerProperty(s.readInt());
		this.ereignisTitel = new SimpleStringProperty((String) s.readObject());
		this.ereignisInhalt = new SimpleStringProperty((String) s.readObject());
		this.created = new SimpleLongProperty(s.readLong());
		this.termin = new SimpleLongProperty(s.readLong());
		this.notizListe = FXCollections.observableArrayList();
		this.notizArray = (Notiz[]) s.readObject();
		timeTermin = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.created.get()), ZoneId.systemDefault());
		time = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.created.get()), ZoneId.systemDefault());
		for (Notiz n : notizArray) {
			notizListe.add(n);
		}
	}
	
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		timeTermin = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.termin.get()), ZoneId.systemDefault());
		time = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.created.get()), ZoneId.systemDefault());
		return (time.format(dtf)+" ["+getClass().getSimpleName()+"] "+getEreignisID().get()+"; \t \""+getEreignisTitel().get()+"\"\t Inhalt = \""+getEreignisInhalt().get()+"\" Termin am: "+timeTermin.format(dtf)+".");
	}
}
