package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.diabetes.app.R;

public class DiabetesApplicationActivity extends Activity {
	
	private Button dataButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dataButton = (Button) findViewById(R.id.informationEntry);
        dataButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent dataEntryForm = new Intent(v.getContext(), DataEntryForm.class);
				startActivityForResult(dataEntryForm, 0);
			}
        });        	
    }
   
}