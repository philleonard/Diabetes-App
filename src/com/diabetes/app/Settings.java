package com.diabetes.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends Activity {
	
	private int daySyringeMax;
	private int nightSyringeMax;
	private int dayWarnLevel;
	private int nightWarnLevel;
	private int tempDayWarnLevel;
	private int tempNightWarnLevel;
	private static final String PREFS_NAME = "insulinLevelPref";
	private SharedPreferences levels;
	private RadioGroup unitRadio;
	private int penUnits; //0 = units 1 = ml
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingspage);	

		load();
		
		final EditText dayPenEditor = (EditText) findViewById(R.id.dayPenEditor);
		final EditText nightPenEditor = (EditText) findViewById(R.id.nightPenEditor);
		final EditText dayWarnEdit = (EditText) findViewById(R.id.dayWarnLevelEdit);
		final EditText nightWarnEdit = (EditText) findViewById(R.id.nightWarnLevelEdit);
		
		unitRadio = (RadioGroup) findViewById(R.id.radioGroup1);
		if (penUnits == 0) {
			unitRadio.check(R.id.radioUnits);
		}
		else {
			unitRadio.check(R.id.radioMillilitre);
		}
		
		final TextView dayValue = (TextView) findViewById(R.id.dayValue);
		dayValue.setText("Current Value: " + daySyringeMax);
		
		final TextView nightValue = (TextView) findViewById(R.id.nightValue);
		nightValue.setText("Current Value: " + nightSyringeMax);
		
		final TextView dayWLI = (TextView) findViewById(R.id.dayWLI);
		dayWLI.setText("Current Value: " + dayWarnLevel);
		
		final TextView nightWLI = (TextView) findViewById(R.id.nightWLI);
		nightWLI.setText("Current Value: " + nightWarnLevel);
		
		Button saveButton = (Button) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {
					daySyringeMax = Integer.parseInt(dayPenEditor.getText().toString());
					}catch (Exception e){}
				try {
					nightSyringeMax = Integer.parseInt(nightPenEditor.getText().toString());
					}catch (Exception e){}
				try {
					tempDayWarnLevel = Integer.parseInt(dayWarnEdit.getText().toString());
					if (tempDayWarnLevel<daySyringeMax) {
						dayWarnLevel=tempDayWarnLevel;
					}
					else {
						printError(1);
					}
					}catch (Exception e){}
				try {
					tempNightWarnLevel = Integer.parseInt(nightWarnEdit.getText().toString());
					if (tempNightWarnLevel<nightSyringeMax) {
						nightWarnLevel=tempNightWarnLevel;
					}
					else {
						printError(2);
					}
					}catch (Exception e){}
				if (unitRadio.getCheckedRadioButtonId() == R.id.radioUnits) {
					penUnits = 0;
				}
				else {
					penUnits = 1;
				}
				save();
				dayValue.setText("Current Value: " + daySyringeMax);
				nightValue.setText("Current Value: " + nightSyringeMax);
				dayWLI.setText("Current Value: " + dayWarnLevel);
				nightWLI.setText("Current Value: " + nightWarnLevel);
				finish();
				}
		});
		
		
	}
	
	private void printError (int choice) {
		if (choice == 1) {
			Toast.makeText(this,"WARNING: You have set your warning level for you day pen higher that its maximum. Please try a lower value.", Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this,"WARNING: You have set your warning level for you overnight pen higher that its maximum. Please try a lower value.", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		load();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		load();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		load();
	}
	
	private void save() {
		levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = levels.edit();
		editor.putInt("daySyringeMax", daySyringeMax);
		editor.putInt("nightSyringeMax", nightSyringeMax);
		editor.putInt("dayWarningLevel", dayWarnLevel);
		editor.putInt("nightWarningLevel", nightWarnLevel);
		editor.putInt("units", penUnits);
		editor.commit();	
	}
	
	private void load() {
		levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
		daySyringeMax = levels.getInt("daySyringeMax", 30);
		nightSyringeMax = levels.getInt("nightSyringeMax", 30);
		dayWarnLevel = levels.getInt("dayWarningLevel", 0);
		nightWarnLevel = levels.getInt("nightWarningLevel", 0);
		penUnits = levels.getInt("units", 0);
	}	
		
}
