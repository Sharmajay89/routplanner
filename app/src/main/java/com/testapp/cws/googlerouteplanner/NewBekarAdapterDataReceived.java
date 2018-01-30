package com.testapp.cws.googlerouteplanner;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by cws on 25/7/17.
 */

public interface NewBekarAdapterDataReceived {
    void getGoogleMapDirectionData(ArrayList<LatLng> jsonObject);

}
