package com.sonyericsson.extras.liveware.extension.smartweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;

public class Weather {
	private static String TAG = SmartWeatherService.LOG_TAG;
	private Context mContext = null;
	private double lat;
	private double lng;
	private String city;
	private Double tempC = 0.0;
	private Double tempF = 0.0;
	private String msg = null;
	SmartWeatherControl wa = null;
	
	
	public Weather(Context context) {
		mContext = context;
	}
	
	
	public void updateLoc() {
	//TODO Use GPS
	lat = 39.9289;
	lng = 116.3883;
	}
	
	public void updateData(SmartWeatherControl w) {
		//TODO get PM2.5
		 QueryAPI q = new QueryAPI(this);
		 String url = "https://api.forecast.io/forecast/b02c017c2c7a3b39f1e32579f99216ad/"+lat+","+lng;
		 Log.d(TAG, "URL API " + url);
		 q.execute(url);
		 wa = w ;
	}
	
	public void DataUpdated(JSONObject data) throws JSONException {
		Log.d(TAG, "update data called");
		
		String temp  = data.getJSONObject("currently").getString("temperature");
		msg = data.getJSONObject("currently").getString("summary");
		tempC = Double.valueOf(temp);
		Log.d(TAG, "TEST string " + temp + "Test double "+ tempC);
		wa.update_data(tempC, msg);
	}
}
