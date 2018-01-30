package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cws on 6/12/17.
 */

public class TestGoogleMapAsynctask extends AsyncTask<Void,Void,JSONObject> {
    Context context;
    String newUrl;
    JSONArray jsonArray_Legs;
    StringBuilder sbb;
    ArrayList<LatLng> leglatlng;
    ArrayList<LatLng> arrayList;
    ArrayList<LatLng> totallegs;
    String charset = "UTF-8";
    ArrayList<ArrayList<LatLng>> allroutes;
    URL url_1;
    String finalorigin,finaldestination,finalvia1,finalvia2,finalvia3,finalvia4,finalvia5,finalvia6,finalvia7,finalvia8;
    String TotalDistance,TotalTime;
    //GoogleRoutePlannerAdapterDataReceived googlerouteplanneradapterinterface;
    ArrayList<String> Dis_Duration;
    ArrayList<String> Total_Duration;
   GoogleRoutePlannerAdapterDataReceived adapterDataReceived;
    String origin,destination;
    JSONObject final_jsonObject;
    String jsonObject_polyline_;
    StringBuilder M_origin;
    StringBuilder MainUrl;
    String jsonresult;
    StringBuilder sb;

    //String source,destinations;
    ArrayList<ArrayList<LatLng>> listofpolyline;
    JSONObject jsonObject_polyline;
    StringBuilder source,destinations,via1,via2,via3,via4,via5,via6,via7,via8;
    String url="https://maps.googleapis.com/maps/api/directions/json";
    BufferedInputStream bufferedInputStream;
    LatLng latLng;
    HashMap<String, String> hashMap;
    public TestGoogleMapAsynctask(Context context, HashMap<String,String> hashMap, GoogleRoutePlannerAdapterDataReceived adapterDataReceived) {
        this.context=context;
        this.hashMap=hashMap;
        this.adapterDataReceived=adapterDataReceived;
        this.origin=hashMap.get("source");
    }




