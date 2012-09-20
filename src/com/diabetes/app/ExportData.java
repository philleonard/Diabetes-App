package com.diabetes.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class ExportData extends Activity {

	File Data = new File(Environment.getExternalStorageDirectory() + "/Diabetes_Health_Tracker_Data/injection_data.csv");
	String file = Environment.getExternalStorageDirectory() + "/Diabetes_Health_Tracker_Data/Data.pdf";
	String Date;
	String BloodSugar;
	String CarbonContent;
	String InsulinDose;
	String Feeling;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			Document document = new Document();

			PdfWriter.getInstance(document,new FileOutputStream(file));

			document.open();

			Paragraph p = new Paragraph();

			PdfPTable table = new PdfPTable(6);
			
			PdfPCell cell = new PdfPCell(new Phrase("Date"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Blood Sugar"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Carbon Content"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Insulin Dose"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Rating"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Comment"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			

			BufferedReader reader = new BufferedReader(new FileReader(Data));

			String Line;
			String[] Temp;
			while ((Line = reader.readLine()) != null) {
				
				Temp = Line.split(",");
				for(int i = 0; i < Temp.length; i++){
					table.addCell(Temp[i]);
				}
				
				if(Temp.length == 4){
					table.addCell("N/A");
					table.addCell("N/A");
				}
			}


			p.add(table);			
			document.add(p);


			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		File targetFile = new File(file);
		Uri targetUri = Uri.fromFile(targetFile);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(targetUri, "application/pdf");

		startActivity(intent);
		finish();
	}

}