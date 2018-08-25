package controllers;

import daten.Kunde;
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
		kunde.setName(lNachname.getText());
		kunde.setFirma(lFirma.getText());
	}

	public void update(Kunde k) {
		if (k != null) {
			System.out.println(k.getFirma());
			System.out.println(k.getName());
			System.out.println(Integer.toString(k.getKundenNummer()));
			lFirma.setText(k.getFirma());
			lVorname.setText("---");
			lNachname.setText(k.getName());
			lKundenNummer.setText(Integer.toString(k.getKundenNummer()));
			lEmail.setText("---");
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
	}

}
