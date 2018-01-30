package com.testapp.cws.googlerouteplanner.listener;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cws on 18/1/18.
 */

public interface MapRouteData {
    public void mapRouteDataItem(ArrayList<LatLng> arrayList, ArrayList<String> step_arraylist, ArrayList<String>Html_list, ArrayList<LatLng> viapoint, ArrayList<LatLng> step_end_list, HashMap<String,String> alldata);
}
