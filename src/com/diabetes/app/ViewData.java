package com.diabetes.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewData extends Activity {
	
	private String Date;
	private String BloodSugar;
	private String CarbonContent;
	private String InsulinDose;
	private int Id = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_data);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Toast.makeText(this, "Please Tilt Your Phone!", 3).show();
		
		//obtaining CSV file 'injection_data'
		File Data = new File(Environment.getExternalStorageDirectory() + "/Diabetes_Health_Tracker_Data/injection_data.csv");
		TableLayout DataTable = (TableLayout) findViewById(R.id.viewDataTable);
		
		TableRow TableHeadings = new TableRow(this);
		TableHeadings.setId(Id);
		
		TextView HeadingDate = new TextView(this);
		HeadingDate.setText("Date");
		HeadingDate.setTypeface(null,Typeface.BOLD);
		HeadingDate.setPadding(10, 2, 10, 2);
		HeadingDate.setBackgroundColor(Color.parseColor("#000000"));
		TableHeadings.addView(HeadingDate);
		HeadingDate.setId(Id);
		
		TextView HeadingBlood = new TextView(this);
		HeadingBlood.setText("BloodSugar");
		HeadingBlood.setTypeface(null,Typeface.BOLD);
		HeadingBlood.setPadding(10, 2, 10, 2);
		HeadingBlood.setBackgroundColor(Color.parseColor("#000000"));
		TableHeadings.addView(HeadingBlood);
		HeadingBlood.setId(Id);
		
		TextView HeadingCarbon = new TextView(this);
		HeadingCarbon.setText("Carbon Content");
		HeadingCarbon.setTypeface(null,Typeface.BOLD);
		HeadingCarbon.setPadding(10, 2, 10, 2);
		HeadingCarbon.setBackgroundColor(Color.parseColor("#000000"));
		TableHeadings.addView(HeadingCarbon);
		HeadingCarbon.setId(Id);
		
		//TableHeadings.addView(border);
		
		TextView HeadingInsulin = new TextView(this);
		HeadingInsulin.setText("Insulin Dose");
		HeadingInsulin.setTypeface(null,Typeface.BOLD);
		HeadingInsulin.setPadding(10, 2, 10, 2);
		HeadingInsulin.setBackgroundColor(Color.parseColor("#000000"));
		TableHeadings.addView(HeadingInsulin);
		HeadingInsulin.setId(Id);
		
		TableHeadings.setPadding(1, 1, 1, 1);
		TableHeadings.setBackgroundColor(Color.parseColor("#ffffff"));
		
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
		     		DataDate.setPadding(10, 2, 10, 2);
		     		DataDate.setBackgroundColor(Color.parseColor("#000000"));
		    		DataDate.setText(Date);
		    		DataRow.addView(DataDate);
		    		
		    		TextView DataBlood = new TextView(this);
		    		DataBlood.setPadding(10, 2, 10, 2);
		    		DataBlood.setBackgroundColor(Color.parseColor("#000000"));
		    		DataBlood.setText(BloodSugar);
		    		DataRow.addView(DataBlood);
		    		
		    		TextView DataCarbon = new TextView(this);
		    		DataCarbon.setPadding(10, 2, 10, 2);
		    		DataCarbon.setBackgroundColor(Color.parseColor("#000000"));
		    		DataCarbon.setText(CarbonContent);
		    		DataRow.addView(DataCarbon);
		    		
		    		TextView DataInsulin = new TextView(this);
		    		DataInsulin.setPadding(10, 2, 10, 2);
		    		DataInsulin.setBackgroundColor(Color.parseColor("#000000"));
		    		DataInsulin.setText(InsulinDose);
		    		
		    		DataRow.setPadding(1, 1, 1, 1);
		    		DataRow.setBackgroundColor(Color.parseColor("#ffffff"));
		    		
		    		DataRow.addView(DataInsulin);
		    		
		    		DataTable.addView(DataRow);	
				}
			} catch (IOException e) {
				
			}
	}
}
