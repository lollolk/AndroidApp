package com.se.wayon;

import java.util.List;

import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

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

		icon = BitmapFactory.decodeResource(res, R.drawable.wayon);
	}



	public String newRequestURL(double lat, double lon, double alt) {

		return BASE_URL + "?lati" + lat + "&longit" + lon + "&altitude" + alt;

	}

	public List<Marker> parse() {
		// TODO Auto-generated method stub

		
		JSONArray data = null;
		List<Marker> markers = new ArrayList<Marker>();

		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject jsonurl = jParser.getJSONFromUrl(BASE_URL);
		JSONObject jsonObj = null;

		try {
			data = jsonurl.getJSONArray(TAG_DATA);

			for (int i = 0; i < data.length(); i++) {
				// create a marker for each POI in the JSON data.
				jsonObj = data.getJSONObject(i);

				Marker ma = processJSONObject(jsonObj);
				if (ma != null)
					markers.add(ma);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.markers2 = markers;
		System.out.println(markers2);
		return markers;
	}

	private Marker processJSONObject(JSONObject jsonObj) {
		if (jsonObj == null) {
			System.out.println("hallo hier");
			return null;
		}

		Marker ma = null;
		//
		try {
			ma = new IconMarker(jsonObj.getString("titel"),
					jsonObj.getDouble("lati"), jsonObj.getDouble("longti"),
					jsonObj.getDouble("altitude"), Color.WHITE, icon);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return ma;
	}



	@Override
	public List<Marker> parse(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

}
