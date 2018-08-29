package excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import alerts.Message;
import daten.Arbeitsmappe;
import daten.Kunde;
import ereignisse.Ereignis;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ExcelWriter {
	XSSFWorkbook wb;
	XSSFSheet kundenSheet;
	XSSFSheet ereignisSheet;
	XSSFSheet alertSheet;

	public ExcelWriter(Arbeitsmappe a) {
		super();
		this.wb = new XSSFWorkbook();
		kundenSheet = wb.createSheet("Kunden");
		ereignisSheet = wb.createSheet("Ereignisse");
		alertSheet = wb.createSheet("Nachrichten");
		if (a != null) {
			writeToExcel(a);
			writeToFile();
		}
	}

	private void writeToFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Exportiere Arbetsmappe in eine Excel File");
		ExtensionFilter extFilter = new ExtensionFilter("Excel Export (*.xlsx)", "*.xlsx");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			try {
				FileOutputStream fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				System.out.println("Erfolgreich Exportiert");
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("Es wurde nicht Exportiert");
		}

		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToExcel(Arbeitsmappe a) {
		writeKundenSheet(a);
		writeEreignisSheet(a);
		writeAlertSheet(a);
	}

	private void writeAlertSheet(Arbeitsmappe a) {
		XSSFRow alertHeaderRow = alertSheet.createRow(0);
		alertHeaderRow.createCell(0).setCellValue("Nachrichtentyp");
		alertHeaderRow.createCell(1).setCellValue("Alert erstellt");
		alertHeaderRow.createCell(2).setCellValue("Nachricht");
		int rowNum = 0;
		for (Message m : a.alertListe) {
			XSSFRow row = alertSheet.createRow(++rowNum);
			row.createCell(0).setCellValue(m.getClass().getSimpleName());
			row.createCell(1).setCellValue(m.getCreated());
			row.createCell(2).setCellValue(m.getNachricht());
		}
		
	}

	private void writeKundenSheet(Arbeitsmappe a) {
		XSSFRow kundenHeaderRow = kundenSheet.createRow(0);
		kundenHeaderRow.createCell(0).setCellValue("Kunden Nummer");
		kundenHeaderRow.createCell(1).setCellValue("Firma");
		kundenHeaderRow.createCell(2).setCellValue("Name");
		kundenHeaderRow.createCell(3).setCellValue("Vorname");
		kundenHeaderRow.createCell(4).setCellValue("E-Mail");
		kundenHeaderRow.createCell(5).setCellValue("Anschrift");
		kundenHeaderRow.createCell(6).setCellValue("Telefonnummer");
		kundenHeaderRow.createCell(7).setCellValue("Favorit");
		int rowNum = 0;
		for (Kunde k : a.kundenListe) {
			XSSFRow row = kundenSheet.createRow(++rowNum);
			row.createCell(0).setCellValue(k.getKundenNummer().get());
			row.createCell(1).setCellValue(k.getFirma().get());
			row.createCell(2).setCellValue(k.getName().get());
			row.createCell(3).setCellValue(k.getVorName().get());
			row.createCell(4).setCellValue(k.getEmail().get());
			row.createCell(5).setCellValue(k.getAnschrift().get());
			row.createCell(6).setCellValue(k.getTelefonnummer().get());
			row.createCell(7).setCellValue(k.getIsFavorit().get());
		}
	}

	private void writeEreignisSheet(Arbeitsmappe a) {
		XSSFRow ereignisHeaderRow = ereignisSheet.createRow(0);
		ereignisHeaderRow.createCell(0).setCellValue("Ereignis Nummer");
		ereignisHeaderRow.createCell(1).setCellValue("Kunden Nummer");
		ereignisHeaderRow.createCell(2).setCellValue("Ereignis Typ");
		ereignisHeaderRow.createCell(3).setCellValue("Ereignis Erstellt");
		ereignisHeaderRow.createCell(4).setCellValue("Ereignis Titel");
		ereignisHeaderRow.createCell(5).setCellValue("Ereignis Inhalt");
		ereignisHeaderRow.createCell(6).setCellValue("Termin");
		int rowNum = 0;
		for (Kunde k : a.kundenListe) {
			for (Ereignis e : k.ereignisListe) {
				XSSFRow row = ereignisSheet.createRow(++rowNum);
				row.createCell(0).setCellValue(e.getEreignisID().get());
				row.createCell(1).setCellValue(k.getKundenNummer().get());
				row.createCell(2).setCellValue(e.getClass().getName());
				row.createCell(3).setCellValue(e.getCreated().get());
				row.createCell(4).setCellValue(e.getEreignisTitel().get());
				row.createCell(5).setCellValue(e.getEreignisInhalt().get());
				row.createCell(6).setCellValue(e.getTermin().get());
			}
		}
	}

}
