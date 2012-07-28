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
import java.io.PrintWriter;
import java.io.RandomAccessFile;
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
	private File injectionDataFile = new File(Environment.getExternalStorageDirectory().toString()+"/Diabetes_Health_Tracker_Data/injection_data.csv");
	
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
					if (lineCount > 10)
						lineCount = 10;
					
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
						}
			
						public void onNothingSelected(AdapterView<?> arg0) {}
				    });
					
					submitButton = (Button) findViewById(R.id.feelingSubmitButton);
					submitButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							addToCsvFile();
							finish();
						}
					});
				}
		}
				
		else {
			Toast.makeText(this,"External Storage not found. Unable to write data", Toast.LENGTH_LONG).show();
			finish();
		}
		
	}
	
	private void addToCsvFile() {
		InputStream in3 = null;
		try {
			in3 = new BufferedInputStream(new FileInputStream(injectionDataFile));
		} catch (FileNotFoundException e) {
			Toast.makeText(this,"Cant find the file", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		BufferedReader br3 = new BufferedReader(new InputStreamReader(in3));
		
		String spinnerSelection = injectionSpinner.getSelectedItem().toString();
		File injectionDataFileTemp = new File(injectionDataFile.toString() + ".temp");
		
		try {
			injectionDataFileTemp.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		BufferedWriter buf = null;
		try {
			buf = new BufferedWriter(new FileWriter(injectionDataFileTemp));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		String currentLine;
		try {
			while ((currentLine = br3.readLine()) != null) {
				if (currentLine.contains(spinnerSelection)) {
					boolean yes = true;
					int commaCount = 0;
					
					String[] currentLineSplit = currentLine.split("");
					for (int z = 0; z < currentLineSplit.length; z++) {
						if (currentLineSplit[z].equals(",")) {
							commaCount++;
						}
					}

					if (commaCount > 3) {
						startActivity(new Intent(getApplicationContext(), OverwriteConformation.class));
						//Change yes = no if no selected from OverwriteConformation.class
					}
										
					if (yes) {
						double feelingRating = feelingRatingBar.getRating();
						String feelingComment = feelingCommentText.getText().toString();
						feelingComment = escapeCommas(feelingComment);
						if (commaCount > 3) {
							currentLine = "";
							int commaRemoveCounter = 0;
							for (int y = 0; y < currentLineSplit.length; y++) {
								if (currentLineSplit[y].contains(","))
									commaRemoveCounter++;
								if (commaRemoveCounter > 3) 
									break;
								currentLine = currentLine + currentLineSplit[y];
							}
						}
						String input = currentLine + ", " + feelingRating + ", " + feelingComment;
						buf.append(input);
					}
					else if (!yes)
						buf.append(currentLine);
				}
				else
					buf.append(currentLine);
				
				buf.flush();
				buf.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		injectionDataFile.delete();
		injectionDataFileTemp.renameTo(injectionDataFile);
	}

	private String escapeCommas(String feelingComment) {
		String[] feelingCommentSplit = feelingComment.split("");
		feelingComment = "";
		for (int x = 0; x < feelingCommentSplit.length; x++) {
			if (feelingCommentSplit[x].contains(",")) {
				feelingCommentSplit[x] = ""; //Find out how to escape commas in a String
			}
			feelingComment = feelingComment + feelingCommentSplit[x];
		}
		return feelingComment;
	}
}















