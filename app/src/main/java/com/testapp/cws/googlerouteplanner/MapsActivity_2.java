package com.testapp.cws.googlerouteplanner;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.testapp.cws.googlerouteplanner.listener.DataClickListener;
import com.testapp.cws.googlerouteplanner.listener.Floating_ButtonClickListener;
import com.testapp.cws.googlerouteplanner.listener.MapRouteData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity_2 extends FragmentActivity implements OnMapReadyCallback, DataClickListener, MapRouteData,Floating_ButtonClickListener {
    String source_A, destination_B, via_1, via_2, via_3, via_4, via_5, via_6, via_7, via_8;
    static private GoogleMap gMap;
    Context context;
    static int recyclerviewvisibility_ = 1, bottomlayout_visibility, recyclerviewlayout_visibility = 1;
    static LatLng source, destination;
    static FloatingActionButton floatingActionButton;
    static ArrayList<LatLng> polilinelist;
    static ArrayList<LatLng> Legslist;
    ArrayList<String> totaldistance;
    String response;
    static TextView routeinfo;
    static RecyclerView recylerview;
    String TotalDistance, TotalTime;
    ArrayList<String> Dis_Duration;
    static FrameLayout linearLayout_map;
    ArrayList<String> Total_Duration;
    public String provider;
    RelativeLayout relativeLayout;
    JSONArray jsonArray_Legs;
    ArrayList<String> totaltime;
    GoogleApiClient googleApiClient;
    ArrayList<ArrayList<LatLng>> FinalArray = new ArrayList<ArrayList<LatLng>>();
    static HashMap<String, String> alldata;
    HashMap<String, ArrayList<LatLng>> hashMaparraylist;
    JSONObject jsonObject;
    List<Address> source_latlan, destination_latlan;
    GoogleMapRouter googleMapRouter;
    static LatLngBounds latLngBounds;
    LatLng latLng_northeast;
    LatLng latLng_southwest;
    static PolylineOptions polylineOptions;
    static FrameLayout linearlayout;
    static MarkerOptions markerOptions;
    GoogleApiClient mGoogleApiClient;

    GoogleRoutePlannerAsynctask test;
    static RecyclerView.LayoutManager layoutManager;
    String API_KEY = "AIzaSyDDbLnXZuJQyAzGposRSuKADEQV0fwe1U4";
    String pinImage = "http://www.googlemapsmarkers.com/v1/009900/";
    static BitmapDescriptor bitmapDescriptor, circle_icon;
    static BitmapDescriptor descriptorFactory;
    static BitmapDescriptor WaybitmapDescriptor;
    ArrayList<LatLng> leglatlng;
    RelativeLayout rel;
    static ArrayList<String> Html_instruction;
    static Boolean directionflag = false, recyclerview_visibility = false;
    LatLng latLng;
    static Boolean recyclerview_click_status = false;
    String[] step;
    AdapterDataClass adapterDataClass;
    static ArrayList<ArrayList<String>> steplist;
    static ArrayList<ArrayList<String>> htmllist;
    static SupportMapFragment mapFragment;
    static ArrayList<String> Waypoints_List;
    StringBuilder stringBuilder;
     static LatLngBounds.Builder builder;
    static FragmentManager fragmentManager;
    static FragmentTransaction fragmentTransaction;
    ArrayList<LatLng> arrayList;
    static int position;
    GoogleRouteDetailModelClass googleRouteDetailModelClass;
    static LatLng recyclerview_latlng = null;
    static ArrayList<LatLng> step_end_location_list;
    static RouteDetailAdapter routeDetailAdapter;
    static ArrayList<String> Step_ArrayList = new ArrayList<>();
    static ArrayList<String> Html_instruction_ = new ArrayList<>();
    static private LatLng selectedLatLng;
    boolean isGPSEnabled ;
    boolean isNetworkEnabled;
    GetMyLocation getMyLocation;
    int REQUEST_LOCATION=100;
    public FusedLocationProviderApi fusedLocationProviderApi;
    static   LocationManager locationManager;
    android.location.LocationListener locationListener;
    static Double latitude,longitude;
    static Location location;
    static LatLng location_latlng;
    static boolean floating_button_click_status=false;
    static boolean frst_tym_recyclerview_click_status=false;
    Floating_ButtonClickListener floating_buttonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("view _click_status", "" + recyclerview_click_status);
        // if(recyclerview_click_status==false)
        // {

        if (savedInstanceState == null) {

            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.activity_maps_landscape);
            } else {
                setContentView(R.layout.activity_maps);
            }

            alldata = new HashMap<>();
            hashMaparraylist = new HashMap<>();
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            context = MapsActivity_2.this;
            recylerview = (RecyclerView) findViewById(R.id.recyclerview);
            layoutManager = new LinearLayoutManager(MapsActivity_2.this);
            recylerview.setLayoutManager(layoutManager);
            polylineOptions = new PolylineOptions();
            markerOptions = new MarkerOptions();
            routeinfo = (TextView) findViewById(R.id.routeinfo);
            LatLngBounds latLngBounds;
             locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
             linearlayout = (FrameLayout) findViewById(R.id.maprecyclerviewlayout);
                linearLayout_map = (FrameLayout) findViewById(R.id.mapframelayout);
                stringBuilder = new StringBuilder();
                Step_ArrayList = new ArrayList<String>();
                Html_instruction = new ArrayList<String>();
                step_end_location_list = new ArrayList<LatLng>();
                steplist = new ArrayList<ArrayList<String>>();
                htmllist = new ArrayList<ArrayList<String>>();
                floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingbutton);
              // googleRouteDetailModelClass = new GoogleRouteDetailModelClass(this);
                Waypoints_List = new ArrayList<String>();
                //Log.e("map poliline size",""+googleRouteDetailModelClass.getPolilineroutes().size());
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.green_dot);
                WaybitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.blue_dot);
                polilinelist = new ArrayList<LatLng>();
                circle_icon=BitmapDescriptorFactory.fromResource(R.drawable.circle_icon);
                Legslist = new ArrayList<LatLng>();
                descriptorFactory = BitmapDescriptorFactory.fromResource(R.drawable.red_dot);
                totaldistance = new ArrayList<>();
                relativeLayout = (RelativeLayout) findViewById(R.layout.routedetail);
                totaltime = new ArrayList<>();
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MapsActivity_2.this);
                mapFragment.setRetainInstance(true);
               // adapterDataClass=new AdapterDataClass();
                polilinelist.addAll(adapterDataClass.arrayList);
                Step_ArrayList.addAll(adapterDataClass.step_array);
                Html_instruction.addAll(adapterDataClass.html_array);
                step_end_location_list.addAll(adapterDataClass.step_end_location_list);
                Log.w("map poliline size",""+adapterDataClass.arrayList.size());//6369
                Log.w("map poliline size",""+polilinelist.size());//6369//7389
                Log.w("map poliline size",""+Step_ArrayList.size());//33//55
                Log.w("map poliline size",""+Html_instruction.size());//33//55
                Log.w("map poliline size",""+step_end_location_list.size());//33//55
                Intent i=getIntent();
                alldata = (HashMap<String, String>) i.getSerializableExtra("alldata");
                Log.e("map hashmap size", "" + alldata.size());
                Legslist=(ArrayList<LatLng>)i.getSerializableExtra("Legslist");
                Log.e("map Legs points", "" + Legslist.size());//3
                Waypoints_List=(ArrayList<String>)i.getSerializableExtra("Waypoints_list");
                Log.e("map waypoints size", "" + Waypoints_List.size());//3
               // Log.w("map poliline size",""+googleRouteDetailModelClass.getPolilineroutes());
               // Log.w("map poliline size",""+googleRouteDetailModelClass.getPolilineroutes().size());
               // polilinelist.addAll(googleRouteDetailModelClass.getPolilineroutes());

                /*Intent i = getIntent();
                polilinelist = (ArrayList<LatLng>) i.getSerializableExtra("arraylist");
                Log.e("map poliline size", "" + polilinelist.size());//232
                //Legslist = (ArrayList<LatLng>) i.getSerializableExtra("viapoints");//source destination latlng
               // Log.e("map legsarray size", "" + Legslist.size());
                alldata = (HashMap<String, String>) i.getSerializableExtra("alldata");
                Log.e("map hashmap size", "" + alldata.size());
                Waypoints_List = (ArrayList<String>) i.getSerializableExtra("Waypoints_List");//o
               Log.e("map wyapoints size", "" + Waypoints_List.size());
               // Html_instruction=(ArrayList<String[]>)i.getSerializableExtra("htmlinstruction");
               Step_ArrayList = (ArrayList<String>) i.getSerializableExtra("stepdistance");
               Log.e("map step size", "" + Step_ArrayList.size());
               Html_instruction = (ArrayList<String>) i.getSerializableExtra("htmlinstruction");
               // Html_instruction_ = new ArrayList<>();
               // Log.e("map html size", "" + Html_instruction.size());
                //steplist = (ArrayList<ArrayList<String>>) i.getSerializableExtra("steplist");
//        Log.e("map step list size", "" + steplist.size());
               // htmllist = (ArrayList<ArrayList<String>>) i.getSerializableExtra("htmllist");
//        Log.e("map step list size", "" + htmllist.size());
                step_end_location_list = (ArrayList<LatLng>) i.getSerializableExtra("step_end_location_list");
                Log.e("map step list size", "" + step_end_location_list.size());//6369

                */

               //1
                //Log.w("map poliline size",""+Legslist.size());//0
                //Log.w("map poliline size",""+alldata.size());//11
               // Log.w("map poliline size",""+Waypoints_List.size());//0
               // Log.w("map poliline size",""+Step_ArrayList.size());//1
               // Log.w("map poliline size",""+Html_instruction.size());//
              //  Log.w("map poliline size",""+step_end_location_list.size());



                for (int t = 0; t < Html_instruction.size(); t++) {
                    //Html_instruction.add(t, String.valueOf(Html.fromHtml(Html_instruction.get(t))));
                    Log.e("html string at", "" + t + "_" + Html.fromHtml(Html_instruction.get(t)).toString().replace("\n", ""));
                    Html_instruction_.add(t, Html.fromHtml(Html_instruction.get(t)).toString().replace("\n", ""));
                    Log.e("new html string at", "" + t + "_" + Html_instruction_.get(t));
                }
                for (int t = 0; t < Step_ArrayList.size(); t++) {
                    //Html_instruction.add(t, String.valueOf(Html.fromHtml(Html_instruction.get(t))));
                    Log.w("step distance at", "" + t + "_" + Step_ArrayList.get(t));

                }
                for (int t = 0; t < step_end_location_list.size(); t++) {
                    //Html_instruction.add(t, String.valueOf(Html.fromHtml(Html_instruction.get(t))));
                    Log.e("Map end location list", "" + t + "_" + step_end_location_list.get(t));
                }
                Log.e("map String html size", "" + Html_instruction_.size());


                routeDetailAdapter = new RouteDetailAdapter(MapsActivity_2.this, Step_ArrayList, Html_instruction_, step_end_location_list,this);
                recylerview.setAdapter(routeDetailAdapter);
                routeDetailAdapter.notifyDataSetChanged();
                mapFragment.setRetainInstance(true);
                Log.e("frst tym recycler", "" + recyclerview_visibility);

                //final Bundle extras = getIntent().getExtras();
                // recyclerview_latlng =i.getParcelableExtra("latlng");
                // adapter_click_flag=extras.getBoolean("adapter_click_status");
                //adapter_click_flag=i.getBooleanExtra("adapter_click_status",false);
                // Log.e("adapter flag befr click", ""+adapter_click_flag);
                routeinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (recyclerview_visibility==false && recyclerviewvisibility_== 1 && recyclerviewlayout_visibility==1)
                        {

                            linearlayout.setVisibility(View.VISIBLE);
                            recylerview.setVisibility(View.VISIBLE);
                            recyclerview_visibility = true;
                            recyclerviewvisibility_ = 0;
                            recyclerviewlayout_visibility = 0;
                            routeinfo.setText("Show Map");
                            //directionflag=true;
                            //gMap.setPadding(50, 300, 50, 15);
                            // Log.e("open recycler status", "" + directionflag);
                            Log.e("open recycler status", "" + recyclerview_visibility);
                            Log.e("open recycler status", "" + recyclerviewvisibility_);
                            Log.e("open recycler status", "" + recyclerviewlayout_visibility);

                        } else if (recyclerview_visibility == true && recyclerviewvisibility_== 0 && recyclerviewlayout_visibility==0) {

                            linearlayout.setVisibility(View.GONE);
                            recylerview.setVisibility(View.GONE);
                            //directionflag = false;
                            recyclerview_visibility = false;
                            recyclerviewvisibility_ = 1;
                            recyclerviewlayout_visibility = 1;
                            routeinfo.setText("Route Info");
                            //Log.e("hide recycler status", "" + directionflag);
                            Log.e("hide recycler status", "" + recyclerview_visibility);
                            Log.e("hide recycler status", "" + recyclerviewvisibility_);
                            Log.e("hide recycler status", "" + recyclerviewlayout_visibility);
                        }
                    }
                });

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                        {
                            Log.e("Permission Denied","Denied");
                         ActivityCompat.requestPermissions(MapsActivity_2.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION);

                        }
                        else
                        {
                            Log.e("Permission Granted","Granted");
                            isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                            Log.e("GPS Provider",""+isGPSEnabled);
                            isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                            Log.e("NLP Provider",""+isGPSEnabled);

                            if(isGPSEnabled==true || isNetworkEnabled==true)
                            {

                               // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
                              location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                               Log.e("Location la", "" + location);
                                 latitude=location.getLatitude();
                                 longitude=location.getLongitude();
                                location_latlng=new LatLng(latitude,longitude);
                                floating_button_click_status=true;
                                frst_tym_recyclerview_click_status=false;
                                //floating_buttonClickListener.floating_ButtonClickListener(location_latlng,floating_button_click_status=true);
                              onMapReady(gMap);

                            }
//
                        }




                        }


                });



            }
            else if (savedInstanceState!= null)
            {
                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.activity_maps_landscape);
                } else {
                    setContentView(R.layout.activity_maps);
                }

                alldata = new HashMap<>();
                hashMaparraylist = new HashMap<>();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                context = MapsActivity_2.this;
                recylerview = (RecyclerView) findViewById(R.id.recyclerview);
                layoutManager = new LinearLayoutManager(MapsActivity_2.this);
                recylerview.setLayoutManager(layoutManager);
                polylineOptions = new PolylineOptions();
                markerOptions = new MarkerOptions();
                step_end_location_list = new ArrayList<LatLng>();
                routeinfo = (TextView) findViewById(R.id.routeinfo);
                LatLngBounds latLngBounds;
                linearlayout = (FrameLayout) findViewById(R.id.maprecyclerviewlayout);
                linearLayout_map = (FrameLayout) findViewById(R.id.mapframelayout);
                stringBuilder = new StringBuilder();
                Step_ArrayList = new ArrayList<String>();
                Html_instruction = new ArrayList<String>();
                steplist = new ArrayList<ArrayList<String>>();
                htmllist = new ArrayList<ArrayList<String>>();
                floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingbutton);
                googleRouteDetailModelClass = new GoogleRouteDetailModelClass(context);
                Waypoints_List = new ArrayList<String>();
                //Log.e("map poliline size",""+googleRouteDetailModelClass.getPolilineroutes().size());
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.green_dot);
                WaybitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.blue_dot);
                polilinelist = new ArrayList<LatLng>();
                circle_icon=BitmapDescriptorFactory.fromResource(R.drawable.circle_icon);
                Legslist = new ArrayList<LatLng>();
                descriptorFactory = BitmapDescriptorFactory.fromResource(R.drawable.red_dot);
                totaldistance = new ArrayList<>();
                relativeLayout = (RelativeLayout) findViewById(R.layout.routedetail);
                totaltime = new ArrayList<>();
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                //mapFragment = (SupportMapFragment) getSupportFragmentManager()
                //      .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MapsActivity_2.this);
                routeDetailAdapter = new RouteDetailAdapter(MapsActivity_2.this, Step_ArrayList, Html_instruction_, step_end_location_list,this);
                recylerview.setAdapter(routeDetailAdapter);
                routeDetailAdapter.notifyDataSetChanged();
                mapFragment.setRetainInstance(true);
                Log.e("on rotataion status", "" + recyclerview_visibility);
                Log.e("on rotation status", "" + recyclerviewvisibility_);
               //locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);



