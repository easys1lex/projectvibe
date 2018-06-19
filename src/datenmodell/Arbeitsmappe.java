package datenmodell;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kunden.BusinessKunde;
import kunden.Kunde;

public class Arbeitsmappe implements Serializable{
	
	private transient ObservableList<Kunde> kunden = FXCollections.observableArrayList();
	private Kunde[] kundenArray;
	
	public void createKundenArrayFromList(ObservableList<Kunde> kunden) {
		this.kundenArray = kunden.toArray(new Kunde[kunden.size()]);
	}

	public Kunde[] getKundenArray() {
		return kundenArray;
	}

	public void setKundenArray(Kunde[] kundenArray) {
		this.kundenArray = kundenArray;
	}

	public ObservableList<Kunde> getKunden() {
		return kunden;
	}

	public void setKunden(ObservableList<Kunde> kunden) {
		this.kunden = kunden;
	}

	public void insertBusinessKunde(String vorname, String nachname, boolean isFavorit, boolean isMale, String email,
			String anschrift, long telefonnummer, String firma) {
		// TODO Auto-generated method stub
		kunden.add(new BusinessKunde(10,vorname, nachname, isFavorit, isMale, email, anschrift, telefonnummer, firma));
	}
	
	private void writeObject(ObjectOutputStream s)throws IOException{
		Kunde[] k = this.kunden.toArray(new Kunde[kunden.size()]);
		s.writeObject(k);
	}

}
