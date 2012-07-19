package com.diabetes.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FeelingRater extends Activity {
	
	private Button submitButton;
	private EditText feelingCommentText; 
	private RatingBar feelingRatingBar;
	private Spinner injectionSpinner;
	private TextView rateErrorBox;
	private boolean storageFound = true;
	private boolean injectionDataMade = true;
	private int lineCount = 0;
	private File injectionDataFile = new File(Environment.getExternalStorageDirectory().toString()+"/.Diabetes_Health_Tracker_Data/injection_data.csv");
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feeling_rater);
		feelingCommentText = (EditText) findViewById(R.id.feelingComment);
		feelingRatingBar = (RatingBar) findViewById(R.id.feelingRatingBar);
		injectionSpinner = (Spinner) findViewById(R.id.injectionSelector);
		
		SdAvail sd = new SdAvail();
		storageFound = sd.check();
		injectionDataMade = sd.injectionDataCheck();
		
		if (storageFound && injectionDataMade) {

			InputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(injectionDataFile));
			} catch (FileNotFoundException e) {
				Toast.makeText(this,"Cant find the file", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			
			if (in!=null) {
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
					try {
						while (br.readLine() != null) {
							lineCount++;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					int maxLine = lineCount;
					if (lineCount > 10) {
						lineCount = 10;
					}
					
					if (lineCount == 0) {
						Toast.makeText(this,"No injection data recorded yet", Toast.LENGTH_LONG).show();
						finish();
					}
					
					InputStream in2 = null;
					try {
						in2 = new BufferedInputStream(new FileInputStream(injectionDataFile));
					} catch (FileNotFoundException e) {
						Toast.makeText(this,"Cant find the file", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
					
					BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
					//Assign Spinner variables to data or past injections
					final String[] spinnerItems = new String [lineCount];
					int i = 0;
					int thisLineCount = 1;
					int startCount = maxLine - lineCount;
					String currentItemString;
					try {
						while ((currentItemString = br2.readLine()) != null) {
							String currentItemStringArray[] = currentItemString.split(",");
							String finalCurrentItemString = currentItemStringArray[0];
							spinnerItems[i] = finalCurrentItemString;
							if (thisLineCount > startCount)
								i++;
							thisLineCount++;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			
					ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems);
					TextView testText = (TextView) findViewById(R.id.textView2);
					
					injectionSpinner.setAdapter(itemsAdapter); 
					injectionSpinner.setSelection(spinnerItems.length - 1); //Set default to latest injection
				    injectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
						public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
							String feelingTimeStamp = spinnerItems[pos];
							Log.i("Spin", feelingTimeStamp);
						}
			
						public void onNothingSelected(AdapterView<?> arg0) {}
				    });
					
					submitButton = (Button) findViewById(R.id.feelingSubmitButton);
					submitButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							try {
								addToCsvFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
							finish();
							}
					});
				}
		}
				
		else {
			Toast.makeText(this,"SD Card/ External Storage not found. App needs external stroage to write data", Toast.LENGTH_LONG).show();
			finish();
		}
		
	}
	
	private void addToCsvFile() throws IOException {
		InputStream in3 = null;
		try {
			in3 = new BufferedInputStream(new FileInputStream(injectionDataFile));
		} catch (FileNotFoundException e) {
			Toast.makeText(this,"Cant find the file", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		BufferedReader br3 = new BufferedReader(new InputStreamReader(in3));
		
		String spinnerSelection = injectionSpinner.getSelectedItem().toString();
		
		int readLineCount = 1;
		while (!br3.readLine().contains(spinnerSelection)) {
			readLineCount++;
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(injectionDataFile));
		
		String feelingComment = feelingCommentText.getText().toString();
		//Write loop to escape any commas in the String to avoid CSV (Comma Separated Values) confusion.
		double feelingRating = feelingRatingBar.getRating();
		String input = ", " + feelingRating + ", " + feelingComment;
		
		//Write input String to the file.
	}
}
















