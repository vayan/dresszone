package com.sonyericsson.extras.liveware.extension.smartweather;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class SmartWeatherControl extends ControlExtension  {
	
	private Handler mHandler = null;
	private Context mcontext = null;
	private int mWidth;
	private int mHeight;


	public SmartWeatherControl(final String hostAppPackageName, final Context context,
            Handler handler) {
		super(context, hostAppPackageName);
		if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
		  	mHandler = handler;
	        mWidth = getSupportedControlWidth(context);
	        mHeight = getSupportedControlHeight(context);
	        mcontext = context;
	}

	 public static int getSupportedControlWidth(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.smart_watch_control_width);
	 }
	 
	 public static int getSupportedControlHeight(Context context) {
	        return context.getResources().getDimensionPixelSize(R.dimen.smart_watch_control_height);
	 }
	 
	 public void update_data(Double temp, String msg){
		 sendText(R.id.textTemp, "Tmp : " + Double.toString(temp));
		 sendText(R.id.textHello, "MSG : " + msg);
	 }
	 
	 @Override
	public void onStart() {
		 Log.d(SmartWeatherService.LOG_TAG, "onStart");
	}
	 
	 @Override
	public void onDestroy() {
		 Log.d(SmartWeatherService.LOG_TAG,"onDestroy");
		super.onDestroy();
	}
	 
	 @Override
	public void onStop() {
		 Log.d(SmartWeatherService.LOG_TAG,"onStop");
		super.onStop();
	}
	 
	 @Override
	public void onTouch(ControlTouchEvent event) {
		 Log.d(SmartWeatherService.LOG_TAG,"onTouch");
		Weather w = new Weather(mcontext);
		 Log.d(SmartWeatherService.LOG_TAG,"up GPS");
		 w.updateLoc();
		 Log.d(SmartWeatherService.LOG_TAG,"up weather");
		w.updateData(this);
		
	}
	 
	 @Override
	public void onSwipe(int direction) {
		 Log.d(SmartWeatherService.LOG_TAG,"onSwipe");
		super.onSwipe(direction);
	}
	 
	 @Override
	public void onPause() {
		 Log.d(SmartWeatherService.LOG_TAG,"onPause");
		super.onPause();
	}
	 
	 @Override
	public void onResume() {
		 Log.d(SmartWeatherService.LOG_TAG,"onResume");
		 
		 Bundle[] data = new Bundle[2];
		 Bundle b1 = new Bundle();
		 Bundle btemp = new Bundle();
		 
	     b1.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.textHello);
	     b1.putString(Control.Intents.EXTRA_TEXT, "1");
	     
	     btemp.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.textTemp);
	     btemp.putString(Control.Intents.EXTRA_TEXT, "1");
		 
	     data[0] = b1;
	     data[1] = btemp;
	     
		 showLayout(R.layout.home, data);
	}
}
