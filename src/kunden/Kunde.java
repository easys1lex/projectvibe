package kunden;

import auftraege.Auftrag;
import javafx.collections.ObservableArray;

public abstract class Kunde {
	
	private long kundennummer;
	private String kontaktEmail;
	
	ObservableArray<Auftrag> x;
	
	Auftrag[] auftraege;

	public Kunde() {
	}

}