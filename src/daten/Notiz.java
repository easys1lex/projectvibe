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

public class Notiz extends TitledPane implements Serializable {

	private static final long serialVersionUID = -2763451768098281520L;
	private transient SimpleStringProperty titel;
	private transient SimpleStringProperty inhalt;

	public SimpleStringProperty getTitel() {
		return titel;
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
		setupBox();
	}

	private void setupBox() {
		super.setText(titel.get());
		super.setCollapsible(false);
		TextArea a = new TextArea(inhalt.get());
		a.setEditable(true);
		a.autosize();
		a.setOnKeyTyped((event) -> {
			this.inhalt.set(a.getText());
			a.autosize();
		});
		VBox box = new VBox();
		box.getChildren().addAll(a);
		super.setContent(box);
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
		setupBox();
	}

}
