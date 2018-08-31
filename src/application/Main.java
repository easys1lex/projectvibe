package application;

import java.io.File;
import java.io.IOException;

import controllers.AlertViewController;
import controllers.Controller;
import controllers.KundenController;
import controllers.KundenDetailController;
import controllers.NotizDetailController;
import controllers.StartanzeigenController;
import daten.Arbeitsmappe;
import daten.Notiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 
 * Dies ist die Hauptklasse, welche für das Starten der Application zuständis
 * ist und wichtige Objekte wir z.B. die Arbeitsmappe des Programmes speichert.
 * 
 * Sie erfüllt auch die Funktion den Großteil der views to Erstellen, Scenen zu
 * initialisieren und die entsprechenden ScenenController zu speichern, damit
 * andere Objekte anstöße zum Updaten der Scene geben können.
 *
 */

public class Main extends Application {
//	Der Dateiname, in dem der letzte Speicherort abgespeichert ist.
	private static String lastPath = "savelink.ser";

//	Der Hauptcontroller, welches für Persisten und das Wechseln von Scenes verantwortlich ist
	private static Controller hauptController;

//	Die wichtigste Controller, welche nur einmal erstellt werden.
	private KundenDetailController kdc;
	private KundenController kc;
	private StartanzeigenController sac;
	private AlertViewController avc;
	
	
//	Die Arbeitsmappe, welche von den Controllern bearbeitet wird
	Arbeitsmappe a = null;
//	Das SaveFile, in welchem bei einem Quicksave die Arbeitsmappe gespeichert wird.
	private File saveFile;

//	Die einzelnen Scenen und Stages, welche von den Controlern initialisiert werden.
	Stage rootStage;
	Scene StartScene;
	Scene KundenScene;
	Scene NotizScene;
	Scene AlertScene;

	/**
	 * Im folgenden kommen entsprechende Getter und Setter für die Oben aufgeliteten
	 * Objekte. Sie sollten selbsterklärend sein.
	 */

	public static Controller getHauptController() {
		return hauptController;
	}

	public static void setHauptController(Controller hauptController) {
		Main.hauptController = hauptController;
	}

	private void setKundenDetailController(KundenDetailController controller) {
		this.kdc = controller;
	}

	private void setKundenController(KundenController controller) {
		this.kc = controller;
	}

	public void setAlertScene(Scene alertScene) {
		AlertScene = alertScene;
	}

	public Scene getNotizScene() {
		return NotizScene;
	}

	public void setNotizScene(Scene notizScene) {
		NotizScene = notizScene;
	}

	public Scene getKundenScene() {
		return KundenScene;
	}

	public void setKundenScene(Scene kundenScene) {
		KundenScene = kundenScene;
	}

	public Scene getStartScene() {
		return StartScene;
	}

	public void setStartScene(Scene startScene) {
		StartScene = startScene;
	}

	public Stage getRootStage() {
		return rootStage;
	}

	public void setRootStage(Stage rootStage) {
		this.rootStage = rootStage;
	}

	public Arbeitsmappe getMappe() {
		return a;
	}

	public void setMappe(Arbeitsmappe mappe) {
		this.a = mappe;
	}

	public String getLastPath() {
		return Main.lastPath;
	}

	public Scene getAlertScene() {
		return AlertScene;
	}

	public KundenController getKundenController() {
		return kc;
	}

	public KundenDetailController getKundenDetailController() {
		return this.kdc;
	}

	public AlertViewController getAlertViewController() {
		return this.avc;
	}

