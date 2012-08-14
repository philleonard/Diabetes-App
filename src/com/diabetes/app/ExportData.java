package com.diabetes.app;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
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
			document.add(p);
			document.close();

			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
	}