package com.diabetes.app;
import com.diabetes.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;


public class DateSelection extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.date_selector);
		
		final DatePicker fromDate = (DatePicker) findViewById(R.id.fromDate);
		final DatePicker toDate = (DatePicker) findViewById(R.id.toDate);
		
		Button submit = (Button) findViewById(R.id.goDateButton);
		submit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String fromDateSelected =  fromDate.getDayOfMonth()  + "/" +  fromDate.getMonth() + "/"  + fromDate.getYear();
				String toDateSelected = toDate.getDayOfMonth()  + "/" +  toDate.getMonth() + "/"  + toDate.getYear();
				Intent dateReturn = new Intent();
				dateReturn.putExtra("fromDate", fromDateSelected);
				dateReturn.putExtra("toDate", toDateSelected);
				setResult(RESULT_OK,dateReturn);
				finish();
			}
		});
	}
}
