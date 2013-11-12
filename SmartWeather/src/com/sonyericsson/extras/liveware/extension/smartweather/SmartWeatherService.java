package com.sonyericsson.extras.liveware.extension.smartweather;

import android.os.Handler;

import com.sonyericsson.extras.liveware.extension.util.Dbg;
import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

public class SmartWeatherService extends ExtensionService{
	public static final String EXTENSION_KEY = "com.sonyericsson.extras.liveware.extension.smartweather.key";
	public static final String LOG_TAG = "SmartWeatherExtension";

	public SmartWeatherService() {
		super(EXTENSION_KEY);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Dbg.d("SmartWeatherService: onCreate");
	};

	@Override
	protected RegistrationInformation getRegistrationInformation() {
		return new SmartWeatherRegisterInfo(this);
	}

	@Override
	protected boolean keepRunningWhenConnected() {
		return false;
	}
	
	@Override
	public ControlExtension createControlExtension(String hostAppPackageName) {
		return new SmartWeatherControl(hostAppPackageName, this, new Handler());
	}

}
