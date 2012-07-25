package com.diabetes.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OverwriteConformation extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rating_overwrite_popup);
		Button yesSelection = (Button) findViewById(R.id.yesRatingSelection);
		yesSelection.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
		
		Button noSelection = (Button) findViewById(R.id.noRatingSelection);
		noSelection.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
		
	}
}
