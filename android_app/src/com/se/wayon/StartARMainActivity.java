package com.se.wayon;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartARMainActivity extends AugmentedActivity {
	private static final String TAG = "StartARMainActivity";


	
	
	private static final Map<String, NetworkDataSource> sources = new ConcurrentHashMap<String, NetworkDataSource>();

	ReutlingenDataSource rds = new ReutlingenDataSource();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NetworkDataSource reutlingen = new ReutlingenDataSource(
				this.getResources());
		sources.put("data", reutlingen);
	}

	@Override
	public void onStart() {
		super.onStart();

		showpois();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v(TAG, "onOptionsItemSelected() item=" + item);
		switch (item.getItemId()) {
		case R.id.showRadar:
			showRadar = !showRadar;
			item.setTitle(((showRadar) ? "Hide" : "Show") + " Radar");
			break;
		case R.id.showZoomBar:
			showZoomBar = !showZoomBar;
			item.setTitle(((showZoomBar) ? "Hide" : "Show") + " Zoom Bar");
			zoomLayout.setVisibility((showZoomBar) ? LinearLayout.VISIBLE
					: LinearLayout.GONE);
			break;
		case R.id.toGmap:
			Intent i = new Intent(StartARMainActivity.this, GMapMainActivity.class);
			startActivity(i);
			finish();
			break;
		
		
		case R.id.exit:
			finish();
			

			break;
		}
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		super.onLocationChanged(location);

	}

	@Override
	protected void markerTouched(Marker marker) {
		Toast t = Toast.makeText(getApplicationContext(), marker.getName(),
				Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}

	public void showpois() {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		rds.parse();

		List<Marker> markers = rds.markers2;

		ARData.addMarkers(markers);

	}
}