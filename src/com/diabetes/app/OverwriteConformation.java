package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OverwriteConformation extends Activity {
	
	public boolean selection = true;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rating_overwrite_popup);
		Button yesSelection = (Button) findViewById(R.id.yesRatingSelection);
		yesSelection.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				selection = true;
				Intent returnIntent = new Intent();
				returnIntent.putExtra("selectionResult", selection);
				setResult(RESULT_OK,returnIntent);     
				finish();
			}
		});
		
		Button noSelection = (Button) findViewById(R.id.noRatingSelection);
		noSelection.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				selection = false;
				Intent returnIntent = new Intent();
				returnIntent.putExtra("selectionResult", selection);
				setResult(RESULT_OK,returnIntent); 
				finish();
			}
		});
		
	}
}
