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
	
	//Für später
	public transient ObservableList<Message> alertListe;
	public transient ObservableList<Notiz> notizListe;
	
	private Message[] alertArray;
	private Notiz[] notizArray; 
	//
	
	private Kunde[] kundenArray;
	private int kundenAnzahl;
	private int ereignisAnzahl;

	public Arbeitsmappe() {
		kundenAnzahl = 0;
		ereignisAnzahl = 0;
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
		s.writeInt(ereignisAnzahl);
		s.writeObject(kundenArray);
		notizArray = notizListe.toArray(new Notiz[notizListe.size()]);
		s.writeObject(notizArray);
		alertArray = alertListe.toArray(new Message[alertListe.size()]);
		s.writeObject(alertArray);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.kundenListe = FXCollections.observableArrayList();
		this.kundenAnzahl = s.readInt();
		this.ereignisAnzahl = s.readInt();
		this.kundenArray = (Kunde[]) s.readObject();
		for (Kunde k : kundenArray) {
			kundenListe.add(k);
		}
		this.notizListe = FXCollections.observableArrayList();
		this.notizArray = (Notiz[])s.readObject();
		for(Notiz n : notizArray) {
			notizListe.add(n);
		}
		this.alertListe = FXCollections.observableArrayList();
		this.alertArray = (Message[])s.readObject();
		for(Message m : alertArray) {
			alertListe.add(m);
		}
	}
	public int returnKundenAnzahl() {
		return ++this.kundenAnzahl;
	}

	public int returnEreignisAnzahl() {
		// TODO Auto-generated method stub
		return ++this.ereignisAnzahl;
	}

	public void setKundenAnzahl(int anzahl) {
		// TODO Auto-generated method stub
		this.kundenAnzahl = anzahl;
	}

	public void setEreignisAnzahl(int anzahl) {
		// TODO Auto-generated method stub
		this.ereignisAnzahl = anzahl;
		
	}

}
