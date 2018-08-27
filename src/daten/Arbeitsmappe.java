package daten;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import alerts.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Arbeitsmappe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4215654795173943727L;

	public transient ObservableList<Kunde> kundenListe;
	
	//F�r sp�ter
	public transient ObservableList<Message> alertListe;
	public transient ObservableList<Notiz> notizListe;
	
	private Message[] alertArray;
	private Notiz[] notizArray; 
	//
	
	private Kunde[] kundenArray;
	private int kundenAnzahl;

	public Arbeitsmappe() {
		kundenListe = FXCollections.observableArrayList();
		alertListe = FXCollections.observableArrayList();
		notizListe = FXCollections.observableArrayList();
	}

	public Kunde insertKunde() {
		Kunde temp = new Kunde(++kundenAnzahl,"","","","","Neuer Kunde",0l,false);
		kundenListe.add(temp);
		return temp;
	}
	private void writeObject(ObjectOutputStream s) throws IOException {
		kundenArray = kundenListe.toArray(new Kunde[kundenListe.size()]);
		s.defaultWriteObject();
		s.writeInt(kundenAnzahl);
		s.writeObject(kundenArray);
		notizArray = notizListe.toArray(new Notiz[notizListe.size()]);
		s.writeObject(notizArray);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.kundenListe = FXCollections.observableArrayList();
		this.kundenAnzahl = s.readInt();
		this.kundenArray = (Kunde[]) s.readObject();
		for (Kunde k : kundenArray) {
			kundenListe.add(k);
		}
		this.notizListe = FXCollections.observableArrayList();
		this.notizArray = (Notiz[])s.readObject();
		for(Notiz n : notizArray) {
			notizListe.add(n);
		}
	}
}