/*
                if (recyclerview_visibility==false && recyclerviewvisibility_==1 && recyclerviewlayout_visibility==1) {
                    recylerview.setVisibility(View.GONE);
                    linearlayout.setVisibility(View.GONE);
                    routeinfo.setText("Route Info");

                }
                else if (selectedLatLng!=null && recyclerview_click_status==true) {
                    recylerview.setVisibility(View.GONE);
                    linearlayout.setVisibility(View.GONE);
                    routeinfo.setText("Route Info");


                }
                else if (recyclerview_visibility== true && recyclerviewvisibility_==0 && recyclerviewlayout_visibility== 0) {
                    recylerview.setVisibility(View.VISIBLE);
                    linearlayout.setVisibility(View.VISIBLE);
                    routeinfo.setText("Show Map");


                }

*/

                routeinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (recyclerview_visibility==false && recyclerviewvisibility_== 1 && recyclerviewlayout_visibility==1)
                        {
                            recylerview.setVisibility(View.VISIBLE);
                            linearlayout.setVisibility(View.VISIBLE);
                            recyclerview_visibility = true;
                            recyclerviewvisibility_ = 0;
                            recyclerviewlayout_visibility = 0;
                            routeinfo.setText("Show Map");

                        }
                        else if (recyclerview_visibility== true && recyclerviewvisibility_== 0 && recyclerviewlayout_visibility==0)
                        {
                            recylerview.setVisibility(View.GONE);
                            linearlayout.setVisibility(View.GONE);
                            recyclerview_visibility = false;
                            recyclerviewvisibility_ = 1;
                            recyclerviewlayout_visibility = 1;
                            routeinfo.setText("Route Info");

                        }

                    }
                });
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                        {
                            Log.e("Permission Denied","Denied");
                            ActivityCompat.requestPermissions(MapsActivity_2.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION);

                        }
                        else
                        {
                            Log.e("Permission Granted","Granted");
                            isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                            Log.e("GPS Provider",""+isGPSEnabled);
                            isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                            Log.e("NLP Provider",""+isGPSEnabled);

                            if(isGPSEnabled==true || isNetworkEnabled==true)
                            {

                                // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                Log.e("Location la", "" + location);
                                latitude=location.getLatitude();
                                longitude=location.getLongitude();
                                location_latlng=new LatLng(latitude,longitude);
                                floating_button_click_status=true;
                                frst_tym_recyclerview_click_status=false;
                                //floating_buttonClickListener.floating_ButtonClickListener(location_latlng,floating_button_click_status=true);
                                onMapReady(gMap);

                            }
//
                        }




                    }


                });




            }

        //}
       // else if(recyclerview_click_status==true)
       // {

         //   routeDetailAdapter = new RouteDetailAdapter(MapsActivity_2.this, Step_ArrayList, Html_instruction_, step_end_location_list,this);
         //   recylerview.setAdapter(routeDetailAdapter);
         //   routeDetailAdapter.notifyDataSetChanged();
           // Intent i=getIntent();
            //Log.e("recyclerview  click",""+i.getSerializableExtra("latlng"));

      //  }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Step_ArrayList= (ArrayList<String>) savedInstanceState.getSerializable("Steplist");
        Html_instruction_= (ArrayList<String>) savedInstanceState.getSerializable("Htmllist");
        directionflag=savedInstanceState.getBoolean("flag");
        recyclerview_visibility=savedInstanceState.getBoolean("recyclervisibility");
        polilinelist= (ArrayList<LatLng>) savedInstanceState.getSerializable("polilinelist_");
        alldata= (HashMap<String, String>) savedInstanceState.getSerializable("alldata_");
        Legslist= (ArrayList<LatLng>) savedInstanceState.getSerializable("legslist_");
        Waypoints_List= (ArrayList<String>) savedInstanceState.getSerializable("waypointslist_");
        recyclerviewvisibility_=savedInstanceState.getInt("recyclervisibility_");
        step_end_location_list=(ArrayList<LatLng>)savedInstanceState.getSerializable("step_end_location_list");
        recyclerviewlayout_visibility=savedInstanceState.getInt("recyclerviewlayout_visibility");
        recyclerview_latlng=savedInstanceState.getParcelable("recyclerview_latlng");
        recyclerview_click_status=savedInstanceState.getBoolean("recyclerview_click_status");
        selectedLatLng=savedInstanceState.getParcelable("selectedLatLng");
        location_latlng=savedInstanceState.getParcelable("location_latlng");
        frst_tym_recyclerview_click_status=savedInstanceState.getBoolean("frst_tym_recyclerview_click_status");
        //latitude=savedInstanceState.getDouble("latitude");
        //longitude=savedInstanceState.getDouble("longitude");
        floating_button_click_status=savedInstanceState.getBoolean("floating_button_click_status");
        //location_latlng=savedInstanceState.getParcelable("location_latlng",location_latlng);
       // selectedLatLng=savedInstanceState.getParcelable("selectedLatLng");
        Log.e("on restored recycler", "" + recyclerviewvisibility_);
        Log.e("on restored recycler", "" + recyclerview_visibility);
        Log.e("on restored recycler", "" + recyclerviewlayout_visibility);
        Log.e("on restored recycler", "" + recyclerview_click_status);
        Log.w("on restored latlng", "" +selectedLatLng);
        routeDetailAdapter = new RouteDetailAdapter(MapsActivity_2.this, Step_ArrayList, Html_instruction_,step_end_location_list,this);
        recylerview.setAdapter(routeDetailAdapter);
        routeDetailAdapter.notifyDataSetChanged();


        if (recyclerview_visibility==false && recyclerviewvisibility_==1 && recyclerviewlayout_visibility==1) {
            recylerview.setVisibility(View.GONE);
            linearlayout.setVisibility(View.GONE);
            routeinfo.setText("Route Info");

        }
        else if (selectedLatLng!=null && recyclerview_click_status==true)
        {
            recylerview.setVisibility(View.GONE);
            linearlayout.setVisibility(View.GONE);
            recyclerview_visibility = false;
            recyclerviewvisibility_ = 1;
            recyclerviewlayout_visibility = 1;
            routeinfo.setText("Route Info");


        }
        else if (recyclerview_visibility == true && recyclerviewvisibility_ == 0 && recyclerviewlayout_visibility == 0) {
            recylerview.setVisibility(View.VISIBLE);
            linearlayout.setVisibility(View.VISIBLE);
            routeinfo.setText("Show Map");

        }


        routeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recyclerview_visibility == false && recyclerviewvisibility_ == 1)
                {
                    recylerview.setVisibility(View.VISIBLE);
                    linearlayout.setVisibility(View.VISIBLE);
                    recyclerview_visibility = true;
                    recyclerviewvisibility_ = 0;
                    recyclerviewlayout_visibility = 0;
                    routeinfo.setText("Show Map");

                    //directionflag=true;
                    //gMap.setPadding(50, 300, 50, 15);
                    //  Log.e("open recycler status", "" + directionflag);
                    //  Log.e("open recycler status", "" + recyclerview_visibility);

                }
                else if (recyclerview_visibility == true && recyclerviewvisibility_ == 0 && recyclerviewlayout_visibility == 0)
                {
                    recylerview.setVisibility(View.GONE);
                    linearlayout.setVisibility(View.GONE);
                    recyclerview_visibility = false;
                    recyclerviewvisibility_ = 1;
                    recyclerviewlayout_visibility = 1;
                    routeinfo.setText("Route Info");

                }

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    Log.e("Permission Denied","Denied");
                    ActivityCompat.requestPermissions(MapsActivity_2.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION);

                }
                else
                {
                    Log.e("Permission Granted","Granted");
                    isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    Log.e("GPS Provider",""+isGPSEnabled);
                    isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    Log.e("NLP Provider",""+isGPSEnabled);

                    if(isGPSEnabled==true || isNetworkEnabled==true)
                    {

                        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        Log.e("Location la", "" + location);
                        latitude=location.getLatitude();
                        longitude=location.getLongitude();
                        location_latlng=new LatLng(latitude,longitude);
                        floating_button_click_status=true;
                        frst_tym_recyclerview_click_status=false;
                        //floating_buttonClickListener.floating_ButtonClickListener(location_latlng,floating_button_click_status=true);
                        onMapReady(gMap);

                    }
//
                }




            }


        });

        mapFragment.setRetainInstance(true);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

       outState.putSerializable("Steplist",Step_ArrayList);
        outState.putSerializable("Htmllist",Html_instruction_);
        outState.putBoolean("flag",directionflag);
        outState.putBoolean("recyclervisibility",recyclerview_visibility);
        outState.putSerializable("polilinelist_",polilinelist);
        outState.putSerializable("alldata_",alldata);
        outState.putSerializable("legslist_",Legslist);
        outState.putSerializable("waypointslist_",Waypoints_List);
        outState.putInt("recyclervisibility_",recyclerviewvisibility_);
        outState.putInt("recyclerviewlayout_visibility",recyclerviewlayout_visibility);
       outState.putParcelable("recyclerview_latlng",recyclerview_latlng);
        outState.putSerializable("step_end_location_list",step_end_location_list);
        outState.putBoolean("recyclerview_click_status",recyclerview_click_status);
        outState.putBoolean("floating_button_click_status",floating_button_click_status);
        outState.putBoolean("frst_tym_recyclerview_click_status",frst_tym_recyclerview_click_status);
        outState.putParcelable("selectedLatLng",selectedLatLng);
       // outState.putDouble("latitude",latitude);
       // outState.putDouble("longitude",longitude);
        outState.putParcelable("location_latlng",location_latlng);
        //outState.putParcelable("selectedLatLng",selectedLatLng);
        //mapFragment.setRetainInstance(true);
        Log.e("on saved recycler", "" + recyclerviewvisibility_);
        Log.e("on saved recycler", "" + recyclerviewlayout_visibility);
        Log.e("on saved recycler", "" + recyclerview_visibility);
        Log.e("sa recyclerview _click_", ""+recyclerview_click_status);
        Log.w("on saved latlng", "" +selectedLatLng);

        super.onSaveInstanceState(outState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;

        if(selectedLatLng!=null && recyclerview_click_status==true)
        {
           gMap.addMarker(new MarkerOptions().position(selectedLatLng).icon(circle_icon));
           gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedLatLng,15.5f));
            Log.w("after frst map draw", "" +selectedLatLng);
            mapFragment.setRetainInstance(true);
        }


        if(location_latlng!=null && floating_button_click_status==true && frst_tym_recyclerview_click_status==false)
        {//mapFragment.setRetainInstance(true);
            gMap.addMarker(new MarkerOptions().position(location_latlng).icon(descriptorFactory));
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location_latlng,14.5f));
            Log.w("after frst map draw", "" +selectedLatLng);
            mapFragment.setRetainInstance(true);

        }
   if (gMap!=null && floating_button_click_status==false && recyclerview_click_status==false)
        {
            gMap.setIndoorEnabled(true);
            gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            gMap.setTrafficEnabled(true);
            polylineOptions.geodesic(true);
            //setPadding (int left, int top,int right, int bottom)
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                gMap.setPadding(50, 60, 150, 10);
            }
            else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                gMap.setPadding(50, 60, 150, 10);

            }
            gMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                   gMap.addMarker(new MarkerOptions().position(polilinelist.get(0)).title(alldata.get("source")).icon(bitmapDescriptor));
                   gMap.addMarker(new MarkerOptions().position(polilinelist.get(polilinelist.size()-1)).title(alldata.get("destination")).icon(descriptorFactory));
                   builder = new LatLngBounds.Builder();
                    for (int k = 0; k< polilinelist.size() - 1; k++) {
                       polylineOptions.add(polilinelist.get(k)).color(Color.BLUE);
                        builder.include(polilinelist.get(k));
                    }
                   gMap.addPolyline(polylineOptions);
                    latLngBounds = builder.build();
                  gMap.setLatLngBoundsForCameraTarget(latLngBounds);
                  gMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
                    //gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
               if (Legslist.size()!= 0 && Waypoints_List.size()!= 0)
               {
                       for (int k = 0; k < Legslist.size() - 1; k++) {
                         gMap.addMarker(new MarkerOptions().position(Legslist.get(k)).title(Waypoints_List.get(k)).icon(WaybitmapDescriptor));

                       }
                  }

                    mapFragment.setRetainInstance(true);
                }
            });





        }





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.clear();
      directionflag=false;
       recyclerview_visibility=false;
       recyclerviewlayout_visibility=1;
       selectedLatLng = null;
        recyclerviewvisibility_=1;
       recyclerview_click_status=false;
        polilinelist.clear();
        Step_ArrayList.clear();
        Html_instruction.clear();
        step_end_location_list.clear();
        location_latlng=null;
        floating_button_click_status=false;
        Log.w("onBack Pressed", "" +selectedLatLng);
        Log.w("onBack Pressed", "" + recyclerview_click_status);


    }



    @Override
    public void dataItemClickListener(LatLng latLng,Boolean click_status,Boolean floating_button_click_st) {
        recylerview.setVisibility(View.GONE);
        routeinfo.setText("Route Info");
        selectedLatLng = latLng;
        recyclerview_click_status=click_status;
        floating_button_click_status=floating_button_click_st;
        onMapReady(gMap);

    }

    @Override
    public void mapRouteDataItem(ArrayList<LatLng> arrayList, ArrayList<String> step_arraylist, ArrayList<String> Html_list, ArrayList<LatLng> viapoint, ArrayList<LatLng> step_end_list, HashMap<String, String> alldata) {

        polilinelist=arrayList;
        Step_ArrayList=step_arraylist;
        Html_instruction=Html_list;
        alldata=alldata;
        step_end_location_list=step_end_list;
        Legslist=viapoint;

    }




    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {

        Log.w("on permission", "" +requestCode);
        Log.w("on Permission", "" + permissions[0]);
        Log.w("on Permission", "" + grantResults[0]);
       // if (requestCode == REQUEST_LOCATION)
       // {
        //    if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We can now safely use the API we requested access to
       //         //Location myLocation =LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
         //   }
        //    else
         //   {
                // Permission was denied or request was cancelled
          //  }
      //  }
    }
    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MapsActivity_2.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MapsActivity_2.this.startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


    @Override
    public void floating_ButtonClickListener(LatLng latLng, Boolean flag) {
        location_latlng=latLng;
        floating_button_click_status=flag;
        onMapReady(gMap);
    }
}
