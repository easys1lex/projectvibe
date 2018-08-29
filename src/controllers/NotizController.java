package controllers;

import application.Main;
import daten.Notiz;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class NotizController extends Controller {
	public NotizController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	ObservableList<Notiz> notizListe;
	Notiz notiz;

	@FXML
	private ListView<Notiz> notizAnzeige;

	@FXML
	private void initialize() {
		notizListe = getMain().getMappe().notizListe;
		notizAnzeige.setItems(notizListe);
	}
	
	@FXML
	void showNotiz(MouseEvent event) {
		System.out.println("FIRE");
		Notiz n = notizAnzeige.getSelectionModel().getSelectedItem();
		if(n!=null) {
			NotizDetailController ndc = createNewNotizStage();
			ndc.update(n, this.notizListe);
		}
	}

	public void update(ObservableList<Notiz> notizListe) {
		this.notizListe = notizListe;
		notizAnzeige.setItems(notizListe);
	}

}