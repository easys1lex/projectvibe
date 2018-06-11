package application;
	
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Callback;
import kunden.BusinessKunde;
import kunden.Kunde;
import kunden.PrivatKunde;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	private TableView tabelle = createTable();
	private ObservableList<Kunde> data = createObservableData();
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			tabelle.setItems(data);
			root.setCenter(tabelle);
			addAdd(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	private ObservableList<Kunde> createObservableData() {
		ObservableList<Kunde> temp = FXCollections.observableArrayList();
		return temp;
	}
	private void addAdd(BorderPane root) {
		final TextField addFirstName = new TextField();
		addFirstName.setPromptText("First Name");
		final TextField addLastName = new TextField();
		addLastName.setPromptText("Last Name");
		final TextField addEmail = new TextField();
		addEmail.setPromptText("Email");
		 
		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        data.add(new PrivatKunde(
		            Long.parseLong(addFirstName.getText()),
		            addEmail.getText()
		        ));
		        addFirstName.clear();
		        addLastName.clear();
		        addEmail.clear();
		    }
		});
		root.setBottom(new HBox(addFirstName,addEmail,addButton));
	}
	
	private TableView createTable() {
		TableView<Kunde> temp = new TableView<Kunde>();
		temp.setEditable(true);
		TableColumn<Kunde, String> firstNameCol = new TableColumn<Kunde, String>("First Name");
        TableColumn<Kunde, String> lastNameCol = new TableColumn<Kunde, String>("Last Name");
        TableColumn<Kunde, String> emailCol = new TableColumn<Kunde, String>("Email");
        temp.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        firstNameCol.setCellValueFactory(
        	    new PropertyValueFactory<Kunde,String>("kundenid")
        	);
        	emailCol.setCellValueFactory(
        	    new PropertyValueFactory<Kunde,String>("email")
        	);
		return temp;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
