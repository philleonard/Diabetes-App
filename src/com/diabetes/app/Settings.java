package com.diabetes.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity {
	
	private int daySyringeMax;
	private int nightSyringeMax;
	private static final String PREFS_NAME = "insulinLevelPref";
	private SharedPreferences levels;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);	
		
		load();
		
		final EditText dayPenEditor = (EditText) findViewById(R.id.dayPenEditor);
		dayPenEditor.setText(daySyringeMax);
		
		final EditText nightPenEditor = (EditText) findViewById(R.id.nightPenEditor);
		dayPenEditor.setText(nightSyringeMax);
		
		Button saveButton = (Button) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				daySyringeMax = Integer.parseInt(dayPenEditor.getText().toString());
				nightSyringeMax = Integer.parseInt(nightPenEditor.getText().toString());
				save();
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
		editor.commit();	
	}
	
	private void load() {
		levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
		daySyringeMax = levels.getInt("daySyringeMax", 0);
		nightSyringeMax = levels.getInt("nightSyringeMax", 0);
	}
}
