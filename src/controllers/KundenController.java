package controllers;

import daten.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class KundenController extends Controller {

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
		kundenTabelle.getSelectionModel().selectLast();
		kdc.makeEditable();
		updateView();
	}

	@FXML
	void deleteCutsomer(ActionEvent event) {
		int ausgewaelterKunde = kundenTabelle.getSelectionModel().getSelectedIndex();
		kundenTabelle.getItems().remove(ausgewaelterKunde);
		updateView();
	}

	@FXML
	public void initialize() {
		kc = this;
		System.out.println("Kundentabelle wird initialisiert.");
		kundenIDColumn.setCellValueFactory(cellData -> cellData.getValue().getKundenNummer().asObject());
		kundenFirmaColumn.setCellValueFactory(cellData -> cellData.getValue().getFirma());
		kundenNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		kundenVornameColumn.setCellValueFactory(cellData -> cellData.getValue().getVorName());
		kundenEmailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
		kundenAnschriftColumn.setCellValueFactory(cellData -> cellData.getValue().getAnschrift());
		kundenTelefonnummerColumn.setCellValueFactory(cellData -> cellData.getValue().getTelefonnummer().asObject());
		kundenFavoritColumn.setCellValueFactory(cellData -> cellData.getValue().getIsFavorit().asObject());

		kundenTabelle.setItems(getMain().getMappe().kundenListe);
		if (kdc != null) {
			System.out.println("kdc exists");
			kundenTabelle.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> kdc.update(newValue));
			updateView();
		} else {
			System.err.println("kdc does not exist");
		}
	}

	@FXML
	public void updateView() {
		if (kundenTabelle.getSelectionModel().getSelectedIndex() < 0) {
			bKundeLoeschen.setDisable(true);
			kdc.lockBearbeiten(true);
		} else {
			bKundeLoeschen.setDisable(false);
			kdc.lockBearbeiten(false);
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