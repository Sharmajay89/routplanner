package com.testapp.cws.googlerouteplanner;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;

/**
 * Created by cws on 14/12/17.
 */

public class GoogleRouteDetailModelClass {
    String[] Summary;
    String[] Distance;
    String[] Duration;

    LatLng southwest,northeast;

    public LatLng getSouthwest() {
        return southwest;
    }

    public void setSouthwest(LatLng southwest) {
        this.southwest = southwest;
    }

    public LatLng getNortheast() {
        return northeast;
    }

    public void setNortheast(LatLng northeast) {
        this.northeast = northeast;
    }

    LatLngBounds latLngBounds;
   ArrayList<LatLng> polilineroutes;
    Context context;


    public GoogleRouteDetailModelClass(Context context) {
        this.context=context;
    }

    public String[] getSummary() {
        return Summary;
    }

    public void setSummary(String[] summary) {
        this.Summary = summary;
    }

    public String[] getDistance() {
        return Distance;
    }

    public void setDistance(String[] distance) {
        this.Distance = distance;
    }

    public String[] getDuration() {
        return Duration;
    }

    public void setDuration(String[] duration) {
        this.Duration = duration;
    }

    public ArrayList<LatLng> getPolilineroutes() {
        return polilineroutes;
    }

    public void setPolilineroutes(ArrayList<LatLng> polilineroutes) {
        this.polilineroutes = polilineroutes;
    }
    public LatLngBounds getLatLngBounds() {
        return latLngBounds;
    }

    public void setLatLngBounds(LatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
    }
}
