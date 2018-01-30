package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.testapp.cws.googlerouteplanner.listener.DataClickListener;

import java.util.ArrayList;

/**
 * Created by cws on 21/12/17.
 */

public class RouteDetailAdapter extends RecyclerView.Adapter<RouteDetailAdapter.ViewHolder>{
    ArrayList<String> step_array=new ArrayList<>();
    ArrayList<String> html_array=new ArrayList<>();;
    ArrayList<LatLng> step_end_location_list=new ArrayList<LatLng>();
    LayoutInflater inflater;
    Boolean recyclerview_click_status=false;
    Context context;
    DataClickListener dataClickListener;

    public RouteDetailAdapter(Context context, ArrayList<String> step_array, ArrayList<String> html_array, ArrayList<LatLng> step_end_location_list,DataClickListener dataClickListener) {
        this.context=context;
        this.dataClickListener=dataClickListener;
        this.step_array=step_array;
        this.html_array=html_array;
        this.step_end_location_list=step_end_location_list;
        Log.w("step array at ada",""+step_array.size());
        Log.w("html array at ada",""+html_array.size());
        Log.w("html array at ada",""+html_array.size());

    }

    @Override
    public RouteDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.routestepdetail,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RouteDetailAdapter.ViewHolder holder, final int position) {


        if(step_array.size()!=0 || html_array.size()!=0 )
     {
         holder.dis.setText(step_array.get(position).toString());

         holder.via.setText(html_array.get(position).toString().replace("\n"," "));
         Log.e("html copntain string ",""+html_array.get(position));
         if(html_array.get(position).contains("turn right"))
         {
          Log.e("html copntain string ",""+html_array.get(position).contains("turn right"));

         }

         holder.via.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context, "Wait For Implementation"+step_end_location_list.get(position), Toast.LENGTH_SHORT).show();
                 LatLng recyclerlatlng=step_end_location_list.get(position);
                 recyclerview_click_status=true;
                 dataClickListener.dataItemClickListener(recyclerlatlng,recyclerview_click_status,false);
                 Log.w("clicked latlng ",""+recyclerlatlng);

                 Log.w("clicked recycler ",""+recyclerview_click_status);


             }
         });

     }

    }

    @Override
    public int getItemCount()
    {
      if(step_array.size()!=0 && html_array.size()!=0)
      {
          return step_array.size();
      }
          return html_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView via,dis;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            via=(TextView)itemView.findViewById(R.id.textview_via);
            dis=(TextView)itemView.findViewById(R.id.textview_distance);
           // turn_right= BitmapDescriptorFactory.fromResource(R.drawable.turn_right_);

        }

    }
}
