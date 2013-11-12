package com.sonyericsson.extras.liveware.extension.smartweather;

import android.util.Log;

public class Weather {
	private static String TAG = SmartWeatherService.LOG_TAG;
	
	public Weather() {
		
	}
	
	public void updateData() {
		 QueryAPI q = new QueryAPI(this);
		 q.execute("https://api.forecast.io/forecast/67355a5713e12d68c5e55c45c15012db/37.8267,-122.423");
	}
	
	public void DataUpdated() {
		Log.d(TAG, "update data called");
	}

}
