package com.diabetes.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.splash_screen);
		Thread splash = new Thread() {
			@Override
			public void run() {
				try {
					ImageView diabetesLogoView = (ImageView) findViewById(R.id.diabeteslogoimage);
					TextView appNameTextView = (TextView) findViewById(R.id.appNameText);
					Animation FadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
					diabetesLogoView.startAnimation(FadeInAnimation); //Set animation to your ImageView
					appNameTextView.startAnimation(FadeInAnimation);
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		};
		splash.start();
	}
}
