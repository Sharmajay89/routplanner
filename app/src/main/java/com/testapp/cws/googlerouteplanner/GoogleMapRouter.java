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
 * Created by cws on 30/11/17.
 */

public class GoogleMapRouter  extends AsyncTask<Void,Void,ArrayList<LatLng>> {
    Context context;
    String newUrl;
    JSONArray jsonArray_Legs;
    StringBuilder sbb;
    ArrayList<LatLng> leglatlng;
    ArrayList<LatLng> arrayList;
    ArrayList<LatLng> totallegs;
    String charset = "UTF-8";
    URL url_1;
    NewBekarAdapterDataReceived adapterDataReceived;
    String origin,destination;
    JSONObject final_jsonObject;
    String jsonObject_polyline_;
    StringBuilder sb;
    ArrayList<String> vias;
    JSONObject jsonObject_polyline;
    StringBuilder result;
    String url="https://maps.googleapis.com/maps/api/directions/json";
    BufferedInputStream bufferedInputStream;
    LatLng latLng;
    HashMap<String, String> hashMap;
    public GoogleMapRouter(Context context, HashMap<String,String> hashMap,NewBekarAdapterDataReceived adapterDataReceived) {
        this.context=context;
        this.hashMap=hashMap;
        this.adapterDataReceived=adapterDataReceived;
        this.origin=hashMap.get("source");
    }

   @Override
    protected ArrayList<LatLng> doInBackground(Void... params) {
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
       vias=new ArrayList<>(hashMap.size()-3);
       for(int k=0;k<=vias.size()-1;k++)
       {
           if(hashMap.get("via_1")==null)
           {

           }
           else {
               vias.add(k, hashMap.get("via_1"));
           }
           if(hashMap.get("via_2")==null)
           {

           }
           else
           {
               vias.add(k,hashMap.get("via_2"));
           }
           if(hashMap.get("via_3")==null)
           {

           }
           else
           {
               vias.add(k,hashMap.get("via_3"));
           }

       }
       Log.e("async via array size",""+vias.size());
       leglatlng=new ArrayList<>();
       if ((hashMap.get("source") != null) && (hashMap.get("destination") != null)) {
            switch (vias.size())
            {
                case 0:
                {
                    try {
                        // URL googledirectionalurl= new URL("https://maps.googleapis.com/maps/api/directions/json");

                        //newUrl=googledirectionalurl.toString();
                        sb = new StringBuilder(url);
                        // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                        sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("api_key"));
                        Log.e("new api  string url", "" + sb);
                        // newUrl=sb.toString();
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
                break;
                case  1:
                {
                    sb = new StringBuilder(url);
                    // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                    sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(vias.get(0)).append("&").append(hashMap.get("api_key"));
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
                return arrayList;
                case 2:
                {
                    sb = new StringBuilder(url);
                    // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                    sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(vias.get(0)).append("|").append(vias.get(1)).append("&").append(hashMap.get("api_key"));
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
                return arrayList;

                case 3:
                {
                    sb = new StringBuilder(url);
                    // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                    sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(vias.get(0)).append("|").append(vias.get(1)).append("|").append(vias.get(2)).append("&").append(hashMap.get("api_key"));
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
                return arrayList;

                case 4:
                {
                    sb = new StringBuilder(url);
                    // sb.append("?origin=").append(hashMap.get("origin")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append(hashMap.get("key"));
                    sb.append("?origin=").append(hashMap.get("source")).append("&").append("destination=").append(hashMap.get("destination")).append("&").append("waypoints=").append(vias.get(0)).append("|").append(vias.get(1)).append("|").append(vias.get(2)).append("|").append(vias.get(3)).append("&").append(hashMap.get("api_key"));
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
                return arrayList;

                }


            }

   return arrayList;
   }



    @Override
    protected void onPostExecute(ArrayList<LatLng> o) {
        super.onPostExecute(o);
        Log.e("Async result jsnobj",""+o);
      adapterDataReceived.getGoogleMapDirectionData(arrayList);
    }
}
