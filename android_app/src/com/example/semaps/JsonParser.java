package com.example.semaps; 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONObject;

public class JsonParser {
	
		
		DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://projekt.komentovaneudalosti.cz/android/sent.php" );
		// Execute the request in the client
	    HttpResponse httpResponse = httpclient.execute(httppost);
	    // Grab the response
	    BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
	    String json = reader.readLine();
	
	    // Instantiate a JSON object from the request response
	    JSONObject jsonObject = new JSONObject(json);
   
}
