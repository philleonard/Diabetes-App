package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DataEntryTab extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_entry_tab);
		
		Button dataEntryButton = (Button) findViewById(R.id.dataEntryButton);
		dataEntryButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), DataEntryForm.class));
			}
		});
		
		Button ratingButton = (Button) findViewById(R.id.ratingButton);
		ratingButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), FeelingRater.class));
			}
		});
	}
}
