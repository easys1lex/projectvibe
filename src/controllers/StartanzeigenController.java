package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartanzeigenController extends Controller {

	public StartanzeigenController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	@FXML
	private Button bOpen;

	@FXML
	private Button bNeu;

	@FXML
	void createNewArbeitsmappe(ActionEvent event) {
		createArbeitsmappe(null);
		showKundenScene();
	}

	@FXML
	void oeffneArbeitsmappe(ActionEvent event) {
		openArbeitsmappeFromFile();
		showKundenScene();
	}


}