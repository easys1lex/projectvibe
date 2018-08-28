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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.ButtonType;

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

	public void createArbeitsmappe() {
		if (getMain().getMappe() != null) {
			if (frageSaveFirst(true) == false) {
//				Falls die Aktion abgebrochen wurde, dann erstelle keine neue Arbeitsmappe.
				return;
			}
		}
		Arbeitsmappe temp = new Arbeitsmappe();
		getMain().setMappe(temp);
		getMain().setSaveFile(null);
		getMain().initializeArbeitsmappenScenes();
		if(getMain().getKundenController()==null) {
			System.out.println("kundencontroller wurde nicht initialisiert");
		}
		if(getMain().getKundenDetailController()==null) {
			System.out.println("kundendetailcontroller wurde nicht initialisiert");
		}
		
		
		if(getMain().getAlertViewController() != null) {
			getMain().getAlertViewController().addMessage(new Message("Neue Arbeitsmappe erstellt"));
			System.out.println("NEUE MESSAGE WURDE ERSTELLT");
		}else {
			System.err.println("AlertViewController ist nicht initialisiert");
		}
//		showKundenScene();
	}


	private boolean frageSaveFirst(boolean abbrechenEnabled) {
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

	public void speichereUnter() {
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
			System.err.println("Keine gültige Arbeitsmappen/Fileauswahl vorhanden.");
		}
	}

	public void openArbeitsmappeFromFile() {
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

	public void writeArbeitsMappeToFile(File file) throws IOException {
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
				System.out.println("EXIT UserApproved");
				Platform.exit();
			}
		} else {
			System.out.println("EXIT NothingToSave");
			Platform.exit();
		}
	}

	public void normalSave() {
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

	public void showStartScene() {
		getMain().getRootStage().setScene(getMain().getStartScene());
		getMain().getMenuController().updateDisable();
	}

	public void showKundenScene() {
		getMain().getRootStage().setScene(getMain().getKundenScene());
		getMain().getKundenController().initialize();
	}

	public void showNotizScene() {
		getMain().getRootStage().setScene(getMain().getNotizScene());
		getMain().getNotizController().updateNotizView();
	}
	public void showAlertScene() {
		getMain().getRootStage().setScene(getMain().getAlertScene());
	}

}
