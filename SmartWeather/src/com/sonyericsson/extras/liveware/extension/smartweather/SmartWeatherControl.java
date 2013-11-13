package com.sonyericsson.extras.liveware.extension.smartweather;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class SmartWeatherControl extends ControlExtension  {
	
	private Handler mHandler = null;
	private Context mcontext = null;
	private int mWidth;
	private int mHeight;
	private int SLIDE_WEATHER = 1;
	private int SLIDE_LIST = 2;
	private int current_slide = SLIDE_WEATHER;


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
	 
	 public void update_data(Double temp, String msg, String icon){
		 sendText(R.id.textTemp, Double.toString(temp) + "°C");
		 sendText(R.id.textHello, msg);
		 
		 String icon_id = icon.replace('-', '_');
		 Log.d(SmartWeatherService.LOG_TAG,"IMAGE ID : " + icon_id);
		 
		 int id_img = mContext.getResources().getIdentifier(icon_id, "drawable", mContext.getPackageName());
	     Bitmap bMap = BitmapFactory.decodeResource(mContext.getResources(), id_img);
		 sendImage(R.id.iconWeather, bMap);
	 }
	 
	 public void LoadWeather() {
		 Bundle[] data = new Bundle[4];
		 Bundle b1 = new Bundle();
		 Bundle btemp = new Bundle();
		 Bundle bmess = new Bundle();
		 Bundle bimg = new Bundle();
		 
	     b1.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.textHello);
	     b1.putString(Control.Intents.EXTRA_TEXT, "Loading");
	     
	     btemp.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.textTemp);
	     btemp.putString(Control.Intents.EXTRA_TEXT, "Loading");
	     
	     bmess.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.textInfo);
	     bmess.putString(Control.Intents.EXTRA_TEXT, "PM2.5:");
		 
	     bimg.putInt(Control.Intents.EXTRA_LAYOUT_REFERENCE, R.id.iconWeather);
	     
	     data[0] = b1;
	     data[1] = btemp;
	     data[2] = bmess;
	     data[3] = bimg;
	     
		 showLayout(R.layout.home, data);
	 }
	 
	 public void  LoadList() {
		 showLayout(R.layout.generalwear, null);
	 }
	 
	 @Override
	public void onStart() {
		 
		 Log.d(SmartWeatherService.LOG_TAG, "onStart");
		 Weather w = new Weather(mcontext);
		 Log.d(SmartWeatherService.LOG_TAG,"up GPS");
		 w.updateLoc();
		 Log.d(SmartWeatherService.LOG_TAG,"up weather");
		w.updateData(this);
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
		
		
	}
	 
	 @Override
	public void onSwipe(int direction) {
		 Log.d(SmartWeatherService.LOG_TAG,"onSwipe");
		 if (direction == Control.Intents.SWIPE_DIRECTION_LEFT && current_slide != SLIDE_LIST) {
			 Log.d(SmartWeatherService.LOG_TAG,"swipe left");
			 current_slide = SLIDE_LIST;
			 LoadList();
		 }
		 if (direction == Control.Intents.SWIPE_DIRECTION_RIGHT && current_slide != SLIDE_WEATHER) {
			 Log.d(SmartWeatherService.LOG_TAG,"swipe right");
			 current_slide = SLIDE_WEATHER;
			 LoadWeather();
		 }
		 
		super.onSwipe(direction);
	}
	 
	 @Override
	public void onPause() {
		 Log.d(SmartWeatherService.LOG_TAG,"onPause");
		super.onPause();
	}
	 
	 @Override
	public void onResume() {
		 if (current_slide == SLIDE_WEATHER) {
			 LoadWeather();
		 } else if (current_slide == SLIDE_LIST) {
			 LoadList();
		 }	 
		 Log.d(SmartWeatherService.LOG_TAG,"onResume");
	}
}