	public File getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}

	private void setAlertController(AlertViewController controller) {
		this.avc = controller;
	}

	/**
	 * Dies ist die Start klasse, welche bei JavaFX Projekten sozusagen die Main()
	 * methode darstellt. Sie Erstellt ein Fenster, überprüft ob es schon ein
	 * letzten Speicherstand gibt und falls nicht, dann wird der Startscreen
	 * Initialisiert. Wenn diese Methode abgeshossen ist, dann wartet das Programm
	 * auf Nutzeranweisungen, welche dann von den einzelnen Controllern bearbeitet
	 * werden.
	 */
	@Override
	public void start(Stage primaryStage) {
//		Erstelle den Hauptcontroller, welcher für das Laden und speichern von rbeitsmappen zuständig ist.
		hauptController = new Controller(this);
//		initialisire die RootStage dieser main.
		setRootStage(primaryStage);
//		Übergebe diese Main allen Controllern, damit diese auf das Datenmodell zugreifen können.
//		Die Controller erben von dem Hauptcontroller. Somit könenn sie aud die Main zugreifen. 
		hauptController.setMain(this);
//		Erstelle die JavaFX GUI
		rootStage = createWindow(rootStage);
//		Überprüfe, ob schon eine Arbeitsmappe existiert, falls keine existiert lege den startbildschirm fest.
		String lastSaveLocation = hauptController.pruefeLastSave();
		if (lastSaveLocation != null && new File(lastSaveLocation).exists()) {
//			Es existiert ein Save, der Die arbeitsmappe aus dem Save lädt.
			try {
				a = hauptController.loadArbeitsMappeFromFile(new File(lastSaveLocation));
//				Arbeitsmappen Scnene werden initialisiert
				initializeArbeitsmappenScenes();
//				Dem nutzer wird nun die KundenScene angezeigt
				hauptController.showKundenScene();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Fehler beim laden. veruche ein anderes File");
				e.printStackTrace();
			}
		} else {
//			Es wurde kein letzter Save gefunden.
//			Dem Nutzer wird der Start Sceen angezeigt.
			initialisiereStartScreen();
			getRootStage().setScene(getStartScene());
		}
	}



	/**
	 * Beschreibung: Diese Methode Initialisiert alle HauptScenen.
	 * Sie dient als Zusammenfassung der einzelenen Initialisierungen.
	 */
	public void initializeArbeitsmappenScenes() {
		initialisiereKundenScene();
		initialisiereAlertScene();
		initialisiereNotizScene();
	}
	
	/**
	 * Beschreibung: Diese Methode Initialisiert den StartScene Das Startscene-
	 * Objekt des Main Objectes wird iniziiert.
	 */
	private void initialisiereStartScreen() {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/views/Startanzeige.fxml"));
			root.setTop(createMenuBar());
			setStartScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Beschreibung: Diese Methode Initialisiert ies NotizScene. Das NotizScene-
	 * Objekt des Main Objectes wird iniziiert.
	 */
	private void initialisiereNotizScene() {
		try {
			FXMLLoader notizLoader = new FXMLLoader(getClass().getResource("/views/Fenster.fxml"));
			BorderPane notizRoot = (BorderPane) notizLoader.load();
			Button b = new Button("Erstelle Notiz");
			b.setOnAction(click -> {
				Notiz n = new Notiz("Neue Arbeitsmappen Notiz", "");
				getMappe().notizListe.add(n);
				NotizDetailController ndc = hauptController.createNewNotizStage();
				ndc.update(n, getMappe().notizListe);
			});
			notizRoot.setTop(createMenuBar());
			ListView<Notiz> lv = hauptController.loadNotizView(getMappe().notizListe);
			notizRoot.setCenter(new VBox(b, lv));
			setNotizScene(new Scene(notizRoot));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Beschreibung: Diese Methode Initialisiert die KundenScene. Das KundenScene-
	 * Objekt des Main Objectes wird iniziiert.
	 */
	private void initialisiereKundenScene() {
		try {
			FXMLLoader detailLoader = new FXMLLoader(getClass().getResource("/views/KundenDetailAnzeige.fxml"));
			VBox kundenDetail = (VBox) detailLoader.load();
			setKundenDetailController(detailLoader.getController());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Kundenanzeige.fxml"));
			BorderPane kundenRoot = (BorderPane) loader.load();
			kundenRoot.setTop(createMenuBar());
			SplitPane sp = (SplitPane) kundenRoot.getCenter();
			sp.getItems().add(kundenDetail);
			setKundenController(loader.getController());
			setKundenScene(new Scene(kundenRoot));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Beschreibung: Diese Methode Initialisiert die AlertScene. Das AlertScene-
	 * Objekt des Main Objectes wird iniziiert.
	 */
	private void initialisiereAlertScene() {
		try {
			FXMLLoader alertLoader = new FXMLLoader(getClass().getResource("/views/AlertView.fxml"));
			BorderPane AlertRoot = (BorderPane) alertLoader.load();
			AlertRoot.setTop(createMenuBar());
			setAlertController(alertLoader.getController());
			Scene temp = new Scene(AlertRoot);
			setAlertScene(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Beschreibung: Diese Methode Erstellt die FensterStage, in elcher dann alle Scenen gezeigt werden.
	 * @return Stage, auf welcher die GUI läuft.
	 * @throws Exception e
	 */
	public Stage createWindow(Stage primaryStage) {
		try {
			FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("/views/Fenster.fxml"));
			BorderPane root = (BorderPane) rootLoader.load();
			Scene scene = new Scene(root);
			root.setTop(createMenuBar());
			primaryStage.setTitle("Customer Relationship Management");
			primaryStage.setScene(scene);
			primaryStage.setWidth(1200);
			primaryStage.setHeight(800);
			primaryStage.setOnCloseRequest((WindowEvent event) -> {
				hauptController.exitApplication(false);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return primaryStage;
	}
	
	
	/**
	 * Beschreibung: Diese Methode erstellt eine neue MenuBar
	 * @return MenuBar, welche dann von den anderen 
	 */
	public MenuBar createMenuBar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MenuBar.fxml"));
			MenuBar menu = (MenuBar) loader.load();
			return menu;
		} catch (Exception e) {
			e.printStackTrace();
			return new MenuBar();
		}
	}
	/**
	 * 
	 * Die main Methode -> Selbsterklärend
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
