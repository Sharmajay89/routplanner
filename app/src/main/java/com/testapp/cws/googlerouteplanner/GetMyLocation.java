package com.testapp.cws.googlerouteplanner;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by cws on 23/1/18.
 */

public class GetMyLocation extends Service implements android.location.LocationListener {
    public LocationManager locationManager;
    Location location;
    int REQUEST_LOCATION;
    public static final long MIN_DISTANCE_FOR_UPDATE = 10;
    public static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
    Context context;
GoogleApiClient mGoogleApiClient;
    public GetMyLocation(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }





    @Override
    public void onLocationChanged(Location location) {
        Log.d("GPS Enabled", ""+location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("GPS Enabled", "");
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
