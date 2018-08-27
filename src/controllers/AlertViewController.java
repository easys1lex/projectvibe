package controllers;

import alerts.Message;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class AlertViewController extends Controller{
	private ObservableList<Message> alerts = null;

    @FXML
    private ListView<Message> boxAppend;
    
    @FXML
    private void initialize() {
    	avc = this;
    	alerts = getMain().getMappe().alertListe;
    	boxAppend.setItems(alerts);
    }

	public void addMessage(Message m) {
		alerts.add(m);
	}
    
    
}
