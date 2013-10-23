package com.example.semaps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GetAllPoisViewActivity extends Activity {

	Button btnConnect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_all_pois_view);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		
		btnConnect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
			// Launching All products Activity
			Intent i = new Intent(getApplicationContext(), AllPoiActivity.class);
			startActivity(i);
			}
		 });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_all_pois_view, menu);
		return true;
	}

}
