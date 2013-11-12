package com.sonyericsson.extras.liveware.extension.smartweather;

import android.content.ContentValues;
import android.content.Context;

import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

public class SmartWeatherRegisterInfo extends RegistrationInformation {
	
	final Context mContext;
	
	protected SmartWeatherRegisterInfo(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context == null");
		}
		mContext = context;
	}

	@Override
	public int getRequiredNotificationApiVersion() {
		return 0;
	}

	@Override
	public int getRequiredWidgetApiVersion() {
		return 0;
	}

	@Override
	public int getRequiredControlApiVersion() {
		return 1;
	}

	@Override
	public int getRequiredSensorApiVersion() {
		return 0;
	}
		
	@Override
	public ContentValues getExtensionRegistrationConfiguration() {
		String iconHostApp = ExtensionUtils.getUriString(mContext, R.drawable.icon);
        String iconExtension = ExtensionUtils
                .getUriString(mContext, R.drawable.icon);

        ContentValues values = new ContentValues();

        values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT,
                mContext.getString(R.string.app_name));
        values.put(Registration.ExtensionColumns.NAME, mContext.getString(R.string.app_name));
        values.put(Registration.ExtensionColumns.EXTENSION_KEY,
        		SmartWeatherService.EXTENSION_KEY);
        values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostApp);
        values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI, iconExtension);
        values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION,
                getRequiredNotificationApiVersion());
        values.put(Registration.ExtensionColumns.PACKAGE_NAME, mContext.getPackageName());
        return values;
	}
	
	@Override
	public boolean isDisplaySizeSupported(int width, int height) {
		return ((width == SmartWeatherControl.getSupportedControlWidth(mContext) && height == SmartWeatherControl
                .getSupportedControlHeight(mContext)));
	}
	
}
