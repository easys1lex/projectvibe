package controllers;

import java.time.Instant;
import java.time.ZoneId;

import application.Main;
import daten.Notiz;
import ereignisse.Ereignis;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NotizDetailController extends Controller{
	private Notiz notiz;
	private ObservableList<Notiz> notizListe;
	@FXML
    private TextField aTextField;

    @FXML
    private TextArea aTextArea;

    @FXML
    private Button bDeleteNotiz;

    @FXML
    private Button bSpeicherNotiz;

    @FXML
    void saveNotiz(ActionEvent event) {
    	String titel = aTextField.getText();
    	String inhalt = aTextArea.getText();
    	if(titel==null) {
    		titel = "";
    	}else if(inhalt ==null){
    		inhalt="";
    	}
    	notiz.getTitel().set(titel);
    	notiz.getInhalt().set(inhalt);
    	closeFenster(event);
    	
    }

    @FXML
    void deleteNotiz(ActionEvent event) {
    	notizListe.remove(notiz);
    	closeFenster(event);
    }
    /**
     * TODO 
     * @author 
     * @param
     * 
     */
	public NotizDetailController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	public void update(Notiz n, ObservableList<Notiz> notizListe) {
		this.notiz = n;
		this.notizListe = notizListe;
		
		if (this.notiz != null) {
			aTextField.setText(this.notiz.getTitel().get());
			aTextArea.setText(this.notiz.getInhalt().get());
		}else {
			aTextField.setText("");
			aTextArea.setText("");
		}
		
	}
	private void closeFenster(ActionEvent event) {
		// close the dialog.
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	

}
