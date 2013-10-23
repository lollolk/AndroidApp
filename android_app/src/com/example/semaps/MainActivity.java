package com.example.semaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.example.semaps.R;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LocationListener {
	private GoogleMap gmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SupportMapFragment mf = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		gmap = mf.getMap();

		LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		String provider = lm.getBestProvider(new Criteria(), true);
		if (provider == null) {
			onProviderDisabled(provider);
		}
		gmap.setMyLocationEnabled(true);
		gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		gmap.animateCamera(CameraUpdateFactory.zoomTo(17));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.iNormal2:
			normalView();
			return true;

		case R.id.iGetpois:
			Intent intent = new Intent(MainActivity.this,
					GetAllPoisViewActivity.class);
			startActivity(intent);
			Toast.makeText(getBaseContext(), "Drücke auf den Button um" +
					" die POIs Liste anzeigen zu lassen",
					Toast.LENGTH_SHORT).show();
			return true;

		case R.id.iHybrid2:
			hybridView();
			return true;
		

		default:
			return super.onOptionsItemSelected(item);

		}

	}

	

	private void hybridView() {
		// TODO Auto-generated method stub
		gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		Toast.makeText(getBaseContext(), "Zur Hybridsicht gewechselt",
				Toast.LENGTH_SHORT).show();

	}

	private void normalView() {
		// TODO Auto-generated method stub
		gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		Toast.makeText(getBaseContext(), "Zur Normalsicht gewechselt",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// Wenn Gps deaktiviert ist.
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("GPS ist deaktiviert");
		builder.setCancelable(false);
		builder.setPositiveButton("Aktiviere GPS",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Intent startGps = new Intent(
								android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						startActivity(startGps);

					}
				});

		builder.setNegativeButton("GPS deaktiviert lassen",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
