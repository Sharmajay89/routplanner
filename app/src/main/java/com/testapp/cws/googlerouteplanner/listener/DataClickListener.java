package com.testapp.cws.googlerouteplanner.listener;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by cws on 15/1/18.
 */

public interface DataClickListener {
    void dataItemClickListener(LatLng latLng,Boolean flag,Boolean floating_button_click_status);

}
