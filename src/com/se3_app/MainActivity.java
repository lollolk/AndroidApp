package com.se3_app;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		Button geoIPsent = (Button) findViewById(R.id.button1);
		/*
		 * http://stackoverflow.com/questions/4655758/how-to-connect-two-layout-java
		 * -android
		 */
		geoIPsent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*
				 * http://stackoverflow.com/questions/2115758/how-to-display-alert
				 * -dialog-in-android
				 */
				AlertDialog.Builder builder1 = new AlertDialog.Builder(
						MainActivity.this);
				builder1.setMessage("Pha.");
				builder1.setCancelable(true);
				builder1.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				AlertDialog alert11 = builder1.create();
				alert11.show();

				// Intent intent = new Intent(MainActivity.this,
				// GEO_IP.class); // start some other method action
				// startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
