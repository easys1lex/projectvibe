package controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import application.Main;
import daten.Notiz;
import ereignisse.Ereignis;
import ereignisse.ErstelltEreignis;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EreignisDetailController extends Controller {
	public EreignisDetailController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}
	private Ereignis ereignis;

	private int ereignisType = -1;

    @FXML
    private Button bEreignisLoeschen;

    @FXML
    private ChoiceBox<String> auswahlBox;

    @FXML
    private Button bEreignisSpeichern;

    @FXML
    private TextField titelArea;

    @FXML
    private ListView<Notiz> notizenAnzeige;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea beschreibungsArea;
    
    @FXML
    private void initialize() {
    	auswahlBox.setItems(FXCollections.observableArrayList("Kunde Erstellt","Kundentreffen","Kauf","Reclamation","Kontakt"));
    	auswahlBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    		public void changed(ObservableValue ov,Number value,Number new_value) {
    			ereignisType = new_value.intValue();
    		}
    		
    	});
    }
    @FXML
    void showNotiz(MouseEvent event) {
    	notizenAnzeige.getSelectionModel().getSelectedItem();

    }
    private void update(Ereignis e) {
    	this.ereignis = e;
    	switch(e.getClass().getName()) {
    	case "ErstelltEreignis":
    		auswahlBox.getSelectionModel().select(0);
    		break;
    	case "TreffenEreignis":
    		auswahlBox.getSelectionModel().select(1);
    		break;
    	case "KaufEreignis":
    		auswahlBox.getSelectionModel().select(2);
    		break;
    	case "ReclamationEreignis":
    		auswahlBox.getSelectionModel().select(3);
    		break;
    	case "KontaktEreignis":
    		auswahlBox.getSelectionModel().select(4);
    		break;
    	}
    	titelArea.setText(e.getEreignisTitel().get());
    	beschreibungsArea.setText(e.getEreignisInhalt().get());
    	dateField.setValue(Instant.ofEpochMilli(ereignis.getTermin().get()).atZone(ZoneId.systemDefault()).toLocalDate());
    	notizenAnzeige.setItems(ereignis.notizListe);
    	
    }

    @FXML
    void enterEreignis(ActionEvent event) {
    	String titel = titelArea.getText();
    	String inhalt = beschreibungsArea.getText();
    	long termin = dateField.getValue().toEpochDay();
    	switch(auswahlBox.getSelectionModel().getSelectedIndex()) {
    	case 0:
    		ereignis = new ErstelltEreignis();
    		break;
    	case 1:
    		ereignis = new TreffenEreignis();
    		break;
    	case 2:
    		ereignis = new KaufEreignis();
    		break;
    	case 3:
    		ereignis = new ReclamationEreignis();
    		break;
    	case 4:
    		ereignis = new KontaktEreignis();
    		break;
    	}
    }

    @FXML
    void deleteEreignis(ActionEvent event) {
    	
    }

}
