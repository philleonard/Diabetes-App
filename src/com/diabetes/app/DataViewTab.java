package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DataViewTab extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_view_tab);	
		
		Button dataViewButton = (Button) findViewById(R.id.dataViewButton);
		dataViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), ViewData.class));
			}
		});
		
		Button graphViewButton = (Button) findViewById(R.id.xyGraphButton);
		graphViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), GraphViewer.class));
			}
		});
		
		Button exportDataButton = (Button) findViewById(R.id.exportDataButton);
		exportDataButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), ExportData.class));
			}
		});
	}
}
