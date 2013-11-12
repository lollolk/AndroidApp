package com.se.wayon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.widget.Toast;

/**
 * Thanks to
 * http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 */
public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);

		Toast.makeText(getBaseContext(), "WAY ON wird gestartet...",
				Toast.LENGTH_LONG).show();
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. 
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your App Main Activity
				Intent i = new Intent(SplashScreen.this, GMapMainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}