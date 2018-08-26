package controllers;

import daten.Kunde;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class KundenDetailController extends Controller {
	private Kunde kunde = null;

	@FXML
	private void initialize() {
		kdc = this;
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
	private RadioButton rBearbeiten;

	@FXML
	private TextField lKundenNummer;

	@FXML
	private TextField lEmail;

	@FXML
	private TextField lNachname;

	@FXML
	private Button bSpeichern;


	@FXML
	void createEreignis(ActionEvent event) {

	}

	@FXML
	void createNotiz(ActionEvent event) {

	}

	@FXML
	void turnEnablement(ActionEvent event) {
		if (rBearbeiten.isSelected()) {
			makeEditable();
		}else {
			disableBearbeiten();
		}
	}
	void disableBearbeiten() {
		lFirma.setDisable(true);
		lVorname.setDisable(true);
		lNachname.setDisable(true);
		bSpeichern.setDisable(true);
		lEmail.setDisable(true);
		rBearbeiten.setSelected(false);
	}

	@FXML
	void saveKunde(ActionEvent event) {
		kunde.setName(new SimpleStringProperty(lNachname.getText()));
		kunde.setFirma(new SimpleStringProperty(lFirma.getText()));
	}

	public void update(Kunde k) {
		if (k != null) {
			lKundenNummer.setText(Integer.toString(k.getKundenNummer().get()));
			lFirma.setText(k.getFirma().get());
			lNachname.setText(k.getName().get());
			lVorname.setText(k.getVorName().get());
			lEmail.setText(k.getEmail().get());
//			lAnschrift.setText();
		} else {
			lFirma.setText("");
			lVorname.setText("");
			lNachname.setText("");
			lKundenNummer.setText("");
			lEmail.setText("");
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
		rBearbeiten.setSelected(true);
		lockBearbeiten(false);
	}

	public void lockBearbeiten(boolean setlocked) {
		rBearbeiten.setDisable(setlocked);
	}

}
