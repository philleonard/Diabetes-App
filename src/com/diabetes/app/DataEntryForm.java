package com.diabetes.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataEntryForm extends Activity {
	
	private Button enterDataButton;
	private EditText bloodSugarText, insulinDoseText, carbContentText;
	private TextView errorBox;
	private boolean done1 = true;
	private boolean done3 = true;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Date date = new Date();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_entry);
		
		bloodSugarText = (EditText) findViewById(R.id.bloodSugarEditText);
		carbContentText = (EditText) findViewById(R.id.carbContentEditText);
		insulinDoseText = (EditText) findViewById(R.id.insulinDoseEditText);
		
		errorBox = (TextView) findViewById(R.id.dataEntryErrorBox);
	    
		enterDataButton = (Button) findViewById(R.id.goButton);
	    enterDataButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Get values in forms
				try {
					double thisBloodSugar = Double.parseDouble(bloodSugarText.getText().toString());
					done1 = true;
				} catch (NumberFormatException e1) {
					bloodSugarText.setText("");
					bloodSugarText.setHint("Blood Sugar");
					errorBox.setText("Please enter a value for blood sugar");
					done1 = false;
				}
				
				try {
					double thisCarbContent = Double.parseDouble(carbContentText.getText().toString());
				} catch (NumberFormatException e2) {}
				
				try {
					double thisInsulinDose = Double.parseDouble(insulinDoseText.getText().toString());
					done3 = true;
	    		} catch (NumberFormatException e3) {
	    			insulinDoseText.setText("");
	    			insulinDoseText.setHint("Insulin Dose");
					errorBox.setText("Please enter a value for insulin");
					done3 = false;
	    		}
				
				if (done1 && done3) {
					String dateStamp = dateFormat.format(date);
					Log.i("DATE", dateStamp);
					//Write result to a text or data file, including dateStamp. If exception for empty thisCarbContent then ignore.
					finish();
				}
				
			}
		});
	}
}
