package application;

import java.io.IOException;

import controller.Controller;
import datenmodell.Arbeitsmappe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	Arbeitsmappe mappe = null;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		Controller.setMain(this);
		initialisiereFenster(primaryStage);
		setMappe(new Arbeitsmappe());
		mappe.insertBusinessKunde("Jannis", "Kiesel", true, true, "jannis.kiesel@outlook.de", "Mühlenfeldstraße. 76", 15905360568L, "Fujitsu");
		initialisiereKundenTabelle((BorderPane)primaryStage.getScene().getRoot());	
	}
	public void initialisiereFenster(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Fenster.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("ProjectVibe");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void initialisiereKundenTabelle(BorderPane root) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/KundenFenster.fxml"));
		try {
			root.setCenter(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public Arbeitsmappe getMappe() {
		return mappe;
	}
	public void setMappe(Arbeitsmappe mappe) {
		this.mappe = mappe;
	}
}
