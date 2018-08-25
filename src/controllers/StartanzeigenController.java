package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartanzeigenController extends Controller {

    @FXML
    private Button bOpen;

    @FXML
    private Button bNeu;

    @FXML
    void createNewArbeitsmappe(ActionEvent event) {
    	createArbeitsmappe();
    }

    @FXML
    void oeffneArbeitsmappe(ActionEvent event) {
    	openArbeitsmappe();

    }

}