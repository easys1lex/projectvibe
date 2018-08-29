package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import alerts.AchtungMessage;
import alerts.ErrorMessage;
import alerts.InfoMessage;
import alerts.Message;
import alerts.SuccessMessage;
import daten.Arbeitsmappe;
import daten.Kunde;
import ereignisse.Ereignis;
import ereignisse.ErstelltEreignis;
import ereignisse.KaufEreignis;
import ereignisse.KontaktEreignis;
import ereignisse.ReclamationEreignis;
import ereignisse.TreffenEreignis;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ExcelReader {
	XSSFWorkbook wb;
	XSSFSheet kundenSheet;
	XSSFSheet ereignisSheet;
	XSSFSheet alertSheet;
	Arbeitsmappe a;
	public ExcelReader() {
		super();
		File importFile = getFileFromFileChooser();
		if(importFile!=null) {
			if(initialisiereExcelreaderFromFile(importFile)) {
				erstelleArbeitsmappeFromExcel();
			}else {
				System.out.println("Fehler beim Importieren");
			}
		}
	}
	private void erstelleArbeitsmappeFromExcel() {
		a = new Arbeitsmappe();
		a.setKundenAnzahl(addKundenAusListe(a));
		a.setEreignisAnzahl(addEreignisseAusListe(a));
		addAlertsAusListe(a);
	}
	public Arbeitsmappe getArbeitsmappe() {
		return this.a;
	}
	private int addEreignisseAusListe(Arbeitsmappe a) {
		int ereignisAnzahl = ereignisSheet.getLastRowNum();
		int maxEreignisId = 0;
		for(int i = 1; i<=ereignisAnzahl;i++) {
			String type = ereignisSheet.getRow(i).getCell(2).getStringCellValue();
			int id = (int) ereignisSheet.getRow(i).getCell(0).getNumericCellValue();
			if (id>=maxEreignisId) {
				maxEreignisId = id;
			}
			int kid = (int) ereignisSheet.getRow(i).getCell(1).getNumericCellValue();
			int kundeIndex = a.kundenListe.indexOf(new Kunde(kid));
			Kunde k = a.kundenListe.get(kundeIndex);
			String titel = ereignisSheet.getRow(i).getCell(4).getStringCellValue();
			String inhalt = ereignisSheet.getRow(i).getCell(5).getStringCellValue();
			long erstellt = (long) ereignisSheet.getRow(i).getCell(3).getNumericCellValue();
			long termin = (long) ereignisSheet.getRow(i).getCell(6).getNumericCellValue();
			if(k!=null) {
				switch(type) {
				case "ereignisse.ErstelltEreignis":
					k.ereignisListe.add(new ErstelltEreignis(id,titel,inhalt,erstellt,termin));
					break;
				case "ereignisse.KaufEreignis":
					k.ereignisListe.add(new KaufEreignis(id,titel,inhalt,erstellt,termin));
					break;
				case "ereignisse.KontakEreignis":
					k.ereignisListe.add(new KontaktEreignis(id,titel,inhalt,erstellt,termin));
					break;
				case "ereignisse.ReclamationEreignis":
					k.ereignisListe.add(new ReclamationEreignis(id,titel,inhalt,erstellt,termin));
					break;
				case "ereignisse.TreffenEreignis":
					k.ereignisListe.add(new TreffenEreignis(id,titel,inhalt,erstellt,termin));
					break;
				case "ereignisse.Ereignis":
					k.ereignisListe.add(new Ereignis(id,titel,inhalt,erstellt,termin));
					break;
				}
			}
		}
		return maxEreignisId;
		
	}
	private void addAlertsAusListe(Arbeitsmappe a) {
		int alertAnzahl = alertSheet.getLastRowNum();
		for(int i = 1; i<=alertAnzahl;i++) {
			long created = (long) alertSheet.getRow(i).getCell(1).getNumericCellValue();
			String nachricht = alertSheet.getRow(i).getCell(2).getStringCellValue();
			String type = alertSheet.getRow(i).getCell(0).getStringCellValue();
			switch (type) {
			case "SuccessMessage":
				a.alertListe.add(new SuccessMessage(created,nachricht));
				break;
			case "AchtungMessage":
				a.alertListe.add(new AchtungMessage(created,nachricht));
				break;
			case "ErrorMessage":
				a.alertListe.add(new ErrorMessage(created,nachricht));
				break;
			case "InfoMessage":
				a.alertListe.add(new InfoMessage(created,nachricht));
				break;
			case "Message":
				a.alertListe.add(new Message(created,nachricht));
				break;
			}
		}
		
	}
	private int addKundenAusListe(Arbeitsmappe a) {
		int kundenanzahl = kundenSheet.getLastRowNum();
		int highestid = 0;
		for(int i = 1; i<=kundenanzahl;i++) {
			int id = (int) kundenSheet.getRow(i).getCell(0).getNumericCellValue();
			if(id >=highestid) {
				highestid = id;
			}
			String firma = kundenSheet.getRow(i).getCell(1).getStringCellValue();
			String name = kundenSheet.getRow(i).getCell(2).getStringCellValue();
			String vorName = kundenSheet.getRow(i).getCell(3).getStringCellValue();
			String email = kundenSheet.getRow(i).getCell(4).getStringCellValue();
			String anschrift = kundenSheet.getRow(i).getCell(5).getStringCellValue();
			long telefonnummer = (long) kundenSheet.getRow(i).getCell(6).getNumericCellValue();
			boolean isFavorit = kundenSheet.getRow(i).getCell(7).getBooleanCellValue();
			a.kundenListe.add(new Kunde(id,name,vorName,email,anschrift,firma,telefonnummer,isFavorit));
		}
		return highestid;
	}
	private boolean initialisiereExcelreaderFromFile(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		try {
			wb = new XSSFWorkbook(fis);
			kundenSheet = wb.getSheetAt(0);
			ereignisSheet = wb.getSheetAt(1);
			alertSheet = wb.getSheetAt(2);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error beim laden");
			e.printStackTrace();
			return false;
		}
		
	}
	public File getFileFromFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Exportiere Arbetsmappe in eine Excel File");
		ExtensionFilter extFilter = new ExtensionFilter("Excel Export (*.xlsx)", "*.xlsx");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			return file;
		} else {
			System.err.println("Es wurde kein File ausgewählt.");
			return null;
		}
		
	}
	public XSSFWorkbook getWorkbookFromFile(File f) {
		
		return wb;
		
	}


}
