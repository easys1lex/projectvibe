package application;

import java.io.File;
import java.io.IOException;

import controllers.AlertViewController;
import controllers.Controller;
import controllers.FensterController;
import controllers.KundenController;
import controllers.KundenDetailController;
import controllers.MenuController;
import controllers.NotizController;
import controllers.StartanzeigenController;
import daten.Arbeitsmappe;
import daten.Kunde;
import daten.Notiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private static String lastPath = "savelink.ser";
	private static Controller hauptController;
	protected KundenDetailController kdc;
	protected KundenController kc;
	protected StartanzeigenController sac;
	protected FensterController fc;
	protected MenuController mc;
	protected NotizController nc;
	protected AlertViewController avc;

	public static Controller getHauptController() {
		return hauptController;
	}

	public static void setHauptController(Controller hauptController) {
		Main.hauptController = hauptController;
	}

	Stage rootStage;
	Scene StartScene;
	Scene KundenScene;
	Scene NotizScene;
	Scene AlertScene;

	@Override
	public void start(Stage primaryStage) {
		hauptController = new Controller(this);
		setRootStage(primaryStage);
//		Übergebe diese Man allen Controllern, damit diese auf das Datenmodell zugreifen können.
		hauptController.setMain(this);
//		Erstelle die JavaFX GUI
		rootStage = createWindow(rootStage);
//		Überprüfe, ob schon eine Arbeitsmappe existiert, falls keine existiert lege den startbildschirm fest.
		String lastSaveLocation = hauptController.pruefeLastSave();
		if (lastSaveLocation != null) {
			try {
				a = hauptController.loadArbeitsMappeFromFile(new File(lastSaveLocation));
				initializeArbeitsmappenScenes();
				hauptController.showKundenScene();

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			initialisiereStartScreen();
			getRootStage().setScene(getStartScene());
		}
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

	MenuBar menuBar;

	public void setMenuBar(MenuBar bar) {
		this.menuBar = bar;
	}

	public MenuBar getMenuBar() {
		return this.menuBar;
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

	Arbeitsmappe a = null;
	private File saveFile;

	public File getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}

	private void initialisiereStartScreen() {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../views/Startanzeige.fxml"));
			root.setTop(createMenuBar());
			setStartScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializeArbeitsmappenScenes() {
		initialisiereKundenScene();
		initialisiereAlertScene();
		initialisiereNotizScene();
	}

	private void initialisiereNotizScene() {
		try {
			FXMLLoader notizLoader = new FXMLLoader(getClass().getResource("../views/NotizView.fxml"));
			BorderPane notizRoot = (BorderPane) notizLoader.load();
			setNotizController(notizLoader.getController());
			notizRoot.setTop(createMenuBar());
			setNotizScene(new Scene(notizRoot));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setNotizController(NotizController controller) {
		this.nc = controller;

	}

	private void initialisiereKundenScene() {
		try {
			FXMLLoader detailLoader = new FXMLLoader(getClass().getResource("../views/KundenDetailAnzeige.fxml"));
			VBox kundenDetail = (VBox) detailLoader.load();
			setKundenDetailController(detailLoader.getController());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Kundenanzeige.fxml"));
			BorderPane kundenRoot = (BorderPane) loader.load();
			kundenRoot.setTop(createMenuBar());
			SplitPane sp = (SplitPane) kundenRoot.getCenter();
			sp.getItems().add(kundenDetail);
			setKundenController(loader.getController());
			setKundenScene(new Scene(kundenRoot));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setKundenDetailController(KundenDetailController controller) {
		System.out.println("KDC SET");
		this.kdc = controller;

	}

	private void setKundenController(KundenController controller) {
		this.kc = controller;

	}

	private void initialisiereAlertScene() {
		try {
			FXMLLoader alertLoader = new FXMLLoader(getClass().getResource("../views/AlertView.fxml"));
			BorderPane AlertRoot = (BorderPane) alertLoader.load();
			AlertRoot.setTop(createMenuBar());
			setAlertController(alertLoader.getController());
			Scene temp = new Scene(AlertRoot);
			setAlertScene(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setAlertController(AlertViewController controller) {
		// TODO Auto-generated method stub
		this.avc = controller;

	}

	public void addMenuBarToScene(Scene s) {
		BorderPane x = (BorderPane) s.getRoot();
		x.setTop(getMenuBar());
	}

	public Stage createWindow(Stage primaryStage) {
		try {
			FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../views/Fenster.fxml"));
			BorderPane root = (BorderPane) rootLoader.load();
			Scene scene = new Scene(root);
			addMenuBarToScene(scene);
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

	public MenuBar createMenuBar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/MenuBar.fxml"));
			MenuBar menu = (MenuBar) loader.load();
			return menu;
		} catch (Exception e) {
			e.printStackTrace();
			return new MenuBar();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Arbeitsmappe getMappe() {
		return a;
	}

	public void setMappe(Arbeitsmappe mappe) {
		this.a = mappe;
	}

	public String getLastPath() {
		return this.lastPath;
	}

	public Scene getAlertScene() {
		// TODO Auto-generated method stub
		return AlertScene;
	}

	public KundenController getKundenController() {
		return kc;
	}

	public KundenDetailController getKundenDetailController() {
		// TODO Auto-generated method stub
		return this.kdc;
	}

	public AlertViewController getAlertViewController() {
		// TODO Auto-generated method stub
		return this.avc;
	}

	public NotizController getNotizController() {
		// TODO Auto-generated method stub
		return this.nc;
	}

	public MenuController getMenuController() {
		// TODO Auto-generated method stub
		return this.mc;
	}
}
