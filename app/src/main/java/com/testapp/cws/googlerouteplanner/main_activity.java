package com.testapp.cws.googlerouteplanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class main_activity extends AppCompatActivity {
    TextView from,to;
     int counter=0,cros_counter=0;
    Intent intent;
    Integer[] via_array=new Integer[9];
    Geocoder geocoder;
    static String r_source=null,r_destination=null,r_via1=null,r_via2=null,r_via3=null,r_via4=null,r_via5=null,
            r_via6=null,
            r_via7=null,
            r_via8=null;
    //static  String relative_via1_x1=null,relative_via2_X2=null,relative__via3_X3=null,relative__via4_X4=null,relative__via5_X5=null,relative__via6_X6=null,relative__via7_X7=null,relative__via8_X8=null;
    Context context;
    static ArrayList<String> restore_viatext=new ArrayList<String>();
    static int v_rel,v_via1,v_via2,v_via3,v_via4,v_via5,v_via6,v_via7,v_via8;
    Button getroute,waypoints;
    int viaresult=0;

    TextView cros1,cros2,cros4,cros3,cros5,cros6,cros7,cros8;
    RelativeLayout relativelayout;
    List<Address> source_latlan;
    List<Address> destination_latlan;
    HashMap<String,String> hashMap;
    MapsActivity mapsActivity;
    List<Address> via1_latlan;
    ArrayList<String> via_text=new ArrayList<String>();
    List<Address> via2_latlan;

    List<Address> via4_latlan,via3_latlan,via5_latlan,via6_latlan,via7_latlan,via8_latlan;
    EditText source,destination,via1,via2,via3,via4,via5,via6,via7,via8;
    String source_A,destination_B,via_1,via_2,via_3,via_4,via_5,via_6,via_7,via_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("first savedinstance", "" + savedInstanceState);

        if (savedInstanceState==null)
        {
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.activity_main_activity_landscape);
            } else {
                setContentView(R.layout.activity_main_activity);
            }
            hashMap = new HashMap<>();
            via_text=new ArrayList<>();
            geocoder = new Geocoder(main_activity.this);
            relativelayout = (RelativeLayout) findViewById(R.id.relativelayoutvia);
            getroute = (Button) findViewById(R.id.getroute);
            source = (EditText) findViewById(R.id.source);
            destination = (EditText) findViewById(R.id.destiny);
            cros1 = (TextView) findViewById(R.id.cross1);
            Log.e("destination line color", "" + destination.getSolidColor());
            cros2 = (TextView) findViewById(R.id.cross2);
            cros3 = (TextView) findViewById(R.id.cross3);
            cros4 = (TextView) findViewById(R.id.cross4);
            cros5 = (TextView) findViewById(R.id.cross5);
            cros6 = (TextView) findViewById(R.id.cross6);
            cros7 = (TextView) findViewById(R.id.cross7);
            cros8 = (TextView) findViewById(R.id.cross8);
            waypoints = (Button) findViewById(R.id.via);
            via1 = (EditText) findViewById(R.id.via1);
            via2 = (EditText) findViewById(R.id.via2);
            via3 = (EditText) findViewById(R.id.via3);
            via4 = (EditText) findViewById(R.id.via4);
            via5 = (EditText) findViewById(R.id.via5);
            via6 = (EditText) findViewById(R.id.via6);
            via7 = (EditText) findViewById(R.id.via7);
            via8 = (EditText) findViewById(R.id.via8);

            waypoints.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter = counter + 1;
                    Log.e("frst Couter on add via", ""+counter);
                      switch (counter) {
                            case 1:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;

                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);
                                break;
                            case 2:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;
                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);

                                break;
                            case 3:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                via3.setVisibility(View.VISIBLE);
                                cros3.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;

                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);

                                break;
                            case 4:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                via3.setVisibility(View.VISIBLE);
                                cros3.setVisibility(View.VISIBLE);
                                via4.setVisibility(View.VISIBLE);
                                cros4.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;
                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);

                                break;
                            case 5:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                via3.setVisibility(View.VISIBLE);
                                cros3.setVisibility(View.VISIBLE);
                                via4.setVisibility(View.VISIBLE);
                                cros4.setVisibility(View.VISIBLE);
                                via5.setVisibility(View.VISIBLE);
                                cros5.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;
                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);

                                break;
                            case 6:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                via3.setVisibility(View.VISIBLE);
                                cros3.setVisibility(View.VISIBLE);
                                via4.setVisibility(View.VISIBLE);
                                cros4.setVisibility(View.VISIBLE);
                                via5.setVisibility(View.VISIBLE);
                                cros5.setVisibility(View.VISIBLE);
                                via6.setVisibility(View.VISIBLE);
                                cros6.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;
                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);

                                break;
                            case 7:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                via3.setVisibility(View.VISIBLE);
                                cros3.setVisibility(View.VISIBLE);
                                via4.setVisibility(View.VISIBLE);
                                cros4.setVisibility(View.VISIBLE);
                                via5.setVisibility(View.VISIBLE);
                                cros5.setVisibility(View.VISIBLE);
                                via6.setVisibility(View.VISIBLE);
                                cros6.setVisibility(View.VISIBLE);
                                via7.setVisibility(View.VISIBLE);
                                cros7.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter=0;
                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);

                                break;
                            case 8:
                                relativelayout.setVisibility(View.VISIBLE);
                                via1.setVisibility(View.VISIBLE);
                                cros1.setVisibility(View.VISIBLE);
                                via2.setVisibility(View.VISIBLE);
                                cros2.setVisibility(View.VISIBLE);
                                via3.setVisibility(View.VISIBLE);
                                cros3.setVisibility(View.VISIBLE);
                                via4.setVisibility(View.VISIBLE);
                                cros4.setVisibility(View.VISIBLE);
                                via5.setVisibility(View.VISIBLE);
                                cros5.setVisibility(View.VISIBLE);
                                via6.setVisibility(View.VISIBLE);
                                cros6.setVisibility(View.VISIBLE);
                                via7.setVisibility(View.VISIBLE);
                                cros7.setVisibility(View.VISIBLE);
                                via8.setVisibility(View.VISIBLE);
                                cros8.setVisibility(View.VISIBLE);
                                cros_counter = counter;
                                //counter = 0;
                                Log.e("Couter after all via", "" + counter);
                                Log.e("total cros counter", "" + cros_counter);
                                // getroute.setClickable(false);
                                Toast.makeText(main_activity.this, "Not More Than Eight Way Points Allowed", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;

                        }
                    }




            });
            cros1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    {
                        via1.setVisibility(View.GONE);
                        via1.setText("");
                        cros1.setVisibility(View.GONE);
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);
                        //counter = 0;
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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);
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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);

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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);
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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);

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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);

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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);
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
                        cros_counter=cros_counter-1;
                        counter=cros_counter;
                        Log.e("Cros_Couter cros click",""+cros_counter);
                        Log.e("Couter on cros click",""+counter);


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
                                                via_1 = via1.getText().toString().trim();
                                                via_2 = via2.getText().toString().trim();
                                                via_3 = via3.getText().toString().trim();
                                                via_4 = via4.getText().toString().trim();
                                                via_5 = via5.getText().toString().trim();
                                                via_6 = via6.getText().toString().trim();
                                                via_7 = via7.getText().toString().trim();
                                                via_8 = via8.getText().toString().trim();
                                                Log.e("source ", "" + source_A);
                                                Log.e("destination ", "" + destination_B);

                                                if (source_A.equals("") || destination_B.equals(""))
                                                {
                                                    Toast.makeText(main_activity.this, "Please Enter The Source Or Destination", Toast.LENGTH_SHORT).show();
                                                } else if (source_A != null && destination_B != null && via_1.equals("") && via_2.equals("") && via_3.equals("") && via_4.equals("") && via_5.equals("") && via_6.equals("") && via_7.equals("") && via_8.equals("")) {
                                                    source_A = source.getText().toString().trim();
                                                    // savedInstanceState.putString("source", source_A);
                                                    destination_B = destination.getText().toString().trim();
                                                    // savedInstanceState.putString("destination", destination_B);
                                                    //hashMap.put("source", "" + source_A);
                                                    // hashMap.put("destination", destination_B);
                                                    // hashMap.put("via 1",via_1);
                                                    Log.e("source", "" + source_A);
                                                    Log.e("Main Destinantion", "" + destination_B);

                                                    if (via_1.equals("")) {
                                                        via_1 = "";
                                                        Log.e("via_ 1 null", "" + via_1);
                                                    } else {

                                                        via_1 = via1.getText().toString().trim();
                                                        //savedInstanceState.putString("via1", via_1);

                                                        Log.e("via_ 1 value", "" + via_1);

                                                    }
                                                    if (via_2.equals("")) {
                                                        via_2 = "";
                                                        Log.e("via_ 2 null", "" + via_2);


                                                    } else {
                                                        via_2 = via2.getText().toString().trim();
                                                        //savedInstanceState.putString("via2", via_2);
                                                        Log.e("via_ 2 value", "" + via_2);

                                                    }
                                                    if (via_3.equals("")) {
                                                        via_3 = "";
                                                        Log.e("via_ 3 null", "" + via_3);

                                                    } else {
                                                        via_3 = via3.getText().toString().trim();
                                                        // savedInstanceState.putString("via3", via_3);
                                                        Log.e("via_ 3 value", "" + via_3);
                                                    }
                                                    if (via_4.equals("")) {
                                                        via_4 = "";
                                                        Log.e("via_4 null", "" + via_4);

                                                    } else {
                                                        via_4 = via4.getText().toString().trim();
                                                        // savedInstanceState.putString("via4", via_4);
                                                        Log.e("via_4 value", "" + via_4);

                                                    }
                                                    if (via_5.equals("")) {
                                                        via_5 = "";
                                                        Log.e("via_5 null", "" + via_5);

                                                    } else {
                                                        via_5 = via5.getText().toString().trim();
                                                        //savedInstanceState.putString("via5", via_5);
                                                        Log.e("via_5 value", "" + via_5);


                                                    }
                                                    if (via_6.equals("")) {
                                                        via_6 = "";
                                                        Log.e("via_6 null", "" + via_6);

                                                    } else {
                                                        via_6 = via6.getText().toString().trim();
                                                        //savedInstanceState.putString("via6", via_6);
                                                        Log.e("via_6 value", "" + via_6);

                                                    }
                                                    if (via_7.equals("")) {
                                                        via_7 = "";
                                                        Log.e("via_7 null", "" + via_7);
                                                    } else {
                                                        via_7 = via7.getText().toString().trim();
                                                        //savedInstanceState.putString("via7", via_7);
                                                        Log.e("via_7 value", "" + via_7);

                                                    }
                                                    if (via_8.equals("")) {
                                                        via_8 = "";
                                                        Log.e("via_8 null", "" + via_8);
                                                    } else {
                                                        via_8 = via8.getText().toString().trim();
                                                        // savedInstanceState.putString("via1", via_8);
                                                        Log.e("via_8 value", "" + via_8);
                                                    }
                                                    intent = new Intent(main_activity.this, Main2Activity.class);
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

                                                } else if (source_A != null && destination_B != null || !via_1.equals("") || !via_2.equals("") || !via_3.equals("") || !via_4.equals("") || !via_5.equals("") || !via_6.equals("") || !via_7.equals("") || !via_8.equals(""))

                                                {
                                                    source_A = source.getText().toString().trim();
                                                    destination_B = destination.getText().toString().trim();
                                                    if (via_1.equals("")) {
                                                        via_1 = "";
                                                        Log.e("via_ 1 null", "" + via_1);
                                                    } else {
                                                        via_1 = via1.getText().toString().trim();
                                                        Log.e("via_ 1 value", "" + via_1);

                                                    }
                                                    if (via_2.equals("")) {
                                                        via_2 = "";
                                                        Log.e("via_ 2 null", "" + via_2);

                                                    } else {
                                                        via_2 = via2.getText().toString().trim();
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

                                                    Log.e("source", "" + source_A);
                                                    Log.e("Main Destinantion", "" + destination_B);
                                                    intent = new Intent(main_activity.this, Main2Activity.class);
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
        else if(savedInstanceState!=null)
        {
            {
                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.activity_main_activity_landscape);
                } else {
                    setContentView(R.layout.activity_main_activity);
                }
                hashMap = new HashMap<>();
                geocoder = new Geocoder(main_activity.this);
                via_text=new ArrayList<String>();
                relativelayout = (RelativeLayout) findViewById(R.id.relativelayoutvia);
                getroute = (Button) findViewById(R.id.getroute);
                source = (EditText) findViewById(R.id.source);
                destination = (EditText) findViewById(R.id.destiny);
                cros1 = (TextView) findViewById(R.id.cross1);
                Log.e("rotated line color",""+ destination.getSolidColor());
                cros2 = (TextView) findViewById(R.id.cross2);
                cros3 = (TextView) findViewById(R.id.cross3);
                cros4 = (TextView) findViewById(R.id.cross4);
                cros5 = (TextView) findViewById(R.id.cross5);
                cros6 = (TextView) findViewById(R.id.cross6);
                cros7 = (TextView) findViewById(R.id.cross7);
                cros8 = (TextView) findViewById(R.id.cross8);
                waypoints = (Button) findViewById(R.id.via);
                via1 = (EditText) findViewById(R.id.via1);
                via2 = (EditText) findViewById(R.id.via2);
                via3 = (EditText) findViewById(R.id.via3);
                via4 = (EditText) findViewById(R.id.via4);
                via5 = (EditText) findViewById(R.id.via5);
                via6 = (EditText) findViewById(R.id.via6);
                via7 = (EditText) findViewById(R.id.via7);
                via8 = (EditText) findViewById(R.id.via8);

               waypoints.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // counter = counter + 1;
                        Log.e("Couter rotate add via", "" + counter);
                        Log.e("Couter rotate add via", "" +cros_counter);
                         if(counter!=0)
                         {
                             Log.e("Couter on add via", "" + counter);
                             counter=counter+1;
                             switch (counter) {
                                 case 1:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     counter=counter+1;
                                     cros_counter = counter;
                                      Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);
                                     break;
                                 case 2:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     via_text.add(counter-2,via1.getText().toString().trim());
                                     via_text.add(counter-1,via2.getText().toString().trim());
                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);

                                     break;
                                 case 3:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     via3.setVisibility(View.VISIBLE);
                                     cros3.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     via_text.add(counter-3,via1.getText().toString().trim());
                                     via_text.add(counter-2,via2.getText().toString().trim());
                                     via_text.add(counter-1,via3.getText().toString().trim());

                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);
                                     break;
                                 case 4:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     via3.setVisibility(View.VISIBLE);
                                     cros3.setVisibility(View.VISIBLE);
                                     via4.setVisibility(View.VISIBLE);
                                     cros4.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     via_text.add(counter-4,via1.getText().toString().trim());
                                     via_text.add(counter-3,via2.getText().toString().trim());
                                     via_text.add(counter-2,via3.getText().toString().trim());
                                     via_text.add(counter-1,via3.getText().toString().trim());
                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);

                                     break;
                                 case 5:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     via3.setVisibility(View.VISIBLE);
                                     cros3.setVisibility(View.VISIBLE);
                                     via4.setVisibility(View.VISIBLE);
                                     cros4.setVisibility(View.VISIBLE);
                                     via5.setVisibility(View.VISIBLE);
                                     cros5.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);

                                     break;
                                 case 6:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     via3.setVisibility(View.VISIBLE);
                                     cros3.setVisibility(View.VISIBLE);
                                     via4.setVisibility(View.VISIBLE);
                                     cros4.setVisibility(View.VISIBLE);
                                     via5.setVisibility(View.VISIBLE);
                                     cros5.setVisibility(View.VISIBLE);
                                     via6.setVisibility(View.VISIBLE);
                                     cros6.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);

                                     break;
                                 case 7:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     via3.setVisibility(View.VISIBLE);
                                     cros3.setVisibility(View.VISIBLE);
                                     via4.setVisibility(View.VISIBLE);
                                     cros4.setVisibility(View.VISIBLE);
                                     via5.setVisibility(View.VISIBLE);
                                     cros5.setVisibility(View.VISIBLE);
                                     via6.setVisibility(View.VISIBLE);
                                     cros6.setVisibility(View.VISIBLE);
                                     via7.setVisibility(View.VISIBLE);
                                     cros7.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);

                                     break;
                                 case 8:
                                     relativelayout.setVisibility(View.VISIBLE);
                                     via1.setVisibility(View.VISIBLE);
                                     cros1.setVisibility(View.VISIBLE);
                                     via2.setVisibility(View.VISIBLE);
                                     cros2.setVisibility(View.VISIBLE);
                                     via3.setVisibility(View.VISIBLE);
                                     cros3.setVisibility(View.VISIBLE);
                                     via4.setVisibility(View.VISIBLE);
                                     cros4.setVisibility(View.VISIBLE);
                                     via5.setVisibility(View.VISIBLE);
                                     cros5.setVisibility(View.VISIBLE);
                                     via6.setVisibility(View.VISIBLE);
                                     cros6.setVisibility(View.VISIBLE);
                                     via7.setVisibility(View.VISIBLE);
                                     cros7.setVisibility(View.VISIBLE);
                                     via8.setVisibility(View.VISIBLE);
                                     cros8.setVisibility(View.VISIBLE);
                                     cros_counter = counter;
                                     //counter=0;
                                     Log.e("Couter after all via", "" + counter);
                                     Log.e("total cros counter", "" + cros_counter);
                                    // counter = 0;
                                     // getroute.setClickable(false);
                                     Toast.makeText(main_activity.this, "Not More Than Eight Way Points Allowed", Toast.LENGTH_SHORT).show();
                                     break;
                                 default:
                                     break;

                             }
                         }
                        else if(counter==0)
                         {
                            Log.e("else cros counter",""+cros_counter);
                            Log.e("else cros counter", "" +counter);
                             counter=counter+1;
                             Log.e("else cros counter", "" +counter);
                             {

                             switch (counter)
                             {
                                     case 1:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         //counter=counter+1;
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);
                                         break;
                                     case 2:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);

                                         break;
                                     case 3:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         via3.setVisibility(View.VISIBLE);
                                         cros3.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);
                                         break;
                                     case 4:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         via3.setVisibility(View.VISIBLE);
                                         cros3.setVisibility(View.VISIBLE);
                                         via4.setVisibility(View.VISIBLE);
                                         cros4.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);

                                         break;
                                     case 5:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         via3.setVisibility(View.VISIBLE);
                                         cros3.setVisibility(View.VISIBLE);
                                         via4.setVisibility(View.VISIBLE);
                                         cros4.setVisibility(View.VISIBLE);
                                         via5.setVisibility(View.VISIBLE);
                                         cros5.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);

                                         break;
                                     case 6:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         via3.setVisibility(View.VISIBLE);
                                         cros3.setVisibility(View.VISIBLE);
                                         via4.setVisibility(View.VISIBLE);
                                         cros4.setVisibility(View.VISIBLE);
                                         via5.setVisibility(View.VISIBLE);
                                         cros5.setVisibility(View.VISIBLE);
                                         via6.setVisibility(View.VISIBLE);
                                         cros6.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);

                                         break;
                                     case 7:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         via3.setVisibility(View.VISIBLE);
                                         cros3.setVisibility(View.VISIBLE);
                                         via4.setVisibility(View.VISIBLE);
                                         cros4.setVisibility(View.VISIBLE);
                                         via5.setVisibility(View.VISIBLE);
                                         cros5.setVisibility(View.VISIBLE);
                                         via6.setVisibility(View.VISIBLE);
                                         cros6.setVisibility(View.VISIBLE);
                                         via7.setVisibility(View.VISIBLE);
                                         cros7.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);

                                         break;
                                     case 8:
                                         relativelayout.setVisibility(View.VISIBLE);
                                         via1.setVisibility(View.VISIBLE);
                                         cros1.setVisibility(View.VISIBLE);
                                         via2.setVisibility(View.VISIBLE);
                                         cros2.setVisibility(View.VISIBLE);
                                         via3.setVisibility(View.VISIBLE);
                                         cros3.setVisibility(View.VISIBLE);
                                         via4.setVisibility(View.VISIBLE);
                                         cros4.setVisibility(View.VISIBLE);
                                         via5.setVisibility(View.VISIBLE);
                                         cros5.setVisibility(View.VISIBLE);
                                         via6.setVisibility(View.VISIBLE);
                                         cros6.setVisibility(View.VISIBLE);
                                         via7.setVisibility(View.VISIBLE);
                                         cros7.setVisibility(View.VISIBLE);
                                         via8.setVisibility(View.VISIBLE);
                                         cros8.setVisibility(View.VISIBLE);
                                         cros_counter = counter;
                                         //counter=0;
                                         Log.e("Couter after all via", "" + counter);
                                         Log.e("total cros counter", "" + cros_counter);
                                         // counter = 0;
                                         // getroute.setClickable(false);
                                         Toast.makeText(main_activity.this, "Not More Than Eight Way Points Allowed", Toast.LENGTH_SHORT).show();
                                         break;
                                     default:
                                         break;

                                 }
                             }

                        }
                    }
                });
                cros1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            via1.setVisibility(View.GONE);
                            via1.setText("");
                            cros1.setVisibility(View.GONE);
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);
                          //  counter = 0;
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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);
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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);

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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);
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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);

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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);

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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);

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
                            cros_counter=cros_counter-1;
                            counter=cros_counter;
                            Log.e("Cros_Couter cros click",""+cros_counter);
                            Log.e("Couter on cros click",""+counter);

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
                                                    via_1 = via1.getText().toString().trim();
                                                    via_2 = via2.getText().toString().trim();
                                                    via_3 = via3.getText().toString().trim();
                                                    via_4 = via4.getText().toString().trim();
                                                    via_5 = via5.getText().toString().trim();
                                                    via_6 = via6.getText().toString().trim();
                                                    via_7 = via7.getText().toString().trim();
                                                    via_8 = via8.getText().toString().trim();
                                                    Log.e("source ", "" + source_A);
                                                    Log.e("destination ", "" + destination_B);

                                                    if (source_A.equals("") || destination_B.equals("")) {
                                                        Toast.makeText(main_activity.this, "Please Enter The Source Or Destination", Toast.LENGTH_SHORT).show();
                                                    } else if (source_A != null && destination_B != null && via_1.equals("") && via_2.equals("") && via_3.equals("") && via_4.equals("") && via_5.equals("") && via_6.equals("") && via_7.equals("") && via_8.equals("")) {
                                                        source_A = source.getText().toString().trim();
                                                        // savedInstanceState.putString("source", source_A);
                                                        destination_B = destination.getText().toString().trim();
                                                        // savedInstanceState.putString("destination", destination_B);
                                                        //hashMap.put("source", "" + source_A);
                                                        // hashMap.put("destination", destination_B);
                                                        // hashMap.put("via 1",via_1);
                                                        Log.e("source", "" + source_A);
                                                        Log.e("Main Destinantion", "" + destination_B);

                                                        if (via_1.equals("")) {
                                                            via_1 = "";
                                                            Log.e("via_ 1 null", "" + via_1);
                                                        } else {

                                                            via_1 = via1.getText().toString().trim();
                                                            //savedInstanceState.putString("via1", via_1);

                                                            Log.e("via_ 1 value", "" + via_1);

                                                        }
                                                        if (via_2.equals("")) {
                                                            via_2 = "";
                                                            Log.e("via_ 2 null", "" + via_2);


                                                        } else {
                                                            via_2 = via2.getText().toString().trim();
                                                            //savedInstanceState.putString("via2", via_2);
                                                            Log.e("via_ 2 value", "" + via_2);

                                                        }
                                                        if (via_3.equals("")) {
                                                            via_3 = "";
                                                            Log.e("via_ 3 null", "" + via_3);

                                                        } else {
                                                            via_3 = via3.getText().toString().trim();
                                                            // savedInstanceState.putString("via3", via_3);
                                                            Log.e("via_ 3 value", "" + via_3);
                                                        }
                                                        if (via_4.equals("")) {
                                                            via_4 = "";
                                                            Log.e("via_4 null", "" + via_4);

                                                        } else {
                                                            via_4 = via4.getText().toString().trim();
                                                            // savedInstanceState.putString("via4", via_4);
                                                            Log.e("via_4 value", "" + via_4);

                                                        }
                                                        if (via_5.equals("")) {
                                                            via_5 = "";
                                                            Log.e("via_5 null", "" + via_5);

                                                        } else {
                                                            via_5 = via5.getText().toString().trim();
                                                            //savedInstanceState.putString("via5", via_5);
                                                            Log.e("via_5 value", "" + via_5);


                                                        }
                                                        if (via_6.equals("")) {
                                                            via_6 = "";
                                                            Log.e("via_6 null", "" + via_6);

                                                        } else {
                                                            via_6 = via6.getText().toString().trim();
                                                            //savedInstanceState.putString("via6", via_6);
                                                            Log.e("via_6 value", "" + via_6);

                                                        }
                                                        if (via_7.equals("")) {
                                                            via_7 = "";
                                                            Log.e("via_7 null", "" + via_7);
                                                        } else {
                                                            via_7 = via7.getText().toString().trim();
                                                            //savedInstanceState.putString("via7", via_7);
                                                            Log.e("via_7 value", "" + via_7);

                                                        }
                                                        if (via_8.equals("")) {
                                                            via_8 = "";
                                                            Log.e("via_8 null", "" + via_8);
                                                        } else {
                                                            via_8 = via8.getText().toString().trim();
                                                            // savedInstanceState.putString("via1", via_8);
                                                            Log.e("via_8 value", "" + via_8);
                                                        }
                                                        intent = new Intent(main_activity.this, Main2Activity.class);
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

                                                    } else if (source_A != null && destination_B != null || !via_1.equals("") || !via_2.equals("") || !via_3.equals("") || !via_4.equals("") || !via_5.equals("") || !via_6.equals("") || !via_7.equals("") || !via_8.equals(""))

                                                    {
                                                        source_A = source.getText().toString().trim();
                                                        destination_B = destination.getText().toString().trim();
                                                        if (via_1.equals("")) {
                                                            via_1 = "";
                                                            Log.e("via_ 1 null", "" + via_1);
                                                        } else {
                                                            via_1 = via1.getText().toString().trim();
                                                            Log.e("via_ 1 value", "" + via_1);

                                                        }
                                                        if (via_2.equals("")) {
                                                            via_2 = "";
                                                            Log.e("via_ 2 null", "" + via_2);

                                                        } else {
                                                            via_2 = via2.getText().toString().trim();
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

                                                        Log.e("source", "" + source_A);
                                                        Log.e("Main Destinantion", "" + destination_B);
                                                        intent = new Intent(main_activity.this, Main2Activity.class);
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

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.w("onconfig","onconfig");
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_main_activity_landscape);

        }
        else
        {
            setContentView(R.layout.activity_main_activity);

        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.w("savedintance","savedinstabce");

        outState.putString("source", source.getText().toString().trim());
        outState.putString("destination",destination.getText().toString().trim());
        outState.putString("via1", via1.getText().toString().trim());
        outState.putString("via2", via2.getText().toString().trim());
        outState.putString("via3", via3.getText().toString().trim());
        outState.putString("via4", via4.getText().toString().trim());
        outState.putString("via5", via5.getText().toString().trim());
        outState.putString("via6", via6.getText().toString().trim());
        outState.putString("via7", via7.getText().toString().trim());
        outState.putString("via8", via8.getText().toString().trim());
       outState.putInt("relativelayout_visibility",relativelayout.getVisibility());
       outState.putInt("via1_visibility",via1.getVisibility());
       outState.putInt("via_2_visibility",via2.getVisibility());
       outState.putInt("via_3_visibility",via3.getVisibility());
       outState.putInt("via_4_visibility",via4.getVisibility());
       outState.putInt("via_5_visibility",via5.getVisibility());
       outState.putInt("via_6_visibility",via6.getVisibility());
       outState.putInt("via_7_visibility",via7.getVisibility());
       outState.putInt("via_8_visibility",via8.getVisibility());
        outState.putInt("via_counter",counter);
        outState.putInt("cros_counter",cros_counter);
        r_via1=via1.getText().toString().trim();
        r_via2=via2.getText().toString().trim();
        r_via3=via3.getText().toString().trim();
        r_via4=via4.getText().toString().trim();
        r_via5=via5.getText().toString().trim();
        r_via6=via6.getText().toString().trim();
        r_via7=via7.getText().toString().trim();
        r_via8=via8.getText().toString().trim();
        Log.w("savedintance via text",""+r_via1);
        Log.w("savedintance via text",""+r_via2);
        Log.w("savedintance via text",""+r_via3);
        Log.w("savedintance via text",""+r_via4);
        Log.w("savedintance via text",""+r_via5);
        Log.w("savedintance via text",""+r_via6);
        Log.w("savedintance via text",""+r_via7);
        Log.w("savedintance via text",""+r_via8);
        switch (cros_counter)
        {
            case 1:
                if(!r_via1.equals(""))
                {
                  restore_viatext.add(cros_counter - 1, r_via1);
                }
                else
                {
                    restore_viatext.add(cros_counter-1," ");
                }
                break;
            case 2:
                if(!r_via1.equals(""))
                {
                    restore_viatext.add(cros_counter-2, r_via1);
                }
                else
                {
                    restore_viatext.add(cros_counter-2," ");
                }
                if(!r_via2.equals(""))
                {
                    restore_viatext.add(cros_counter-1, r_via2);
                }
                else
                {
                    restore_viatext.add(cros_counter-1," ");
                }
                break;
            case 3:
                if(!r_via1.equals(""))
                {
                    restore_viatext.add(cros_counter-3, r_via1);
                }
                else
                {
                    restore_viatext.add(cros_counter-3," ");
                }
                if(!r_via2.equals(""))
                {
                    restore_viatext.add(cros_counter-2, r_via2);
                }
                else
                {
                    restore_viatext.add(cros_counter-2," ");
                }
                if(!r_via3.equals(""))
                {
                    restore_viatext.add(cros_counter-1, r_via3);
                }
                else
                {
                    restore_viatext.add(cros_counter-1," ");
                }
                break;

           // case 8:
            //    if(savedInstanceState.getInt("via8_visibility")==0)
            //    {
                    //  restore_viatext.add(cros_counter-8,savedInstanceState.getString("via1"));
                    // restore_viatext.add(cros_counter-7,savedInstanceState.getString("via2"));
                    // restore_viatext.add(cros_counter-6,savedInstanceState.getString("via3"));
                    //  restore_viatext.add(cros_counter-5,savedInstanceState.getString("via4"));
                    //  restore_viatext.add(cros_counter-4,savedInstanceState.getString("via5"));
                    //  restore_viatext.add(cros_counter-3,savedInstanceState.getString("via6"));
                    //  restore_viatext.add(cros_counter-2,savedInstanceState.getString("via7"));
               //     restore_viatext.add(cros_counter-1,savedInstanceState.getString("via8"));
             //   }
              //  break;
        }




       outState.putStringArrayList("vialist",restore_viatext);
        //via_text=new ArrayList<String>(counter);
       // Log.e("on saved list size",""+via_text.size());

      /*
       if(counter!=0) {

           switch (counter)
           {
               case 1:
                   if(!via1.getText().toString().trim().equals("")) {
                       via_text.add(0, via1.getText().toString().trim());

                   }
                   else
                   {
                       via_text.add(0,"");

                   }
                   break;
               case 2:
                   if(!via1.getText().toString().trim().equals("")) {
                       via_text.add(0, via1.getText().toString().trim());

                   }
                   else
                   {
                       via_text.add(0,"");

                   }
                   if(!via2.getText().toString().trim().equals("")) {
                       via_text.add(1, via2.getText().toString().trim());

                   }
                   else
                   {
                       via_text.add(1,"");

                   }
                   break;
               case 3:
                   if(!via1.getText().toString().trim().equals("")) {
                       via_text.add(0, via1.getText().toString().trim());

                   }
                   else
                   {
                       via_text.add(0,"");

                   }
                   if(!via2.getText().toString().trim().equals("")) {
                       via_text.add(1, via2.getText().toString().trim());

                   }
                   else
                   {
                       via_text.add(1,"");

                   }
                   if(!via3.getText().toString().trim().equals("")) {
                       via_text.add(2, via3.getText().toString().trim());

                   }
                   else
                   {
                       via_text.add(2,"");

                   }
                   break;


           }

       }
       */
       // outState.putStringArrayList("vialist",via_text);



        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.w("restoresavedinstance","restoresavedinstance");
        Log.w("restore savedinstance",""+savedInstanceState);

            Log.w("restoresavedinstance", "" + savedInstanceState.getString("source"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("destination"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via1"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via2"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via3"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via4"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via5"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via6"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via7"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getString("via8"));

            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("relativelayout_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via1_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_2_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_3_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_4_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_5_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_6_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_7_visibility"));
            Log.w("restoresavedinstance", "" + savedInstanceState.getInt("via_8_visibility"));
            counter=savedInstanceState.getInt("via_counter");
            cros_counter=savedInstanceState.getInt("cros_counter");
        restore_viatext=new ArrayList<>();
        r_source = savedInstanceState.getString("source");
        r_destination = savedInstanceState.getString("destination");

        Log.w("restoresaved_counter", "" + savedInstanceState.getInt("via_counter"));
        Log.w("restoresaved_counter", "" + savedInstanceState.getInt("cros_counter"));
        Log.e("on restore instance",""+counter);
        Log.e("on restore instance",""+cros_counter);
        restore_viatext=savedInstanceState.getStringArrayList("vialist");
        Log.e("on saved list size",""+restore_viatext.size());

        switch (cros_counter)
        {
            case 1:
                if(savedInstanceState.getInt("via1_visibility")==0)
            {
                restore_viatext.add(cros_counter-1,savedInstanceState.getString("via1"));
            }
                break;
            case 2:
                if(savedInstanceState.getInt("via2_visibility")==0)
                {
             restore_viatext.add(cros_counter-2,savedInstanceState.getString("via1"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via2"));
                }
                break;
            case 3:
                if(savedInstanceState.getInt("via3_visibility")==0)
                {
                restore_viatext.add(cros_counter-3,savedInstanceState.getString("via1"));
                   restore_viatext.add(cros_counter-2,savedInstanceState.getString("via2"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via3"));
                }
                break;
            case 4:
                if(savedInstanceState.getInt("via4_visibility")==0)
                {
                   restore_viatext.add(cros_counter-4,savedInstanceState.getString("via1"));
                   restore_viatext.add(cros_counter-3,savedInstanceState.getString("via2"));
                   restore_viatext.add(cros_counter-2,savedInstanceState.getString("via3"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via4"));
                }
                break;
            case 5:
                if(savedInstanceState.getInt("via5_visibility")==0)
                {
                   restore_viatext.add(cros_counter-5,savedInstanceState.getString("via1"));
               restore_viatext.add(cros_counter-4,savedInstanceState.getString("via2"));
                    restore_viatext.add(cros_counter-3,savedInstanceState.getString("via3"));
                   restore_viatext.add(cros_counter-2,savedInstanceState.getString("via4"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via5"));
                }
                break;
            case 6:
                if(savedInstanceState.getInt("via6_visibility")==0)
                {
                    restore_viatext.add(cros_counter-6,savedInstanceState.getString("via1"));
                    restore_viatext.add(cros_counter-5,savedInstanceState.getString("via2"));
                    restore_viatext.add(cros_counter-4,savedInstanceState.getString("via3"));
                  restore_viatext.add(cros_counter-3,savedInstanceState.getString("via4"));
                   restore_viatext.add(cros_counter-2,savedInstanceState.getString("via5"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via6"));
                }
                break;
            case 7:
                if(savedInstanceState.getInt("via7_visibility")==0)
                {
                    restore_viatext.add(cros_counter-7,savedInstanceState.getString("via1"));
                    restore_viatext.add(cros_counter-6,savedInstanceState.getString("via2"));
                    restore_viatext.add(cros_counter-5,savedInstanceState.getString("via3"));
                   restore_viatext.add(cros_counter-4,savedInstanceState.getString("via4"));
                    restore_viatext.add(cros_counter-3,savedInstanceState.getString("via5"));
                    restore_viatext.add(cros_counter-2,savedInstanceState.getString("via6"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via7"));
                }
                break;
            case 8:
                if(savedInstanceState.getInt("via8_visibility")==0)
                {
                    restore_viatext.add(cros_counter-8,savedInstanceState.getString("via1"));
                  restore_viatext.add(cros_counter-7,savedInstanceState.getString("via2"));
               restore_viatext.add(cros_counter-6,savedInstanceState.getString("via3"));
             restore_viatext.add(cros_counter-5,savedInstanceState.getString("via4"));
                  restore_viatext.add(cros_counter-4,savedInstanceState.getString("via5"));
                  restore_viatext.add(cros_counter-3,savedInstanceState.getString("via6"));
                  restore_viatext.add(cros_counter-2,savedInstanceState.getString("via7"));
                    restore_viatext.add(cros_counter-1,savedInstanceState.getString("via8"));
                }
                break;
         }
        for(int k=0;k<restore_viatext.size()-1;k++)
     {
       Log.e("list text",""+restore_viatext.get(k));

   }

           // r_via1 = savedInstanceState.getString("via1");
            //r_via2 = savedInstanceState.getString("via2");
          //  r_via3 = savedInstanceState.getString("via3");
           // r_via4 = savedInstanceState.getString("via4");
           // r_via5 = savedInstanceState.getString("via5");
          //  r_via6 = savedInstanceState.getString("via6");
          //  r_via7 = savedInstanceState.getString("via7");
           // r_via8 = savedInstanceState.getString("via8");


        r_via2 = savedInstanceState.getString("via2");
        r_via3 = savedInstanceState.getString("via3");
        r_via4 = savedInstanceState.getString("via4");
        r_via5 = savedInstanceState.getString("via5");
        r_via6 = savedInstanceState.getString("via6");
        r_via7 = savedInstanceState.getString("via7");
        r_via8 = savedInstanceState.getString("via8");
            v_rel = savedInstanceState.getInt("relativelayout_visibility");
            via_array[0] = v_rel;
            v_via1 = savedInstanceState.getInt("via1_visibility");
            via_array[1] = v_via1;
            v_via2 = savedInstanceState.getInt("via2_visibility");
            via_array[2] = v_via2;
            v_via3 = savedInstanceState.getInt("via3_visibility");
            via_array[3] = v_via3;
            v_via4 = savedInstanceState.getInt("via4_visibility");
            via_array[4] = v_via4;
            v_via5 = savedInstanceState.getInt("via5_visibility");
            via_array[5] = v_via5;
            v_via6 = savedInstanceState.getInt("via6_visibility");
            via_array[6] = v_via6;
            v_via7 = savedInstanceState.getInt("via7_visibility");
            via_array[7] = v_via7;
            v_via8 = savedInstanceState.getInt("via8_visibility");
            via_array[8] = v_via8;
            source.setText(r_source);
            destination.setText(r_destination);
            source.setText(r_source);
            destination.setText(r_destination);
           // via1.setText(r_via1);
            //via2.setText(r_via2);
           // via3.setText(r_via3);
           // via4.setText(r_via4);
           // via5.setText(r_via5);
           // via6.setText(r_via6);
          //  via7.setText(r_via7);
          //  via8.setText(r_via8);

        //via_text=savedInstanceState.getStringArrayList("vialist");
//        Log.e("via list data 0",""+via_text.get(0));
  //      Log.e("via list data 1",""+via_text.get(1));
       // Log.e("via list data 2",""+via_text.get(2));



        if(cros_counter!=0)
        {
            Log.e("on restore instance",""+counter);
            switch (cros_counter)
            {
                case 1:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-1));
                     cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);
                    break;
                case 2:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-2));
                    via2.setText(restore_viatext.get(cros_counter-1));
                    cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);

                    break;
                case 3:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via3.setVisibility(View.VISIBLE);
                    cros3.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-3));
                    via2.setText(restore_viatext.get(cros_counter-2));
                    via3.setText(restore_viatext.get(cros_counter-1));
                    cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);

                    break;
                case 4:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via3.setVisibility(View.VISIBLE);
                    cros3.setVisibility(View.VISIBLE);
                    via4.setVisibility(View.VISIBLE);
                    cros4.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-4));
                    via2.setText(restore_viatext.get(cros_counter-3));
                    via3.setText(restore_viatext.get(cros_counter-2));
                    via4.setText(restore_viatext.get(cros_counter-1));

                    cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);

                    break;
                case 5:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via3.setVisibility(View.VISIBLE);
                    cros3.setVisibility(View.VISIBLE);
                    via4.setVisibility(View.VISIBLE);
                    cros4.setVisibility(View.VISIBLE);
                    via5.setVisibility(View.VISIBLE);
                    cros5.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-5));
                    via2.setText(restore_viatext.get(cros_counter-4));
                    via3.setText(restore_viatext.get(cros_counter-3));
                    via4.setText(restore_viatext.get(cros_counter-2));
                    via5.setText(restore_viatext.get(cros_counter-1));
                    cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);

                    break;
                case 6:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via3.setVisibility(View.VISIBLE);
                    cros3.setVisibility(View.VISIBLE);
                    via4.setVisibility(View.VISIBLE);
                    cros4.setVisibility(View.VISIBLE);
                    via5.setVisibility(View.VISIBLE);
                    cros5.setVisibility(View.VISIBLE);
                    via6.setVisibility(View.VISIBLE);
                    cros6.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-6));
                    via2.setText(restore_viatext.get(cros_counter-5));
                    via3.setText(restore_viatext.get(cros_counter-4));
                    via4.setText(restore_viatext.get(cros_counter-3));
                    via5.setText(restore_viatext.get(cros_counter-2));
                    via6.setText(restore_viatext.get(cros_counter-1));
                    cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);

                    break;
                case 7:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via3.setVisibility(View.VISIBLE);
                    cros3.setVisibility(View.VISIBLE);
                    via4.setVisibility(View.VISIBLE);
                    cros4.setVisibility(View.VISIBLE);
                    via5.setVisibility(View.VISIBLE);
                    cros5.setVisibility(View.VISIBLE);
                    via6.setVisibility(View.VISIBLE);
                    cros6.setVisibility(View.VISIBLE);
                    via7.setVisibility(View.VISIBLE);
                    cros7.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-7));
                    via2.setText(restore_viatext.get(cros_counter-6));
                    via3.setText(restore_viatext.get(cros_counter-5));
                    via4.setText(restore_viatext.get(cros_counter-4));
                    via5.setText(restore_viatext.get(cros_counter-3));
                    via6.setText(restore_viatext.get(cros_counter-2));
                    via7.setText(restore_viatext.get(cros_counter-1));
                    cros_counter = counter;
                    //counter=0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);

                    break;
                case 8:
                    relativelayout.setVisibility(View.VISIBLE);
                    via1.setVisibility(View.VISIBLE);
                    cros1.setVisibility(View.VISIBLE);
                    via2.setVisibility(View.VISIBLE);
                    cros2.setVisibility(View.VISIBLE);
                    via3.setVisibility(View.VISIBLE);
                    cros3.setVisibility(View.VISIBLE);
                    via4.setVisibility(View.VISIBLE);
                    cros4.setVisibility(View.VISIBLE);
                    via5.setVisibility(View.VISIBLE);
                    cros5.setVisibility(View.VISIBLE);
                    via6.setVisibility(View.VISIBLE);
                    cros6.setVisibility(View.VISIBLE);
                    via7.setVisibility(View.VISIBLE);
                    cros7.setVisibility(View.VISIBLE);
                    via8.setVisibility(View.VISIBLE);
                    cros8.setVisibility(View.VISIBLE);
                    via1.setText(restore_viatext.get(cros_counter-8));
                    via2.setText(restore_viatext.get(cros_counter-7));
                    via3.setText(restore_viatext.get(cros_counter-6));
                    via4.setText(restore_viatext.get(cros_counter-5));
                    via5.setText(restore_viatext.get(cros_counter-4));
                    via6.setText(restore_viatext.get(cros_counter-3));
                    via7.setText(restore_viatext.get(cros_counter-2));
                    via8.setText(restore_viatext.get(cros_counter-1));

                    cros_counter = counter;
                    //counter = 0;
                    Log.e("Couter after all via", "" + counter);
                    Log.e("total cros counter", "" + cros_counter);
                    // getroute.setClickable(false);
                    Toast.makeText(main_activity.this, "Not More Than Eight Way Points Allowed", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }

        super.onRestoreInstanceState(savedInstanceState);
   }
}
