package com.diabetes.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends Activity {
	
	private int daySyringeMax;
	private int nightSyringeMax;
	private int dayWarnLevel;
	private int nightWarnLevel;
	private static final String PREFS_NAME = "insulinLevelPref";
	private SharedPreferences levels;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingspage);	

		load();
		
		final EditText dayPenEditor = (EditText) findViewById(R.id.dayPenEditor);
		final EditText nightPenEditor = (EditText) findViewById(R.id.nightPenEditor);
		final EditText dayWarnEdit = (EditText) findViewById(R.id.dayWarnLevelEdit);
		final EditText nightWarnEdit = (EditText) findViewById(R.id.nightWarnLevelEdit);
		
		final TextView dayValue = (TextView) findViewById(R.id.dayValue);
		dayValue.setText("Current Value: " + daySyringeMax);
		
		final TextView nightValue = (TextView) findViewById(R.id.nightValue);
		nightValue.setText("Current Value: " + nightSyringeMax);
		
		final TextView dayWLI = (TextView) findViewById(R.id.dayWLI);
		dayWLI.setText("Current Warning Level: " + dayWarnLevel);
		
		final TextView nightWLI = (TextView) findViewById(R.id.nightWLI);
		nightWLI.setText("Current Warning Level: " + nightWarnLevel);
		
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
					dayWarnLevel = Integer.parseInt(dayWarnEdit.getText().toString());
					}catch (Exception e){}
				try {
					nightWarnLevel = Integer.parseInt(nightWarnEdit.getText().toString());
					}catch (Exception e){}
				save();
				dayValue.setText("Current Value: " + daySyringeMax);
				nightValue.setText("Current Value: " + nightSyringeMax);
				dayWLI.setText("Current Warning Level: " + dayWarnLevel);
				nightWLI.setText("Current Warning Level: " + nightWarnLevel);
				finish();
				}
		});
		
		
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
		editor.commit();	
	}
	
	private void load() {
		levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
		daySyringeMax = levels.getInt("daySyringeMax", 0);
		nightSyringeMax = levels.getInt("nightSyringeMax", 0);
		dayWarnLevel = levels.getInt("dayWarningLevel", 0);
		nightWarnLevel = levels.getInt("nightWarningLevel", 0);
	}	
		
}
