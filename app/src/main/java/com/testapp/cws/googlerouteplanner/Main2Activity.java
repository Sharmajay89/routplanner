package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    String newUrl;
    LatLng endlocationlatlng;
    int tot_time = 0;
    int tim1 = 0;
    TextView title;
    int a_time;
    ArrayList<ArrayList<String>> stepsarray;
    List<GoogleRouteDetailModelClass> list;
    StringBuilder sbb;
    String TotalDistance_final;
    int totaltime_1 = 0;
  static   ArrayList<String[]> routes_summary;
     static ArrayList<String> Waypoints_list;
     static  RecyclerView.LayoutManager layoutManager;
    static GoogleRouteMapAdapter googlemapadpater;
    BitmapDescriptor bitmapDescriptor;
    double totaldistance = 0;
    ArrayList<String> routes_legs_summary;
    BitmapDescriptor descriptorFactory;
    BitmapDescriptor WaybitmapDescriptor;
    ArrayList<LatLng> leglatlng;
    ArrayList<LatLng> arrayList;
    double northeast_lat, northeast_lng;
    double southwest_lat, southwest_lng;
   static JSONArray jsonArray_Legs, Step_Leg_Array;
    JSONObject northeast, southwest;
static Context context_r;
    static HashMap<String,String> hashmap_r;
    JSONObject bounds;
    JSONObject overview_polyline,Step_polyline;
    ArrayList<LatLng> totallegs;
    static ArrayList<LatLng> steps_poliline_list,Steps_poliline_list_final;
    static ArrayList<ArrayList<LatLng>> Step_Polilinelist_;
    static ArrayList<ArrayList<ArrayList<LatLng>>> final_steps_poliline_list;
    JSONObject jsonObject;
    JSONArray routes;
    static LatLng steps_end_location_points;
    static String[] summary_;
    static String[] distance_;
    String[] step_html;
    static String[] duration_;
    String[] step_distance_;
    String charset = "UTF-8";
    ArrayList<ArrayList<LatLng>> allroutes;
    URL url_1;
    static ArrayList<ArrayList<LatLng>> Polilinelist_;
    String finalorigin, finaldestination, finalvia1, finalvia2, finalvia3, finalvia4, finalvia5, finalvia6, finalvia7, finalvia8;
    String TotalDistance, TotalTime;
    ArrayList<String> Dis_Duration;
    ArrayList<String> Total_Duration;
    AdapterDataReceived adapterDataReceived;
    String origin, destination;
   static LatLngBounds latLngBounds;
    PolylineOptions polylineOptions;
    MarkerOptions markerOptions;
    JSONObject final_jsonObject;
    PolylineOptions p;
    int newtotaltime = 0;
    String jsonObject_polyline_;
    StringBuilder M_origin;
     static ArrayList<LatLng> endLatlngarray,legs_endLatlngarray;
    StringBuilder MainUrl;
    static LatLng latLng_northeast;
    static LatLng latLng_southwest;
    String jsonresult;
    StringBuilder sb;
    TestGoogleMapAsynctask test;
    StringBuilder steppoliline_points;
  static ArrayList<String> Step_distance_list, Html_instruction_list;
     static ArrayList<ArrayList<String>> Step_ArrayList, Html_instruction;
    static ArrayList<LatLng> polilinelist;
    //String source,destinations;
    ArrayList<ArrayList<LatLng>> listofpolyline;
    JSONObject jsonObject_polyline;
    StringBuilder source, destinations, via1, via2, via3, via4, via5, via6, via7, via8;
    String url = "https://maps.googleapis.com/maps/api/directions/json";
    BufferedInputStream bufferedInputStream;
    LatLng latLng;
    int totaltime;
     static LatLngBounds.Builder builder;
    int d = 0;
    int mint1;
   static ArrayList<JSONObject> Routes_Json;
    static String[] Waypoints;
    static RecyclerView recyclerView;
   static HashMap<String, String> hashMap;
   static HashMap<String, String> Map_hashMap;
    String[] distance, duration, summary;
    ArrayList<ArrayList<LatLng>> polilineresponse;
    Context context;
    static ArrayList<ArrayList<LatLng>> steps_end_location;
   static ArrayList<LatLng> steps_end_location_list;
    String API_KEY = "AIzaSyDDbLnXZuJQyAzGposRSuKADEQV0fwe1U4";
    GoogleRouteDetailModelClass googleRouteDetailModelClass;
    String source_A, destination_B, via_1, via_2, via_3, via_4, via_5, via_6, via_7, via_8;
    GoogleRoutePlannerAsynctask googleRoutePlannerAsynctask;
    Integer[] totatltime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState==null)
        {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main2_landscape);
        } else {
            setContentView(R.layout.activity_main2);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        hashMap = new HashMap<>();
        Map_hashMap = new HashMap<>();
        context = this;
            context_r=this;
            title=(TextView)findViewById(R.id.tittlemain);
        Step_distance_list = new ArrayList<String>();
        Step_ArrayList = new ArrayList<ArrayList<String>>();
        steps_end_location=new ArrayList<ArrayList<LatLng>>();
        Waypoints_list = new ArrayList<>();
        Html_instruction_list = new ArrayList<String>();
        Html_instruction = new ArrayList<ArrayList<String>>();
        layoutManager = new LinearLayoutManager(Main2Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        googleRouteDetailModelClass = new GoogleRouteDetailModelClass(Main2Activity.this);
        //builder = new LatLngBounds.Builder();
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

        // if(source_A!=null && destination_B!=null && via_1.equals("") && via_2.equals("")&& via_3.equals("")&& via_4.equals("")&& via_5.equals("")&& via_6.equals("")&& via_7.equals("")&& via_8.equals("") )
        // {
        googleRoutePlannerAsynctask = (GoogleRoutePlannerAsynctask) new GoogleRoutePlannerAsynctask(Main2Activity.this, hashMap, new AdapterDataReceived() {
            @Override
            public void getGoogleMapDirectionData(JSONObject jsonObject, List<GoogleRouteDetailModelClass> list) {

                Log.e("Jsonobject", "" + jsonObject);
                try {
                    if (!(jsonObject.getString("status").equals("OVER_QUERY_LIMIT") || (jsonObject.getString("status").equals("ZERO_RESULTS")))) {
                        try {
                            if (jsonObject != null && (!jsonObject.getString("status").equals("OVER_QUERY_LIMIT") || !jsonObject.getString("status").equals("ZERO_RESULTS"))) {
                                try {
                                    jsonresult = jsonObject.getString("status");
                                    if (jsonresult.equals("OK"))

                                    {
                                        jsonObject = jsonObject;
                                        {
                                            //int padding = 80;
                                            if (jsonObject != null) {
                                                try {
                                                    routes = jsonObject.getJSONArray("routes");
                                                    Log.e("routes length", "" + routes.length());
                                                    if (routes.length()==1)
                                                    {
                                                        Log.e("only one routes", "with all via");
                                                        int routelength = routes.length();
                                                        summary_ = new String[routelength];
                                                        Polilinelist_ = new ArrayList<ArrayList<LatLng>>();
                                                       // ArrayList<ArrayList<LatLng>> final_poliline_list=new ArrayList<ArrayList<LatLng>>();
                                                        ArrayList<ArrayList<LatLng>> final_end_location_list=new ArrayList<ArrayList<LatLng>>();
                                                        ArrayList<LatLng> all_steps_route_poliline=new ArrayList<>();
                                                        ArrayList<LatLng> all_steps_end_location_list=new ArrayList<>();
                                                        Step_Polilinelist_=new ArrayList<>(routelength);
                                                        //ArrayList<ArrayList<String>>Step_ArrayList=new ArrayList<>();
                                                        //ArrayList<ArrayList<String> >Html_ArrayList=new ArrayList<>();

                                                        for (int i = 0; i <= routes.length() - 1; i++)
                                                        {
                                                            bounds = routes.getJSONObject(i).getJSONObject("bounds");
                                                            Double jsonObjectbounds_northeast_lat = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("northeast").getDouble("lat");
                                                            Double jsonObjectbounds_northeast_lng = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("northeast").getDouble("lng");
                                                            Double jsonObjectbounds_southwest_lat = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("southwest").getDouble("lat");
                                                            Double jsonObjectbounds_southwest_lng = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("southwest").getDouble("lng");
                                                            latLng_northeast = new LatLng(jsonObjectbounds_northeast_lat, jsonObjectbounds_northeast_lng);
                                                            latLng_southwest = new LatLng(jsonObjectbounds_southwest_lat, jsonObjectbounds_southwest_lng);
                                                            Log.e("northeast latlng", "" + latLng_northeast);
                                                            Log.e("southwest latlng", "" + latLng_southwest);
                                                           // googleRouteDetailModelClass.setSouthwest(latLng_southwest);
                                                            //googleRouteDetailModelClass.setNortheast(latLng_northeast);
                                                            builder = new LatLngBounds.Builder();
                                                            builder.include(latLng_northeast).include(latLng_southwest);
                                                            latLngBounds = builder.build();
                                                            Log.e("Latlng Bounds ", "" + latLngBounds);
                                                            overview_polyline = routes.getJSONObject(i).getJSONObject("overview_polyline");
                                                            polilinelist = (ArrayList<LatLng>) PolyUtil.decode(overview_polyline.getString("points"));
                                                            summary_[i] = routes.getJSONObject(i).getString("summary");
                                                            //googleRouteDetailModelClass.setLatLngBounds(latLngBounds);
                                                            Log.e("Polyline list size", "" + i + "_" + polilinelist.size());
                                                            Polilinelist_.add(i, polilinelist);
                                                            Log.e("Polyline summary", "" + i + "_" + "" + summary_[i]);
                                                            jsonArray_Legs = routes.getJSONObject(i).getJSONArray("legs");
                                                            Log.e("legs array length", "" + jsonArray_Legs.length());//3
                                                            distance_ = new String[jsonArray_Legs.length()];
                                                            duration_ = new String[jsonArray_Legs.length()];
                                                            Waypoints = new String[jsonArray_Legs.length()];//way points array
                                                            legs_endLatlngarray=new ArrayList<LatLng>();//  way points end lat lng list
                                                            endLatlngarray = new ArrayList<LatLng>();//
                                                            newtotaltime = 0;
                                                            totatltime = new Integer[jsonArray_Legs.length()];
                                                           // ArrayList<String>Step_ArrayList = new ArrayList<String>(jsonArray_Legs.length());
                                                          //  Html_instruction = new ArrayList<ArrayList<String>>(jsonArray_Legs.length());
                                                        //    steps_end_location = new ArrayList<ArrayList<LatLng>>(jsonArray_Legs.length());

                                                            Waypoints_list = new ArrayList<String>();
                                                            //Step_distance_list=new ArrayList<String>();
                                                            // Html_instruction_list=new ArrayList<String>();
                                                            Step_distance_list = new ArrayList<String>();
                                                            Html_instruction_list = new ArrayList<String>();
                                                            Step_distance_list = new ArrayList<String>();
                                                            Steps_poliline_list_final=new ArrayList<LatLng>();
                                                            steps_end_location_list=new ArrayList<LatLng>();

                                                            for (int j = 0; j <= jsonArray_Legs.length() - 1; j++)
                                                            {

                                                                distance_[j] = jsonArray_Legs.getJSONObject(j).getJSONObject("distance").getString("text");
                                                                duration_[j] = jsonArray_Legs.getJSONObject(j).getJSONObject("duration").getString("text");
                                                                Waypoints[j] = jsonArray_Legs.getJSONObject(j).getString("end_address");
                                                                Log.e("distance ", "" + j + "_" + "" + distance_[j]);
                                                                Log.e("duration", "" + j + "_" + "" + duration_[j]);
                                                                Log.e("waypoints ", "" + j + "_" + "" + Waypoints[j]);
                                                                Double end_location_lat = jsonArray_Legs.getJSONObject(j).getJSONObject("end_location").getDouble("lat");
                                                                Double end_location_lng = jsonArray_Legs.getJSONObject(j).getJSONObject("end_location").getDouble("lng");
                                                                LatLng end_latlng = new LatLng(end_location_lat, end_location_lng);
                                                                endLatlngarray.add(j, end_latlng);
                                                                Waypoints_list.add(j, Waypoints[j]);
                                                                //Step_ArrayList.add(j, Step_distance_list);
                                                                // Log.e("Step Array List Size", "" + Step_ArrayList.size());
                                                                // Html_instruction.add(j, Html_instruction_list);
                                                                // Log.e("Html Distance List Size", "" + Html_instruction.size());
                                                                // Log.e("Step final List Size", "" + Step_ArrayList);//3
                                                                //  Log.e("Html final List Size", "" + Html_instruction.size());
                                                                Log.e("End Latlng Array", "" + j + "_" + "" + endLatlngarray.get(j));//27.1766744 lng : 78.0080749
                                                                Log.e("Waypoints Array", "" + j + "_" + "" + Waypoints_list.get(j));//agra/mathura
                                                                Step_Leg_Array = jsonArray_Legs.getJSONObject(j).getJSONArray("steps");
                                                                Log.e("Steps Array Length", "" + j + "_" + "" + Step_Leg_Array.length());//18//11

                                                                for (int s = 0; s <=Step_Leg_Array.length() - 1; s++)
                                                                {
                                                                    String ss = Step_Leg_Array.getJSONObject(s).getJSONObject("distance").getString("text");
                                                                    Step_distance_list.add(ss);
                                                                    Log.e("Steps Distance", "" + s + "_" + "" + Step_distance_list.get(s));
                                                                    Log.e("Steps Distance size", "" + s + "_" + "" + Step_distance_list.size());
                                                                    String html = Step_Leg_Array.getJSONObject(s).getString("html_instructions");
                                                                    // Log.e("Steps Hmtl", "" + s + "_" + "" +step_html[s]);
                                                                    Html_instruction_list.add(html);
                                                                    Log.e("Html Distance", "" + s + "_" + "" + Html_instruction_list.get(s));
                                                                    Step_polyline=Step_Leg_Array.getJSONObject(s).getJSONObject("polyline");
                                                                    // Log.w("step poliline", "" + s + "_" + "" +Step_polyline);
                                                                    steps_poliline_list=(ArrayList<LatLng>) PolyUtil.decode(Step_polyline.getString("points"));
                                                               Log.w("step poliline string", "" + s + "_" + "" +steps_poliline_list);//38
                                                                    Log.w("step poliline string", "" + s + "_" + "" +steps_poliline_list.size());//38
                                                                    Steps_poliline_list_final.addAll(steps_poliline_list);//3850
                                                                    Log.w("step added poliline", "" + s + "_" + Steps_poliline_list_final.size());//38/88/...3850
                                                                    Double steps_endlocation_lat=Step_Leg_Array.getJSONObject(s).getJSONObject("start_location").getDouble("lat");
                                                                    Double steps_endlocation_lng=Step_Leg_Array.getJSONObject(s).getJSONObject("start_location").getDouble("lng");
                                                                    // Log.e("End location", "" + s + "_" + "" + steps_endlocation_lat);
                                                                    //Log.e("end location", "" + s + "_" + "" + steps_endlocation_lng);
                                                                    steps_end_location_points=new LatLng(steps_endlocation_lat,steps_endlocation_lng);
                                                                    steps_end_location_list.add(steps_end_location_points);

                                                                }

                                                               // all_steps_route_poliline.addAll(i,Steps_poliline_list_final);
                                                                //all_steps_end_location_list.addAll(i,steps_end_location_list);
                                                               //// Step_ArrayList.addAll(i,Step_distance_list);
                                                                //Html_ArrayList.addAll(i,Html_instruction_list);

                                                               // Log.e("legs poli list size", "" + i + "_" + all_steps_route_poliline);
                                                               // Log.e("legs poli list size", "" + i + "_" + all_steps_route_poliline.size());//final 8045

                                                               // Log.e("Step distance list", "" + i + "_" +  Step_ArrayList);
                                                               // Log.e("Step distance list", "" +i + "_" +  Step_ArrayList.size());//18//11//25


                                                                String d = distance_[j];
                                                                String[] di = d.split(" ");
                                                                Log.e("string  at ", "" + j + "_" + di[i]);
                                                                Log.e("string  length ", "" + j + "" + di.length);
                                                                NumberFormat nf = NumberFormat.getInstance();

                                                                double di1 = 0;
                                                                try {
                                                                    di1 = nf.parse(di[i]).doubleValue();
                                                                } catch (ParseException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                Log.e("string di", "" + di1);
                                                                totaldistance = totaldistance + di1;
                                                                Log.e("int got from string", "" + totaldistance);
                                                                StringBuilder ToTalDistance = new StringBuilder(String.valueOf(totaldistance));
                                                                ToTalDistance.append(" ").append("KM");
                                                                Log.e("final distance string", "" + ToTalDistance);
                                                                String TotalDistance_final = ToTalDistance.toString();
                                                                distance_[i] = TotalDistance_final;
                                                                // distance calculated
                                                                String time = duration_[j];
                                                                String[] time_ = time.split(" ");
                                                                Log.e(" time array length", "" + j + "" + time_.length);
                                                                //totatltime = new Integer[jsonArray_Legs.length()];

                                                                if (time_.length >= 2) {
                                                                    int timearray = 0;
                                                                    Log.e("tim1__ ", "" + time_);
                                                                    //tim1 = Integer.parseInt(time_[0]);
                                                                    // Log.e("converted tim1__ ", "" + tim1);
                                                                    if (time_.length == 2) {
                                                                        Log.e("time array length", "" + time_.length);
                                                                        if ((time_[1].equals("day") || time_[1].equals("days"))) {
                                                                            tim1 = tim1 * 24 * 60;
                                                                            Log.e("converted tim1 to mint ", "" + tim1);
                                                                        } else if ((time_[1].equals("hours") || time_[1].equals("hour"))) {
                                                                            tim1 = tim1 * 60;
                                                                            Log.e("converted tim1 to mint ", "" + tim1);
                                                                        } else if ((time_[1].equals("mins") || time_[1].equals("min"))) {
                                                                            tim1 = Integer.parseInt(time_[timearray]);
                                                                            Log.e("converted tim1 to mint ", "" + tim1);
                                                                        }

                                                                    } else {
                                                                        if ((time_[1].equals("hours") || time_[1].equals("hour")) && (time_[3].equals("mins") || time_[3].equals("min"))) {
                                                                            tim1 = (Integer.parseInt(time_[timearray]) * 60) + Integer.parseInt(time_[timearray + 2]);
                                                                            Log.e("converted tim1_", "" + tim1);
                                                                        } else if ((time_[1].equals("day") || time_[1].equals("days")) && (time_[3].equals("hours") || time_[3].equals("hour"))) {
                                                                            tim1 = (Integer.parseInt(time_[timearray]) * 24 * 60 + Integer.parseInt(time_[timearray + 2]) * 60);
                                                                            Log.e("converted tim1 to mint ", "" + tim1);
                                                                        }

                                                                    }


                                                                }
                                                                totaltime = tim1;

                                                                //int totaltime = tim1 + mint1;
                                                                newtotaltime = newtotaltime + totaltime;
                                                                //a_time=a_time+totaltime;
                                                                Log.e("Total Time", "" + j + "" + newtotaltime);
                                                                // Log.e("Total new final Time", ""+a_time);
                                                                totatltime[j] = totaltime;
                                                                int final_hourtime = newtotaltime / 60;
                                                                int final_minttime = newtotaltime % 60;
                                                                Log.e("final total hour Time", "" + final_hourtime);
                                                                Log.e("Total mint Time", "" + final_minttime);
                                                                StringBuilder final_time = new StringBuilder();
                                                                final_time.append(final_hourtime).append(" ").append("h").append(" ").append(final_minttime).append(" ").append("Min");
                                                                Log.e("exact final time", "" + final_time);
                                                                duration_[i] = String.valueOf(final_time);

                                                                //googleRouteDetailModelClass.setDuration(duration_);
                                                                //googleRouteDetailModelClass.setDistance(distance_);

                                                            }
                                                            Log.e("step array list size", "" + i + "_" + Step_distance_list);
                                                            Log.e("html array list size", "" + i + "_" + Html_instruction_list);
                                                            Log.e("step array list size", "" + i + "_" + Step_distance_list.size());//18+11+25=55
                                                            Log.e("html array list size", "" + i + "_" + Html_instruction_list.size());//18+11+25=55
                                                            Log.e("Step End location", "" + i + "_" +  steps_end_location_list.size());//18//11//25=55
                                                            Log.w("step added poliline", "" + i + "_" + Steps_poliline_list_final.size());//38/88/...3850
                                                            Log.e("Way Points Array Size", "" + Waypoints_list);//3
                                                            Log.e("Way Points Array Size", "" + distance_[i]);//
                                                            Log.e("Way Points Array Size", "" + duration_[i]);//
                                                            Log.e("Way Points Array Size", "" + Waypoints_list.get(0));//
                                                           // Log.e("Way Points Array Size", "" + Waypoints_list.get(1));//
                                                            Step_Polilinelist_.add(i,Steps_poliline_list_final);
                                                            Step_ArrayList.add(i,Step_distance_list);
                                                            Html_instruction.add(i,Html_instruction_list);
                                                            steps_end_location.add(i,steps_end_location_list);
                                                            Log.e("new array size", "" + i + "_" + Step_Polilinelist_.size());//1
                                                            Log.e("new array list size", "" + i + "_" + Step_Polilinelist_.get(0).size());//8405
                                                            Log.e("new array size", "" + i + "_" + Step_ArrayList.size());//1
                                                            Log.e("new array list size", "" + i + "_" + Step_ArrayList.get(0).size());//55
                                                            Log.e("new array size", "" + i + "_" + Html_instruction.size());//1
                                                            Log.e("new array list size", "" + i + "_" + Html_instruction.get(0).size());//55
                                                            Log.e("new array size", "" + i + "_" + steps_end_location.size());//1
                                                            Log.e("new array list size", "" + i + "_" + steps_end_location.get(0).size());//55
                                                            Log.e("new array list size", "" + i + "_" + endLatlngarray.size());//3


                                                            // Log.e("End Latlng Array Size", "" + endLatlngarray.size());
                                                            //Log.e("Way Points Array Size", "" + Waypoints_list.size());//3
                                                            //Log.e("final step List Size", "" + Step_ArrayList.size());
                                                            //Log.e("final html List Size", "" + Html_instruction.size());
                                                          //  Log.e("poliline list size", "" + Polilinelist_.size());//231
                                                           // Log.e("final route poliline", "" +all_steps_route_poliline.size());//8045
                                                           // Log.e("End Latlng Array Size", "" + endLatlngarray);
                                                           // Log.e("Way Points Array Size", "" + Waypoints_list);
                                                          //  Log.e("final step List Size", "" + Step_ArrayList);
                                                          // Log.e("final html List Size", "" + Html_instruction);
                                                          //  Log.e("final route poliline", "" +all_steps_end_location_list.size());//18+11+25
                                                          //  Log.e("final route poliline", "" +all_steps_end_location_list);//18+11+25
                                                          //  Step_Polilinelist_.add(i,all_steps_route_poliline);
                                                           // Log.e("again final poliline", "" + Step_Polilinelist_.size());//8045

                                                        }


                                                      googlemapadpater = new GoogleRouteMapAdapter(context, hashMap, Waypoints_list, distance_, duration_, summary_, endLatlngarray, Step_Polilinelist_, Step_ArrayList, Html_instruction,steps_end_location);
                                                        recyclerView.setAdapter(googlemapadpater);
                                                       googlemapadpater.notifyDataSetChanged();


                                                    }
                                                    else if (routes.length()!= 1)
                                                    {

                                                        Log.e("all possible routes", "Without Via points");
                                                        Log.e("route without via", "only route is there");
                                                        Log.e("routes length", "" + routes.length());
                                                            int routelength = routes.length();
                                                            Routes_Json = new ArrayList<JSONObject>(routelength);
                                                            summary_ = new String[routelength];
                                                            distance_ = new String[routelength];
                                                            duration_ = new String[routelength];
                                                            routes_summary=new ArrayList<String[]>(routelength);
                                                            Polilinelist_ = new ArrayList<ArrayList<LatLng>>(routelength);
                                                            polilinelist=new ArrayList<LatLng>();
                                                            Step_Polilinelist_=new ArrayList<>(routelength);
                                                            endLatlngarray = new ArrayList<LatLng>();
                                                           ArrayList<ArrayList<LatLng>> all_endLatlngarray=new ArrayList<ArrayList<LatLng>>(routelength);
                                                            String routes_leg_summary;
                                                           // steppoliline_points=new StringBuilder();

                                                            for (int i= 0; i<=routes.length()-1; i++)
                                                            {
                                                                Double jsonObjectbounds_northeast_lat = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("northeast").getDouble("lat");
                                                                Double jsonObjectbounds_northeast_lng = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("northeast").getDouble("lng");
                                                                Double jsonObjectbounds_southwest_lat = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("southwest").getDouble("lat");
                                                                Double jsonObjectbounds_southwest_lng = routes.getJSONObject(i).getJSONObject("bounds").getJSONObject("southwest").getDouble("lng");
                                                                latLng_northeast = new LatLng(jsonObjectbounds_northeast_lat, jsonObjectbounds_northeast_lng);
                                                                latLng_southwest = new LatLng(jsonObjectbounds_southwest_lat, jsonObjectbounds_southwest_lng);
                                                               // Log.e("northeast latlng", "" + latLng_northeast);
                                                              //  Log.e("southwest latlng", "" + latLng_southwest);
                                                                builder = new LatLngBounds.Builder();
                                                                builder.include(latLng_northeast).include(latLng_southwest);
                                                                latLngBounds=builder.build();
                                                               // Log.e("Latlng Bounds ", "" +latLngBounds);
                                                                overview_polyline = routes.getJSONObject(i).getJSONObject("overview_polyline");
                                                                polilinelist = (ArrayList<LatLng>) PolyUtil.decode(overview_polyline.getString("points"));
                                                                summary_[i] = routes.getJSONObject(i).getString("summary");
                                                               // Log.e("main Polyline list size", "" + i + "_" + polilinelist.size());
                                                                Polilinelist_.add(i, polilinelist);
                                                               // Log.e("main route summary", "" + i + "_" + "" + summary_[i]);
                                                                jsonArray_Legs = routes.getJSONObject(i).getJSONArray("legs");
                                                               // Log.e("legs array length", ""+ jsonArray_Legs.length());//1
                                                                Step_distance_list = new ArrayList<String>();
                                                                Html_instruction_list = new ArrayList<String>();
                                                                steps_end_location_list=new ArrayList<LatLng>();
                                                                steps_poliline_list=new ArrayList<LatLng>();
                                                                Steps_poliline_list_final=new ArrayList<LatLng>();

                                                                if (jsonArray_Legs.length()==1)
                                                                {

                                                                    for (int j= 0; j<jsonArray_Legs.length(); j++)
                                                                    {

                                                                        distance_[i] = jsonArray_Legs.getJSONObject(j).getJSONObject("distance").getString("text");
                                                                        duration_[i] = jsonArray_Legs.getJSONObject(j).getJSONObject("duration").getString("text");
                                                                       // Log.e("Polyline distance", "" + j + "_" + "" + distance_[i]);
                                                                      //  Log.e("Polyline duration", "" + j + "_" + "" + duration_[i]);
                                                                        Double destination_end_lat=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location").getDouble("lat");
                                                                        Double destination_end_lng=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location").getDouble("lng");
                                                                        Double source_end_lat=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location").getDouble("lat");
                                                                        Double source_end_lng=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location").getDouble("lat");
                                                                        LatLng destination_latlng=new LatLng(destination_end_lat,destination_end_lng);
                                                                        LatLng source_latlng=new LatLng(source_end_lat,source_end_lng);
                                                                      //  Log.e("Source Latlng", "" + j + "_" + "" + source_latlng);
                                                                     //   Log.e("Destination Lng", "" + j + "_" + "" + destination_latlng);
                                                                        endLatlngarray.add(source_latlng);
                                                                        endLatlngarray.add(destination_latlng);
                                                                        //Log.e("Destination Lng", "" + j + "_" + "" + endLatlngarray.size());//2
                                                                        //all_endLatlngarray.add(i,endLatlngarray);
                                                                       // Log.e("final latlng list at", "" + j + "_" + "" +endLatlngarray.get(j));//2
                                                                       // Log.e("final latlng list size", "" + j + "_" + ""+all_endLatlngarray.size());
                                                                        Step_Leg_Array = jsonArray_Legs.getJSONObject(j).getJSONArray("steps");
                                                                       // Log.w("Steps Array Length", "" + j + "_" + "" + Step_Leg_Array.length());
                                                                        // Log.w(" initial step polil", "" +steps_poliline_list.size());//0
                                                                     //   Log.e("Steps end location", "" + j + "_" + "" + steps_end_location.size());
                                                                        for (int s = 0; s <Step_Leg_Array.length(); s++)
                                                                        {
                                                                            String ss = Step_Leg_Array.getJSONObject(s).getJSONObject("distance").getString("text");
                                                                            Step_distance_list.add(s, ss);
                                                                           // Log.e("Steps Distance", "" + s + "_" + "" + Step_distance_list.get(s));
                                                                            String html = Step_Leg_Array.getJSONObject(s).getString("html_instructions");                                                                  // Log.e("Steps Hmtl", "" + s + "_" + "" +step_html[s]);
                                                                            Html_instruction_list.add(s, html);
                                                                           // Log.e("Html Distance", "" + s + "_" + "" + Html_instruction_list.get(s));
                                                                         Double steps_endlocation_lat=Step_Leg_Array.getJSONObject(s).getJSONObject("start_location").getDouble("lat");
                                                                         Double steps_endlocation_lng=Step_Leg_Array.getJSONObject(s).getJSONObject("start_location").getDouble("lng");
                                                                           // Log.e("End location", "" + s + "_" + "" + steps_endlocation_lat);
                                                                            //Log.e("end location", "" + s + "_" + "" + steps_endlocation_lng);
                                                                          steps_end_location_points=new LatLng(steps_endlocation_lat,steps_endlocation_lng);
                                                                            steps_end_location_list.add(s,steps_end_location_points);
                                                                          //  Log.e("step latlng list", "" + s + "_" + "" +steps_end_location_list.size());
                                                                            Step_polyline=Step_Leg_Array.getJSONObject(s).getJSONObject("polyline");
                                                                           // Log.w("step poliline", "" + s + "_" + "" +Step_polyline);
                                                                            steps_poliline_list=(ArrayList<LatLng>) PolyUtil.decode(Step_polyline.getString("points"));
                                                                            //Log.w("step poliline string", "" + s + "_" + "" +steps_poliline_list);
                                                                            Steps_poliline_list_final.addAll(steps_poliline_list);
                                                                          // Log.w("step poliline string", "" + s + "_" + "" +Steps_poliline_list_final.size());
                                                                          //  Log.w("step poliline string",""+ s + "_"+Steps_poliline_list_final);
                                                                        }

                                                                        Step_ArrayList.add(i, Step_distance_list);
                                                                       // Log.w("Step Array List Size_", ""+i+Step_ArrayList.get(i).size());//33
                                                                        Html_instruction.add(i, Html_instruction_list);
                                                                       // Log.w("Html Distanc List Size_",""+i+ Html_instruction.get(i).size());//33
                                                                        steps_end_location.add(i,steps_end_location_list);
                                                                       // Log.w("Step Latlng List 0", ""+i+steps_end_location.get(i).size());//33
                                                                       Step_Polilinelist_.add(i,Steps_poliline_list_final);
                                                                      // Log.w("Step Poliline List ", ""+i+Step_Polilinelist_.get(i).size());//6369

                                                                    }

//                                                                 Log.e("Step Latlng List 1", "" + steps_end_location.get(1).size());//61
                                                                    //googleRouteDetailModelClass.setDuration(duration_);
                                                                    //Log.e("without via endleg", "" + endLatlngarray.size());


                                                                }



                                                            }

                                                            googlemapadpater = new GoogleRouteMapAdapter(context, hashMap, Waypoints_list, distance_, duration_, summary_,endLatlngarray, Step_Polilinelist_, Step_ArrayList, Html_instruction,steps_end_location);
                                                            recyclerView.setAdapter(googlemapadpater);
                                                            googlemapadpater.notifyDataSetChanged();





                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                            } else {
                                                //Toast.makeText(MapsActivity.this, "No Response,Limit Exceeded Please Try Again", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                    }
                                    // json response ok else
                                    else if (jsonresult.equals("OVER_QUERY_LIMIT")) {
                                        Toast.makeText(Main2Activity.this, "Limit exceeded,try again later", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }
                            }
                            // Json Null Else
                            else

                            {
                                Toast.makeText(Main2Activity.this, "No Response", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(context, "No Response,Limit Exceeded,Please Try Later", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).execute();
    }
    else if(savedInstanceState!=null)
        {
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.activity_main2_landscape);
            } else {
                setContentView(R.layout.activity_main2);
            }
            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            hashMap = new HashMap<>();
            Map_hashMap = new HashMap<>();
            context = this;
            context_r=this;
            steps_end_location=new ArrayList<ArrayList<LatLng>>();
            Step_distance_list = new ArrayList<String>();
            Step_ArrayList = new ArrayList<ArrayList<String>>();
            Waypoints_list = new ArrayList<>();
            title=(TextView)findViewById(R.id.tittlemain);
            Html_instruction_list = new ArrayList<String>();
            Html_instruction = new ArrayList<ArrayList<String>>();
            layoutManager = new LinearLayoutManager(Main2Activity.this);
            recyclerView.setLayoutManager(layoutManager);
            googleRouteDetailModelClass = new GoogleRouteDetailModelClass(Main2Activity.this);
            builder = new LatLngBounds.Builder();
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
            googlemapadpater = new GoogleRouteMapAdapter(Main2Activity.this, hashMap, Waypoints_list, distance_, duration_, summary_, endLatlngarray, Polilinelist_, Step_ArrayList, Html_instruction, steps_end_location);
            recyclerView.setAdapter(googlemapadpater);
            googlemapadpater.notifyDataSetChanged();


        }


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        Log.e("On saved way List Size", "" +Waypoints_list.size());
        Log.e("On saved Step List Size", "" + Step_ArrayList.size());
        Log.e("On saved Html List Size", "" + Html_instruction.size());
        Log.e("on saved via endleg", "" + endLatlngarray.size());
        Log.e("Polyline distance", "" + "_" + "" + distance_.length);
        Log.e("Polyline duration", "" + "_" + "" + duration_.length);
        Log.e("Polyline summary", "" + "_" + "" + summary_);
        Log.e("hashmap size", "" + "_" + "" + hashMap.size());
        Log.e("Polyline route", "" + "_" + "" + Polilinelist_.size());
        outState.putSerializable("hashmap",hashMap);
        outState.putSerializable("waypointslist",Waypoints_list);
        outState.putSerializable("distance",distance_);
        outState.putSerializable("duration",duration_);
        outState.putSerializable("summary",summary_);
        outState.putSerializable("endlatlng",endLatlngarray);
        outState.putSerializable("polilinelist",Polilinelist_);
        outState.putSerializable("steplist",Step_ArrayList);
        outState.putSerializable("htmllist",Html_instruction);
        outState.putSerializable("steps_end_location",steps_end_location);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       hashMap= (HashMap<String, String>) savedInstanceState.getSerializable("hashmap");
        Waypoints_list=savedInstanceState.getStringArrayList("waypointslist");
        distance_= (String[]) savedInstanceState.getSerializable("distance");
        duration_=(String[])savedInstanceState.getSerializable("duration");
        summary_=(String[])savedInstanceState.getSerializable("summary");
        endLatlngarray=savedInstanceState.getParcelableArrayList("endlatlng");
        Polilinelist_= (ArrayList<ArrayList<LatLng>>) savedInstanceState.getSerializable("polilinelist");
        Step_ArrayList= (ArrayList<ArrayList<String>>) savedInstanceState.getSerializable("steplist");
        Html_instruction= (ArrayList<ArrayList<String>>) savedInstanceState.getSerializable("htmllist");
        steps_end_location=(ArrayList<ArrayList<LatLng>>)savedInstanceState.getSerializable("steps_end_location");
        Log.e("On restore poinstSize", "" +Waypoints_list.size());
        Log.e("On Restore Step Size", "" + Step_ArrayList.size());
        Log.e("On restore Html Size", "" + Html_instruction.size());
        Log.e("on saved via endleg", "" + endLatlngarray.size());
        Log.e("Polyline distance", "" + "_" + "" + distance_.length);
        Log.e("Polyline duration", "" + "_" + "" + duration_.length);
        Log.e("Polyline summary", "" + "_" + "" + summary_);
        Log.e("hashmap size", "" + "_" + "" + hashMap.size());
        Log.e("Polyline route", "" + "_" + "" + Polilinelist_.size());
        Log.e("step end location", "" + "_" + "" +steps_end_location);

        googlemapadpater = new GoogleRouteMapAdapter(Main2Activity.this, hashMap, Waypoints_list, distance_, duration_, summary_, endLatlngarray, Polilinelist_, Step_ArrayList, Html_instruction, steps_end_location);
        recyclerView.setAdapter(googlemapadpater);
        googlemapadpater.notifyDataSetChanged();





    }
}