    @Override
    protected JSONObject doInBackground(Void... params) {
        {
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
            source = new StringBuilder(hashMap.get("source"));
            destinations = new StringBuilder(hashMap.get("destination"));
            via1 = new StringBuilder(hashMap.get("via_1"));
            via2 = new StringBuilder(hashMap.get("via_2"));
            via3 = new StringBuilder(hashMap.get("via_3"));
            via4 = new StringBuilder(hashMap.get("via_4"));
            via5 = new StringBuilder(hashMap.get("via_5"));
            via6 = new StringBuilder(hashMap.get("via_6"));
            via7 = new StringBuilder(hashMap.get("via_7"));
            via8 = new StringBuilder(hashMap.get("via_8"));

            for (int k = 0; k < source.length() - 1; k++) {
                if (Character.isWhitespace(source.charAt(k))) {
                    Log.e("White space exist", "" + true);
                    source.setCharAt(k, '+');
                    finalorigin = source.toString();
                    Log.e("White space exist", "" + source);
                    Log.e("White space exist", "" + finalorigin);
                } else
                    Log.e("White space exist", "" + false);
                finalorigin = source.toString();
            }
            for (int k = 0; k < destinations.length() - 1; k++) {
                if (Character.isWhitespace(destinations.charAt(k))) {
                    Log.e("White space exist", "" + true);
                    destinations.setCharAt(k, '+');
                    finaldestination = destinations.toString();
                    Log.e("White space exist", "" + destinations);
                    Log.e("White space exist", "" + finaldestination);

                } else
                    Log.e("White space exist", "" + false);
                finaldestination = destinations.toString();
            }
            if (hashMap.get("via_1")!=null) {
                for (int k = 0; k < via1.length() - 1; k++) {
                    if (Character.isWhitespace(via1.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via1.setCharAt(k, '+');
                        finalvia1 = via1.toString();
                        Log.e("finalvia 1", "" + finalvia1);

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia1 = via1.toString();
                }
            }
            else
            {
                finalvia1="";
                Log.e("Not null ", "" + false);
            }
            if (!hashMap.get("via_2").equals("")) {
                for (int k = 0; k < via2.length() - 1; k++) {
                    if (Character.isWhitespace(via2.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via2.setCharAt(k, '+');
                        finalvia2 = via2.toString();

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia2 = via2.toString();
                }
            }
            else
            {
                finalvia2="";
            }
            if (!hashMap.get("via_3").equals("")) {
                for (int k = 0; k < via3.length() - 1; k++) {
                    if (Character.isWhitespace(via3.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via3.setCharAt(k, '+');
                        finalvia3 = via3.toString();

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia3 = via3.toString();
                }
            }
            else
            {
                finalvia3="";
            }
            if(!hashMap.get("via_4").equals("")) {
            for (int k = 0; k < via4.length() - 1; k++) {
                if (Character.isWhitespace(via4.charAt(k))) {
                    Log.e("White space exist", "" + true);
                    via4.setCharAt(k, '+');
                    finalvia4 = via4.toString();

                } else
                    Log.e("White space exist", "" + false);
                finalvia4 = via4.toString();
            }
        }
            else
            {
                finalvia4="";
            }
            if(!hashMap.get("via_5").equals("")) {
                for (int k = 0; k < via5.length() - 1; k++) {
                    if (Character.isWhitespace(via5.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via5.setCharAt(k, '+');
                        finalvia5 = via5.toString();

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia5 = via5.toString();
                }
            }
            else
            {
                finalvia5="";
            }
            if(!hashMap.get("via_6").equals("")) {
                for (int k = 0; k < via6.length() - 1; k++) {
                    if (Character.isWhitespace(via6.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via6.setCharAt(k, '+');
                        finalvia6 = via6.toString();

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia6 = via6.toString();
                }
            }
            else
            {
                finalvia6="";
            }
            if(!hashMap.get("via_7").equals("")) {
                for (int k = 0; k < via7.length() - 1; k++) {
                    if (Character.isWhitespace(via7.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via7.setCharAt(k, '+');
                        finalvia7 = via7.toString();

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia7 = via7.toString();
                }
            }
            else
            {
                finalvia7="";
            }
            if(!hashMap.get("via_8").equals("")) {
                for (int k = 0; k < via8.length() - 1; k++) {
                    if (Character.isWhitespace(via8.charAt(k))) {
                        Log.e("White space exist", "" + true);
                        via8.setCharAt(k, '+');
                        finalvia8 = via8.toString();

                    } else
                        Log.e("White space exist", "" + false);
                    finalvia8 = via8.toString();
                }
            }
            else
            {
                finalvia8="";
            }
            Log.e("hash map source",""+finalorigin);
            Log.e("hash map dest",""+finaldestination);
            Log.e("hash map source",""+finalvia1);
            Log.e("hash map dest",""+finalvia2);
            Log.e("hash map source",""+finalvia3);
            Log.e("hash map dest",""+finalvia4);
            Log.e("hash map source",""+finalvia5);
            Log.e("hash map dest",""+finalvia6);
            Log.e("hash map source",""+finalvia7);
            Log.e("hash map dest",""+finalvia8);
            leglatlng=new ArrayList<>();
            Dis_Duration=new ArrayList<>();
            Total_Duration=new ArrayList<>();
            if ((hashMap.get("source")!= null) && (hashMap.get("destination")!= null))
            {
                Log.e("source Dest not null",""+true);
                if ((hashMap.get("via_1").equals("")) && (hashMap.get("via_2").equals("")) && (hashMap.get("via_3").equals("")) && (hashMap.get("via_4").equals("")) && (hashMap.get("via_5").equals("")) && (hashMap.get("via_6").equals("")) && (hashMap.get("via_7").equals("")) && (hashMap.get("via_8").equals("")))
                {
                    try {
                        // URL googledirectionalurl= new URL("https://maps.googleapis.com/maps/api/directions/json");
                        M_origin=new StringBuilder("?origin=");
                        M_origin.append(finalorigin).append("&").append("destination=").append(finaldestination).append("&").append("mode=driving").append("&").append("alternatives=true").append("&").append(hashMap.get("api_key"));
                        Log.e("M origin",""+M_origin);
                        //newUrl=googledirectionalurl.toString();
                        sb = new StringBuilder(url);
                         sb.append(M_origin);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        //sb.append("?origin=").append(finalorigin).append("&").append("destination=").append(finaldestination).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        URL url_1 = new URL(sb.toString());
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        int res = httpURLConnection.getResponseCode();
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            sbb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sbb.append(line);
                            }
                            br.close();

                        }
                        final_jsonObject = new JSONObject(sbb.toString());
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = final_jsonObject.getJSONArray("routes");
                        allroutes=new ArrayList<ArrayList<LatLng>>();
                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            //arrayList = new ArrayList<>();
                            //totaltime=new ArrayList<>();
                           // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points"));
                            //jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                           // allroutes.add(i,arrayList);
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);

                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                else
                {
                    Log.e("hash map source",""+finalvia1);
                    Log.e("hash map dest",""+finalvia2);
                    Log.e("hash map source",""+finalvia3);
                    Log.e("hash map dest",""+finalvia4);
                    Log.e("hash map source",""+finalvia5);
                    Log.e("hash map dest",""+finalvia6);
                    Log.e("hash map source",""+finalvia7);
                    Log.e("hash map dest",""+finalvia8);

                    M_origin=new StringBuilder("?origin=");
                    M_origin.append(finalorigin).append("&").append("destination=").append(finaldestination).append("&").append("waypoints=").append(finalvia1).append("|").append(finalvia2).append("|").append(finalvia3).append("|").append(finalvia4).append("|").append(finalvia5).append("|").append(finalvia6).append("|").append(finalvia7).append("|").append(finalvia8).append("|").append("&").append("mode=driving").append("&").append("alternatives=true").append("&").append(hashMap.get("api_key"));
                    Log.e("M origin",""+M_origin);

                    try {
                        // URL googledirectionalurl= new URL("https://maps.googleapis.com/maps/api/directions/json");

                        //newUrl=googledirectionalurl.toString();
                        sb = new StringBuilder(url);
                        sb.append(M_origin);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        //sb.append("?origin=").append(finalorigin).append("&").append("destination=").append(finaldestination).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        URL url_1 = new URL(sb.toString());
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        int res = httpURLConnection.getResponseCode();
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            sbb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sbb.append(line);
                            }
                            br.close();

                        }
                        final_jsonObject = new JSONObject(sbb.toString());
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = final_jsonObject.getJSONArray("routes");

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        //arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                       // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                       // Log.e("Async polyline Array", "" + arrayList.size());
                        //Log.e("Async polyline", "" + arrayList);
                        // Log.e("Async polyline Array", "" + totaltime.get(0));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

               /*
                else if ((hashMap.get("via_1")!=null) || (hashMap.get("via_2")!= null) || (hashMap.get("via_3")!= null) || (hashMap.get("via_4") == null) || (hashMap.get("via_5")!= null) || (hashMap.get("via_6")!=null) || (hashMap.get("via_7")!= null) || (hashMap.get("via_8")!= null))
                {
                    if ((hashMap.get("via_1")!= null) && (hashMap.get("via_2")== null) && (hashMap.get("via_3")== null) && (hashMap.get("via_4")== null) && (hashMap.get("via_5")== null) && (hashMap.get("via_6")==null) && (hashMap.get("via_7")== null) && (hashMap.get("via_8")== null) )
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async resultarraylen",""+jsonArray.length());
                        for (int i = 0; i <= jsonArray.length()- 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");

                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {;
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 1 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 1 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 1 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 1  array lisLatlng",""+leglatlng.size());

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

//                   Log.e("Legs Array length",""+jsonArray_Legs.length());
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                        // Log.e("Async polyline Array", "" + totaltime.get(0));

                    }
                    else if ((hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")== null) && (hashMap.get("via_4")== null) && (hashMap.get("via_5")== null) && (hashMap.get("via_6")==null) && (hashMap.get("via_7")== null) && (hashMap.get("via_8")== null))
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 2 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 2 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 2 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 2  array lisLatlng",""+leglatlng.size());
//
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                    else if ( (hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")!= null) && (hashMap.get("via_4")== null) && (hashMap.get("via_5")== null) && (hashMap.get("via_6")==null) && (hashMap.get("via_7")== null) && (hashMap.get("via_8")== null))
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("|").append(hashMap.get("via_3")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 3 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 3 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 3 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 3  array lisLatlng",""+leglatlng.size());
//
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                    else if ( (hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")!= null) && (hashMap.get("via_4")!= null) && (hashMap.get("via_5")== null) && (hashMap.get("via_6")==null) && (hashMap.get("via_7")== null) && (hashMap.get("via_8")== null))
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("|").append(hashMap.get("via_3")).append("|").append(hashMap.get("via_4")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 4 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 4 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 4 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 4  array lisLatlng",""+leglatlng.size());
//
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                    else if ( (hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")!= null) && (hashMap.get("via_4")!= null) && (hashMap.get("via_5")!= null) && (hashMap.get("via_6")==null) && (hashMap.get("via_7")== null) && (hashMap.get("via_8")== null))

                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("|").append(hashMap.get("via_3")).append("|").append(hashMap.get("via_4")).append("|").append(hashMap.get("via_5")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 5 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 5 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 5 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 5  array lisLatlng",""+leglatlng.size());
//
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                    else if ( (hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")!= null) && (hashMap.get("via_4")!= null) && (hashMap.get("via_5")!= null) && (hashMap.get("via_6")!=null) && (hashMap.get("via_7")== null) && (hashMap.get("via_8")== null))
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("|").append(hashMap.get("via_3")).append("|").append(hashMap.get("via_4")).append("|").append(hashMap.get("via_5")).append("|").append(hashMap.get("via_6")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 6 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 6 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 6 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 6  array lisLatlng",""+leglatlng.size());
//
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                    else if ( (hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")!= null) && (hashMap.get("via_4")!= null) && (hashMap.get("via_5")!= null) && (hashMap.get("via_6")!=null) && (hashMap.get("via_7")!= null) && (hashMap.get("via_8")== null))
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("|").append(hashMap.get("via_3")).append("|").append(hashMap.get("via_4")).append("|").append(hashMap.get("via_5")).append("|").append(hashMap.get("via_6")).append("|").append(hashMap.get("via_7")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 7 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 7 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 7 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 7  array lisLatlng",""+leglatlng.size());
//
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                    else if ( (hashMap.get("via_1")!=null) && (hashMap.get("via_2")!=null) && (hashMap.get("via_3")!= null) && (hashMap.get("via_4")!= null) && (hashMap.get("via_5")!= null) && (hashMap.get("via_6")!=null) && (hashMap.get("via_7")!= null) && (hashMap.get("via_8")!= null))
                    {
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(hashMap.get("via_1")).append("|").append(hashMap.get("via_2")).append("|").append(hashMap.get("via_3")).append("|").append(hashMap.get("via_4")).append("|").append(hashMap.get("via_5")).append("|").append(hashMap.get("via_6")).append("|").append(hashMap.get("via_7")).append("|").append(hashMap.get("via_8")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        try {
                            url_1 = new URL(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url_1.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int res = 0;
                        try {
                            res = httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("res", "" + res);
                        if (res == HttpURLConnection.HTTP_OK) {

                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sbb = new StringBuilder();
                            String line;
                            try {
                                while ((line = br.readLine()) != null) {
                                    sbb.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            final_jsonObject = new JSONObject(sbb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Async result jsnobj", "" + final_jsonObject);
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = final_jsonObject.getJSONArray("routes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            try {
                                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");
                                Log.e("legs json arraylen",""+jsonArray_Legs.length());
                                Log.e("Legs array length",""+leglatlng.size());
                                for(int j=0;j<jsonArray_Legs.length()-1;j++)
                                {
                                    //jsonArray_Legs=jsonArray.getJSONObject(j).getJSONArray("legs");
                                    JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                                    Log.e("Legs 8 end",""+jsonObjectlegs.get("lat"));
                                    Log.e("Legs 8 end",""+jsonObjectlegs.get("lng"));
                                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                                    Log.e("Legs 8 Latlng",""+latLng);
                                    leglatlng.add(j,latLng);
                                    Log.e("Legs 8  array lisLatlng",""+leglatlng.size());
//
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        try {
                            arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                    }
                }

                */



            }


        }
        return final_jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject o) {
        super.onPostExecute(o);

        Log.e("Async result jsnobj", "" + o);
        if(o!=null)
        {
        try {
             jsonresult = o.getString("status");
           
            if (jsonresult.equals("OK")){

     adapterDataReceived.getGoogleMapDirectionData(o);
            }
            else
            {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
            Log.e("Async result status", "" + jsonresult);
        } catch (JSONException e) {
            e.printStackTrace();

        }}
        

/*
        if (jsonresult.equals("OK"))
        {
            try {
                JSONArray jsonArray = o.getJSONArray("routes");
                Log.e("Async Post Arraylen", "" + jsonArray.length());
                listofpolyline=new ArrayList<>(jsonArray.length());
                Log.e("Async ListofArraylen", ""+listofpolyline.size());
                for (int i = 0; i <= jsonArray.length()-1; i++) {
                    arrayList = new ArrayList<>();
                    //totaltime=new ArrayList<>();
                    arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points"));
                    //jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
//                    allroutes.add(i,arrayList);
                    //jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                    //arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points"));
                   // listofpolyline.add(i,arrayList);
                    jsonArray_Legs = jsonArray.getJSONObject(i).getJSONArray("legs");
                    Log.e("Post legs arraylen", "" + jsonArray_Legs.length());
                    Log.e("Legs ListLatlng length", "" + leglatlng.size());

                    for (int j = 0; j< jsonArray_Legs.length(); j++) {
                        JSONObject jsonObjectlegs = jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                        Log.e(" Post Legs 1 end", "" + jsonObjectlegs.get("lat"));
                        Log.e("Post Legs 1 end", "" + jsonObjectlegs.get("lng"));
                        JSONObject jsonObjectDistance = jsonArray_Legs.getJSONObject(j).getJSONObject("distance");
                        TotalDistance = jsonObjectDistance.getString("text");
                        Dis_Duration.add(j, TotalDistance);
                        JSONObject jsonObjectDuration = jsonArray_Legs.getJSONObject(j).getJSONObject("duration");
                        TotalTime = jsonObjectDuration.getString("text");
                        Total_Duration.add(j, TotalTime);
                        Log.e("Post total Distance", "" + TotalDistance);
                        Log.e("Post total Time", "" + TotalTime);
                        latLng = new LatLng((double) jsonObjectlegs.get("lat"), (double) jsonObjectlegs.get("lng"));
                        Log.e("Post Legs 1 Latlng", "" + latLng);
                        leglatlng.add(j, latLng);
                        Log.e("Post Legs 1 lisLatlng", "" + leglatlng.size());
//
                    }
                    Log.e("Async polyline", "" + jsonObject_polyline_);
                    arrayList = new ArrayList<>();
                    //totaltime=new ArrayList<>();
                    try {
                        arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                    // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));

                   // Log.e("Post Async polyline", "" + allroutes.get(1));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
        else
        {
            jsonresult="ZERO RESULT";
        }
        */


      //adapterDataReceived.getGoogleMapDirectionData(jsonresult,Dis_Duration,Total_Duration,leglatlng,arrayList);


         //adapterDataReceived.getGoogleMapDirectionData(o,list);

       

    }
}
