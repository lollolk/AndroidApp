package com.se_3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Connection_to_DB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connection_to__db);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connection_to__db, menu);
		return true;
	}

}
