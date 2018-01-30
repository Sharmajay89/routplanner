package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     TextView from,to;
    int counter=0;
    Intent intent;
    Geocoder geocoder;
    Context context;
    Button getroute,waypoints;
    int viaresult=0;

    TextView cros1,cros2,cros4,cros3,cros5,cros6,cros7,cros8;
    RelativeLayout relativelayout;
     List<Address> source_latlan;
    List<Address> destination_latlan;
    HashMap<String,String> hashMap;
    MapsActivity mapsActivity;
    List<Address> via1_latlan;
    List<Address> via2_latlan;

     List<Address> via4_latlan,via3_latlan,via5_latlan,via6_latlan,via7_latlan,via8_latlan;
    EditText source,destination,via1,via2,via3,via4,via5,via6,via7,via8;
    String source_A,destination_B,via_1,via_2,via_3,via_4,via_5,via_6,via_7,via_8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hashMap=new HashMap<>();
        geocoder=new Geocoder(MainActivity.this);
        relativelayout=(RelativeLayout)findViewById(R.id.relativelayout);
        getroute=(Button)findViewById(R.id.getroute);
        source=(EditText)findViewById(R.id.source);
        destination=(EditText)findViewById(R.id.destiny);
        cros1=(TextView)findViewById(R.id.cross1);
        Log.e("destination line color",""+destination.getSolidColor());
        cros2=(TextView)findViewById(R.id.cross2);
        cros3=(TextView)findViewById(R.id.cross3);
        cros4=(TextView)findViewById(R.id.cross4);
        cros5=(TextView)findViewById(R.id.cross5);
        cros6=(TextView)findViewById(R.id.cross6);
        cros7=(TextView)findViewById(R.id.cross7);
        cros8=(TextView)findViewById(R.id.cross8);
        waypoints=(Button) findViewById(R.id.via);
        via1=(EditText)findViewById(R.id.via1);
        via2=(EditText)findViewById(R.id.via2);
        via3=(EditText)findViewById(R.id.via3);
        via4=(EditText)findViewById(R.id.via4);
        via5=(EditText)findViewById(R.id.via5);
        via6=(EditText)findViewById(R.id.via6);
        via7=(EditText)findViewById(R.id.via7);
        via8=(EditText)findViewById(R.id.via8);
        waypoints.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               counter=counter+1;
               switch (counter)
               {
                   case 1:
                       relativelayout.setVisibility(View.VISIBLE);
                       via1.setVisibility(View.VISIBLE);
                       cros1.setVisibility(View.VISIBLE);
                       break;
                   case 2:
                       via2.setVisibility(View.VISIBLE);
                       cros2.setVisibility(View.VISIBLE);
                       break;
                   case 3:via3.setVisibility(View.VISIBLE);
                       cros3.setVisibility(View.VISIBLE);
                       break;
                   case 4:via4.setVisibility(View.VISIBLE);
                       cros4.setVisibility(View.VISIBLE);
                       break;
                   case 5:
                       via5.setVisibility(View.VISIBLE);
                       cros5.setVisibility(View.VISIBLE);
                       break;
                   case 6:via6.setVisibility(View.VISIBLE);
                       cros6.setVisibility(View.VISIBLE);
                       break;
                   case 7:via7.setVisibility(View.VISIBLE);
                       cros7.setVisibility(View.VISIBLE);
                       break;
                   case 8:via8.setVisibility(View.VISIBLE);
                       cros8.setVisibility(View.VISIBLE);
                       counter=0;
                      // getroute.setClickable(false);
                       Toast.makeText(MainActivity.this, "Not More Than Eight Way Points Allowed", Toast.LENGTH_SHORT).show();
                       break;
                   default:
                       break;

               }

