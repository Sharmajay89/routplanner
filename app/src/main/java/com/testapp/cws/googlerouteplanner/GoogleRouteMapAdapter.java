package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.testapp.cws.googlerouteplanner.listener.MapRouteData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cws on 14/12/17.
 */

public class GoogleRouteMapAdapter extends RecyclerView.Adapter<GoogleRouteMapAdapter.ViewHolder> {
     String[] distance,duration,summary;
    LatLngBounds latlngbounds;
    Context context;
    HashMap<String,String> alldata=new HashMap<>();
   ArrayList<LatLng>source_destination_endlatlng=new ArrayList<>();
    ArrayList<String> Waypoints_List=new ArrayList<String>();
    ArrayList<String> step_array;
    ArrayList<String> html_array;
    ArrayList<LatLng> arrayList;
    ArrayList<LatLng> source_destination_latlng_points;
    ArrayList<ArrayList<String>> Html_instruction_=new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>>Step_ArrayList=new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<LatLng>>step_end_location=new ArrayList<ArrayList<LatLng>>();
    ArrayList<LatLng> step_end_location_list= new ArrayList<LatLng>();
     MapsActivity_2 mapsActivity_2;
      MapAdapterAsynctask mapAdapterAsynctask;
    LayoutInflater inflater;
    MapRouteData mapRouteData;
   AdapterDataClass adapterDataClass;
    LatLng southwest,northeast;
    LatLng latLng;

    int totaltime,totaldistance;
    ArrayList<ArrayList<LatLng>>polilinelist=new ArrayList<ArrayList<LatLng>>();

    GoogleRouteDetailModelClass googleRouteDetailModelClass;
    /*
    public GoogleRouteMapAdapter(Context contex, String[] distance, String[] duration, String[] summary,LatLng southwest,LatLng northeast, ArrayList<ArrayList<LatLng>> polilinelis) {


          this.southwest=southwest;
        this.northeast=northeast;
        this.context=contex;
        this.distance=distance;
        this.duration=duration;
        this.summary=summary;
        this.polilinelist=polilinelis;
        googleRouteDetailModelClass=new GoogleRouteDetailModelClass(context);
  Log.e("distance length",""+distance.length);
 Log.e("duration length",""+duration.length);
    Log.e("summary length",""+summary.length);
     ///Log.e("latlngbound",""+googleRouteDetailModelClass.getLatLngBounds());
  Log.e("poliline size",""+polilinelist.size());
        Log.e("latlng southwest",""+southwest);
        Log.e("latlng northeast",""+northeast);


    }
    */

    public GoogleRouteMapAdapter(Context contex,HashMap<String,String> hashMap,ArrayList<String> Waypoints_List ,String[] distance, String[] duration, String[] summary, ArrayList<LatLng> endlatlngarray,ArrayList<ArrayList<LatLng>> polilinelis,ArrayList<ArrayList<String>> Step_ArrayList,ArrayList<ArrayList<String>> html_instruction_,ArrayList<ArrayList<LatLng>> step_end_location) {
        //summary=new String[polilinelist.size()];
       // duration=new String[polilinelist.size()];
       // distance=new String[polilinelist.size()];
        this.Waypoints_List=Waypoints_List ;
        this.alldata=hashMap;
        this.source_destination_endlatlng=endlatlngarray;
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
        googleRouteDetailModelClass=new GoogleRouteDetailModelClass(context);

         Log.e("distance length",""+distance.length);
         Log.e("duration length",""+duration.length);
         Log.e("summary length",""+summary.length);
        ///Log.e("latlngbound",""+googleRouteDetailModelClass.getLatLngBounds());
      Log.w("adapter poliline size",""+polilinelist.size());
       Log.w("adapter poliline size",""+polilinelist.get(0).size());//8045
       // Log.w("adapter poliline size",""+polilinelist.get(1).size());
       // Log.e("latlng southwest",""+southwest);
        //Log.e("latlng northeast",""+northeast);
    Log.w("ada via points size",""+source_destination_endlatlng.size());//3
       Log.w("ada Waypoints List size",""+Waypoints_List.size());//3
       Log.w("ada hashmap size",""+alldata.size());//11
      Log.w("ada step array list",""+Step_ArrayList.size());//1
//        Log.w("ada step array list",""+Step_ArrayList.get(0).size());
      //  Log.w("ada step array list",""+Step_ArrayList.get(1).size());
 Log.w("ada html array list",""+Html_instruction_.size());//1
     //Log.w("ada html array list",""+Html_instruction_.get(0).size());
       // Log.w("ada html array list",""+Html_instruction_.get(1).size());
       Log.w("ada end location size",""+step_end_location.size());//1
       // Log.w("ada end location size",""+step_end_location.get(0));
       // Log.w("ada end location size",""+step_end_location.get(1));
       // Log.w("ada summay size",""+this.summary.length);
       // Log.w("adapter summary at",""+summary[0]);
       // Log.w("adapter summary at",""+summary[1]);
       // Log.w("ada duration size",""+duration.length);
      //  Log.w("adapter duration at",""+duration[0]);
       // Log.w("adapter duration at",""+duration[1]);
       // Log.w("ada distance size",""+this.distance.length);
       //  Log.w("adapter distance at",""+this.distance[0]);
       // Log.w("adapter distance at",""+this.distance[1]);



    }



