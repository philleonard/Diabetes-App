package com.diabetes.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewData extends Activity {
	
	String Date;
	String BloodSugar;
	String CarbonContent;
	String InsulinDose;
	int Id = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_data);
		
		//obtaining CSV file 'injection_data'
		File Data = new File(Environment.getExternalStorageDirectory() + "/.Diabetes_Health_Tracker_Data/.injection_data.csv");
		TableLayout DataTable = (TableLayout) findViewById(R.id.viewDataTable);
		
		TableRow TableHeadings = new TableRow(this);
		TableHeadings.setId(Id);
		
		TextView HeadingDate = new TextView(this);
		HeadingDate.setText("Date");
		TableHeadings.addView(HeadingDate);
		HeadingDate.setId(Id);
		
		TextView HeadingBlood = new TextView(this);
		HeadingBlood.setText("BloodSugar");
		TableHeadings.addView(HeadingBlood);
		HeadingBlood.setId(Id);
		
		TextView HeadingCarbon = new TextView(this);
		HeadingCarbon.setText("Carbon Content");
		TableHeadings.addView(HeadingCarbon);
		HeadingCarbon.setId(Id);
		
		TextView HeadingInsulin = new TextView(this);
		HeadingInsulin.setText("Insulin Dose");
		TableHeadings.addView(HeadingInsulin);
		HeadingInsulin.setId(Id);
		
		DataTable.addView(TableHeadings);
		
			try {
				BufferedReader reader = new BufferedReader(new FileReader(Data));
				
				String Line;
				while ((Line = reader.readLine()) != null) {
		             String[] RowData = Line.split(",");
		             Date = RowData[0];
		             BloodSugar = RowData[1];
		             CarbonContent = RowData[2];
		             InsulinDose = RowData[3];
		         
		             //need to make table rows and create a new tablelayout in xml file
		            TableRow DataRow = new TableRow(this);
		     		DataRow.setId(Id = Id + 1);
		     		
		     		TextView DataDate = new TextView(this);
		    		DataDate.setText(Date);
		    		DataRow.addView(DataDate);
		    		
		    		TextView DataBlood = new TextView(this);
		    		DataBlood.setText(BloodSugar);
		    		DataRow.addView(DataBlood);
		    		
		    		TextView DataCarbon = new TextView(this);
		    		DataCarbon.setText(CarbonContent);
		    		DataRow.addView(DataCarbon);
		    		
		    		TextView DataInsulin = new TextView(this);
		    		DataInsulin.setText(InsulinDose);
		    		DataRow.addView(DataInsulin);
		    		
		    		DataTable.addView(DataRow);	
				}
			} catch (IOException e) {
				
			}
	}
}
