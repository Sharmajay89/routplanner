package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cws on 6/12/17.
 */

public class OptGoogleMapAsynctask extends AsyncTask<Void,Void,JSONObject> {
    Context context;
    String newUrl;
    JSONArray jsonArray_Legs;
    StringBuilder sbb;
    ArrayList<LatLng> leglatlng;
    ArrayList<LatLng> arrayList;
    ArrayList<LatLng> totallegs;
    String charset = "UTF-8";
    URL url_1;
    AdapterDataReceived adapterDataReceived;
    String origin,destination;
    JSONObject final_jsonObject;
    String jsonObject_polyline_;
    StringBuilder sb;
    JSONObject jsonObject_polyline;
    StringBuilder result;
    String url="https://maps.googleapis.com/maps/api/directions/json";
    BufferedInputStream bufferedInputStream;
    LatLng latLng;
    HashMap<String, String> hashMap;

    public OptGoogleMapAsynctask(Context context, HashMap<String,String> hashMap,AdapterDataReceived adapterDataReceived) {
        this.context=context;
        this.hashMap=hashMap;
        this.adapterDataReceived=adapterDataReceived;
        this.origin=hashMap.get("source");
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        Log.e("async origin", "" + hashMap.get("source"));
        Log.e("async destination", "" + hashMap.get("destination"));
        Log.e("async key", "" + hashMap.get("api_key"));
        Log.e("async waypoint", "" + hashMap.get("via_1"));
        Log.e("async waypoint", "" + hashMap.get("via_2"));
        Log.e("async waypoint", "" + hashMap.get("via_3"));
        Log.e("async waypoint", "" + hashMap.get("via_4"));
        Log.e("async waypoint", "" + hashMap.get("via_5"));
        Log.e("async waypoint", "" + hashMap.get("via_6"));
        Log.e("async waypoint", "" + hashMap.get("via_7"));
        Log.e("async waypoint", "" + hashMap.get("via_8"));
        return null;
    }
}
