package com.sonyericsson.extras.liveware.extension.smartweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class QueryAPI extends AsyncTask<String, Void, JSONObject>{
	private Exception exception; //todo check exception
	private static String TAG = SmartWeatherService.LOG_TAG;
	private Weather w; 
	
	public QueryAPI(Weather w) {
		this.w = w;
		Log.d(TAG, "query api created");
	}

	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;

	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    is.close();

	    return sb.toString();
	}
	
	@Override
	protected JSONObject doInBackground(String... params) {
		String url = params[0];
		String result = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;
        JSONArray valArray = null;
        JSONObject json = null;
        Log.d(TAG, "Start query API");
        try {
                response = httpclient.execute(httpget);
                Log.i(TAG, "Status:[" + response.getStatusLine().toString() + "]");
                HttpEntity entity = response.getEntity();
                
                if (entity != null) {
                        
                        InputStream instream = entity.getContent();
                        result = convertStreamToString(instream);
                        Log.i(TAG, "Result of converstion: [" + result + "]");
                        
                        instream.close();
                }
        } catch (ClientProtocolException e) {
                Log.e("REST", "There was a protocol based error", e);
        } catch (IOException e) {
                Log.e("REST", "There was an IO Stream related error", e);
        } catch (Exception e) {
			e.printStackTrace();
		}
        try{
        	 Log.d(TAG, "Done go json");
            json = new JSONObject(result);
            JSONArray nameArray = json.names();
            valArray = json.toJSONArray(nameArray);
//            for (int i = 0; i < valArray.length(); i++) {
//                    Log.i(TAG, "<jsonname" + i + ">\\n" + nameArray.getString(i)    + "\\n</jsonname" + i + ">\\n" + "<jsonvalue" + i + ">\\n" + valArray.getString(i) + "\\n</jsonvalue"   + i + ">");
//            }
            Log.d(TAG, "Json done");
	    }
	    catch (JSONException e) {
	            Log.e("JSON", "There was an error parsing the JSON", e);
	    }
		return json;
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		try {
			w.DataUpdated(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
