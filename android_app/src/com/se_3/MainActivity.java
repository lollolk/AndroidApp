package com.se_3;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setUpMap();
	}

	private void setUpMap() {
		// MyLocation Layer von GoogleMap aktivieren
		map.setMyLocationEnabled(true);

		// Get LocationManager object from System Service LOCATION-SERVICE
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Create a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Get the name of the best Provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Get current Location
		Location myLocation = locationManager.getLastKnownLocation(provider);

		// set map type
		map.setMapType(map.MAP_TYPE_HYBRID);

		// Get Latitude of the current location
		double latitude = myLocation.getLatitude();

		// Get Longtude of the current location
		double longitude = myLocation.getLongitude();

		// Create a LatLng object for the current location
		LatLng latlng = new LatLng(latitude, longitude);

		// Show the current location in Google Map
		map.moveCamera(CameraUpdateFactory.newLatLng(latlng));

		// Zoom in the Google Map
		map.animateCamera(CameraUpdateFactory.zoomTo(14));
		map.addMarker(new MarkerOptions().position(
		new LatLng(latitude, longitude)).title("Mein Standort"));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
