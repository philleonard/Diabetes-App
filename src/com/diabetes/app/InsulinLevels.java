package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class InsulinLevels extends Activity {
	
	private int overNightLevel;
	private int dailyLevel;
	private int daySyringeMax;
	private int nightSyringeMax;
	private int tempDayLevel;
	private int tempNightLevel;
	private static final String PREFS_NAME = "insulinLevelPref";
	private SharedPreferences levels;
	//totalSyringe should be from a settings value for different sized syringes.
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insulin_meter);	
		
		load();
		final ProgressBar dailyBar = (ProgressBar) findViewById(R.id.DailyInsulinBar);
		dailyBar.setMax(daySyringeMax);
		if (dailyLevel>daySyringeMax) {
			dailyLevel = daySyringeMax;
		}
		dailyBar.setProgress(dailyLevel);
		
		final TextView dailyInsulinLevel = (TextView) findViewById(R.id.dailyInsilinLevel);
		dailyInsulinLevel.setText("Daily Insulin Pen Level: " + dailyLevel);
		
		final ProgressBar overNightBar = (ProgressBar) findViewById(R.id.OvernightInsulinBar);
		overNightBar.setMax(nightSyringeMax);
		if (overNightLevel>nightSyringeMax) {
			overNightLevel = nightSyringeMax;
		}
		overNightBar.setProgress(overNightLevel);
		
		final TextView ONInsulinLevel = (TextView) findViewById(R.id.ONInsilinPenLevel);
		ONInsulinLevel.setText("Overnight Insulin Pen Level: " + overNightLevel);
		
		final EditText dayTopUp = (EditText) findViewById(R.id.dayTUEdit);
		final EditText nightTopUp = (EditText) findViewById(R.id.nightTUEdit);
		
		Button topUpDayBut = (Button) findViewById(R.id.dayTopUp);
		
		topUpDayBut.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
					tempDayLevel = Integer.parseInt(dayTopUp.getText().toString());
					if (tempDayLevel <= daySyringeMax-dailyLevel) {
						dailyLevel = dailyLevel+tempDayLevel;
						dailyBar.setProgress(dailyLevel);
						dailyInsulinLevel.setText("Daily Insulin Pen Level: " + dailyLevel);
					}
					else {
						printError(1);
					}
				}catch (Exception e){}
			}
		});
		
		Button topUpNightBut = (Button) findViewById(R.id.nightTopUp);
		topUpNightBut.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
					tempNightLevel = Integer.parseInt(nightTopUp.getText().toString());
					if (tempNightLevel <= nightSyringeMax-overNightLevel) {
						overNightLevel = overNightLevel+tempNightLevel;
						overNightBar.setProgress(overNightLevel);
						ONInsulinLevel.setText("Overnight Insulin Pen Level: " + overNightLevel);
					}
					else {
						printError(2);
					}
				}catch (Exception e){}
			}
		});
		
		
		
		
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
	
	private void printError (int errorCode){
		if (errorCode == 1) {
			Toast.makeText(this,"WARNING: Adding " + tempDayLevel + " units would overflow the pen. The maximum this pen can hold is " + daySyringeMax, Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this,"WARNING: Adding " + tempNightLevel + " units would overflow the pen. The maximum this pen can hold is " + nightSyringeMax, Toast.LENGTH_LONG).show();
		}
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
