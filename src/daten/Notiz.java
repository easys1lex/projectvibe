package daten;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class Notiz implements Serializable{
	
	private static final long serialVersionUID = -2763451768098281520L;
	public transient SimpleStringProperty titel;
	public transient SimpleStringProperty inhalt;
	
	public Notiz(String titel, String inhalt) {
		super();
		this.titel = new SimpleStringProperty(titel);
		this.inhalt = new SimpleStringProperty(inhalt);
	}
	
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeObject(titel.get());
		s.writeObject(inhalt.get());
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		titel = new SimpleStringProperty((String) s.readObject());
		inhalt = new SimpleStringProperty((String)s.readObject());
	}
	
}
