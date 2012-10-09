package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class InsulinLevels extends Activity {
	
	private int overNightLevel;
	private int dailyLevel;
	private int daySyringeMax;
	private int nightSyringeMax;
	private static final String PREFS_NAME = "insulinLevelPref";
	private SharedPreferences levels;
	//totalSyringe should be from a settings value for different sized syringes.
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insulin_meter);	
		
		load();
		final ProgressBar dailyBar = (ProgressBar) findViewById(R.id.DailyInsulinBar);
		dailyBar.setMax(daySyringeMax);
		dailyBar.setProgress(dailyLevel);
		
		final TextView dailyInsulinLevel = (TextView) findViewById(R.id.dailyInsilinLevel);
		dailyInsulinLevel.setText("Daily Insulin Pen Level: " + dailyLevel);
		
		final ProgressBar overNightBar = (ProgressBar) findViewById(R.id.OvernightInsulinBar);
		overNightBar.setMax(nightSyringeMax);
		overNightBar.setProgress(overNightLevel);
		
		final TextView ONInsulinLevel = (TextView) findViewById(R.id.ONInsilinPenLevel);
		ONInsulinLevel.setText("Overnight Insulin Pen Level: " + overNightLevel);
		
		Button resetDay = (Button) findViewById(R.id.resetDay);
		resetDay.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				dailyLevel = daySyringeMax; //Get daily level from global var in settings
				dailyBar.setProgress(dailyLevel);
				dailyInsulinLevel.setText("Daily Insulin Pen Level: " + dailyLevel);
			}
		});
		
		Button resetNight = (Button) findViewById(R.id.resetON);
		resetNight.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				overNightLevel = nightSyringeMax; //Get night level from global var in settings
				overNightBar.setProgress(overNightLevel);
				ONInsulinLevel.setText("Overnight Insulin Pen Level: " + overNightLevel);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		save();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		save();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		save();
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
		editor.putInt("dailyLevel", dailyLevel);
		editor.putInt("overNightLevel", overNightLevel);
		editor.commit();	
	}
	
	private void load() {
		levels = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
		dailyLevel = levels.getInt("dailyLevel", 0);
		overNightLevel = levels.getInt("overNightLevel", 0);
		daySyringeMax = levels.getInt("daySyringeMax", 30);
		nightSyringeMax = levels.getInt("nightSyringeMax", 30);
	}
}
