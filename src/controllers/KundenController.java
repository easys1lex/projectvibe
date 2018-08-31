package controllers;

import java.time.Instant;
import java.util.Optional;

import alerts.AchtungMessage;
import alerts.SuccessMessage;
import application.Main;
import daten.Kunde;
import ereignisse.ErstelltEreignis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class KundenController extends Controller {

	public KundenController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	@FXML
	private TableColumn<Kunde, String> kundenVornameColumn;

	@FXML
	private RadioMenuItem rFavorit;

	@FXML
	private TableColumn<Kunde, String> kundenAnschriftColumn;

	@FXML
	private RadioMenuItem rFirma;

	@FXML
	private RadioMenuItem rTelefonnummer;

	@FXML
	private RadioMenuItem rID;

	@FXML
	private RadioMenuItem rEmail;

	@FXML
	private TableColumn<Kunde, String> kundenFirmaColumn;

	@FXML
	private RadioMenuItem rVorname;

	@FXML
	private TableColumn<Kunde, Long> kundenTelefonnummerColumn;

	@FXML
	private Button bNeuerKunde;

	@FXML
	private TableColumn<Kunde, String> kundenNameColumn;

	@FXML
	private TableColumn<Kunde, Integer> kundenIDColumn;

	@FXML
	private Button bKundeLoeschen;

	@FXML
	private RadioMenuItem rNachname;

	@FXML
	private TableColumn<Kunde, String> kundenEmailColumn;

	@FXML
	private TableView<Kunde> kundenTabelle;

	@FXML
	private TableColumn<Kunde, Boolean> kundenFavoritColumn;

	@FXML
	private RadioMenuItem rAnschrift;

	@FXML
	void createNewCustomer(ActionEvent event) {
		Kunde k = getMain().getMappe().insertKunde();
		k.ereignisListe.add(new ErstelltEreignis(getMain().getMappe().returnEreignisAnzahl(), "Neuer Kunde mit der ID: "+k.getKundenNummer().get()+" wurde erstellt", "", Instant.now().toEpochMilli()));
		kundenTabelle.getSelectionModel().selectLast();
		getMain().getKundenDetailController().makeEditable();
		updateView();
		getMain().getAlertViewController().addMessage(new SuccessMessage("Kunde mit der ID ["+k.getKundenNummer().get()+"] wurde angelegt."));
	}

	@FXML
	void deleteCutsomer(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Kunden Löschen");
		alert.setHeaderText("Kunde: ID = " + kundenTabelle.getSelectionModel().getSelectedItem().getKundenNummer().get()
				+ ", Firma = " + kundenTabelle.getSelectionModel().getSelectedItem().getFirma().get() + ".");
		alert.setContentText("Möchten Sie diesen Kunden löschen?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			int ausgewaelterKunde = kundenTabelle.getSelectionModel().getSelectedIndex();
			Kunde k = kundenTabelle.getSelectionModel().getSelectedItem();
			getMain().getAlertViewController().addMessage(new AchtungMessage(
					"Ein Kunde [" + k.getKundenNummer().get() + "] der Firma [" + k.getFirma().get() + "] wurde gelöscht."));
			kundenTabelle.getItems().remove(ausgewaelterKunde);
			updateView();
		}
	}

	@FXML
	public void initialize() {
//		System.out.println("Kundentabelle wird initialisiert.");
		kundenIDColumn.setCellValueFactory(cellData -> cellData.getValue().getKundenNummer().asObject());
		kundenFirmaColumn.setCellValueFactory(cellData -> cellData.getValue().getFirma());
		kundenNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		kundenVornameColumn.setCellValueFactory(cellData -> cellData.getValue().getVorName());
		kundenEmailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
		kundenAnschriftColumn.setCellValueFactory(cellData -> cellData.getValue().getAnschrift());
		kundenTelefonnummerColumn.setCellValueFactory(cellData -> cellData.getValue().getTelefonnummer().asObject());
		kundenFavoritColumn.setCellValueFactory(cellData -> cellData.getValue().getIsFavorit().asObject());

		kundenTabelle.setItems(getMain().getMappe().kundenListe);
		if (getMain().getKundenDetailController() != null) {
//			System.out.println("kdc exists");
			kundenTabelle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				getMain().getKundenDetailController().update(newValue);
				updateView();
			});

			updateView();
		} else {
			System.err.println("kdc does not exist");
		}
	}

	@FXML
	public void updateView() {
		if (kundenTabelle.getSelectionModel().getSelectedIndex() < 0) {
			bKundeLoeschen.setDisable(true);
			if (getMain().getKundenDetailController() != null) {
				getMain().getKundenDetailController().lockBearbeiten(true);
			}

		} else {
			bKundeLoeschen.setDisable(false);
			if (getMain().getKundenDetailController() != null) {
				getMain().getKundenDetailController().lockBearbeiten(false);
			}
		}
		kundenIDColumn.setVisible(rID.isSelected());
		kundenFirmaColumn.setVisible(rFirma.isSelected());
		kundenNameColumn.setVisible(rNachname.isSelected());
		kundenVornameColumn.setVisible(rVorname.isSelected());
		kundenEmailColumn.setVisible(rEmail.isSelected());
		kundenAnschriftColumn.setVisible(rAnschrift.isSelected());
		kundenTelefonnummerColumn.setVisible(rTelefonnummer.isSelected());
		kundenFavoritColumn.setVisible(rFavorit.isSelected());
	}

}