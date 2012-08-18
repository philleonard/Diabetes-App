package com.diabetes.app;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class ExportData extends Activity {

	String file = Environment.getExternalStorageDirectory() + "/Diabetes_Health_Tracker_Data/Data.pdf";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			Document document = new Document();

			PdfWriter.getInstance(document,new FileOutputStream(file));
			document.open();

			Paragraph p = new Paragraph("Hello");

			/*PdfPTable table = new PdfPTable(4);

			PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			PdfPCell c2 = new PdfPCell(new Phrase("Table Header 1"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c2);

			PdfPCell c3 = new PdfPCell(new Phrase("Table Header 1"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c3);

			PdfPCell c4 = new PdfPCell(new Phrase("Table Header 1"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c4);*/

			document.add(p);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*File targetFile = new File(file);
		Uri targetUri = Uri.fromFile(targetFile);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(targetUri, "application/pdf");

		startActivity(intent);*/
	}

}