package controllers;

import daten.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class KundenController extends Controller{

    @FXML
    private Button bNeuerKunde;

    @FXML
    private TableColumn<Kunde, String> kundenNameColumn;

    @FXML
    private TableColumn<Kunde, Integer> kundenIDColumn;

    @FXML
    private Button bKundeLoeschen;

    @FXML
    private TableView<Kunde> kundenTabelle;

    @FXML
    private TableColumn<Kunde,String> kundenFirmaColumn;

    @FXML
    void createNewCustomer(ActionEvent event) {
    	Kunde k = getMain().getMappe().insertKunde("Neuer Kunde", "");
    	kundenTabelle.getSelectionModel().selectLast();
    	kdc.makeEditable();
    }

    @FXML
    void deleteCutsomer(ActionEvent event) {
    	int ausgewaelterKunde = kundenTabelle.getSelectionModel().getSelectedIndex();
    	kundenTabelle.getItems().remove(ausgewaelterKunde);
    }
    @FXML
    public void initialize() {
    	System.out.println("KundenTabelle");
    	kundenNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	kundenFirmaColumn.setCellValueFactory(cellData -> cellData.getValue().getFirmaProperty());
    	kundenIDColumn.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asObject());
    	kundenTabelle.setItems(getMain().getMappe().kundenListe);
    	if (kdc !=null) {
    		System.out.println("kdc exists");
			kundenTabelle.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> kdc.update(newValue));
		}
    }

}