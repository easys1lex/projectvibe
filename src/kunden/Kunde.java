package kunden;

import auftraege.Auftrag;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public abstract class Kunde {
	
	private long kundenid;
	private String email;
	//ObservableList<Auftrag> auftraege;
	
	public long getKundenid() {
		return kundenid;
	}


	public void setKundenid(long kundenid) {
		this.kundenid = kundenid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


//	public ObservableList<Auftrag> getAuftraege() {
//		return auftraege;
//	}
//
//
//	public void setAuftraege(ObservableList<Auftrag> auftraege) {
//		this.auftraege = auftraege;
//	}


	public Kunde(long kundenid, String email) {
		super();
		this.kundenid = kundenid;
		this.email = email;
	}
}
