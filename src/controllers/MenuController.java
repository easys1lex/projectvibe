package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuController extends Controller{

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
    	createArbeitsmappe();
    	updateDisable();
    }

    @FXML
    void loadArbeismappe(ActionEvent event) {
    	openArbeitsmappe();
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
    	updateDisable();
    }

    @FXML
    void openImportMenu(ActionEvent event) {
    	updateDisable();
    }

    @FXML
    void exitApplication(ActionEvent event) {
    	updateDisable();
    	exitApplication();
    	
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

    }

    @FXML
    void switchToAlertView(ActionEvent event) {

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
    		mKunden.setDisable(true);
    	}else {
    		mSaveArbeitsmappe.setDisable(false);
    		mSaveArbeitsmappeUnter.setDisable(false);
    		mExport.setDisable(false);
    		mKunden.setDisable(false);
    	}
    }
    @FXML
    public void initialize() {
    	mc=this;
    	updateDisable();
    }
    
    @FXML
    public void switchToStartanzeige(ActionEvent event) {
    	showStartScene();
    	updateDisable();
    }

}
