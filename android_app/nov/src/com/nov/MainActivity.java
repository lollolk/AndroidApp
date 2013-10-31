package com.nov;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText byear; // To take birthyear as input from user
	Button submit;
	TextView tv; // TextView to show the result of MySQL query

	public static final String tag_data = "data";
	public static final String url = "http://projekty.komentovaneudalosti.cz/android/json2.json"; // in
	String returnString = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode
				// is
				// most
				// commonly
				// used
				// to
				// catch
				// accidental
				// disk
				// or
				// network
				// access
				// on
				// the
				// application's
				// main
				// thread
				.penaltyLog().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		byear = (EditText) findViewById(R.id.editText1);
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

						returnString += "\n" + json_data.getDouble("lati")
								+ " -> " + json_data.getDouble("longti");
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
}