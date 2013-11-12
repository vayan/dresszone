package com.sonyericsson.extras.liveware.extension.smartweather;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class SmartWeatherControl extends ControlExtension {
	
	private Handler mHandler = null;
	private int mWidth;
	private int mHeight;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.RGB_565;


	public SmartWeatherControl(final String hostAppPackageName, final Context context,
            Handler handler) {
		super(context, hostAppPackageName);
		if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
		  	mHandler = handler;
	        mWidth = getSupportedControlWidth(context);
	        mHeight = getSupportedControlHeight(context);
	}

	 public static int getSupportedControlWidth(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.smart_watch_control_width);
	 }
	 
	 public static int getSupportedControlHeight(Context context) {
	        return context.getResources().getDimensionPixelSize(R.dimen.smart_watch_control_height);
	 }
	 
	 @Override
	public void onStart() {
		 Log.d(SmartWeatherService.LOG_TAG, "onStart");
		 HelloWorld("Hell");
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
		HelloWorld("touched");
		Weather w = new Weather();
		w.updateData();
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
		 Log.d(SmartWeatherService.LOG_TAG,"onRemuse");
		 HelloWorld("Hello");
	}
	 
	 public void HelloWorld(String text) {
		 LinearLayout root = new LinearLayout(mContext);
	     root.setLayoutParams(new LayoutParams(mWidth, mHeight));
	     
	     LinearLayout sampleLayout = (LinearLayout)LinearLayout.inflate(mContext, R.layout.home,
	                root);
	        sampleLayout.measure(mWidth, mHeight);
	        sampleLayout
	                .layout(0, 0, sampleLayout.getMeasuredWidth(), sampleLayout.getMeasuredHeight());
	        TextView t = new TextView(mContext);
	        t=(TextView)sampleLayout.findViewById(R.id.textHello); 
	        t.setText(text);
	        Log.d(SmartWeatherService.LOG_TAG,"Text setted");
	        Bitmap menu = Bitmap.createBitmap(mWidth, mHeight, BITMAP_CONFIG);
	        // Set default density to avoid scaling.
	        menu.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	        Canvas canvas = new Canvas(menu);
	        sampleLayout.draw(canvas);
	        showBitmap(menu);
	 }
	
}
