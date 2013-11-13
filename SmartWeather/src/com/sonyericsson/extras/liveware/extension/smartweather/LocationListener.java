package com.sonyericsson.extras.liveware.extension.smartweather;

import android.location.Location;
import android.os.Bundle;

public class LocationListener implements android.location.LocationListener  {
	public static double latitude;  
    public static double longitude; 
	
	@Override
	public void onLocationChanged(Location location) {
        latitude=location.getLatitude();  
        longitude=location.getLongitude();  	
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
