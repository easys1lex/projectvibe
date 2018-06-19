package controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FensterController extends Controller{
	
	@FXML
	private MenuItem speicherArbeitsmappe;
	
	@FXML
	void saveArbeitsmappe(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Speichere Arbeitsmappe");
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(getMain().getMappe());
				// .writeObject(arbeitsmappe);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
