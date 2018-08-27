package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import alerts.Message;
import application.Main;
import daten.Arbeitsmappe;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.ButtonType;

public class Controller {

	protected static Main main;
	protected static KundenDetailController kdc = null;
	protected static KundenController kc = null;
	protected static StartanzeigenController sac = null;
	protected static FensterController fc = null;
	protected static MenuController mc = null;
	protected static NotizController nc = null;
	protected static AlertViewController avc = null;
	public static NotizController getNotizController(){
		return nc;
	}

	public static void setMain(Main m) {
		main = m;
	}

	public static Main getMain() {
		return main;
	}

	public static String pruefeLastSave() {
		File f = new File("savelink.ser");
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
				return saveLocation;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return saveLocation;
		}
		return null;
	}

	public static void createArbeitsmappe() {
		if (main.getMappe() != null) {
			if (frageSaveFirst(true) == false) {
				return;
			}
		}
		Arbeitsmappe test = new Arbeitsmappe();
		main.setMappe(test);
		main.setSaveFile(null);
		main.initializeArbeitsmappenScenes();
		avc.addMessage(new Message("Neue Arbeitsmappe erstellt"));
		showKundenScene();
	}


	private static boolean frageSaveFirst(boolean abbrechenEnabled) {
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

	public static Arbeitsmappe load(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Arbeitsmappe temp = (Arbeitsmappe) ois.readObject();
		ois.close();
		fis.close();
		main.setSaveFile(file);
		return temp;

	}

	public static void speichereUnter() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Speichere Arbeitsmappe");
		ExtensionFilter extFilter = new ExtensionFilter("Vibe Save Files (*.vsf)", "*.vsf");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null && main.getMappe() != null) {
			try {
				save(file);
				main.setSaveFile(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else {
			System.err.println("Keine gültige Arbeitsmappen/Fileauswahl vorhanden.");
		}
	}

	public static void openArbeitsmappe() {
		if (main.getMappe() != null) {
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
				main.setMappe(load(file));
				main.setSaveFile(file);
				main.initializeArbeitsmappenScenes();
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

	public static void save(File file) throws IOException {
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

	public static void exitApplication(boolean mitAbbrechen) {
		if (main.getMappe() != null) {
			if (frageSaveFirst(mitAbbrechen) != false) {
				System.out.println("EXIT UserApproved");
				Platform.exit();
			}
		} else {
			System.out.println("EXIT NothingToSave");
			Platform.exit();
		}
	}

	public static void normalSave() {
		if (main.getSaveFile() != null) {
			try {
				save(main.getSaveFile());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			speichereUnter();
		}
	}

	public static void showStartScene() {
		main.getRootStage().setScene(main.getStartScene());
		if (kc!=null) {
			kc.updateView();
		}
	}

	public static void showKundenScene() {
		main.getRootStage().setScene(main.getKundenScene());
		kc.updateView();
	}
	public static void showNotizScene() {
		main.initializeArbeitsmappenScenes();
		main.getRootStage().setScene(main.getNotizScene());
		kc.updateView();
	}
	public static void showAlertScene() {
		main.getRootStage().setScene(main.getAlertScene());
		kc.updateView();
	}

}
