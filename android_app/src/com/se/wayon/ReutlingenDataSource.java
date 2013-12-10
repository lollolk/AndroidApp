package com.se.wayon;

import java.util.List;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.Toast;

public class ReutlingenDataSource extends NetworkDataSource {

	private static final String BASE_URL = "http://projekty.komentovaneudalosti.cz/android/json.json";
	private static final String TAG_DATA = "data";
	private static Bitmap icon = null;
	String name;
	List<Marker> markers2;

	public ReutlingenDataSource() {

	}

	public ReutlingenDataSource(Resources res) {
		if (res == null)
			throw new NullPointerException();

		createIcon(res);
	}

	protected void createIcon(Resources res) {
		if (res == null)
			throw new NullPointerException();
		// creates icon with wayon logo for every poi.
		icon = BitmapFactory.decodeResource(res, R.drawable.wayon);
	}

	@Override
	public String createRequestURL(double lat, double lon, double alt) {
		

		return BASE_URL;

	}

	public String newRequestURL(double lat, double lon, double alt) {

		return BASE_URL + "?lati" + lat + "&longit" + lon + "&altitude" + alt;

	}

	public List<Marker> parse() {
		// TODO Auto-generated method stub
		

		JSONObject json = null;
		JSONArray data = null;
		List<Marker> markers = new ArrayList<Marker>();

		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject jsonurl = jParser.getJSONFromUrl(BASE_URL);
		JSONObject jsonObj = null;

		try {
			
			data = jsonurl.getJSONArray(TAG_DATA);
			
			// loop through json objects
			for (int i = 0; i < data.length(); i++) {
				// Create a marker for each POI in the JSON data.
				jsonObj = data.getJSONObject(i);

				// json = data.getJSONObject(i);
				Marker ma = processJSONObject(jsonObj);
				if (ma != null)
					markers.add(ma);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.markers2 = markers;
		return markers;
	}

	private Marker processJSONObject(JSONObject jsonObj) {
		if (jsonObj == null) {
			
			return null;
		}

		Marker ma = null;
		
		try {
			// adds information from json objects to the markers 
			ma = new IconMarker(jsonObj.getString("titel"),
					jsonObj.getDouble("lati"), jsonObj.getDouble("longti"),
					jsonObj.getDouble("altitude"), Color.WHITE, icon);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// }
		return ma;
	}

	@Override
	public String newRequestURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Marker> parse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
