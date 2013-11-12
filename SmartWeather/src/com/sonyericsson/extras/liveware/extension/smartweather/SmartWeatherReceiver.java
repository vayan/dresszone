package com.sonyericsson.extras.liveware.extension.smartweather;

import com.sonyericsson.extras.liveware.extension.util.Dbg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SmartWeatherReceiver extends BroadcastReceiver {
	
	public SmartWeatherReceiver() {
		 Dbg.setLogTag(SmartWeatherService.LOG_TAG);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		intent.setClass(context, SmartWeatherService.class);
		context.startService(intent);
	}

}
