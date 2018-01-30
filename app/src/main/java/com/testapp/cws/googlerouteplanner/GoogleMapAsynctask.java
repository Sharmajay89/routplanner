package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

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

public class GoogleMapAsynctask extends AsyncTask<Void,Void,JSONObject> {
    Context context;
    String newUrl;
    JSONArray jsonArray_Legs;
    StringBuilder sbb;
    ArrayList<LatLng> leglatlng;
    ArrayList<LatLng> arrayList;
    ArrayList<LatLng> totallegs;
    String charset = "UTF-8";
    String jsonresult="";
    URL url_1;
    String finalorigin,finaldestination;
    String TotalDistance,TotalTime;
    ArrayList<String> Dis_Duration;
    ArrayList<String> Total_Duration;
    AdapterDataReceived adapterDataReceived;
    String origin,destination;
    JSONObject final_jsonObject;
    String jsonObject_polyline_;
    StringBuilder M_origin;
    StringBuilder MainUrl;
    StringBuilder sb;
    //String source,destinations;
    JSONObject jsonObject_polyline;
    StringBuilder source,destinations;
    String url="https://maps.googleapis.com/maps/api/directions/json";
    BufferedInputStream bufferedInputStream;
    LatLng latLng;
    HashMap<String, String> hashMap;
    public GoogleMapAsynctask(Context context, HashMap<String,String> hashMap,AdapterDataReceived adapterDataReceived) {
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
            source=new StringBuilder(hashMap.get("source"));
            destinations=new StringBuilder(hashMap.get("destination"));
            for (int k=0;k<source.length()-1;k++)
            {
                if (Character.isWhitespace(source.charAt(k)))
                {
                  Log.e("White space exist",""+true);
                    source.setCharAt(k,'+');
                    finalorigin=source.toString();
                    Log.e("White space exist",""+source);
                    Log.e("White space exist",""+finalorigin);
                }
                else
                    Log.e("White space exist",""+false);
            }
            for (int k=0;k<destinations.length()-1;k++)
            {
                if (Character.isWhitespace(destinations.charAt(k)))
                {
                    Log.e("White space exist",""+true);
                    destinations.setCharAt(k,'+');
                    finaldestination=destinations.toString();
                    Log.e("White space exist",""+destinations);
                    Log.e("White space exist",""+finaldestination);

                }
                else
                    Log.e("White space exist",""+false);
            }

            Log.e("hash map source",""+hashMap.get("source"));
            Log.e("hash map dest",""+finaldestination);
            leglatlng=new ArrayList<>();
            Dis_Duration=new ArrayList<>();
            Total_Duration=new ArrayList<>();
            if ((hashMap.get("source")!= null) && (hashMap.get("destination")!= null)) {
                Log.e("source Dest not null",""+true);
                if ((hashMap.get("via_1").equals("")) && (hashMap.get("via_2").equals("")) && (hashMap.get("via_3").equals("")) && (hashMap.get("via_4").equals("")) && (hashMap.get("via_5").equals("")) && (hashMap.get("via_6").equals("")) && (hashMap.get("via_7").equals("")) && (hashMap.get("via_8").equals("")))
                {
                    try {
                        // URL googledirectionalurl= new URL("https://maps.googleapis.com/maps/api/directions/json");
                        M_origin=new StringBuilder("?origin=");
                        M_origin.append(finalorigin).append("&").append("destination=").append(finaldestination).append("&").append(hashMap.get("api_key"));
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

                        Log.e("Async result array size", "" + jsonArray.length());
                        for (int i = 0; i <= jsonArray.length() - 1; i++) {
                            jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                        }
                        Log.e("Async polyline", "" + jsonObject_polyline_);
                        arrayList = new ArrayList<>();
                        //totaltime=new ArrayList<>();
                        arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONObject("overview_polyline").getString("points"));
                        // arrayList = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("polyline").getString("points"));
                        // totaltime = (ArrayList<LatLng>) PolyUtil.decode(jsonArray.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getJSONObject("duration").getString("text"));
                        Log.e("Async polyline Array", "" + arrayList.size());
                        Log.e("Async polyline", "" + arrayList);
                        // Log.e("Async polyline Array", "" + totaltime.get(0));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
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



            }


        }
        return final_jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject o) {
        super.onPostExecute(o);
        Log.e("Async result jsnobj",""+o);
        try {
            JSONArray jsonArray=o.getJSONArray("routes");
            Log.e("Async Post Arraylen",""+jsonArray.length());
            for (int i = 0; i <= jsonArray.length() - 1; i++) {
                jsonObject_polyline_ = jsonArray.getJSONObject(i).getJSONObject("overview_polyline").getString("points");
                jsonArray_Legs=jsonArray.getJSONObject(i).getJSONArray("legs");

                Log.e("Post legs arraylen",""+jsonArray_Legs.length());
                Log.e("Legs ListLatlng length",""+leglatlng.size());
                for(int j=0;j<jsonArray_Legs.length();j++)
                {
                   JSONObject jsonObjectlegs=jsonArray_Legs.getJSONObject(j).getJSONObject("end_location");
                    Log.e(" Post Legs 1 end",""+jsonObjectlegs.get("lat"));
                    Log.e("Post Legs 1 end",""+jsonObjectlegs.get("lng"));
                    JSONObject jsonObjectDistance=jsonArray_Legs.getJSONObject(j).getJSONObject("distance");
                    TotalDistance=jsonObjectDistance.getString("text");
                    Dis_Duration.add(j,TotalDistance);
                   JSONObject jsonObjectDuration=jsonArray_Legs.getJSONObject(j).getJSONObject("duration");
                    TotalTime=jsonObjectDuration.getString("text");
                    Total_Duration.add(j,TotalTime);
                    Log.e("Post total Distance",""+TotalDistance);
                    Log.e("Post total Time",""+TotalTime);
                    latLng=new LatLng((double)jsonObjectlegs.get("lat"),(double)jsonObjectlegs.get("lng"));
                    Log.e("Post Legs 1 Latlng",""+latLng);
                    leglatlng.add(j,latLng);
                    Log.e("Post Legs 1 lisLatlng",""+leglatlng.size());
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
                Log.e("Post Asyncpolyline size","" + arrayList.size());
                Log.e("Post Async polyline", "" + arrayList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       //adapterDataReceived.getGoogleMapDirectionData(jsonresult,Dis_Duration,Total_Duration,leglatlng,arrayList);
    }
}
