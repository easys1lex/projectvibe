package application;

import java.io.File;
import java.io.IOException;

import controllers.Controller;
import controllers.KundenController;
import daten.Arbeitsmappe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private static String lastPath = "savelink.ser";
	Stage rootStage;
	Scene StartScene;
	Scene KundenScene;
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


	@Override
	public void start(Stage primaryStage){
		setRootStage(primaryStage);
//		Übergebe diese Man allen Controllern, damit diese auf das Datenmodell zugreifen können.
		Controller.setMain(this);
//		Erstelle die JavaFX GUI
		createWindow(rootStage);
		initialisiereStartScreen();
//		Überprüfe, ob schon eine Arbeitsmappe existiert, falls keine existiert lege den startbildschirm fest.
		String lastSaveLocation = Controller.pruefeLastSave();
		if (lastSaveLocation!=null) {
			try {
				a = Controller.load(new File(lastSaveLocation));
				initializeArbeitsmappenScenes();
				Controller.showKundenScene();
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Alternativ");
			
			Controller.showStartScene();
		}
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
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Kundenanzeige.fxml"));
			BorderPane kundenRoot = (BorderPane) loader.load();
			kundenRoot.setTop(createMenuBar());
			SplitPane sp = (SplitPane)kundenRoot.getCenter();
			VBox kundenDetail = (VBox) FXMLLoader.load(getClass().getResource("../views/KundenDetailAnzeige.fxml"));
			sp.getItems().add(kundenDetail);
			KundenController k = (KundenController) loader.getController();
			k.initialize();
			setKundenScene(new Scene(kundenRoot));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addMenuBarToScene(Scene s) {
		BorderPane x = (BorderPane) s.getRoot();
		x.setTop(getMenuBar());
	}

	public void createWindow(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Fenster.fxml"));
			BorderPane root = (BorderPane)loader.load();
//			root.setTop(getMenuBar());
			Scene scene = new Scene(root);
			addMenuBarToScene(scene);
			primaryStage.setScene(scene);
			primaryStage.setWidth(1200);
			primaryStage.setHeight(800);
			primaryStage.setOnCloseRequest((WindowEvent event) -> {
				Controller.exitApplication();
		    });
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public MenuBar createMenuBar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/MenuBar.fxml"));
			MenuBar menu = (MenuBar)loader.load();
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
}
