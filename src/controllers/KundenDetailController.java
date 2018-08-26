package controllers;

import daten.Kunde;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class KundenDetailController extends Controller {
	private Kunde kunde = null;

	@FXML
	private void initialize() {
		kdc = this;
		// force the field to be numeric only
		lTelefonnummer.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            lTelefonnummer.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}

	@FXML
	private TextField lFirma;

	@FXML
	private TextField lVorname;

	@FXML
	private Button bNeuesEreignis;

	@FXML
	private Button bNeueNotiz;

	@FXML
	private TextField lTelefonnummer;

	@FXML
	private ToggleButton tWichtig;

	@FXML
	private RadioButton rBearbeiten;

	@FXML
	private MenuItem bKunden;

	@FXML
	private TextField lKundenNummer;

	@FXML
	private TextField lAnschrift;

	@FXML
	private Button bSpeichern;

	@FXML
	private MenuItem bEreignisse;

	@FXML
	private MenuItem bNotiz;

	@FXML
	private TextField lEmail;

	@FXML
	private TextField lNachname;

	@FXML
	void createEreignis(ActionEvent event) {

	}

	@FXML
	void createNotiz(ActionEvent event) {

	}

	@FXML
	void switchToKundenView(ActionEvent event) {

	}

	@FXML
	void switchToEreignisView(ActionEvent event) {

	}

	@FXML
	void switchToNotizView(ActionEvent event) {

	}

	@FXML
	void turnEnablement(ActionEvent event) {
		if (rBearbeiten.isSelected()) {
			makeEditable();
		} else {
			disableBearbeiten();
		}
	}

	void disableBearbeiten() {
		lFirma.setDisable(true);
		lVorname.setDisable(true);
		lNachname.setDisable(true);
		bSpeichern.setDisable(true);
		lEmail.setDisable(true);
		rBearbeiten.setSelected(true);
		lAnschrift.setDisable(true);
		tWichtig.setDisable(true);
		rBearbeiten.setSelected(false);
		lTelefonnummer.setDisable(true);
	}

	@FXML
	void saveKunde(ActionEvent event) {
		kunde.setFirma(lFirma.getText());
		kunde.setName(lNachname.getText());
		kunde.setVorName(lVorname.getText());
		kunde.setEmail(lEmail.getText());
		kunde.setAnschrift(lAnschrift.getText());
		kunde.setTelefonnummer(Long.parseLong(lTelefonnummer.getText()));
		kunde.setIsFavorit(tWichtig.isSelected());
	}

	public void update(Kunde k) {
		if (k != null) {
			lKundenNummer.setText(Integer.toString(k.getKundenNummer().get()));
			lFirma.setText(k.getFirma().get());
			lNachname.setText(k.getName().get());
			lVorname.setText(k.getVorName().get());
			lEmail.setText(k.getEmail().get());
			lAnschrift.setText(k.getAnschrift().get());
			lTelefonnummer.setText(Long.toString(k.getTelefonnummer().get()));
			tWichtig.setSelected(k.getIsFavorit().get());
		} else {
			lKundenNummer.setText("");
			lFirma.setText("");
			lNachname.setText("");
			lVorname.setText("");
			lEmail.setText("");
			lAnschrift.setText("");
			lTelefonnummer.setText("");
			tWichtig.setSelected(false);
		}
		disableBearbeiten();
		kunde = k;
	}

	public void makeEditable() {
		lFirma.setDisable(false);
		lVorname.setDisable(false);
		lNachname.setDisable(false);
		bSpeichern.setDisable(false);
		lEmail.setDisable(false);
		lTelefonnummer.setDisable(false);
		lAnschrift.setDisable(false);
		tWichtig.setDisable(false);
		rBearbeiten.setSelected(true);
		lockBearbeiten(false);
	}

	public void lockBearbeiten(boolean setlocked) {
		rBearbeiten.setDisable(setlocked);
	}
	


}
