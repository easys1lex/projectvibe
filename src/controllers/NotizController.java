package controllers;

import java.util.Optional;

import application.Main;
import daten.Notiz;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class NotizController extends Controller {
	public NotizController() {
		super(Main.getHauptController().getMain());
		// TODO Auto-generated constructor stub
	}

	ObservableList<Notiz> notizen = null;

	public void setListe(ObservableList<Notiz> n) {
		notizen = n;
		updateNotizView();
	}

	@FXML
	private VBox flowPane;

	@FXML
	private Button bNeueNotiz;

	@FXML
	void createNewNotiz(ActionEvent event) {
		erstelleNotiz(getMain().getMappe().notizListe);
	}

	public void erstelleNotiz(ObservableList<Notiz> liste) {
		setListe(liste);
		notizen = liste;
		TextInputDialog dialog = new TextInputDialog("Neue Notiz");
		dialog.setTitle("Neue Notiz");
		dialog.setHeaderText("Erstelle eine neue Notiz");
		dialog.setContentText("Notizname: ");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> {
			Notiz n = new Notiz(name, "");
			liste.add(n);
		});
		updateNotizView();

	}

	@FXML
	private void initialize() {
		setListe(getMain().getMappe().notizListe);
		updateNotizView();

	}

	public void updateNotizView() {
		flowPane.getChildren().clear();
		Button deleteNotiz = null;
		for (Notiz n : notizen) {
			if (((VBox) n.getContent()).getChildren().size() < 2) {
				deleteNotiz = new Button("Löschen");
				((VBox) n.getContent()).getChildren().add(deleteNotiz);
				deleteNotiz.setOnAction((event) -> {
					notizen.remove(n);
					updateNotizView();
				});
			}
			flowPane.getChildren().add(n);
		}
	}

}