    @Override
    public GoogleRouteMapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.routedetail, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(GoogleRouteMapAdapter.ViewHolder holder, final int position) {
        Log.w("position", "" +position);
        Log.w("summary lenght", "" +summary.length);
       if (summary.length==1)
        // with via points
        {
            holder.t1.setText(summary[position]);
            holder.t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                    Log.w("poliline at 1", "" + polilinelist.get(position).size());//8045
//                    Log.e("poliline at 2", "" + polilinelist.get(2).size());

                    arrayList = new ArrayList<LatLng>();
                    step_array = new ArrayList<String>();
                    html_array = new ArrayList<String>();
                    step_end_location_list = new ArrayList<LatLng>();
                    arrayList.addAll(polilinelist.get(position));
                    step_array.addAll(Step_ArrayList.get(position));
                    html_array.addAll(Html_instruction_.get(position));
                    step_end_location_list.addAll(step_end_location.get(position));
                    //source_destination_endlatlng.addAll(source_destination_endlatlng.get(position))
                    Log.w("array length", "" + arrayList.size());//8045
                    Log.w("array length", "" + arrayList.get(1));
                    Log.w("step array length", "" + step_array.size());//55
                    Log.w("step html length", "" + html_array.size());//55
                    Log.w("step lallng list", "" + step_end_location_list.size());//55
                    //googleRouteDetailModelClass.setPolilineroutes(null);
                    adapterDataClass=new AdapterDataClass();
                    //googleRouteDetailModelClass.setPolilineroutes(arrayList);
                    adapterDataClass.arrayList=arrayList;
                    adapterDataClass.step_array=step_array;
                    adapterDataClass.html_array=html_array;
                    adapterDataClass.step_end_location_list=step_end_location_list;
                    Log.w("step  list", "" +adapterDataClass.arrayList);
                    //arrayList.add(polilinelist.get(position).size());
                    Intent i = new Intent(context, MapsActivity_2.class);
                    i.putExtra("alldata",(Serializable) alldata);//hashmap
                    i.putExtra("Legslist",(Serializable)source_destination_endlatlng);
                    i.putExtra("Waypoints_list",(Serializable)Waypoints_List);
                    context.startActivity(i);
                }
            });
            String  totaldistance=distance[position];
            holder.t3.setText(totaldistance);
            holder.t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                    Log.w("poliline at 1", "" + polilinelist.get(position).size());//8045
