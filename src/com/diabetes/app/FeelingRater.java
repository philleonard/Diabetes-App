package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class FeelingRater extends Activity {
	
	private Button submitButton;
	private EditText feelingCommentText; 
	private RatingBar feelingRatingBar;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feeling_rater);
		
		feelingCommentText = (EditText) findViewById(R.id.feelingComment);
		feelingRatingBar = (RatingBar) findViewById(R.id.feelingRatingBar);
		
		//Comprise spinner list from last 5 or so entries in the data file. 
		
		submitButton = (Button) findViewById(R.id.feelingSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		
				String feelingComment = feelingCommentText.getText().toString();
				double feelingRating = feelingRatingBar.getRating();
		
				finish();
			}
		});
	}
}
