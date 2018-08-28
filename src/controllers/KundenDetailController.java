package controllers;

import java.io.IOException;

import application.Main;
import daten.Kunde;
import ereignisse.Ereignis;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class KundenDetailController extends Controller {
	public KundenDetailController() {
		super(Main.getHauptController().getMain());
	}

	private Kunde kunde = null;

	@FXML
	private void initialize() {
		// force the field to be numeric only
		lTelefonnummer.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					lTelefonnummer.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	@FXML
	void showEreignis(MouseEvent event) {
		Ereignis e = ereigisListe.getSelectionModel().getSelectedItem();
		if(e!=null) {
			System.out.println(e.toString());
		}
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
	private ScrollPane scrollPane;

	@FXML
	private TextField lTelefonnummer;

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
	private ToggleButton tWichtig;

	@FXML
	private MenuItem bNotiz;

	@FXML
	private ListView<Ereignis> ereigisListe;

	@FXML
	private TextField lEmail;

	@FXML
	private TextField lNachname;

	@FXML
	private GridPane gridPane;

	
	void deleteSelectedEreignis() {

	}

	@FXML
	void createEreignis(ActionEvent event) {
		Ereignis neu = getMain().getMappe().insertEreignis(kunde);
		VBox root;
		try {
			root = (VBox) FXMLLoader.load(getClass().getResource("../views/EreignisDetailView.fxml"));
			Stage ereignisStage = new Stage();
			ereignisStage.setTitle("Ereignis mit der ID: "+neu.getEreignisID().get());
			ereignisStage.setScene(new Scene(root));
			ereignisStage.setWidth(600);
			ereignisStage.setHeight(600);
			ereignisStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}

	@FXML
	void createNotiz(ActionEvent event) {
		getMain().getNotizController().erstelleNotiz(kunde.notizListe);
		switchToNotizView(event);
	}

	@FXML
	void switchToKundenView(ActionEvent event) {
		scrollPane.setContent(gridPane);
	}

	@FXML
	void switchToEreignisView(ActionEvent event) {

	}

	@FXML
	void switchToNotizView(ActionEvent event) {

		FXMLLoader notizLoader = new FXMLLoader(getClass().getResource("../views/NotizView.fxml"));
		try {
			BorderPane notizRoot = (BorderPane) notizLoader.load();
			notizRoot.setTop(null);
			((NotizController) notizLoader.getController()).setListe(kunde.notizListe);
			((NotizController) notizLoader.getController()).updateNotizView();
			scrollPane.setContent(notizRoot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		switchToKundenView(null);
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
		kunde = k;
		if (k != null) {
			lKundenNummer.setText(Integer.toString(k.getKundenNummer().get()));
			lFirma.setText(k.getFirma().get());
			lNachname.setText(k.getName().get());
			lVorname.setText(k.getVorName().get());
			lEmail.setText(k.getEmail().get());
			lAnschrift.setText(k.getAnschrift().get());
			lTelefonnummer.setText(Long.toString(k.getTelefonnummer().get()));
			tWichtig.setSelected(k.getIsFavorit().get());
			ereigisListe.setItems(k.ereignisListe);
		} else {
			System.err.println("NOKUNDE");
			lKundenNummer.setText("");
			lFirma.setText("");
			lNachname.setText("");
			lVorname.setText("");
			lEmail.setText("");
			lAnschrift.setText("");
			lTelefonnummer.setText("");
			tWichtig.setSelected(false);
			ereigisListe.setItems(null);
		}
		disableBearbeiten();
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
		bNeueNotiz.setDisable(setlocked);
		bNeuesEreignis.setDisable(setlocked);
		bKunden.setDisable(setlocked);
		bNotiz.setDisable(setlocked);
		bEreignisse.setDisable(setlocked);
	}

}
