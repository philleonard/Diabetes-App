package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.achartengine.chart.XYChart;

public class GraphViewer extends Activity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xy_chart);
		
		Button dateSelection = (Button) findViewById(R.id.openDateSelector);
		dateSelection.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
					startActivityForResult(new Intent(getApplicationContext(), DateSelection.class), 0);			
			}
		});
	}
}
