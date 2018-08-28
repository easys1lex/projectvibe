package controllers;

import alerts.Message;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AlertViewController extends Controller{
	public AlertViewController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	protected ObservableList<Message> alerts = null;

    @FXML
    private ListView<Message> boxAppend;
    
    @FXML
    private void initialize() {
    	alerts = getMain().getMappe().alertListe;
    	boxAppend.setItems(alerts);
    	FXCollections.sort(alerts);
    }

	protected void addMessage(Message m) {
		alerts.add(m);
		FXCollections.sort(alerts);
	}
    
    
}
