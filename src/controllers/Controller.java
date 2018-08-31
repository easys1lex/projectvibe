package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import alerts.ErrorMessage;
import alerts.SuccessMessage;
import application.Main;
import daten.Arbeitsmappe;
import daten.Notiz;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

public class Controller {

	protected Main main;
	public Controller(Main m) {
		setMain(m);
	}

	public void setMain(Main m) {
		main = m;
	}

	public Main getMain() {
		return main;
	}

	public String pruefeLastSave() {
		File f = new File(getMain().getLastPath());
		if (f.exists() && f.isFile()) {
			FileInputStream fis;
			ObjectInputStream ois;
			String saveLocation = null;
			try {
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				saveLocation = (String) ois.readObject();
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return saveLocation;
		}
		return null;
	}

	protected void createArbeitsmappe(Arbeitsmappe a) {
		if (getMain().getMappe() != null) {
			if (frageSaveFirst(true) == false) {
//				Falls die Aktion abgebrochen wurde, dann erstelle keine neue Arbeitsmappe.
				return;
			}
		}
		Arbeitsmappe temp;
		if(a!=null) {
			temp = a;
		}else {
			temp = new Arbeitsmappe();
		}
		 
		getMain().setMappe(temp);
		getMain().setSaveFile(null);
		getMain().initializeArbeitsmappenScenes();
		showKundenScene();
//		if(getMain().getKundenController()==null) {
//			System.out.println("kundencontroller wurde nicht initialisiert");
//		}
//		if(getMain().getKundenDetailController()==null) {
//			System.out.println("kundendetailcontroller wurde nicht initialisiert");
//		}
		
		
		if(getMain().getAlertViewController() != null) {
			getMain().getAlertViewController().addMessage(new SuccessMessage("Eine neue Arbeitsmappe wurde erstellt!"));
		}else {
			System.err.println("AlertViewController ist nicht initialisiert");
		}
//		showKundenScene();
	}


	protected boolean frageSaveFirst(boolean abbrechenEnabled) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Speichere Arbeitsmappe?");
		alert.setHeaderText("Möchten Sie die derzeit ausgewählte Arbeitsmappe speichern?");
		alert.setContentText("Falls nicht werden änderungen verloren gehen!");

		ButtonType speichern = new ButtonType("Speichern");
		ButtonType speichernUnter = new ButtonType("Speichern unter");
		ButtonType ohneSpeichern = new ButtonType("Nicht speichern");
		alert.getButtonTypes().setAll(speichern, speichernUnter, ohneSpeichern);
		if (abbrechenEnabled) {
			ButtonType abbrechen = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().add(abbrechen);
		}
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == speichern) {
			normalSave();
			return true;
		} else if (result.get() == speichernUnter) {
			speichereUnter();
			return true;
		} else if (result.get() == ohneSpeichern) {
			return true;
		} else {
			return false;
		}
	}

	public Arbeitsmappe loadArbeitsMappeFromFile(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Arbeitsmappe temp = (Arbeitsmappe) ois.readObject();
		ois.close();
		fis.close();
		getMain().setSaveFile(file);
		return temp;

	}

	protected void speichereUnter() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Speichere Arbeitsmappe");
		ExtensionFilter extFilter = new ExtensionFilter("Vibe Save Files (*.vsf)", "*.vsf");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null && getMain().getMappe() != null) {
			try {
				writeArbeitsMappeToFile(file);
				getMain().setSaveFile(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			getMain().getAlertViewController().addMessage(new ErrorMessage("Die Arbeitsmappe konnte nicht gespeichert werden!"));
		}
	}

	protected void openArbeitsmappeFromFile() {
		if (getMain().getMappe() != null) {
			if (frageSaveFirst(true) == false) {
				return;
			}
		}
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Öffne Arbeitsmappe");
		ExtensionFilter extFilter = new ExtensionFilter("Vibe Save Files (*.vsf)", "*.vsf");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			try {
				getMain().setMappe(loadArbeitsMappeFromFile(file));
				getMain().setSaveFile(file);
				getMain().initializeArbeitsmappenScenes();
				showKundenScene();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.err.println("Keine gültige Arbeitsmappen/Fileauswahl vorhanden.");
		}
	}

	protected void writeArbeitsMappeToFile(File file) throws IOException {
		getMain().getAlertViewController().addMessage(new SuccessMessage("Die Arbeitsmappe wurde in ["+file.getAbsolutePath()+"] gespeichert!"));
		Arbeitsmappe a = main.getMappe();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(a);
		oos.close();
		fos.close();
		FileOutputStream fosLastFile = new FileOutputStream(new File(getMain().getLastPath()));
		ObjectOutputStream oosLastFile = new ObjectOutputStream(fosLastFile);
		oosLastFile.writeObject(file.getAbsolutePath());
		oosLastFile.close();
		fosLastFile.close();
	}

	public void exitApplication(boolean mitAbbrechen) {
		if (getMain().getMappe() != null) {
			if (frageSaveFirst(mitAbbrechen) != false) {
//				System.out.println("EXIT UserApproved");
				Platform.exit();
			}
		} else {
//			System.out.println("EXIT NothingToSave");
			Platform.exit();
		}
	}

	protected void normalSave() {
		if (getMain().getSaveFile() != null) {
			try {
				writeArbeitsMappeToFile(getMain().getSaveFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			speichereUnter();
		}
	}

	protected void showStartScene() {
		getMain().getRootStage().setScene(getMain().getStartScene());
//		getMain().getMenuController().updateDisable();
	}

	public void showKundenScene() {
		getMain().getRootStage().setScene(getMain().getKundenScene());
	}

	protected void showNotizScene() {
		getMain().getRootStage().setScene(getMain().getNotizScene());
		
	}
	protected void showAlertScene() {
		getMain().getRootStage().setScene(getMain().getAlertScene());
	}
	public ListView<Notiz> loadNotizView(ObservableList<Notiz> notizListe) {
		FXMLLoader notizLoader = new FXMLLoader(getClass().getResource("/views/NotizView.fxml"));
		ListView<Notiz>notizRoot = null;
		try {
			notizRoot = (ListView<Notiz>) notizLoader.load();
			NotizController nc = (NotizController)notizLoader.getController();
			nc.update(notizListe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notizRoot;
		
	}
	public NotizDetailController createNewNotizStage() {
		VBox root;
		FXMLLoader loader = null;
		NotizDetailController ndc = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/views/NotizDetailView.fxml"));
			root = (VBox) loader.load();
			Stage notizStage = new Stage();
			notizStage.setTitle("Notiz bearbeiten");
			notizStage.setScene(new Scene(root));
			notizStage.setWidth(600);
			notizStage.setHeight(400);
			notizStage.show();
			notizStage.setResizable(false);
			ndc = (NotizDetailController)loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ndc;
	}

}
