package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.testapp.cws.googlerouteplanner.listener.MapRouteData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cws on 18/1/18.
 */

public class MapAdapterAsynctask extends AsyncTask<Void,Void,Void> {

    String[] distance,duration,summary;
    LatLngBounds latlngbounds;
    Context context;
    HashMap<String,String> alldata=new HashMap<>();
    ArrayList<LatLng> viapoints=new ArrayList<LatLng>();
    ArrayList<String> Waypoints_List=new ArrayList<String>();
    ArrayList<String> step_array;
    ArrayList<String> html_array;
    ArrayList<LatLng> arrayList;
    ArrayList<String> Html_instruction_=new ArrayList<String>();
    ArrayList<String>Step_ArrayList=new ArrayList<String>();
    ArrayList<LatLng>step_end_location=new ArrayList<LatLng>();
    ArrayList<LatLng> step_end_location_list= new ArrayList<LatLng>();
    MapRouteData mapRouteData;


    LayoutInflater inflater;

    LatLng southwest,northeast;
    LatLng latLng;

    int totaltime,totaldistance;
    ArrayList<LatLng>polilinelist=new ArrayList<LatLng>();

    public MapAdapterAsynctask(Context contex,HashMap<String,String> hashMap,ArrayList<String> Waypoints_List ,String[] distance, String[] duration, String[] summary, ArrayList<LatLng> viapoints,ArrayList<LatLng> polilinelis,ArrayList<String> Step_ArrayList,ArrayList<String> html_instruction_,ArrayList<LatLng> step_end_location,MapRouteData mapRouteData) {
        this.Waypoints_List=Waypoints_List ;
        this.alldata=hashMap;
        this.viapoints=viapoints;
        this.mapRouteData=mapRouteData;
        // this.southwest=southwest;
        // this.northeast=northeast;
        this.context=contex;
        this.distance=distance;
        this.duration=duration;
        this.summary=summary;
        this.polilinelist=polilinelis;
        this.Step_ArrayList=Step_ArrayList;
        this.Html_instruction_=html_instruction_;
        this.step_end_location=step_end_location;

        Log.w("asyc poliline size",""+polilinelist.size());
       // Log.w("asyc poliline size",""+polilinelist.get(0).size());
       // Log.w("asyc poliline size",""+polilinelist.get(1).size());
        // Log.e("latlng southwest",""+southwest);
        //Log.e("latlng northeast",""+northeast);
        Log.w("asyc via points size",""+viapoints.size());
        Log.w("asyc Waypoints size",""+Waypoints_List.size());
        Log.w("asyc hashmap size",""+alldata.size());
        Log.w("asyc step array list",""+Step_ArrayList.size());
       // Log.w("asyc step array list",""+Step_ArrayList.get(0).size());
       // Log.w("asyc step array list",""+Step_ArrayList.get(1).size());
        Log.w("asyc html array list",""+Html_instruction_.size());
       // Log.w("asyc html array list",""+Html_instruction_.get(0).size());
       // Log.w("asyc html array list",""+Html_instruction_.get(1).size());
        Log.w("asycend location size",""+step_end_location.size());
        Log.w("asyc summay size",""+this.summary.length);
       // Log.w("asycr summary at",""+summary[0]);
      //  Log.w("asyc summary at",""+summary[1]);
        Log.w("asycduration size",""+duration.length);
        //Log.w("asyc duration at",""+duration[0]);
       // Log.w("asyc duration at",""+duration[1]);
        Log.w("asyca distance size",""+this.distance.length);
       // Log.w("asyc distance at",""+this.distance[0]);
       // Log.w("asyc distance at",""+this.distance[1]);


    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.w("asyn poliline at 1", "" + polilinelist.size());

        // Log.e("poliline at 2", "" + polilinelist.get(1).size());
//                    Log.e("poliline at 2", "" + polilinelist.get(2).size());
        arrayList = new ArrayList<LatLng>(polilinelist.size());
        step_array=new ArrayList<String>(Step_ArrayList.size());
        html_array=new ArrayList<String>(Html_instruction_.size());
        step_end_location_list=new ArrayList<LatLng>(step_end_location.size());
         for (int k = 0; k <= polilinelist.size() - 1; k++)
         {
          arrayList.add(k, polilinelist.get(k));
          }
        for (int k = 0; k<Step_ArrayList.size(); k++)
        {

          step_array.add(k, Step_ArrayList.get(k));
         Log.e("step array length At", "" + k + "_" + step_array.get(k));
           html_array.add(k, Html_instruction_.get(k));
          Log.e("step html At", "" + k + "_" + html_array.get(k));
         step_end_location_list.add(k, step_end_location.get(k));
          Log.e("end location latlng", "" + k + "_" + step_end_location_list.get(k));
//
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        Log.w("On Post", "" +arrayList.size());
        Log.w("On Post", "" + step_array.size());
        Log.w("On Post", "" +html_array.size());
         Log.w("On Post", "" +viapoints.size());
        Log.w("On Post", "" +step_end_location_list.size());
        Log.w("On Post", "" +alldata.size());
       mapRouteData.mapRouteDataItem(arrayList,step_array,html_array,viapoints,step_end_location_list,alldata);

Intent i = new Intent(context, MapsActivity_2.class);
       //// i.putExtra("arraylist",(Serializable) arrayList);
        // i.putExtra("viapoints",(Serializable) viapoints);
      //   i.putExtra("alldata",(Serializable) alldata);
  //  i.putExtra("stepdistance",(Serializable)step_array);
    //   i.putExtra("htmlinstruction",(Serializable)html_array);
     //  i.putExtra("step_end_location_list",(Serializable)step_end_location_list);

     context.startActivity(i);
    }
}
