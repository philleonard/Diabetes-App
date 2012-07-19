package com.diabetes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.splash_screen);
		final View splashView = findViewById(R.layout.splash_screen);
		Thread splash = new Thread() {
			@Override
			public void run() {
				try {
					ImageView diabetesLogoView = (ImageView) findViewById(R.id.diabeteslogoimage);
					TextView appNameTextView = (TextView) findViewById(R.id.appNameText);
					
					Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeinsplash);
					
					diabetesLogoView.startAnimation(fadeInAnimation); //Set animation to your ImageView
					appNameTextView.startAnimation(fadeInAnimation);
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
					overridePendingTransition(R.anim.fadein, R.anim.fadeout);
				}
			}
		};
		splash.start();
	}
	public void onPause(Bundle savedInstanceState) {
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		super.onPause();
	}
}
