package com.diabetes.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DataEntryForm extends Activity {
	
	private Button enterDataButton;
	private EditText bloodSugarText, insulinDoseText, carbContentText;
	private TextView errorBox;
	private boolean done1 = true;
	private boolean done3 = true;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Date date = new Date();
	private String dateStamp;
	private BufferedWriter buf;
	private double thisBloodSugar;
	private double thisCarbContent = 0;
	private double thisInsulinDose;
	private boolean sdCardPresent;
	private boolean injectionDataPresent;
	private static final String PREFS_NAME = "insulinLevelPref";
	private SharedPreferences levels;
	private RadioGroup penRadio;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_entry);
		
		bloodSugarText = (EditText) findViewById(R.id.bloodSugarEditText);
		carbContentText = (EditText) findViewById(R.id.carbContentEditText);
		insulinDoseText = (EditText) findViewById(R.id.insulinDoseEditText);
		penRadio = (RadioGroup) findViewById(R.id.radioGroup1);
		errorBox = (TextView) findViewById(R.id.dataEntryErrorBox);
	    
		enterDataButton = (Button) findViewById(R.id.goButton);
	    enterDataButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Get values in forms
				try {
					thisBloodSugar = Double.parseDouble(bloodSugarText.getText().toString());
					done1 = true;
				} catch (NumberFormatException e1) {
					bloodSugarText.setText("");
					bloodSugarText.setHint("Blood Sugar");
					errorBox.setText("Please enter a value for blood sugar");
					done1 = false;
				}
				
				try {
					thisCarbContent = Double.parseDouble(carbContentText.getText().toString());
				} catch (NumberFormatException e2) {}
				
				try {
					thisInsulinDose = Double.parseDouble(insulinDoseText.getText().toString());
					done3 = true;
	    		} catch (NumberFormatException e3) {
	    			insulinDoseText.setText("");
	    			insulinDoseText.setHint("Insulin Dose");
					errorBox.setText("Please enter a value for insulin");
					done3 = false;
	    		}
				
				if (done1 && done3) {
					removeFromPen();
					
					dateStamp = dateFormat.format(date);
					
					String writeData;
					if (thisCarbContent == 0)
						writeData = dateStamp + ", " + thisBloodSugar + ", n/a, " + thisInsulinDose; 
					else
						writeData = dateStamp + ", " + thisBloodSugar + ", " + thisCarbContent + ", " + thisInsulinDose; 
					
					if (writeDataToInjectionData(writeData))
						finish();
					else
						errorBox.setText("External storage device not found");
				}
				
			}

			private void removeFromPen() {
				if (penRadio.getCheckedRadioButtonId() == R.id.dailyRadio) {
					levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
					int dailyLevel = levels.getInt("dailyLevel", 0);
					if (dailyLevel < thisInsulinDose) {
						//Dialog asking for confirmation to ignore remaining insulin level
					}
					dailyLevel = (int) (dailyLevel - thisInsulinDose);
					SharedPreferences.Editor editor = levels.edit();
					editor.remove("dailyLevel");
					editor.putInt("dailyLevel", dailyLevel);
					editor.commit();	
				}
				else {
					levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
					int overNightLevel = levels.getInt("overNightLevel", 0);
					if (overNightLevel < thisInsulinDose) {
						//Dialog asking for confirmation to ignore remaining insulin level
					}
					overNightLevel = (int) (overNightLevel - thisInsulinDose);
					SharedPreferences.Editor editor = levels.edit();
					editor.remove("overNightLevel");
					editor.putInt("overNightLevel", overNightLevel);
					editor.commit();	
				}
			}
		});
	}
	
	public boolean writeDataToInjectionData(String appendDataString) {
		
		SdAvail sd = new SdAvail(); 
		sdCardPresent = sd.check();
		injectionDataPresent = sd.injectionDataCheck();
		
		if (sdCardPresent && injectionDataPresent) {		
			File injectionData = new File(Environment.getExternalStorageDirectory().toString() + "/Diabetes_Health_Tracker_Data/injection_data.csv");
			//Write to file now
			try {
				buf = new BufferedWriter(new FileWriter(injectionData, true));
				buf.append(appendDataString);
				buf.newLine();
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			Toast.makeText(this,"External Storage not found. Unable to write data", Toast.LENGTH_LONG).show();
			finish();
		}
		
		return done1;
		
	}
}
