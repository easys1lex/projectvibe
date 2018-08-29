package controllers;

import java.time.Instant;
import java.time.ZoneId;

import alerts.AchtungMessage;
import alerts.ErrorMessage;
import alerts.InfoMessage;
import alerts.SuccessMessage;
import application.Main;
import daten.Notiz;
import ereignisse.Ereignis;
import ereignisse.ErstelltEreignis;
import ereignisse.KaufEreignis;
import ereignisse.KontaktEreignis;
import ereignisse.ReclamationEreignis;
import ereignisse.TreffenEreignis;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EreignisDetailController extends Controller {
	public EreignisDetailController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	private Ereignis ereignis;
	private ObservableList<Ereignis> ereignisListe;

	private int ereignisType = -1;

	@FXML
	private Button bEreignisLoeschen;

	@FXML
	private Button bNeueNotiz;

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

		auswahlBox.setItems(
				FXCollections.observableArrayList("Kunde Erstellt", "Kundentreffen", "Kauf", "Reclamation", "Kontakt"));
		auswahlBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				ereignisType = new_value.intValue();
			}

		});
	}

	@FXML
	void showNotiz(MouseEvent event) {
		Notiz n = notizenAnzeige.getSelectionModel().getSelectedItem();
		if (n != null) {
			NotizDetailController ndc = createNewNotizStage();
			ndc.update(n, ereignis.notizListe);
		}
	}

	@FXML
	void erstelleNotiz(ActionEvent event) {
		Notiz n = new Notiz("Neue Notiz", "");
		ereignis.notizListe.add(n);
		NotizDetailController ndc = createNewNotizStage();
		ndc.update(n, ereignis.notizListe);
		getMain().getAlertViewController().addMessage(new InfoMessage("Eine Neue Notiz wurde Angelegt"));
	}

	

	public void update(Ereignis e, ObservableList<Ereignis> ereignisListe) {
		this.ereignis = e;
		this.ereignisListe = ereignisListe;
		if (e!=null) {
			notizenAnzeige.setItems(e.notizListe);
		}
		if (ereignis != null) {
			switch (ereignis.getClass().getName()) {
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
			dateField.setValue(
					Instant.ofEpochMilli(ereignis.getTermin().get()).atZone(ZoneId.systemDefault()).toLocalDate());
			notizenAnzeige.setItems(ereignis.notizListe);
		}

	}

	@FXML
	void enterEreignis(ActionEvent event) {
		String titel = titelArea.getText();
		String inhalt = beschreibungsArea.getText();
		long termin;
		try {
			termin = dateField.getValue().toEpochDay();
		} catch (Exception e) {
			termin = 0;
		}
		termin = termin * 86400 * 1000;
		if (titel == null) {
			titel = "";
		} else if (inhalt == null) {
			inhalt = "";
		} else if (termin < 10) {
			termin = System.currentTimeMillis();
		}

		if (ereignis == null && ereignisListe != null) {
			getMain().getMappe().returnEreignisAnzahl();
			switch (auswahlBox.getSelectionModel().getSelectedIndex()) {
			case 0:
				getMain().getKundenDetailController().getKunde().ereignisListe
						.add(new ErstelltEreignis(getMain().getMappe().returnEreignisAnzahl(), titel, inhalt, termin));
				break;
			case 1:
				getMain().getKundenDetailController().getKunde().ereignisListe
						.add(new TreffenEreignis(getMain().getMappe().returnEreignisAnzahl(), titel, inhalt, termin));
				break;
			case 2:
				getMain().getKundenDetailController().getKunde().ereignisListe
						.add(new KaufEreignis(getMain().getMappe().returnEreignisAnzahl(), titel, inhalt, termin));
				break;
			case 3:
				getMain().getKundenDetailController().getKunde().ereignisListe
						.add(new ReclamationEreignis(getMain().getMappe().returnEreignisAnzahl(), titel, inhalt, termin));
				break;
			default:
				getMain().getKundenDetailController().getKunde().ereignisListe
						.add(new KontaktEreignis(getMain().getMappe().returnEreignisAnzahl(), titel, inhalt, termin));
				break;
			}
			getMain().getAlertViewController().addMessage(new SuccessMessage("Ein Neues Ereignis ["+titel+"] wurde Angelegt"));
//			System.out.println("EreignisErstellt");
//			System.out.println(termin);
		} else if (ereignisListe != null) {
			ereignis.getEreignisTitel().set(titel);
			ereignis.getEreignisInhalt().set(inhalt);
			ereignis.getTermin().set(termin);
			int ereignisIndex = ereignisListe.indexOf(ereignis);
	    	ereignisListe.remove(ereignis);
	    	ereignisListe.add(ereignisIndex, ereignis);
		} else {
			getMain().getAlertViewController().addMessage(new ErrorMessage("Ein Neues Ereignis ["+titel+"] konnte nicht angelegt werden."));
		}
		closeFenster(event);
	}

	private void closeFenster(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	void deleteEreignis(ActionEvent event) {
		ereignisListe.remove(ereignis);
		closeFenster(event);
		getMain().getAlertViewController().addMessage(new AchtungMessage("Das Ereignis ["+ereignis.getEreignisID().get()+"] mit dem Titel ["+ereignis.getEreignisTitel().get()+"] wurde gelöscht."));
	}

}
