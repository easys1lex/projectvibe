package controllers;

import alerts.InfoMessage;
import alerts.SuccessMessage;
import application.Main;
import excel.ExcelReader;
import excel.ExcelWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuController extends Controller{

    public MenuController() {
		super(Main.getHauptController().getMain());
	}

	@FXML
    private MenuItem mNotizen;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mNeueArbeismappe;

    @FXML
    private MenuItem mSaveArbeitsmappeUnter;

    @FXML
    private MenuItem mEreignisse;

    @FXML
    private MenuItem mSaveArbeitsmappe;

    @FXML
    private MenuItem mKunden;

    @FXML
    private MenuItem mOeffneArbeitsmappe;

    @FXML
    private MenuItem mAnzeige;

    @FXML
    private MenuItem mExport;

    @FXML
    private MenuItem mAlerts;

    @FXML
    private MenuItem mAlertHelp;

    @FXML
    private MenuItem mImport;
    
    @FXML
    private MenuItem mStartanzeige;

    @FXML
    void createNewArbeitsmappe(ActionEvent event) {
    	createArbeitsmappe(null);
    	updateDisable();
    }

    @FXML
    void loadArbeismappe(ActionEvent event) {
    	openArbeitsmappeFromFile();
    	updateDisable();
    }

    @FXML
    void saveArbeitsmappeUnter(ActionEvent event) {
    	speichereUnter();
    	updateDisable();
    }

    @FXML
    void saveArbeitsmappe(ActionEvent event) {
    	normalSave();
    	updateDisable();
    }

    @FXML
    void openExportMenu(ActionEvent event) {
    	ExcelWriter e = new ExcelWriter(getMain().getMappe());
    	getMain().getAlertViewController().addMessage(new InfoMessage("Arbeitsmappe wurde exportiert."));
    	updateDisable();
    }

    @FXML
    void openImportMenu(ActionEvent event) {
    	if (frageSaveFirst(true) == false) {
//			Falls die Aktion abgebrochen wurde, dann importier nicht.
			return;
		}
    	ExcelReader e = new ExcelReader();
    	createArbeitsmappe(e.getArbeitsmappe());
    	updateDisable();
    	System.out.println("Arbeitsmappe Imortiert");
    	getMain().getAlertViewController().addMessage(new SuccessMessage("Aus ExcelSheet importiert!"));
    }

    @FXML
    void exitApplication(ActionEvent event) {
    	exitApplication(true);
    	updateDisable();
    	
    }

    @FXML
    void switchToKundenView(ActionEvent event) {
    	showKundenScene();
    	updateDisable();
    }

    @FXML
    void switchToEreignisView(ActionEvent event) {

    }

    @FXML
    void switchToNotizenView(ActionEvent event) {
    	showNotizScene();
    	updateDisable();
    }

    @FXML
    void switchToAlertView(ActionEvent event) {
    	showAlertScene();
    	updateDisable();
    }

    @FXML
    void showAnzeigeHelp(ActionEvent event) {

    }

    @FXML
    void showAlertHelp(ActionEvent event) {

    }
    
    public void updateDisable() {
    	if (getMain().getMappe()==null) {
    		mSaveArbeitsmappe.setDisable(true);
    		mSaveArbeitsmappeUnter.setDisable(true);
    		mExport.setDisable(true);
    		mNotizen.setDisable(true);
    		mKunden.setDisable(true);
    		mAlerts.setDisable(true);
    		mStartanzeige.setDisable(true);
    		mImport.setDisable(false);
    		
    	}else {
    		mSaveArbeitsmappe.setDisable(false);
    		mSaveArbeitsmappeUnter.setDisable(false);
    		mExport.setDisable(false);
    		mNotizen.setDisable(false);
    		mKunden.setDisable(false);
    		mAlerts.setDisable(false);
    		mStartanzeige.setDisable(true);
    		mImport.setDisable(false);
    	}
    }
    @FXML
    public void initialize() {
    	updateDisable();
    }
    
    @FXML
    public void switchToStartanzeige(ActionEvent event) {
    	showStartScene();
//    	updateDisable();
    }


}
