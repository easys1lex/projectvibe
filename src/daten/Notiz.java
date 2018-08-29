package daten;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import controllers.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class Notiz implements Serializable {

	private static final long serialVersionUID = -2763451768098281520L;
	private transient SimpleStringProperty titel;
	private transient SimpleStringProperty inhalt;

	public SimpleStringProperty getTitel() {
		return titel;
	}
	public String toString() {
		return this.titel.get()+"\t"+this.inhalt.get();
		
	}

	public void setTitel(SimpleStringProperty titel) {
		this.titel = titel;
	}

	public SimpleStringProperty getInhalt() {
		return inhalt;
	}

	public void setInhalt(SimpleStringProperty inhalt) {
		this.inhalt = inhalt;
	}

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
		inhalt = new SimpleStringProperty((String) s.readObject());
	}

}
