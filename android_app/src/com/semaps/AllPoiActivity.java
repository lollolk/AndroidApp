package com.semaps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllPoiActivity extends Activity {

	Button submit;
	TextView tv; // TextView to show the result of MySQL query

	public static final String url = "http://projekty.komentovaneudalosti.cz/android/json2.json";
	String returnString = "";

	public static final String tag_data = "results";

	// products JSONArray
	JSONArray pois = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_poi);

		submit = (Button) findViewById(R.id.submitbutton);
		tv = (TextView) findViewById(R.id.showresult);

		// define the action when user clicks on submit button
		submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				JSONParser jParser = new JSONParser();

				// getting JSON string from URL
				JSONObject json = jParser.getJSONFromUrl(url);

				try {

					// Creating JSON Parser instance
					JSONArray s = json.getJSONArray(tag_data);

					for (int i = 0; i < s.length(); i++) {
						JSONObject json_data = s.getJSONObject(i);

						returnString += "\n" + json_data.getDouble("lat")
								+ " -> " + json_data.getDouble("lng");
					}
				} catch (JSONException e) {
					Log.e("log_tag", "Error parsing data " + e.toString());
				}

				try {
					tv.setText(returnString);
				} catch (Exception e) {
					Log.e("log_tag", "Error in Display!" + e.toString());
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_poi, menu);
		return true;
	}

}
