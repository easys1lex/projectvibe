package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FensterController extends Controller {
    public FensterController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}


	@FXML
    private BorderPane boxPane;
	
	@FXML
	private void initialize() {
	}
	

	public void addAlertlabel(Label alertLabel) {
		boxPane.setBottom(alertLabel);
	}

}
