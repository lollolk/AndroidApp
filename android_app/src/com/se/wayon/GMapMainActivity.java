package com.se.wayon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.test_db.JSONParser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GMapMainActivity extends FragmentActivity implements
		LocationListener {

	private static final String LOG_TAG = "SE_App";
	// URL to make request
	private static String url = "http://projekty.komentovaneudalosti.cz/android/json.json";

	// JSON Node names
	private static final String TAG_DATA = "data";
	
	// Erstellt eine Map
	private GoogleMap gmap;
	// POIs JSONArray
	JSONArray data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_first);

		// Erzeugt die Map in der App
		SupportMapFragment mf = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		gmap = mf.getMap();

		LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		String provider = lm.getBestProvider(new Criteria(), true);
		
		// For updating the current position. Not needed at the moment.
		// lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50,
		// this);

		
		gmap.setMyLocationEnabled(true);
		// Set Map Type to Hybrid when the app starts
		gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		// Zoom to current position 
		gmap.animateCamera(CameraUpdateFactory.zoomTo(18));
	}

	// Menu buttons
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.iNormal2:
			normalView();
			return true;

		case R.id.iGetpois:
			showPois();
			Toast.makeText(getBaseContext(), "Es werden alle POIs angezeigt..",
					Toast.LENGTH_SHORT).show();
			return true;

		case R.id.iHybrid2:
			hybridView();
			return true;

		case R.id.iARSicht:
			// Starts the ARMainActivity
			Intent i = new Intent(GMapMainActivity.this,
					StartARMainActivity.class);
			startActivity(i);

		default:
			return super.onOptionsItemSelected(item);

		}

	}

	public void showPois() {
		// Verhindert die Fehlermeldung die Auftritt weil der Code nicht in der
		// void run() aufgerufen wird. 
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		JSONArray data;
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			// Getting Array of POIs
			
			data = json.getJSONArray(TAG_DATA);

			
			for (int i = 0; i < data.length(); i++) {
				// Create a marker for each POI in the JSON data.
				JSONObject jsonObj = data.getJSONObject(i);
				gmap.addMarker(new MarkerOptions()
						.title(jsonObj.getString("titel"))
						.snippet(jsonObj.getString("snippets"))
						.position(
								new LatLng(jsonObj.getDouble("lati"), jsonObj
										.getDouble("longti"))));

			}

			

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	
	private void hybridView() {
		// Change to hybrid view
		gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		Toast.makeText(getBaseContext(), "Zur Hybridsicht gewechselt",
				Toast.LENGTH_SHORT).show();

	}

	private void normalView() {
		// Change to normal view
		gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		Toast.makeText(getBaseContext(), "Zur Normalsicht gewechselt",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_first, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