//                    Log.e("poliline at 2", "" + polilinelist.get(2).size());

                    arrayList = new ArrayList<LatLng>();
                    step_array = new ArrayList<String>();
                    html_array = new ArrayList<String>();
                    step_end_location_list = new ArrayList<LatLng>();
                    arrayList.addAll(polilinelist.get(position));
                    step_array.addAll(Step_ArrayList.get(position));
                    html_array.addAll(Html_instruction_.get(position));
                    step_end_location_list.addAll(step_end_location.get(position));
                    //source_destination_endlatlng.addAll(source_destination_endlatlng.get(position))
                    Log.w("array length", "" + arrayList.size());//8045
                    Log.w("array length", "" + arrayList.get(1));
                    Log.w("step array length", "" + step_array.size());//55
                    Log.w("step html length", "" + html_array.size());//55
                    Log.w("step lallng list", "" + step_end_location_list.size());//55
                    //googleRouteDetailModelClass.setPolilineroutes(null);
                    adapterDataClass=new AdapterDataClass();
                    //googleRouteDetailModelClass.setPolilineroutes(arrayList);
                    adapterDataClass.arrayList=arrayList;
                    adapterDataClass.step_array=step_array;
                    adapterDataClass.html_array=html_array;
                    adapterDataClass.step_end_location_list=step_end_location_list;
                    Log.w("step  list", "" +adapterDataClass.arrayList);
                    //arrayList.add(polilinelist.get(position).size());
                    Intent i = new Intent(context, MapsActivity_2.class);
                    i.putExtra("alldata",(Serializable) alldata);//hashmap
                    i.putExtra("Legslist",(Serializable)source_destination_endlatlng);
                    i.putExtra("Waypoints_list",(Serializable)Waypoints_List);
                    context.startActivity(i);
                }
            });
            holder.t2.setText(duration[position]);
            holder.t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                    Log.e("poliline at 1", "" + polilinelist.get(position).size());

                    Log.w("poliline at 1", "" + polilinelist.get(position).size());//8045
//                    Log.e("poliline at 2", "" + polilinelist.get(2).size());

                    arrayList = new ArrayList<LatLng>();
                    step_array = new ArrayList<String>();
                    html_array = new ArrayList<String>();
                    step_end_location_list = new ArrayList<LatLng>();
                    arrayList.addAll(polilinelist.get(position));
                    step_array.addAll(Step_ArrayList.get(position));
                    html_array.addAll(Html_instruction_.get(position));
                    step_end_location_list.addAll(step_end_location.get(position));
                    //source_destination_endlatlng.addAll(source_destination_endlatlng.get(position))
                    Log.w("array length", "" + arrayList.size());//8045
                    Log.w("array length", "" + arrayList.get(1));
                    Log.w("step array length", "" + step_array.size());//55
                    Log.w("step html length", "" + html_array.size());//55
                    Log.w("step lallng list", "" + step_end_location_list.size());//55
                    //googleRouteDetailModelClass.setPolilineroutes(null);
                    adapterDataClass=new AdapterDataClass();
                    //googleRouteDetailModelClass.setPolilineroutes(arrayList);
                    adapterDataClass.arrayList=arrayList;
                    adapterDataClass.step_array=step_array;
                    adapterDataClass.html_array=html_array;
                    adapterDataClass.step_end_location_list=step_end_location_list;
                    Log.w("step  list", "" +adapterDataClass.arrayList);
                    //arrayList.add(polilinelist.get(position).size());
                    Intent i = new Intent(context, MapsActivity_2.class);
                    i.putExtra("alldata",(Serializable) alldata);//hashmap
                    i.putExtra("Legslist",(Serializable)source_destination_endlatlng);
                    i.putExtra("Waypoints_list",(Serializable)Waypoints_List);
                    context.startActivity(i);
                }
            });
        }


        else if(summary.length!=1)
