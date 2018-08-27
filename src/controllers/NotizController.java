package controllers;

import java.util.Optional;

import daten.Notiz;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class NotizController extends Controller {
	ObservableList<Notiz> notizen = null;
	public void setListe(ObservableList<Notiz> n){
		notizen = n;
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
		nc = this;
		setListe(getMain().getMappe().notizListe);
		updateNotizView();

	}

	public void updateNotizView() {
		flowPane.getChildren().clear();
		for (Notiz n : notizen) {
			
			if (((VBox) n.getContent()).getChildren().size() < 2) {
				Button deleteNotiz = new Button("Löschen");
				deleteNotiz.setOnAction((event) -> {
					notizen.remove(n);
					updateNotizView();
				});
				((VBox) n.getContent()).getChildren().add(deleteNotiz);
			}

			flowPane.getChildren().add(n);
		}
	}

}