package kunden;

import auftraege.Auftrag;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public abstract class Kunde {
	
	private long kundennummer;
	private String kontaktEmail;
	ObservableList<Auftrag> auftraege;
	
	
	public Kunde() {
		
	}

}