/*
               if(counter==1)
               {
                   via1.setVisibility(View.VISIBLE);
                   cros1.setVisibility(View.VISIBLE);
               }
               else if(counter==2)
               {
                   via2.setVisibility(View.VISIBLE);
                   cros2.setVisibility(View.VISIBLE);
               }
               else if(counter==3)
               {
                   via3.setVisibility(View.VISIBLE);
                   cros3.setVisibility(View.VISIBLE);
               }
               else if(counter==4)
               {
                   via4.setVisibility(View.VISIBLE);
                   cros4.setVisibility(View.VISIBLE);
                   counter=0;
               }
               */

           }
       });
       cros1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 {
                    via1.setVisibility(View.GONE);
                     via1.setText("");
                     cros1.setVisibility(View.GONE);
                    counter = 0;
                }
            }
        });
        cros2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                {
                    via2.setVisibility(View.GONE);
                    via2.setText("");
                    cros2.setVisibility(View.GONE);
                    counter = 1;
                }

            }
        });
        cros3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                {
                    via3.setVisibility(View.GONE);
                    via3.setText("");
                    cros3.setVisibility(View.GONE);
                    counter = 2;

                }
            }
        });
        cros4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    via4.setVisibility(View.GONE);
                    via4.setText("");
                    cros4.setVisibility(View.GONE);
                    counter = 3;

                }
            }
        });

        cros5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    via5.setVisibility(View.GONE);
                    via5.setText("");
                    cros5.setVisibility(View.GONE);
                    counter = 4;

                }
            }
        });
        cros6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    via6.setVisibility(View.GONE);
                    via6.setText("");
                    cros6.setVisibility(View.GONE);
                    counter = 5;

                }
            }
        });
        cros7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    via7.setVisibility(View.GONE);
                    via7.setText("");
                    cros7.setVisibility(View.GONE);
                    counter = 6;

                }
            }
        });
        cros8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                {
                    via8.setVisibility(View.GONE);
                    via8.setText("");
                    cros8.setVisibility(View.GONE);
                    counter = 7;

                }
            }
        });
