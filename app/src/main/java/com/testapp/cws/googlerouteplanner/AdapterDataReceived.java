package com.testapp.cws.googlerouteplanner;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by cws on 25/7/17.
 */

public interface AdapterDataReceived {

    //public void text();
    //nfjgju
    //void getGoogleMapDirectionData(String JsonResponse, ArrayList<String> arrayList, ArrayList<String> arrayList1, ArrayList<LatLng> Legs, ArrayList<LatLng> jsonObject);

    //void getGoogleMapDirectionData(JSONObject jsonObject);
    void getGoogleMapDirectionData(JSONObject jsonObject, List<GoogleRouteDetailModelClass> list);

}
