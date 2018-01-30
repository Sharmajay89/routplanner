package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String source_A,destination_B,via_1,via_2,via_3,via_4,via_5,via_6,via_7,via_8;
    private GoogleMap gMap;
   Context context;
    List<GoogleRouteDetailModelClass> list;
       LatLng source,destination;

    String summary;
    String distance,duration;
    ArrayList<LatLng> polilinelist;
    ArrayList<LatLng> Legslist;
    ArrayList<String> totaldistance;
    String response;
    PolylineOptions p;
    LatLng endlocationlatlng;
    String TotalDistance,TotalTime;
    ArrayList<String> Dis_Duration;
    ArrayList<String> Total_Duration;
    RelativeLayout relativeLayout,rel;
    JSONArray jsonArray_Legs;
    JSONObject northeast,southwest;
    JSONArray routes;
    JSONObject bounds;
    JSONObject overview_polyline;

    ArrayList<String> totaltime;
    HashMap<String,String> hashMap;
    GoogleRoutePlannerAsynctask googleRoutePlannerAsynctask;
    JSONObject jsonObject;
    List<Address> source_latlan,destination_latlan;
    GoogleMapRouter  googleMapRouter;
    LatLngBounds latLngBounds;
    PolylineOptions polylineOptions;
    MarkerOptions markerOptions;
    LatLngBounds.Builder builder;
    GoogleMapAsynctask googleMapAsynctask;
    TestGoogleMapAsynctask test;
    double northeast_lat,northeast_lng;
    double southwest_lat,southwest_lng;
    String API_KEY="AIzaSyDDbLnXZuJQyAzGposRSuKADEQV0fwe1U4";
    String pinImage =  "http://www.googlemapsmarkers.com/v1/009900/";
  BitmapDescriptor bitmapDescriptor;
    BitmapDescriptor descriptorFactory;
    BitmapDescriptor WaybitmapDescriptor;
    ArrayList<LatLng> leglatlng;
    LatLng latLng;
    ArrayList<LatLng> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
                setContentView(R.layout.activity_maps_landscape);
                hashMap = new HashMap<>();
                polylineOptions = new PolylineOptions();
                markerOptions = new MarkerOptions();
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.green_dot);
                WaybitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.blue_dot);
                polilinelist = new ArrayList<LatLng>();
                Legslist = new ArrayList<LatLng>();
                descriptorFactory = BitmapDescriptorFactory.fromResource(R.drawable.red_dot);
                totaldistance = new ArrayList<>();
                relativeLayout = (RelativeLayout) findViewById(R.layout.routedetail);

                totaltime = new ArrayList<>();
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                context = this;
                Intent intent = getIntent();
                source_A = intent.getStringExtra("source");
                destination_B = intent.getStringExtra("destination");
                via_1 = intent.getStringExtra("via_1");
                via_2 = intent.getStringExtra("via_2");
                via_3 = intent.getStringExtra("via_3");
                via_4 = intent.getStringExtra("via_4");
                via_5 = intent.getStringExtra("via_5");
                via_6 = intent.getStringExtra("via_6");
                via_7 = intent.getStringExtra("via_7");
                via_8 = intent.getStringExtra("via_8");
                if (intent.getStringExtra("via_1") == null) {
                    via_1 = null;
                    Log.e("via_ 1 null", "" + via_1);
                } else {
                    via_1 = intent.getStringExtra("via_1");
                    Log.e("via_ 1", "" + via_1);

                    hashMap.put("via_1", via_1);
                }
                if (intent.getStringExtra("via_2") == null) {
                    via_2 = null;
                    Log.e("via_ 2 null", "" + via_2);
                } else {
                    via_2 = intent.getStringExtra("via_2");
                    Log.e("via_ 2", "" + via_2);
                    hashMap.put("via_2", via_2);
                }
                if (intent.getStringExtra("via_3") == null) {
                    via_3 = null;
                    Log.e("via_ 3 null", "" + via_3);
                } else {
                    via_3 = intent.getStringExtra("via_3");
                    Log.e("via_ 3 maps", "" + via_3);
                    hashMap.put("via_3", via_3);
                }
                if (intent.getStringExtra("via_4") == null) {
                    via_4 = null;
                    Log.e("via_ 4 null", "" + via_4);
                } else {
                    via_4 = intent.getStringExtra("via_4");
                    Log.e("via_4", "" + via_4);
                    hashMap.put("via_4", via_4);
                }
                if (intent.getStringExtra("via_5") == null) {
                    via_5 = null;
                    Log.e("via_ 5 null", "" + via_5);
                } else {
                    via_5 = intent.getStringExtra("via_5");
                    Log.e("via_5", "" + via_5);
                    hashMap.put("via_5", via_5);
                }
                if (intent.getStringExtra("via_6") == null) {
                    via_6 = null;
                    Log.e("via_ 6 null", "" + via_6);
                } else {
                    via_6 = intent.getStringExtra("via_6");
                    Log.e("via_6", "" + via_6);
                    hashMap.put("via_6", via_6);
                }
                if (intent.getStringExtra("via_7") == null) {
                    via_7 = null;
                    Log.e("via_ 7 null", "" + via_7);
                } else {
                    via_7 = intent.getStringExtra("via_7");
                    Log.e("via_ 1", "" + via_7);
                    hashMap.put("via_7", via_7);
                }
                if (intent.getStringExtra("via_8") == null) {
                    via_8 = null;
                    Log.e("via_ 8 null", "" + via_8);
                } else {
                    via_8 = intent.getStringExtra("via_8");
                    Log.e("via_ 1", "" + via_8);
                    hashMap.put("via_8", via_8);
                }
                Log.e("Map source", "" + source_A);
                Log.e("Map Destinantion", "" + destination_B);
                Log.e("Map via 1", "" + via_1);
                Log.e("Map via 2", "" + via_2);
                Log.e("Map via 3", "" + via_3);
                Log.e("Map via 4", "" + via_4);
                Log.e("Map via 5", "" + via_5);
                Log.e("Map via 6", "" + via_6);
                Log.e("Map via 7", "" + via_7);
                Log.e("Map via8", "" + via_8);
                hashMap.put("source", "" + source_A);
                hashMap.put("destination", destination_B);
                hashMap.put("api_key", API_KEY);
                Log.e("Hash Map Size", "" + hashMap.size());


                googleRoutePlannerAsynctask = (GoogleRoutePlannerAsynctask) new GoogleRoutePlannerAsynctask(MapsActivity.this, hashMap, new AdapterDataReceived() {
                    @Override
                    public void getGoogleMapDirectionData(JSONObject jsonObject, List<GoogleRouteDetailModelClass> list)

                    {
                        jsonObject = jsonObject;
                        int padding = 80;
                        if (jsonObject != null) {
                            try {
                                response = jsonObject.getString("status");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (response.equals("OK")) {

                                try {
                                    routes = jsonObject.getJSONArray("routes");
                                    Log.e("routes length", "" + routes.length());

                                    if (routes.length() == 1) {
                                        Log.e("only one routes", "with all via");
                                        for (int i = 0; i <= routes.length() - 1; i++) {

                                            bounds = routes.getJSONObject(i).getJSONObject("bounds");
                                            northeast = bounds.getJSONObject("northeast");
                                            northeast_lat = northeast.getDouble("lat");
                                            northeast_lng = northeast.getDouble("lng");
                                            southwest = bounds.getJSONObject("southwest");
                                            southwest_lat = southwest.getDouble("lat");
                                            southwest_lng = southwest.getDouble("lng");
                                            Log.e("source lat", "" + northeast_lat);
                                            Log.e("source lng", "" + northeast_lng);
                                            Log.e("dest lat", "" + southwest_lat);
                                            Log.e("dest lng", "" + southwest_lng);
                                            overview_polyline = routes.getJSONObject(i).getJSONObject("overview_polyline");
                                            polilinelist = (ArrayList<LatLng>) PolyUtil.decode(overview_polyline.getString("points"));
                                            Log.e("Polyline list 1 size", "" + polilinelist.size());
                                            Log.e("source list 1 lat/lng", "" + polilinelist.get(0));
                                            Log.e("source list 1 lat/lng", "" + polilinelist.get(polilinelist.size() - 1));
                                            Log.e("Polyline list 2 size", "" + polilinelist.size());
                                            Log.e("source list 2 lat/lng", "" + polilinelist.get(0));
                                            Log.e("source list 2 lat/lng", "" + polilinelist.get(polilinelist.size() - 1));
                                            jsonArray_Legs = routes.getJSONObject(i).getJSONArray("legs");
                                            Log.e("legs array length", "" + jsonArray_Legs.length());
                                            for (int k = 0; k < polilinelist.size() - 1; k++) {
                                                builder.include(polilinelist.get(k));
                                                latLngBounds = builder.build();


                                            }
                                            gMap.setLatLngBoundsForCameraTarget(latLngBounds);
                                            gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding));
                                            for (int k = 0; k < polilinelist.size() - 1; k++) {

                                                gMap.addPolyline(new PolylineOptions().geodesic(true).add(polilinelist.get(k)).add(polilinelist.get(k + 1)).color(Color.BLUE));

                                            }
                                            gMap.addMarker(new MarkerOptions().position(polilinelist.get(0)).title(hashMap.get("source")).icon(bitmapDescriptor));
                                            gMap.addMarker(new MarkerOptions().position(polilinelist.get(polilinelist.size() - 1)).title(hashMap.get("destination")));
                                            for (int j = 0; j < jsonArray_Legs.length() - 1; j++) {
                                                JSONObject endlocation_via1 = jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                                double endlocation_lat = endlocation_via1.getDouble("lat");
                                                double endlocation_lng = endlocation_via1.getDouble("lng");
                                                endlocationlatlng = new LatLng(endlocation_lat, endlocation_lng);
                                                gMap.addMarker(new MarkerOptions().position(endlocationlatlng).icon(WaybitmapDescriptor));

                                            }

                                            Log.e("poliline list size", "" + polilinelist.size());
                                        }
                                    } else if (routes.length() != 1) {
                                        for (int i = 0; i < routes.length(); i++) {

                                            bounds = routes.getJSONObject(i).getJSONObject("bounds");
                                            northeast = bounds.getJSONObject("northeast");
                                            northeast_lat = northeast.getDouble("lat");
                                            northeast_lng = northeast.getDouble("lng");
                                            southwest = bounds.getJSONObject("southwest");
                                            southwest_lat = southwest.getDouble("lat");
                                            southwest_lng = southwest.getDouble("lng");

                                            overview_polyline = routes.getJSONObject(i).getJSONObject("overview_polyline");
                                            polilinelist = (ArrayList<LatLng>) PolyUtil.decode(overview_polyline.getString("points"));
                                            summary = routes.getJSONObject(i).getString("summary");
                                            Log.e("Polyline list size", "" + i + "_" + polilinelist.size());
                                            Log.e("summary route ", "" + i + "_" + "" + summary);

                                            jsonArray_Legs = routes.getJSONObject(i).getJSONArray("legs");
                                            Log.e("legs array length", "" + jsonArray_Legs.length());
                                            for (int k = 0; k < polilinelist.size() - 1; k++) {
                                                builder.include(polilinelist.get(k));
                                                latLngBounds = builder.build();
                                                p = new PolylineOptions();
                                                p.geodesic(true).add(polilinelist.get(k)).add(polilinelist.get(k + 1)).color(Color.BLUE).clickable(true);
                                                gMap.addPolyline(p).setTag(summary);
                                                gMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
                                                    @Override
                                                    public void onPolylineClick(Polyline polyline) {

                                                        Toast.makeText(MapsActivity.this, "" + summary + "\n" + "" + distance + "" + duration, Toast.LENGTH_SHORT).show();

                                                        Log.e("Polyline position", " -- " + gMap.addPolyline(p).getTag());
                                                    }
                                                });
                                                // gMap.addPolyline(new PolylineOptions().geodesic(true).add(polilinelist.get(k)).add(polilinelist.get(k + 1)).color(Color.BLUE));

                                            }
                                            gMap.setLatLngBoundsForCameraTarget(latLngBounds); //
                                            gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding));
                                            for (int j = 0; j < jsonArray_Legs.length(); j++) {
                                                distance = jsonArray_Legs.getJSONObject(j).getJSONObject("distance").getString("text");
                                                duration = jsonArray_Legs.getJSONObject(j).getJSONObject("duration").getString("text");
                                                Log.e("Polyline distance", "" + i + "_" + "" + distance);
                                                Log.e("Polyline duration", "" + i + "_" + "" + duration);
                                            }
                                            if (jsonArray_Legs.length() == 1) {
                                                //distance = jsonArray_Legs.getJSONObject(i).getJSONObject("distance").getString("text");
                                                //duration = jsonArray_Legs.getJSONObject(i).getJSONObject("duration").getString("text");
                                                //Log.e("Polyline distance",""+i+"_"+ ""+distance);
                                                // Log.e("Polyline duration",""+i+"_"+ ""+duration);

                                            } else {
                                                for (int j = 0; j < jsonArray_Legs.length() - 1; j++) {
                                                    JSONObject endlocation_via1 = jsonArray_Legs.getJSONObject(i).getJSONObject("end_location");
                                                    double endlocation_lat = endlocation_via1.getDouble("lat");
                                                    double endlocation_lng = endlocation_via1.getDouble("lng");
                                                    endlocationlatlng = new LatLng(endlocation_lat, endlocation_lng);

                                                }
                                            }

                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.e("poliline afetr marker", "" + polilinelist.size());
                                gMap.addMarker(new MarkerOptions().position(polilinelist.get(0)).title(hashMap.get("source")).icon(bitmapDescriptor));
                                gMap.addMarker(new MarkerOptions().position(polilinelist.get(polilinelist.size() - 1)).title(hashMap.get("destination")));

                            }
                        } else {
                            Toast.makeText(MapsActivity.this, "No Response,Limit Exceeded Please Try Again", Toast.LENGTH_LONG).show();
                        }
                    }

                }).execute();



        }
        else

        {
        setContentView(R.layout.activity_maps);
        hashMap = new HashMap<>();
        polylineOptions = new PolylineOptions();
        markerOptions = new MarkerOptions();
        bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.green_dot);
        WaybitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.blue_dot);
        polilinelist = new ArrayList<LatLng>();
        Legslist = new ArrayList<LatLng>();
        descriptorFactory = BitmapDescriptorFactory.fromResource(R.drawable.red_dot);
        totaldistance = new ArrayList<>();
        relativeLayout = (RelativeLayout) findViewById(R.layout.routedetail);

        totaltime = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = this;
        Intent intent = getIntent();
        source_A = intent.getStringExtra("source");
        destination_B = intent.getStringExtra("destination");
        via_1 = intent.getStringExtra("via_1");
        via_2 = intent.getStringExtra("via_2");
        via_3 = intent.getStringExtra("via_3");
        via_4 = intent.getStringExtra("via_4");
        via_5 = intent.getStringExtra("via_5");
        via_6 = intent.getStringExtra("via_6");
        via_7 = intent.getStringExtra("via_7");
        via_8 = intent.getStringExtra("via_8");
        if (intent.getStringExtra("via_1") == null) {
            via_1 = null;
            Log.e("via_ 1 null", "" + via_1);
        } else {
            via_1 = intent.getStringExtra("via_1");
            Log.e("via_ 1", "" + via_1);

            hashMap.put("via_1", via_1);
        }
        if (intent.getStringExtra("via_2") == null) {
            via_2 = null;
            Log.e("via_ 2 null", "" + via_2);
        } else {
            via_2 = intent.getStringExtra("via_2");
            Log.e("via_ 2", "" + via_2);
            hashMap.put("via_2", via_2);
        }
        if (intent.getStringExtra("via_3") == null) {
            via_3 = null;
            Log.e("via_ 3 null", "" + via_3);
        } else {
            via_3 = intent.getStringExtra("via_3");
            Log.e("via_ 3 maps", "" + via_3);
            hashMap.put("via_3", via_3);
        }
        if (intent.getStringExtra("via_4") == null) {
            via_4 = null;
            Log.e("via_ 4 null", "" + via_4);
        } else {
            via_4 = intent.getStringExtra("via_4");
            Log.e("via_4", "" + via_4);
            hashMap.put("via_4", via_4);
        }
        if (intent.getStringExtra("via_5") == null) {
            via_5 = null;
            Log.e("via_ 5 null", "" + via_5);
        } else {
            via_5 = intent.getStringExtra("via_5");
            Log.e("via_5", "" + via_5);
            hashMap.put("via_5", via_5);
        }
        if (intent.getStringExtra("via_6") == null) {
            via_6 = null;
            Log.e("via_ 6 null", "" + via_6);
        } else {
            via_6 = intent.getStringExtra("via_6");
            Log.e("via_6", "" + via_6);
            hashMap.put("via_6", via_6);
        }
        if (intent.getStringExtra("via_7") == null) {
            via_7 = null;
            Log.e("via_ 7 null", "" + via_7);
        } else {
            via_7 = intent.getStringExtra("via_7");
            Log.e("via_ 1", "" + via_7);
            hashMap.put("via_7", via_7);
        }
        if (intent.getStringExtra("via_8") == null) {
            via_8 = null;
            Log.e("via_ 8 null", "" + via_8);
        } else {
            via_8 = intent.getStringExtra("via_8");
            Log.e("via_ 1", "" + via_8);
            hashMap.put("via_8", via_8);
        }
        Log.e("Map source", "" + source_A);
        Log.e("Map Destinantion", "" + destination_B);
        Log.e("Map via 1", "" + via_1);
        Log.e("Map via 2", "" + via_2);
        Log.e("Map via 3", "" + via_3);
        Log.e("Map via 4", "" + via_4);
        Log.e("Map via 5", "" + via_5);
        Log.e("Map via 6", "" + via_6);
        Log.e("Map via 7", "" + via_7);
        Log.e("Map via8", "" + via_8);

        hashMap.put("source", "" + source_A);
        hashMap.put("destination", destination_B);
        hashMap.put("api_key", API_KEY);
        Log.e("Hash Map Size", "" + hashMap.size());

        /*

        test = (TestGoogleMapAsynctask) new TestGoogleMapAsynctask(getApplicationContext(), hashMap, new AdapterDataReceived() {
            @Override
            public void getGoogleMapDirectionData(String Response, ArrayList<String> distance, ArrayList<String> time, ArrayList<LatLng> legs, ArrayList<LatLng> jsonObject)

            {
                response = Response;
                Legslist = legs;
                polilinelist = jsonObject;
                totaltime = time;
                totaldistance = distance;
                int padding = 50;

                if( response.equals("OK")) {

                  gMap.addMarker(new MarkerOptions().position(polilinelist.get(0)).title("A").icon(bitmapDescriptor));
                   gMap.addMarker(new MarkerOptions().position(polilinelist.get(polilinelist.size() - 1)).title("B"));


                    for (int i = 0; i < polilinelist.size() - 1; i++) {
                        source=polilinelist.get(i);
                        destination=polilinelist.get(polilinelist.size() - 1);
                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 5.5f));
                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(source, 5.5f));
                        switch (i)
                        {
                            case 0:gMap.addPolyline(polylineOptions.geodesic(true).add(polilinelist.get(i)).add(polilinelist.get(i + 1)).color(Color.BLUE));
                                break;
                            case 1:
                                gMap.addPolyline(polylineOptions.geodesic(true).add(polilinelist.get(i)).add(polilinelist.get(i + 1)).color(Color.BLACK));
                                 break;
                        }


                        //gMap.addPolyline(new PolylineOptions().geodesic(true).add(polilinelist.get(i)).add(polilinelist.get(i + 1)).color(Color.BLUE));
                        builder.include(polilinelist.get(i));
                        LatLngBounds latLngBounds = builder.build();
                        gMap.setLatLngBoundsForCameraTarget(latLngBounds);
                        gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding));

                    }

                    for (int j = 0; j < Legslist.size() - 1; j++) {
                        gMap.addMarker(new MarkerOptions().position(Legslist.get(j)).icon(WaybitmapDescriptor));

                    }
                }
                else if (response.equals("ZERO RESULT"))
                {
                    Toast.makeText(context, "Invalid Source Or Destination,Please Try Again", Toast.LENGTH_SHORT).show();
                }

            }


        }).execute();
        */
        googleRoutePlannerAsynctask = (GoogleRoutePlannerAsynctask) new GoogleRoutePlannerAsynctask(MapsActivity.this, hashMap, new AdapterDataReceived() {
            @Override
            public void getGoogleMapDirectionData(JSONObject jsonObject, List<GoogleRouteDetailModelClass> list)

            {
                jsonObject = jsonObject;
                int padding = 80;
                if (jsonObject != null) {
                    try {
                        response = jsonObject.getString("status");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (response.equals("OK")) {

                        try {
                            routes = jsonObject.getJSONArray("routes");
                            Log.e("routes length", "" + routes.length());

                            if (routes.length() == 1) {
                                Log.e("only one routes", "with all via");
                                for (int i = 0; i <= routes.length() - 1; i++) {

                                    bounds = routes.getJSONObject(i).getJSONObject("bounds");
                                    northeast = bounds.getJSONObject("northeast");
                                    northeast_lat = northeast.getDouble("lat");
                                    northeast_lng = northeast.getDouble("lng");
                                    southwest = bounds.getJSONObject("southwest");
                                    southwest_lat = southwest.getDouble("lat");
                                    southwest_lng = southwest.getDouble("lng");
                                    Log.e("source lat", "" + northeast_lat);
                                    Log.e("source lng", "" + northeast_lng);
                                    Log.e("dest lat", "" + southwest_lat);
                                    Log.e("dest lng", "" + southwest_lng);
                                    overview_polyline = routes.getJSONObject(i).getJSONObject("overview_polyline");
                                    polilinelist = (ArrayList<LatLng>) PolyUtil.decode(overview_polyline.getString("points"));
                                    Log.e("Polyline list 1 size", "" + polilinelist.size());
                                    Log.e("source list 1 lat/lng", "" + polilinelist.get(0));
                                    Log.e("source list 1 lat/lng", "" + polilinelist.get(polilinelist.size() - 1));
                                    Log.e("Polyline list 2 size", "" + polilinelist.size());
                                    Log.e("source list 2 lat/lng", "" + polilinelist.get(0));
                                    Log.e("source list 2 lat/lng", "" + polilinelist.get(polilinelist.size() - 1));
                                    jsonArray_Legs = routes.getJSONObject(i).getJSONArray("legs");
                                    Log.e("legs array length", "" + jsonArray_Legs.length());
                                    for (int k = 0; k < polilinelist.size() - 1; k++) {
                                        builder.include(polilinelist.get(k));
                                        latLngBounds = builder.build();


                                    }
                                    gMap.setLatLngBoundsForCameraTarget(latLngBounds);
                                    gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding));
                                    for (int k = 0; k < polilinelist.size() - 1; k++) {

                                        gMap.addPolyline(new PolylineOptions().geodesic(true).add(polilinelist.get(k)).add(polilinelist.get(k + 1)).color(Color.BLUE));

                                    }
                                    gMap.addMarker(new MarkerOptions().position(polilinelist.get(0)).title(hashMap.get("source")).icon(bitmapDescriptor));
                                    gMap.addMarker(new MarkerOptions().position(polilinelist.get(polilinelist.size() - 1)).title(hashMap.get("destination")));
                                    for (int j = 0; j < jsonArray_Legs.length() - 1; j++) {
                                        JSONObject endlocation_via1 = jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                        double endlocation_lat = endlocation_via1.getDouble("lat");
                                        double endlocation_lng = endlocation_via1.getDouble("lng");
                                        endlocationlatlng = new LatLng(endlocation_lat, endlocation_lng);
                                        gMap.addMarker(new MarkerOptions().position(endlocationlatlng).icon(WaybitmapDescriptor));

                                    }

                                    Log.e("poliline list size", "" + polilinelist.size());
                                }
                            } else if (routes.length() != 1) {
                                for (int i = 0; i < routes.length(); i++) {

                                    bounds = routes.getJSONObject(i).getJSONObject("bounds");
                                    northeast = bounds.getJSONObject("northeast");
                                    northeast_lat = northeast.getDouble("lat");
                                    northeast_lng = northeast.getDouble("lng");
                                    southwest = bounds.getJSONObject("southwest");
                                    southwest_lat = southwest.getDouble("lat");
                                    southwest_lng = southwest.getDouble("lng");

                                    overview_polyline = routes.getJSONObject(i).getJSONObject("overview_polyline");
                                    polilinelist = (ArrayList<LatLng>) PolyUtil.decode(overview_polyline.getString("points"));
                                    summary = routes.getJSONObject(i).getString("summary");
                                    Log.e("Polyline list size", "" + i + "_" + polilinelist.size());
                                    Log.e("summary route ", "" + i + "_" + "" + summary);

                                    jsonArray_Legs = routes.getJSONObject(i).getJSONArray("legs");
                                    Log.e("legs array length", "" + jsonArray_Legs.length());
                                    for (int k = 0; k < polilinelist.size() - 1; k++) {
                                        builder.include(polilinelist.get(k));
                                        latLngBounds = builder.build();
                                        p = new PolylineOptions();
                                        p.geodesic(true).add(polilinelist.get(k)).add(polilinelist.get(k + 1)).color(Color.BLUE).clickable(true);
                                        gMap.addPolyline(p).setTag(summary);
                                        gMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
                                            @Override
                                            public void onPolylineClick(Polyline polyline) {

                                                Toast.makeText(MapsActivity.this, "" + summary + "\n" + "" + distance + "" + duration, Toast.LENGTH_SHORT).show();

                                                Log.e("Polyline position", " -- " + gMap.addPolyline(p).getTag());
                                            }
                                        });
                                        // gMap.addPolyline(new PolylineOptions().geodesic(true).add(polilinelist.get(k)).add(polilinelist.get(k + 1)).color(Color.BLUE));

                                    }
                                    gMap.setLatLngBoundsForCameraTarget(latLngBounds); //
                                    gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding));
                                    for (int j = 0; j < jsonArray_Legs.length(); j++) {
                                        distance = jsonArray_Legs.getJSONObject(j).getJSONObject("distance").getString("text");
                                        duration = jsonArray_Legs.getJSONObject(j).getJSONObject("duration").getString("text");
                                        Log.e("Polyline distance", "" + i + "_" + "" + distance);
                                        Log.e("Polyline duration", "" + i + "_" + "" + duration);
                                    }
                                    if (jsonArray_Legs.length() == 1) {
                                        //distance = jsonArray_Legs.getJSONObject(i).getJSONObject("distance").getString("text");
                                        //duration = jsonArray_Legs.getJSONObject(i).getJSONObject("duration").getString("text");
                                        //Log.e("Polyline distance",""+i+"_"+ ""+distance);
                                        // Log.e("Polyline duration",""+i+"_"+ ""+duration);

                                    } else {
                                        for (int j = 0; j < jsonArray_Legs.length() - 1; j++) {
                                            JSONObject endlocation_via1 = jsonArray_Legs.getJSONObject(i).getJSONObject("end_location");
                                            double endlocation_lat = endlocation_via1.getDouble("lat");
                                            double endlocation_lng = endlocation_via1.getDouble("lng");
                                            endlocationlatlng = new LatLng(endlocation_lat, endlocation_lng);

                                        }
                                    }

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("poliline afetr marker", "" + polilinelist.size());
                        gMap.addMarker(new MarkerOptions().position(polilinelist.get(0)).title(hashMap.get("source")).icon(bitmapDescriptor));
                        gMap.addMarker(new MarkerOptions().position(polilinelist.get(polilinelist.size() - 1)).title(hashMap.get("destination")));

                    }
                } else {
                    Toast.makeText(MapsActivity.this, "No Response,Limit Exceeded Please Try Again", Toast.LENGTH_LONG).show();
                }
            }

        }).execute();


    }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.green_dot);
        gMap.setIndoorEnabled(true);
        builder = new LatLngBounds.Builder();
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.setTrafficEnabled(true);







    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }


}