getroute.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // Toast.makeText(MainActivity.this, "Not Implemented Now", Toast.LENGTH_SHORT).show();
        source_A = source.getText().toString().trim();
        destination_B = destination.getText().toString().trim();
        geocoder = new Geocoder(getApplicationContext());
        via_1=via1.getText().toString().trim();
        via_2=via2.getText().toString().trim();
        via_3=via3.getText().toString().trim();
        via_4=via4.getText().toString().trim();
        via_5=via5.getText().toString().trim();
        via_6=via6.getText().toString().trim();
        via_7=via7.getText().toString().trim();
        via_8=via8.getText().toString().trim();
        Log.e("source ",""+source_A);
        Log.e("destination ",""+destination_B);

        if(source_A.equals("") || destination_B.equals("")  )
        {
            Toast.makeText(MainActivity.this, "Please Enter The Source Or Destination", Toast.LENGTH_SHORT).show();
        }

        else if(source_A!=null && destination_B!=null && via_1.equals("") && via_2.equals("") && via_3.equals("") && via_4.equals("") && via_5.equals("") && via_6.equals("") && via_7.equals("") && via_8.equals(""))
        {
            source_A = source.getText().toString().trim();
            destination_B = destination.getText().toString().trim();
            //hashMap.put("source", "" + source_A);
           // hashMap.put("destination", destination_B);
            // hashMap.put("via 1",via_1);
            Log.e("source", "" + source_A);
            Log.e("Main Destinantion", "" + destination_B);

            if (via_1.equals(""))
            {
                via_1 = "";
                Log.e("via_ 1 null", "" + via_1);
            }
            else
            {

                    via_1 = via1.getText().toString().trim();

                Log.e("via_ 1 value", "" + via_1);

            }
            if (via_2.equals("")) {
                via_2 = "";
                Log.e("via_ 2 null", "" + via_2);


            } else {
                via_2 = via2.getText().toString().trim();
                ;
                Log.e("via_ 2 value", "" + via_2);

            }
            if (via_3.equals("")) {
                via_3 = "";
                Log.e("via_ 3 null", "" + via_3);

            } else {
                via_3 = via3.getText().toString().trim();

                Log.e("via_ 3 value", "" + via_3);
            }
            if (via_4.equals("")) {
                via_4 = "";
                Log.e("via_4 null", "" + via_4);

            } else {
                via_4 = via4.getText().toString().trim();

                Log.e("via_4 value", "" + via_4);

            }
            if (via_5.equals("")) {
                via_5 = "";
                Log.e("via_5 null", "" + via_5);

            } else {
                via_5 = via5.getText().toString().trim();

                Log.e("via_5 value", "" + via_5);


            }
            if (via_6.equals("")) {
                via_6 = "";
                Log.e("via_6 null", "" + via_6);

            } else {
                via_6 = via6.getText().toString().trim();

                Log.e("via_6 value", "" + via_6);

            }
            if (via_7.equals("")) {
                via_7 = "";
                Log.e("via_7 null", "" + via_7);
            } else {
                via_7 = via7.getText().toString().trim();

                Log.e("via_7 value", "" + via_7);

            }
            if (via_8.equals("")) {
                via_8 = "";
                Log.e("via_8 null", "" + via_8);
            } else {
                via_8 = via8.getText().toString().trim();

                Log.e("via_8 value", "" + via_8);
            }
                    intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("source", source_A);
                    intent.putExtra("destination", destination_B);
                  intent.putExtra("via_1", via_1);
                    intent.putExtra("via_8", via_8);
                    intent.putExtra("via_7", via_7);
                    intent.putExtra("via_4", via_4);
                    intent.putExtra("via_5", via_5);
                   intent.putExtra("via_3", via_3);
                  intent.putExtra("via_6", via_6);
                  intent.putExtra("via_2", via_2);
                    startActivity(intent);
                    destination.setText("");

        }

        else if(source_A!=null && destination_B!=null || !via_1.equals("") || !via_2.equals("") || !via_3.equals("") || !via_4.equals("") || !via_5.equals("") || !via_6.equals("") || !via_7.equals("") || !via_8.equals(""))

        {
            source_A = source.getText().toString().trim();
            destination_B = destination.getText().toString().trim();
            if (via_1.equals(""))
            {
                via_1 = "";
                Log.e("via_ 1 null", "" + via_1);
            }
            else
            {
                via_1 = via1.getText().toString().trim();
                Log.e("via_ 1 value", "" +via_1);

            }
            if (via_2.equals("")) {
                via_2 = "";
                Log.e("via_ 2 null", "" + via_2);

            }
            else {
                via_2 = via2.getText().toString().trim();
                Log.e("via_ 2 value", "" + via_2);

            }
            if (via_3.equals("")) {
                via_3 = "";
                Log.e("via_ 3 null", "" + via_3);

            }
            else {
                via_3 = via3.getText().toString().trim();
                Log.e("via_ 3 value", "" + via_3);
            }
            if (via_4.equals(""))
            {
                via_4 = "";
                Log.e("via_4 null", "" + via_4);

            }
            else
            {
                via_4 = via4.getText().toString().trim();
                Log.e("via_4 value", "" + via_4);

            }
            if (via_5.equals("")) {
                via_5 = "";
                Log.e("via_5 null", "" + via_5);

            }
            else
            {
                via_5 = via5.getText().toString().trim();
                Log.e("via_5 value", "" + via_5);

            }
            if (via_6.equals("")) {
                via_6 = "";
                Log.e("via_6 null", "" + via_6);

            }
            else {
                via_6 = via6.getText().toString().trim();
                Log.e("via_6 value", "" + via_6);

            }
            if (via_7.equals("")) {
                via_7 = "";
                Log.e("via_7 null", "" + via_7);
            }
            else
            {
                via_7 = via7.getText().toString().trim();
                Log.e("via_7 value", "" + via_7);

            }
            if (via_8.equals("")) {
                via_8 = "";
                Log.e("via_8 null", "" + via_8);
            }
            else {
                via_8 = via8.getText().toString().trim();
                Log.e("via_8 value", "" + via_8);
            }

                    Log.e("source", "" + source_A);
                    Log.e("Main Destinantion", "" + destination_B);
                    intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("source", source_A);
                    intent.putExtra("destination", destination_B);
                    intent.putExtra("via_1", via_1);
                    intent.putExtra("via_8", via_8);
                    intent.putExtra("via_7", via_7);
                    intent.putExtra("via_4", via_4);
                    intent.putExtra("via_5", via_5);
                    intent.putExtra("via_3", via_3);
                    intent.putExtra("via_6", via_6);
                    intent.putExtra("via_2", via_2);
                    startActivity(intent);
                    destination.setText("");
                    Log.e("Main via 1", "" + via_1);
                    Log.e("Main via 2", "" + via_2);
                    Log.e("Main via 3", "" + via_3);
                    Log.e("Main via 4", "" + via_4);
                    Log.e("Main via 5", "" + via_5);
                    Log.e("Main via 6", "" + via_6);
                    Log.e("Main via 7", "" + via_7);
                    Log.e("Main via 8", "" + via_8);
            }
            }


    }
);


}
}
