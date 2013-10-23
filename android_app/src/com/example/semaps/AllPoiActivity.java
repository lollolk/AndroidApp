package com.example.semaps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AllPoiActivity extends ListActivity {

	// Progress Dialog
    private ProgressDialog pDialog;
 
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> poiList;
 
    // url to get all products list
    private static String url_all_poi = "http://projekty.komentovaneudalosti.cz/android/sent.php";
 
 // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_POI = "pois";
    private static final String TAG_PID = "key_prim";
    private static final String TAG_NAME = "name";
 
    // products JSONArray
    JSONArray pois = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_poi);
		
		
		// Hashmap for ListView
        poiList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllProducts().execute();
 
        // Get listview
        ListView lv = getListView();
 
        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                
            	String poiid = ((TextView) view.findViewById(R.id.poi_id)).getText()
                        .toString();
 
                
            }
        });
	}

	
	
	
	 class LoadAllProducts extends AsyncTask<String, String, String> {
		 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(AllPoiActivity.this);
	            pDialog.setMessage("Loading products. Please wait...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	 
	        /**
	         * getting All products from url
	         * */
	        protected String doInBackground(String... args) {
	            // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            // getting JSON string from URL
	            JSONObject json = jParser.makeHttpRequest(url_all_poi, "GET", params);
	 
	            // Check your log cat for JSON reponse
	            Log.d("All Products: ", json.toString());
	 
	            try {
	                // Checking for SUCCESS TAG
	                int success = json.getInt(TAG_SUCCESS);
	 
	                if (success == 1) {
	                    // products found
	                    // Getting Array of Products
	                    pois = json.getJSONArray(TAG_POI);
	 
	                    // looping through All Products
	                    for (int i = 0; i < pois.length(); i++) {
	                        JSONObject c = pois.getJSONObject(i);
	 
	                        // Storing each json item in variable
	                        String id = c.getString(TAG_POI);
	                        String name = c.getString(TAG_NAME);
	 
	                        // creating new HashMap
	                        HashMap<String, String> map = new HashMap<String, String>();
	 
	                        // adding each child node to HashMap key => value
	                        map.put(TAG_POI, id);
	                        map.put(TAG_NAME, name);
	 
	                        // adding HashList to ArrayList
	                        poiList.add(map);
	                    }
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	 
	            return null;
	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog after getting all products
	            pDialog.dismiss();
	            // updating UI from Background Thread
	            runOnUiThread(new Runnable() {
	                public void run() {
	                    /**
	                     * Updating parsed JSON data into ListView
	                     * */
	                    ListAdapter adapter = new SimpleAdapter(
	                            AllPoiActivity.this, poiList,
	                            R.layout.list_all_poi, new String[] { TAG_PID,
	                                    TAG_NAME},
	                            new int[] { R.id.poi_id, R.id.name });
	                    // updating listview
	                    setListAdapter(adapter);
	                }
	            });
	 
	        }
	 
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_poi, menu);
		return true;
	}

}
