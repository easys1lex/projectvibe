package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kunden.Kunde;

public class KundenFensterController extends Controller{
	

    @FXML
    private Button neuerKundeBtn;
    
    @FXML
    private Button kundenLoeschenBtn;
	
    @FXML
    private TableView<Kunde> kundenTabelle;

    @FXML
    private TableColumn<Kunde, String> nachnameCol;

    @FXML
    private TableColumn<Kunde, String> vornameCol;

    @FXML
    private TableColumn<Kunde, String> firmaCol;

    @FXML
    private TableColumn<Kunde, Integer> kundenIDCol;
    
    @FXML
    private void initialize() {
    	System.out.println("KundenTabelle");
    	nachnameCol.setCellValueFactory(cellData -> cellData.getValue().getNachname());
    	firmaCol.setCellValueFactory(cellData -> cellData.getValue().getFirma());
    	vornameCol.setCellValueFactory(cellData -> cellData.getValue().getVorname());
//    	kundenIDCol.setCellValueFactory(cellData -> cellData.getValue().getKundenID());
    	kundenTabelle.setItems(main.getMappe().getKunden());
    }
    
    @FXML
    void neuenKunden(ActionEvent event) {
    	Stage neuerKundeStage = new Stage();
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NeuerKunde.fxml"));
			GridPane neuerKundeRoot = (GridPane)loader.load();
			Scene neuerKundeScene = new Scene(neuerKundeRoot,400,400);
			neuerKundeStage.setResizable(false);
			neuerKundeStage.setAlwaysOnTop(true);
			
			neuerKundeStage.setScene(neuerKundeScene);
			neuerKundeStage.setTitle("Neuen Kunden anlegen");
			neuerKundeStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void loescheKunden(ActionEvent event) {
    	int ausgewaelterKunde = kundenTabelle.getSelectionModel().getSelectedIndex();
    	kundenTabelle.getItems().remove(ausgewaelterKunde);
    }
    
}