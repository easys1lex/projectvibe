package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NeuerKundeController extends Controller{

    @FXML
    private TextField nachnameIn;

    @FXML
    private Button abbrechenBtn;

    @FXML
    private Button anlegenBtn;

    @FXML
    private TextField firmaIn;

    @FXML
    private TextField vornameIn;
    @FXML
    void kundenAnlegen(ActionEvent event) {
    	main.getMappe().insertBusinessKunde(vornameIn.getText(), nachnameIn.getText(), false, true, "", "", 909L, firmaIn.getText());
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void kundenAnlegenAbbrechen(ActionEvent event) {

    }

    @FXML
    void vornameID(ActionEvent event) {

    }

}