package com.sonyericsson.extras.liveware.extension.smartweather;

import org.json.JSONException;
import org.json.JSONObject;

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
		
	//BEIJING	
	lat = 39.9289;
	lng = 116.3883;
	
	//PARIS
//	lat = 48.856667;
//	lng = 2.350987;
	
	//LYON
//	lat = 45.767299;
//	lng = 4.834329;
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
		
		String icon  = data.getJSONObject("currently").getString("icon");
		String temp  = data.getJSONObject("currently").getString("temperature");
		msg = data.getJSONObject("currently").getString("summary");
		tempF = Double.valueOf(temp);
		tempC = (double) Math.round((((tempF - 32) * 5/9)));
		Log.d(TAG, "TEST string " + temp + "Test double "+ tempC);
		wa.update_data(tempC, msg, icon);
	}
}