//      without Via points
        {
            //if (position==0)
           // {
                holder.t1.setText(summary[position]);
                 holder.t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   public GoogleRouteMapAdapter(Context contex,HashMap<String,String> hashMap,ArrayList<String> Waypoints_List ,String[] distance, String[] duration, String[] summary, ArrayList<LatLng> viapoints,ArrayList<ArrayList<LatLng>> polilinelis,ArrayList<ArrayList<String>> Step_ArrayList,ArrayList<ArrayList<String>> html_instruction_,ArrayList<ArrayList<LatLng>> step_end_location)
                    Log.w("poliline at 1", "" + polilinelist.get(position).size());
//                    Log.e("poliline at 2", "" + polilinelist.get(2).size());

                    arrayList = new ArrayList<LatLng>();
                    step_array = new ArrayList<String>();
                    html_array = new ArrayList<String>();
                    step_end_location_list = new ArrayList<LatLng>();
                    arrayList.addAll(polilinelist.get(position));
                    step_array.addAll(Step_ArrayList.get(position));
                    html_array.addAll(Html_instruction_.get(position));
                    step_end_location_list.addAll(step_end_location.get(position));
                    //source_destination_endlatlng.addAll(source_destination_endlatlng.get(position))
                    Log.w("array length", "" + arrayList.size());
                    Log.w("array length", "" + arrayList.get(1));
                     Log.w("step array length", "" + step_array.size());
                    Log.w("step html length", "" + html_array.size());
                    Log.w("step lallng list", "" + step_end_location_list.size());
                    //googleRouteDetailModelClass.setPolilineroutes(null);
                    adapterDataClass=new AdapterDataClass();
                    //googleRouteDetailModelClass.setPolilineroutes(arrayList);
                    adapterDataClass.arrayList=arrayList;
                    adapterDataClass.step_array=step_array;
                    adapterDataClass.html_array=html_array;
                    adapterDataClass.step_end_location_list=step_end_location_list;
                    Log.w("step  list", "" +adapterDataClass.arrayList);
                    //arrayList.add(polilinelist.get(position).size());
                   Intent i = new Intent(context, MapsActivity_2.class);
                  i.putExtra("alldata",(Serializable) alldata);//hashmap
                   i.putExtra("Legslist",(Serializable)source_destination_endlatlng);
                    i.putExtra("Waypoints_list",(Serializable)Waypoints_List);
                    context.startActivity(i);
                   // i.putExtra("arraylist",(Serializable) arrayList);
                   // i.putExtra("viapoints",(Serializable) source_destination_endlatlng);
                    //i.putExtra("alldata",(Serializable) alldata);//hashmap
                   //i.putExtra("stepdistance",(Serializable)step_array);
                   //i.putExtra("htmlinstruction",(Serializable)html_array);
                   //i.putExtra("step_end_location_list",(Serializable)step_end_location_list);

                    //Log.w("end location list ", "" + step_end_location_list.size());//31

//
                    // for (int k = 0; k <= polilinelist.get(position).size() - 1; k++) {
                    //  arrayList.add(k, polilinelist.get(position).get(k));
                    //    Log.w("poliline at ",""+k+"_"+ polilinelist.get(position).get(k));
                    //    }
                    // for (int k = 0; k<Step_ArrayList.get(position).size(); k++) {

                    //    step_array.add(k, Step_ArrayList.get(position).get(k));
                    //   Log.e("step array length At", "" + k + "_" + step_array.get(k));
                    //     html_array.add(k, Html_instruction_.get(position).get(k));
                    //    Log.e("step html At", "" + k + "_" + html_array.get(k));
                    //    step_end_location_list.add(k, step_end_location.get(position).get(k));
                    //     Log.e("end location latlng", "" + k + "_" + step_end_location_list.get(k));
//

               // }

                       // Log.w("new array list size", "" + arrayList.size());//232
                       // Log.w("final step array length", "" +step_array.size());
                       // Log.w("final step html length", "" +html_array.size());
                       //  Log.w("final end latlng length", "" +step_end_location_list.size());
//
                  //mapAdapterAsynctask = (MapAdapterAsynctask) new MapAdapterAsynctask(context, alldata, Waypoints_List, distance, duration, summary, viapoints,polilinelist.get(position),Step_ArrayList.get(position),Html_instruction_.get(position), step_end_location.get(position),mapRouteData).execute();


                 //  }
                }
            });
            holder.t3.setText(distance[position]);
            holder.t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

                    Log.e("poliline at ", "" + polilinelist.get(position).size());
                    arrayList = new ArrayList<LatLng>();
                    step_array = new ArrayList<String>();
                    html_array = new ArrayList<String>();
                    step_end_location_list = new ArrayList<LatLng>();
                    arrayList.addAll(polilinelist.get(position));
                    step_array.addAll(Step_ArrayList.get(position));
                    html_array.addAll(Html_instruction_.get(position));
                    step_end_location_list.addAll(step_end_location.get(position));
                    //source_destination_endlatlng.addAll(source_destination_endlatlng.get(position))
                    Log.w("array length", "" + arrayList.size());
                    Log.w("array length", "" + arrayList.get(1));
                    Log.w("step array length", "" + step_array.size());
                    Log.w("step html length", "" + html_array.size());
                    Log.w("step lallng list", "" + step_end_location_list.size());
                    //googleRouteDetailModelClass.setPolilineroutes(null);
                    adapterDataClass=new AdapterDataClass();
                    //googleRouteDetailModelClass.setPolilineroutes(arrayList);
                    adapterDataClass.arrayList=arrayList;
                    adapterDataClass.step_array=step_array;
                    adapterDataClass.html_array=html_array;
                    adapterDataClass.step_end_location_list=step_end_location_list;
                    Log.w("step  list", "" +adapterDataClass.arrayList);
                    //arrayList.add(polilinelist.get(position).size());
                    Intent i = new Intent(context, MapsActivity_2.class);
                    i.putExtra("alldata",(Serializable) alldata);//hashmap
                    i.putExtra("Legslist",(Serializable)source_destination_endlatlng);
                    i.putExtra("Waypoints_list",(Serializable)Waypoints_List);
                    context.startActivity(i);
                }
            });
            holder.t2.setText(duration[position]);
            holder.t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                    Log.e("poliline at ", "" + polilinelist.get(position).size());
                    arrayList = new ArrayList<LatLng>();
                    step_array = new ArrayList<String>();
                    html_array = new ArrayList<String>();
                    step_end_location_list = new ArrayList<LatLng>();
                    arrayList.addAll(polilinelist.get(position));
                    step_array.addAll(Step_ArrayList.get(position));
                    html_array.addAll(Html_instruction_.get(position));
                    step_end_location_list.addAll(step_end_location.get(position));
                    //source_destination_endlatlng.addAll(source_destination_endlatlng.get(position))
                    Log.w("array length", "" + arrayList.size());
                    Log.w("array length", "" + arrayList.get(1));
                    Log.w("step array length", "" + step_array.size());
                    Log.w("step html length", "" + html_array.size());
                    Log.w("step lallng list", "" + step_end_location_list.size());
                    //googleRouteDetailModelClass.setPolilineroutes(null);
                    adapterDataClass=new AdapterDataClass();
                    //googleRouteDetailModelClass.setPolilineroutes(arrayList);
                    adapterDataClass.arrayList=arrayList;
                    adapterDataClass.step_array=step_array;
                    adapterDataClass.html_array=html_array;
                    adapterDataClass.step_end_location_list=step_end_location_list;
                    Log.w("step  list", "" +adapterDataClass.arrayList);
                    //arrayList.add(polilinelist.get(position).size());
                    Intent i = new Intent(context, MapsActivity_2.class);
                    i.putExtra("alldata",(Serializable) alldata);//hashmap
                    i.putExtra("Legslist",(Serializable)source_destination_endlatlng);
                    i.putExtra("Waypoints_list",(Serializable)Waypoints_List);
                    context.startActivity(i);
                }
            });

        }



    }

    @Override
    public int getItemCount()

    {
        if(summary.length==distance.length && distance.length==duration.length)
        {
            return summary.length;
        }
      else
        {
            if (summary.length== 1)
            {
                return summary.length;

            }
        return 0;
    }

    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
       ImageView vi;
        TextView t1,t2,t3;
        Intent i;
        public ViewHolder(View itemView) {
            super(itemView);
         vi=(ImageView)itemView.findViewById(R.id.image);
            t1=(TextView)itemView.findViewById(R.id.textview_via);
            t2=(TextView)itemView.findViewById(R.id.textview_time);
            t3=(TextView)itemView.findViewById(R.id.textview_distance);
               }
    }
}
