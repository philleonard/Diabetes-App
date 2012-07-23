package com.diabetes.app;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.diabetes.app.R;

public class DiabetesApplicationActivity extends TabActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), SplashScreen.class));
        setContentView(R.layout.main);
      
        SdAvail sd = new SdAvail();
		if (!sd.check() || !sd.injectionDataCheck()) {
			Toast.makeText(this,"External Storage not found. Unable to write data" , Toast.LENGTH_LONG).show();
		}
		
		TabHost tabHost = getTabHost();
		Resources resources = getResources(); 
		
		Intent dataEntryIntent = new Intent().setClass(this, DataEntryTab.class);
		TabSpec dataEntryTab = tabHost.newTabSpec("Data Entry").setIndicator("Data Entry").setContent(dataEntryIntent);		  
		tabHost.addTab(dataEntryTab);
		
		Intent dataViewIntent = new Intent().setClass(this, DataViewTab.class);
		TabSpec dataViewTab = tabHost.newTabSpec("Data View").setIndicator("Data View").setContent(dataViewIntent);
		tabHost.addTab(dataViewTab);
		
		Intent settingsIntent = new Intent().setClass(this, SettingsAndExtraTab.class);
		TabSpec settingsTab = tabHost.newTabSpec("Settings & Extra").setIndicator("Settings & Extra").setContent(settingsIntent);
		tabHost.addTab(settingsTab);
		
		tabHost.setCurrentTab(0);
    }  
}